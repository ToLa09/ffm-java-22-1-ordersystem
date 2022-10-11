package ordersystem.ffmjava221ordersystem;

import ordersystem.ffmjava221ordersystem.shop.ShopService;
import ordersystem.ffmjava221ordersystem.shop.model.Order;
import ordersystem.ffmjava221ordersystem.shop.model.Product;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ShopController {
    private final ShopService service;

    public ShopController(ShopService service) {
        this.service = service;
    }

    @GetMapping("products")
    public Map<String, Product> getAllProducts(){
        return service.getProductList();
    }

    @GetMapping("products/{id}")
    public Product getProduct(@PathVariable String id){
        return service.getProduct(id);
    }

    @PostMapping("products")
    public Product addProduct(@RequestBody Product product){
        service.addProduct(product);
        return product;
    }

    @GetMapping("orders")
    public List<Order> getAllOrders(){
        return service.getAllOrders();
    }

    @GetMapping("orders/{id}")
    public Order getOrder(@PathVariable String id){
        return service.getOrder(id);
    }

    @PostMapping("orders")
    public String addOrder(@RequestBody List<String> productIdList){
        return service.orderProducts(productIdList);
    }
    @PostMapping("orders/{id}")
    public String overwriteOrder(@RequestBody List<String> productIdList, @PathVariable String id){
        service.overwriteOrder(id,productIdList);
        return service.getOrder(id).toString();
    }
    @DeleteMapping("orders/{id}")
    public void removeOrder(@PathVariable String id){
        service.removeOrder(id);
    }
}
