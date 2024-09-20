package org.ecommerce.util.database;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import io.github.cdimascio.dotenv.Dotenv;

import java.sql.Connection;
import java.sql.SQLException;

public class DataSource {

    private static final int POOL_SIZE = 10;

    private static final HikariDataSource dataSource;
    private static final Dotenv dotenv = Dotenv.load();

    static {
        HikariConfig hikariConfig = new HikariConfig();

        String url = String.format("%s://%s:%s/%s",
                dotenv.get("DB_URL"),
                dotenv.get("HOST"),
                dotenv.get("DB_PORT"),
                dotenv.get("DB_NAME"));

        hikariConfig.setJdbcUrl(url);
        hikariConfig.setUsername(dotenv.get("DB_USER"));
        hikariConfig.setPassword(dotenv.get("DB_PASSWORD"));
        hikariConfig.setMaximumPoolSize(POOL_SIZE);

        dataSource = new HikariDataSource(hikariConfig);
    }

    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
}
