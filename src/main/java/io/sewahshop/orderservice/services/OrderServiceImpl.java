package io.sewahshop.orderservice.services;

import io.sewahshop.orderservice.domains.Order;
import io.sewahshop.orderservice.repositories.OrderRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class OrderServiceImpl implements OrderService{
    private final OrderRepository orderRepository;
    OrderServiceImpl(OrderRepository orderRepository){
        this.orderRepository = orderRepository;
    }

    @Override
    public Flux<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public Mono<Order> submitOrder(Order order) {
        return null;
    }

}
