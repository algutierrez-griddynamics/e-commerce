package org.ecommerce.util.database;

import com.zaxxer.hikari.HikariDataSource;
import lombok.Getter;
import org.ecommerce.exceptions.EntityNotFound;
import org.ecommerce.logs.Log;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;


@Getter
public class Operations <T> {

    private final HikariDataSource hikariDataSource;

    public Operations(HikariDataSource hikariDataSource) {
        this.hikariDataSource = hikariDataSource;
    }

    Connection getConnection() throws SQLException {
        return hikariDataSource.getConnection();
    }


    // Run queries that should not have any result, such table creation or a plain insert
    public void execute(String query, Object... args) throws SQLException {
        try (Connection connection = getConnection(); // TODO: Is it ok that each method gets a connection from the pool every time it is requested?
             PreparedStatement statement = connection.prepareStatement(query)) {
            setParameters(statement, args);
            statement.executeUpdate();
        }
    }


    //    Now takes Consumer instead of Object... and exposes internal state (PreparedStatement) to the caller,
    //    which is not always good, but in this case can give more flexibility to the caller
    public void execute(String query, Consumer<PreparedStatement> consumer) throws SQLException {
        try (Connection connection = getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            consumer.accept(stmt);
            stmt.executeUpdate();
        }
    }

    // Runs a query and returns the result. Handle the case, when the function can return 0 results.
    // If the number of results is greater than 1, throw an exception
    public T findOne(String query, Function<ResultSet, T> mapper, Object... args) throws SQLException {
        try (Connection connection = getConnection();
             PreparedStatement stmt = connection.prepareStatement(query, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY)) {
            setParameters(stmt, args);
            try (ResultSet rs = stmt.executeQuery()) {
                if (isOneResult(rs))
                    return mapper.apply(rs);
            }
        }
        return null;
    }

    // Runs a query and returns many results (greater than or equal to 0) as a list.
    public List<T> findMany(String query, Function<ResultSet, T> mapper, Object... args) throws SQLException {
        List<T> results = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            setParameters(stmt, args);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Log.info(rs.getString(1));
                    results.add(mapper.apply(rs));
                }
            }
        }
        return results;
    }


    private void setParameters(PreparedStatement stmt, Object... args) throws SQLException {
        for (int i = 0; i < args.length; i++) {
            stmt.setObject(i + 1, args[i]);
        }
    }

    private boolean isOneResult(ResultSet rs) throws SQLException {
        int rowcount = 0;
        if (rs.last()) {
            rowcount = rs.getRow();
            rs.beforeFirst();
        }
        if (rowcount == 0)
            throw new EntityNotFound("No results returned from query: " + rs);
        else if (rowcount > 1)
            throw new SQLException("Too many results returned from query: " + rs);
        return true;
    }
}