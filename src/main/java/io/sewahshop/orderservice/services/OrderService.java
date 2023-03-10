package io.sewahshop.orderservice.services;

import io.sewahshop.orderservice.domains.Order;
import io.sewahshop.orderservice.dto.OrderDispatchedMessage;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface OrderService {
    Flux<Order> getAllOrders(String userId);
    Mono<Order> submitOrder(String isbn, Integer quantity);
    public Flux<Order> consumeOrderDispatchedEvent(
            Flux<OrderDispatchedMessage> flux
                                                  );
}
