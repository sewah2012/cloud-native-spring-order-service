package io.sewahshop.orderservice.events;

import io.sewahshop.orderservice.dto.OrderDispatchedMessage;
import io.sewahshop.orderservice.services.OrderService;
import java.util.function.Consumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Flux;

@Configuration
public class OrderFunctions {
    private static final Logger log =
            LoggerFactory.getLogger(OrderFunctions.class);

    @Bean
    public Consumer<Flux<OrderDispatchedMessage>> dispatchOrder(OrderService orderService) {
        return flux ->
                orderService.consumeOrderDispatchedEvent(flux)
                 .doOnNext(order -> log.info("The order with id {} is dispatched", order.id()))
                 .subscribe();
    }
}
