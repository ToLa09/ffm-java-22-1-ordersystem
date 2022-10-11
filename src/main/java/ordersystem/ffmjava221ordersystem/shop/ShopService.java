package ordersystem.ffmjava221ordersystem.shop;

import ordersystem.ffmjava221ordersystem.shop.model.Order;
import ordersystem.ffmjava221ordersystem.shop.model.Product;
import ordersystem.ffmjava221ordersystem.shop.repo.OrderRepo;
import ordersystem.ffmjava221ordersystem.shop.repo.ProductRepo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class ShopService {

    private ProductRepo productRepo;
    private OrderRepo orderRepo;


    public ShopService(ProductRepo productRepo, OrderRepo orderRepo) {
        this.productRepo = productRepo;
        this.orderRepo = orderRepo;
    }

    public void addProduct(Product product) {
        String uuid = UUID.randomUUID().toString();
        productRepo.addProduct(uuid, product);
    }

    public String orderProducts(List<String> productIdList) {
        List<Product> productList = new ArrayList<>();
        for (String id : productIdList){
            productList.add(productRepo.getProduct(id));
        }
        Order newOrder = new Order(UUID.randomUUID().toString(),productList);
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
