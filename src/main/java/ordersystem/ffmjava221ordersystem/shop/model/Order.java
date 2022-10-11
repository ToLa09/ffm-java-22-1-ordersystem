package ordersystem.ffmjava221ordersystem.shop.model;

import java.util.List;

public record Order (String orderId, List<Product> products){
//    private final String orderId;
//    private List<Product> products;
//
//    public Order(String uuid, List<Product> products) {
//        this.orderId = uuid;
//        this.products = products;
//    }
//
//    public List<Product> getProducts() {
//        return products;
//    }
//
//    public String getOrderId() {
//        return orderId;
//    }
//
//    @Override
//    public String toString() {
//        return "{" +
//                "Bestellnummer=" + orderId +
//                ", bestellte Produkte=" + products +
//                "}\n";
//    }
}

