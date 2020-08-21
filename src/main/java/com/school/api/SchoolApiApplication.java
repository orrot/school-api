package com.school.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@ComponentScan({"com.school.api"})
@EntityScan(basePackages = {"com.school.api"}, basePackageClasses = {Jsr310JpaConverters.class})
@EnableJpaRepositories(basePackages = { "com.school.api"}, basePackageClasses = { Jsr310JpaConverters.class })
@EnableTransactionManagement
public class SchoolApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(SchoolApiApplication.class, args);
    }
}
