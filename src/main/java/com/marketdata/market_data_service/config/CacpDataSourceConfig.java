package com.marketdata.market_data_service.config;

import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
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

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        basePackages = "com.marketdata.market_data_service.cacp.repository",
        entityManagerFactoryRef = "cacpEntityManagerFactory",
        transactionManagerRef = "cacpTransactionManager"
)
public class CacpDataSourceConfig {

    @Primary
    @Bean(name = "cacpDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.primary")
    public DataSource cacpDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Primary
    @Bean(name = "cacpEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean cacpEntityManagerFactory(
            EntityManagerFactoryBuilder builder,
            @Qualifier("cacpDataSource") DataSource dataSource,
            JpaProperties jpaProperties) {

        Map<String, Object> properties = new HashMap<>(jpaProperties.getProperties());
        properties.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        properties.put("hibernate.hbm2ddl.auto", "none");
        properties.put("hibernate.show_sql", false);

        return builder
                .dataSource(dataSource)
                .packages("com.marketdata.market_data_service.cacp.entity")
                .persistenceUnit("cacp")
                .properties(properties)
                .build();
    }

    @Primary
    @Bean(name = "cacpTransactionManager")
    public PlatformTransactionManager cacpTransactionManager(
            @Qualifier("cacpEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }
}
