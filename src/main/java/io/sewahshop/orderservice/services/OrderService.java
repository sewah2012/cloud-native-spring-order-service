package io.sewahshop.orderservice.services;

import io.sewahshop.orderservice.domains.Order;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface OrderService {
    Flux<Order> getAllOrders();
    Mono<Order> submitOrder(String isbn, Integer quantity);

}
