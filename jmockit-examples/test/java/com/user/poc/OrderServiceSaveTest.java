package com.user.poc;

import com.user.poc.order.Order;
import com.user.poc.order.OrderRepository;
import com.user.poc.order.OrderService;
import mockit.Expectations;
import mockit.Mocked;
import mockit.Tested;
import mockit.Verifications;
import org.junit.Test;

public class OrderServiceSaveTest {
    @Tested
    private OrderService orderService;

    @Mocked
    private OrderRepository orderRepository;

    @Test
    public void testSaveOrder() {
        Order order = new Order(1, "New Order");

        new Expectations() {{
            orderRepository.save(order);
        }};

        orderService.saveOrder(order);

        new Verifications() {{
            orderRepository.save(order);
            minTimes  = 1;
        }};
    }
}

