package org.ecommerce.util.database;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;


@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "gd.datasource")
public class DataSourceProperties {
    private String url;
    private String username;
    private String password;
}
