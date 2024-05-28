package com.agencia.pf.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import javax.sql.DataSource;

@Configuration
public class DatabaseConfig {

    @Value("${spring.datasource.url}")
    private String dbUrl;

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;


    @Bean
    public DataSource dataSource() {
        HikariDataSource dataSource = new HikariDataSource();
        // Configura los parámetros del pool de conexiones
        dataSource.setMaximumPoolSize(20);
        dataSource.setIdleTimeout(30000);
        dataSource.setPoolName("MyConnectionPool");
        // Configura los demás parámetros como la URL, nombre de usuario y contraseña según los valores en application.properties o application.yml
        dataSource.setJdbcUrl(dbUrl);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        return dataSource;
    }
}
