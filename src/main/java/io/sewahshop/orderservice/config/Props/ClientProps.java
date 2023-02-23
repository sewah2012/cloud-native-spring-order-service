package io.sewahshop.orderservice.config.Props;

import jakarta.validation.constraints.NotNull;
import java.net.URI;
import org.springframework.boot.context.properties.ConfigurationProperties;


@ConfigurationProperties(prefix = "my.clients")
public record ClientProps(
        @NotNull
        URI catalogServiceUri
){

}
