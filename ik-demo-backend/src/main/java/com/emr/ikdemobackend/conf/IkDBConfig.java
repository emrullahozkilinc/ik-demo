package com.emr.ikdemobackend.conf;

import jakarta.persistence.EntityManagerFactory;
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

import javax.sql.DataSource;

@Configuration
@EnableJpaRepositories(
        entityManagerFactoryRef = "ikEntityManagerFactory",
        transactionManagerRef = "ikTransactionManager",
        basePackages = {"com.emr.ikdemobackend.repository"}
)
public class IkDBConfig {

    @Primary
    @Bean(name = "ikDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.ik")
    public DataSource ikDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Primary
    @Bean(name = "ikEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(
            EntityManagerFactoryBuilder builder,
            @Qualifier("ikDataSource") DataSource dataSource) {

        return builder
                .dataSource(dataSource)
                .packages("com.emr.ikdemobackend.entity")
                .persistenceUnit("ik")
                .build();
    }

    @Primary
    @Bean(name = "ikTransactionManager")
    public PlatformTransactionManager ikTransactionManager(
            @Qualifier("ikEntityManagerFactory") EntityManagerFactory ikEntityManagerFactory
    ) {
        return new JpaTransactionManager(ikEntityManagerFactory);
    }
}