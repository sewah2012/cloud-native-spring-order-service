package io.sewahshop.orderservice.repositories;

import io.sewahshop.orderservice.config.PersistenceConfig;
import io.sewahshop.orderservice.domains.OrderStatus;
import io.sewahshop.orderservice.services.OrderService;
import io.sewahshop.orderservice.services.OrderServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.r2dbc.DataR2dbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.*;

@DataR2dbcTest
@Import(PersistenceConfig.class)
@Testcontainers
class OrderRepositoryTest {

    @Container
    static PostgreSQLContainer<?> postgresql = new PostgreSQLContainer<>(DockerImageName.parse("postgres:14.4"));

    @Autowired
    private OrderRepository orderRepository;

    @DynamicPropertySource
    static void postgresqlProperties(DynamicPropertyRegistry registry){
        registry.add("spring.r2dbc.url", OrderRepositoryTest::r2dbcUrl);
        registry.add("spring.r2dbc.username", postgresql::getUsername);
        registry.add("spring.r2dbc.password", postgresql::getPassword);
        registry.add("spring.flyway.url", postgresql::getJdbcUrl);

    }

    private static String r2dbcUrl() {
        return String.format("r2dbc:postgresql://%s:%s/%s",
                             postgresql.getContainerIpAddress(),
                             postgresql.getMappedPort(PostgreSQLContainer.POSTGRESQL_PORT),
                             postgresql.getDatabaseName());
    }

    @Test
    void createRejectedOrder(){
        var rejectedOrder = OrderServiceImpl.buildRejectedOrder("1234567890", 3);
        StepVerifier
                .create(orderRepository.save(rejectedOrder))
                .expectNextMatches(order -> order.status().equals(OrderStatus.REJECTED))
                .verifyComplete();
    }
}