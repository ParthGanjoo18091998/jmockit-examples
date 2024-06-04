package com.user.poc;

import com.employee.poc.order.Order;
import com.employee.poc.order.OrderRepository;
import com.employee.poc.order.OrderService;
import com.user.poc.order.Order;
import com.user.poc.order.OrderRepository;
import com.user.poc.order.OrderService;
import mockit.Expectations;
import mockit.Mocked;
import mockit.Tested;
import mockit.Verifications;
import org.junit.Test;

import static org.testng.AssertJUnit.assertEquals;

public class OrderServiceTest {
    @Tested
    private OrderService orderService;

    @Mocked
    private OrderRepository orderRepository;

    @Test
    public void testGetOrderById() {
        new Expectations() {{
            orderRepository.findOrderById(1);
            result = new Order(1, "Test Order");
        }};

        Order order = orderService.getOrderById(1);
        assertEquals("Test Order", order.getDescription());

        new Verifications() {{
            orderRepository.findOrderById(1);
            minTimes  = 1;
        }};
    }
}

