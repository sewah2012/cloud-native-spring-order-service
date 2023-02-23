package io.sewahshop.orderservice.services;

import io.sewahshop.orderservice.clients.BookClient;
import io.sewahshop.orderservice.domains.Order;
import io.sewahshop.orderservice.domains.OrderStatus;
import io.sewahshop.orderservice.dto.Book;
import io.sewahshop.orderservice.repositories.OrderRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class OrderServiceImpl implements OrderService{
    private final OrderRepository orderRepository;
    private final BookClient bookClient;
    OrderServiceImpl(OrderRepository orderRepository, BookClient bookClient){
        this.orderRepository = orderRepository;
        this.bookClient=bookClient;
    }

    @Override
    public Flux<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public Mono<Order> submitOrder(String isbn, Integer quantity) {
//        return Mono.just(buildRejectedOrder(isbn, quantity));
        return bookClient
                .getBookByIsbn(isbn)
                .map(book -> buildAcceptedOrder(book, quantity))
                .defaultIfEmpty(buildRejectedOrder(isbn, quantity))
                .flatMap(orderRepository::save);

    }

    public static Order buildRejectedOrder(String bookIsbn, int quantity) {
        return Order.of(bookIsbn, null, null, quantity, OrderStatus.REJECTED);
    }

    public static Order buildAcceptedOrder(Book book, int quantity) {
        return Order.of(book.isbn(), book.title() + " - " + book.author(),
                        book.price(), quantity, OrderStatus.ACCEPTED);
    }


}
