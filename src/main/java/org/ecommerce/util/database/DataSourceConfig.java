package org.ecommerce.util.database;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;


@Configuration
public class DataSourceConfig {

    private static final int POOL_SIZE = 10;
    private final DataSourceProperties dataSourceProperties;

    public DataSourceConfig(DataSourceProperties dataSourceProperties) {
        this.dataSourceProperties = dataSourceProperties;
    }

    @Profile("local")
    @Bean
        public HikariDataSource getLocalDataSource() {
        return buildDataSource();
    }

    @Profile("dev")
    @Bean
    public HikariDataSource getDevDataSource() {
        return buildDataSource();
    }


    private HikariDataSource buildDataSource() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(dataSourceProperties.getUrl());
        config.setUsername(dataSourceProperties.getUsername());
        config.setPassword(dataSourceProperties.getPassword());
        config.setMaximumPoolSize(POOL_SIZE);
        return new HikariDataSource(config);
    }

}
