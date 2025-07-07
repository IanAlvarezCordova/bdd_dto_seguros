package com.example.bdd_dto.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.net.URI;
import java.net.URISyntaxException;

@Configuration
public class DatabaseConfig {

    @Value("${DATABASE_URL:jdbc:postgresql://localhost:5432/bdd_dto_seguros?user=postgres&password=admin}")
    private String databaseUrl;

    @Bean
    public DataSource dataSource() throws URISyntaxException {
        URI dbUri;
        try {
            dbUri = new URI(databaseUrl.startsWith("postgresql://") ? databaseUrl : "postgresql://" + databaseUrl.substring("jdbc:postgresql://".length()));
        } catch (URISyntaxException e) {
            throw new IllegalArgumentException("Invalid DATABASE_URL: " + databaseUrl, e);
        }

        String username = dbUri.getUserInfo().split(":")[0];
        String password = dbUri.getUserInfo().split(":")[1];
        String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ":" + (dbUri.getPort() != -1 ? dbUri.getPort() : 5432) + dbUri.getPath();

        return DataSourceBuilder.create()
                .url(dbUrl)
                .username(username)
                .password(password)
                .driverClassName("org.postgresql.Driver")
                .build();
    }
}