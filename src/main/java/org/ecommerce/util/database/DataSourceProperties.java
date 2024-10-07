package org.ecommerce.util.database;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;


@Getter
@Setter
@ConfigurationProperties(prefix = "gd.datasource")
public class DataSourceProperties {
    private String url;
    private String username;
    private String password;
}
