package org.ecommerce.util.database;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;


@Getter
@Setter
@ToString
@ConfigurationProperties(prefix = "gd.datasource")
public class DataSourceProperties {
    private String url;
    private String username;
    private String password;
}
