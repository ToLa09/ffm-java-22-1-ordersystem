package ordersystem.ffmjava221ordersystem.shop.repo;

import ordersystem.ffmjava221ordersystem.shop.ServiceUtils;
import ordersystem.ffmjava221ordersystem.shop.model.Order;
import ordersystem.ffmjava221ordersystem.shop.model.OrderStatus;
import ordersystem.ffmjava221ordersystem.shop.model.Product;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


class OrderRepoTest {
    private final OrderRepo orderRepo = new OrderRepo();
    private final ServiceUtils serviceUtils = new ServiceUtils();

    @Test
    void removeOrder() {
        //given
        String id = serviceUtils.generateUUID();
        List<Product> testProducts = new ArrayList<>();
        Order testOrder = new Order(id, testProducts, OrderStatus.RECEIVED);
        orderRepo.addOrder(testOrder);
        //when
        orderRepo.removeOrder(id);
        String actual = orderRepo.toString();
        //then
        String expected = "{orderList=[]}";
        assertEquals(expected,actual);
    }
}