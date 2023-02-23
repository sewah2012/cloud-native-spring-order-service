package io.sewahshop.orderservice.repositories;

import io.sewahshop.orderservice.domains.Order;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends ReactiveCrudRepository<Order, Long> {

}
