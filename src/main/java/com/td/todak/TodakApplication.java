package com.td.todak;

import com.td.todak.config.properties.AppProperties;
import com.td.todak.config.properties.CorsProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({
        CorsProperties.class,
        AppProperties.class
})
public class TodakApplication {

    public static void main(String[] args) {
        SpringApplication.run(TodakApplication.class, args);
    }

}
