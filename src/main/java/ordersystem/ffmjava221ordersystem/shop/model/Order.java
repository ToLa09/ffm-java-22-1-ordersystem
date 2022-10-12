package ordersystem.ffmjava221ordersystem.shop.model;

import java.util.List;

public record Order (String orderId, List<Product> products){
}

