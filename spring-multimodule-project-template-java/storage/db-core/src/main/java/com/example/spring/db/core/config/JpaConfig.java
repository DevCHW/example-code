package com.example.spring.db.core.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EntityScan(basePackages = "com.nemam.storage.db.core.jpa")
@EnableJpaRepositories(basePackages = "com.nemam.storage.db.core.jpa")
public class JpaConfig {

}
