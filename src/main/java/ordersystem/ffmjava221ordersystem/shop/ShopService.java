package ordersystem.ffmjava221ordersystem.shop;

import ordersystem.ffmjava221ordersystem.shop.model.ProductToAdd;
import ordersystem.ffmjava221ordersystem.shop.model.Order;
import ordersystem.ffmjava221ordersystem.shop.model.Product;
import ordersystem.ffmjava221ordersystem.shop.repo.OrderRepo;
import ordersystem.ffmjava221ordersystem.shop.repo.ProductRepo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class ShopService {

    private final ProductRepo productRepo;
    private final OrderRepo orderRepo;
    private final ServiceUtils serviceUtils;


    public ShopService(ProductRepo productRepo, OrderRepo orderRepo, ServiceUtils serviceUtils) {
        this.productRepo = productRepo;
        this.orderRepo = orderRepo;
        this.serviceUtils = serviceUtils;
    }

    public Product addProduct(ProductToAdd productToAdd) {
        String uuid = serviceUtils.generateUUID();
        Product newProduct = new Product(uuid,productToAdd.productNumber(),productToAdd.name(), productToAdd.price());
        productRepo.addProduct(uuid, newProduct);
        return newProduct;
    }

    public String orderProducts(List<String> productIdList) {
        List<Product> productList = new ArrayList<>();
        for (String id : productIdList){
            productList.add(productRepo.getProduct(id));
        }
        Order newOrder = new Order(serviceUtils.generateUUID(), productList);
        orderRepo.addOrder(newOrder);
        return newOrder.orderId();
    }

    public String changeOrder(String id, List<String> productIdList){
        List<Product> productList = new ArrayList<>();
        for (String productId : productIdList){
            productList.add(productRepo.getProduct(productId));
        }
        Order newOrder = new Order(id.toString(),productList);
        orderRepo.addOrder(newOrder);
        return newOrder.orderId();
    }

    public String listProducts() {
        return productRepo.toString();
    }

    public Map<String, Product> getProductList(){
        return productRepo.getProducts();
    }

    public Product getProduct(String id){
        return productRepo.getProduct(id);
    }

    public Order getOrder(String orderId){
        return orderRepo.getOrder(orderId);
    }

    public String listOrders(){
        return orderRepo.toString();
    }

    public List<Order> getAllOrders(){
        return orderRepo.getOrderList();
    }

    public String overwriteOrder(String id, List<String> productIdList) {
        orderRepo.removeOrder(id);
        List<Product> productList = new ArrayList<>();
        for (String productId : productIdList){
            productList.add(productRepo.getProduct(productId));
        }
        Order newOrder = new Order(id,productList);
        orderRepo.addOrder(newOrder);
        return newOrder.orderId();
    }
    public void removeOrder(String id){
        orderRepo.removeOrder(id);
    }

//    public Product getProductBySerialID(int serialID) {
//        return productRepo.getProductBySerialID(serialID);
//    }
}
