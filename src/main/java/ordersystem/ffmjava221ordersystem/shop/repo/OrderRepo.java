package ordersystem.ffmjava221ordersystem.shop.repo;

import ordersystem.ffmjava221ordersystem.shop.model.Order;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class OrderRepo {
    private List<Order> orderList = new ArrayList<>();

    public void addOrder(Order order) {
        orderList.add(order);
    }

    public List<Order> getOrderList() {
        return orderList;
    }

    public Order getOrder(String orderId) {
        for (Order order : orderList) {
            if (order.orderId().equals(orderId)) {
                return order;
            }
        }
        throw new IllegalArgumentException("Keine Bestellung mit dieser Bestellnummer gefunden");
    }

    public void removeOrder(String orderId) {
        for (Order order : orderList){
            if (order.orderId().equals(orderId)) {
                orderList.remove(order);
                return;
            }
        }
    }

    @Override
    public String toString() {
        return "{" +
                "orderList=" + orderList +
                '}';
    }
}
