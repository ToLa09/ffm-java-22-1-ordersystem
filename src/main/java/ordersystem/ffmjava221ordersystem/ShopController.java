package ordersystem.ffmjava221ordersystem;

import ordersystem.ffmjava221ordersystem.shop.ShopService;
import ordersystem.ffmjava221ordersystem.shop.model.OrderStatus;
import ordersystem.ffmjava221ordersystem.shop.model.ProductToAdd;
import ordersystem.ffmjava221ordersystem.shop.model.Order;
import ordersystem.ffmjava221ordersystem.shop.model.Product;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class ShopController {
    private final ShopService service;

    public ShopController(ShopService service) {
        this.service = service;
    }

    //-------------------Product Requests-------------------//
    @GetMapping("products")
    public Map<String, Product> getAllProducts(){
        return service.getProductList();
    }

    @GetMapping("products/{id}")
    public Product getProduct(@PathVariable String id){
        return service.getProduct(id);
    }

    @PostMapping("products")
    public Product addProduct(@RequestBody ProductToAdd productToAdd){
        return service.addProduct(productToAdd);
    }

    //-------------------Order Requests-------------------//
    @GetMapping("orders")
    public List<Order> getOrders(@RequestParam Optional<OrderStatus> status, Optional<String> id){
        if(!id.orElse("").isEmpty()) {
            return new ArrayList<>(List.of(service.getOrder(id.orElse(""))));
        }
        if(status.orElse(null) != null) {
            return service.getOrdersWithStatus(status.get());
        }
        return service.getAllOrders();
    }

    @PostMapping("orders")
    public String addOrder(@RequestBody List<String> productIdList){
        return service.orderProducts(productIdList);
    }
    @PutMapping("orders/{id}")
    public String overwriteOrder(@RequestBody List<String> productIdList, @PathVariable String id){
        service.overwriteOrder(id,productIdList);
        return service.getOrder(id).toString();
    }
    @DeleteMapping("orders/{id}")
    public void removeOrder(@PathVariable String id){
        service.removeOrder(id);
    }
}
