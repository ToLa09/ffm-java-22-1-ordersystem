package ordersystem.ffmjava221ordersystem.shop;

import ordersystem.ffmjava221ordersystem.shop.model.OrderStatus;
import ordersystem.ffmjava221ordersystem.shop.model.ProductToAdd;
import ordersystem.ffmjava221ordersystem.shop.model.Order;
import ordersystem.ffmjava221ordersystem.shop.model.Product;
import ordersystem.ffmjava221ordersystem.shop.repo.OrderRepo;
import ordersystem.ffmjava221ordersystem.shop.repo.ProductRepo;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

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
        Order newOrder = new Order(serviceUtils.generateUUID(), productList, OrderStatus.RECEIVED);
        orderRepo.addOrder(newOrder);
        return newOrder.orderId();
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

    public List<Order> getAllOrders(){
        return orderRepo.getOrderList();
    }


    public List<Order> getOrdersWithStatus(OrderStatus orderStatus){
        return orderRepo.getOrderList()
                .stream()
                .filter(order -> order.orderStatus().equals(orderStatus))
                .collect(Collectors.toList());
    }

    public Order overwriteOrder(String id, List<String> productIdList) {
        orderRepo.removeOrder(id);
        List<Product> productList = new ArrayList<>();
        for (String productId : productIdList){
            productList.add(productRepo.getProduct(productId));
        }
        Order newOrder = new Order(id,productList, OrderStatus.RECEIVED);
        orderRepo.addOrder(newOrder);
        return newOrder;
    }
    public void removeOrder(String id){
        orderRepo.removeOrder(id);
    }
}
