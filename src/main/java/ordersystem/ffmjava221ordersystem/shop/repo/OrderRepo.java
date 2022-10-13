package ordersystem.ffmjava221ordersystem.shop.repo;

import ordersystem.ffmjava221ordersystem.shop.model.Order;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

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
        for (Order o : orderList) {
            if (o.orderId().equals(orderId)) {
                return o;
            }
        }
        throw new IllegalArgumentException("Keine Bestellung mit dieser Bestellnummer gefunden");
    }

    @Override
    public String toString() {
        return "{" +
                "orderList=" + orderList +
                '}';
    }

    public void removeOrder(String id) {
        for (Order order : orderList){
            if (order.orderId() == id) {
                orderList.remove(order);
                return;
            }
        }
    }
}
