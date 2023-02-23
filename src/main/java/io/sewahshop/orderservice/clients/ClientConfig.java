package io.sewahshop.orderservice.clients;

import io.sewahshop.orderservice.config.Props.ClientProps;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class ClientConfig {
    @Bean
    WebClient webClient(ClientProps props, WebClient.Builder builder){
        return builder.
                baseUrl(props.catalogServiceUri().toString())
                .build();
    }
}
