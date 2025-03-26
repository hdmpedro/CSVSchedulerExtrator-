/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.hdmpedro.scheduler.dao;

import com.zaxxer.hikari.HikariDataSource;
import io.hdmpedro.scheduler.config.DatabaseConfig;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import javax.sql.DataSource;

/**
 *
 * @author DSK-11
 */
public class DatabaseManager {
    private static final Map<String, DataSource> dataSources = new ConcurrentHashMap<>();

    public static void initialize(List<DatabaseConfig> configs) {
        configs.forEach(config -> {

            if (config.getDatabaseName() == null || config.getDatabaseName().isEmpty()) {
                throw new RuntimeException("Database name não configurado para: " + config.getName());
            }

            HikariDataSource ds = new HikariDataSource();
            ds.setJdbcUrl(String.format("jdbc:mariadb://%s:%d/%s",
                    config.getHost(),
                    config.getPort(),
                    config.getDatabaseName()));
            ds.setUsername(config.getUser());
            ds.setPassword(config.getPassword());

            ds.setMaximumPoolSize(10);
            ds.setConnectionTimeout(30000);

            dataSources.put(config.getName(), ds);
        });
    }

    public static Connection getConnection(String dbName) throws SQLException {
        DataSource ds = dataSources.get(dbName);
        if (ds == null) {
            throw new SQLException("Database '" + dbName + "' não encontrado");
        }
        return ds.getConnection();
    }
}
