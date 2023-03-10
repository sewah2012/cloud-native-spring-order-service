package io.sewahshop.orderservice.web;

import io.sewahshop.orderservice.domains.Order;
import io.sewahshop.orderservice.dto.OrderRequest;
import io.sewahshop.orderservice.services.OrderService;
import jakarta.validation.Valid;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;

    OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public Flux<Order> getAllOrders(@AuthenticationPrincipal Jwt jwt) {
        return orderService.getAllOrders(jwt.getSubject());
    }


    @PostMapping
    public Mono<Order> submitOrder(
            @RequestBody @Valid OrderRequest orderRequest
                                  ) {
        return orderService.submitOrder(orderRequest.isbn(), orderRequest.quantity());
    }

}
