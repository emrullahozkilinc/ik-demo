package com.emr.ikdemobackend.conf;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import javax.sql.DataSource;

@Configuration
@EnableJpaRepositories(
        entityManagerFactoryRef = "ikEntityManagerFactory",
        basePackages = "com.emr.ikdemobackend.repository"
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
}