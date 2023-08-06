package com.example.test.config;

import jakarta.persistence.EntityManagerFactory;
import java.util.HashMap;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
    entityManagerFactoryRef = "entityManagerFactory2",
    basePackages = {
        "com.example.test.repository2"
    }
)
public class Db2Config {

  @Bean(name = "dataSource2")
  @ConfigurationProperties(prefix = "spring.db2.datasource")
  public DataSource dataSource() {
    return DataSourceBuilder.create().build();
  }

  @Bean(name = "entityManagerFactory2")
  public LocalContainerEntityManagerFactoryBean entityManagerFactory(
      EntityManagerFactoryBuilder builder, @Qualifier("dataSource2") DataSource dataSource) {
    HashMap<String, Object> properties = new HashMap<>();
    properties.put("hibernate.hbm2ddl.auto", "update");

    return builder.dataSource(dataSource)
        .properties(properties)
        .packages("com.example.test.models2")
        .persistenceUnit("db2")
        .build();
  }

  @Bean(name = "transactionManager2")
  public PlatformTransactionManager transactionManager(@Qualifier("entityManagerFactory2")
  EntityManagerFactory entityManagerFactory) {
    return new JpaTransactionManager(entityManagerFactory);
  }

}