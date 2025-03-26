package com.example.hikaricp.config;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.concurrent.TimeUnit;

@Configuration
public class MongoConfig {

    @Bean
    public MongoClient mongoClient() {
        ConnectionString connectionString = new ConnectionString("mongodb://localhost:27017/mydb");

        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .applyToConnectionPoolSettings(builder ->
                        builder.maxSize(50)  // Maximum pool connections
                                .minSize(10)  // Minimum idle connections
                                .maxWaitTime(2000, TimeUnit.MILLISECONDS) // Max wait for a connection
                                .maxConnectionLifeTime(5, TimeUnit.MINUTES)) // Max connection lifetime
                .build();

        return MongoClients.create(settings);
    }
}

