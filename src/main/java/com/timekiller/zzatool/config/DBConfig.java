package com.timekiller.zzatool.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import java.sql.Driver;

import javax.sql.DataSource;

@Configuration
@PropertySource("classpath:/db.properties")
public class DBConfig {
    @Autowired Environment env;

    @Bean
    public DataSource dataSource() {
        SimpleDriverDataSource ds = new SimpleDriverDataSource();

        try {
            ds.setDriverClass(
                    (Class<? extends Driver>)
                            Class.forName(env.getProperty("spring.datasource.driver-class-name")));
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        ds.setUrl(env.getProperty("spring.datasource.url"));
        ds.setUsername(env.getProperty("spring.datasource.username"));
        ds.setPassword(env.getProperty("spring.datasource.password"));

        return ds;
    }
}
