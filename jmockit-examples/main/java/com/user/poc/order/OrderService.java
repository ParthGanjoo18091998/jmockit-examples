package com.user.poc.order;

public class OrderService {
    private OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Order getOrderById(int id) {
        return orderRepository.findOrderById(id);
    }

    public void saveOrder(Order order) {
        orderRepository.save(order);
    }
}
