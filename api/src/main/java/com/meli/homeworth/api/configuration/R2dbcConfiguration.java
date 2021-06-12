package com.meli.homeworth.api.configuration;

import io.r2dbc.spi.ConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.r2dbc.config.EnableR2dbcAuditing;
import org.springframework.r2dbc.connection.init.ConnectionFactoryInitializer;
import org.springframework.r2dbc.connection.init.ResourceDatabasePopulator;

@Configuration
@EnableR2dbcAuditing
public class R2dbcConfiguration {
  @Bean
  public ConnectionFactoryInitializer initializer(ConnectionFactory connectionFactory) {
    ResourceDatabasePopulator populator = new ResourceDatabasePopulator(new ClassPathResource("schema.sql"));
    ConnectionFactoryInitializer initializer = new ConnectionFactoryInitializer();

    initializer.setConnectionFactory(connectionFactory);
    initializer.setDatabasePopulator(populator);

    return initializer;
  }
}
