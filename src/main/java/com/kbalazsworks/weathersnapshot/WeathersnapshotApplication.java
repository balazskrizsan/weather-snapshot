package com.kbalazsworks.weathersnapshot;

import com.kbalazsworks.weathersnapshot.config.ApplicationProperties;
import com.kbalazsworks.weathersnapshot.config.SwaggerConfig;
import io.searchbox.client.JestClient;
import io.searchbox.client.JestClientFactory;
import io.searchbox.client.config.HttpClientConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import springfox.documentation.annotations.ApiIgnore;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.sql.DataSource;
import java.util.HashSet;
import java.util.function.Predicate;

import static java.util.Collections.singletonList;
import static springfox.documentation.builders.PathSelectors.regex;


@SpringBootApplication
@EnableScheduling
@EnableSwagger2
@EnableOpenApi
public class WeathersnapshotApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(WeathersnapshotApplication.class, args);
    }

    @Bean
    public ApplicationProperties applicationProperties()
    {
        return new ApplicationProperties();
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder()
    {
        return new BCryptPasswordEncoder();
    }

    @Primary
    @Bean
    public DataSource dataSource()
    {
        ApplicationProperties applicationProperties = applicationProperties();

        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(applicationProperties.getDataSourceDriverClassName());
        dataSource.setUrl(applicationProperties.getDataSourceUrl());
        dataSource.setUsername(applicationProperties.getDataSourceUsername());
        dataSource.setPassword(applicationProperties.getDataSourcePassword());

        return dataSource;
    }

    @Bean
    public JestClient jestClient()
    {
        String connectionUrl = applicationProperties().getSearchlyUrl();

        JestClientFactory factory = new JestClientFactory();
        factory.setHttpClientConfig(new HttpClientConfig.Builder(connectionUrl).multiThreaded(true).build());

        return factory.getObject();
    }


    @Bean
    public Docket healthCheckApi()
    {
        return new Docket(DocumentationType.SWAGGER_2)
            .groupName("health-check-api")
            .apiInfo(new SwaggerConfig().apiInfo())
            .produces(new HashSet<>(singletonList("application/json")))
            .select()
            .paths(healthCheckPaths())
            .build()
            .ignoredParameterTypes(ApiIgnore.class)
            .enableUrlTemplating(true);
    }

    private Predicate<String> healthCheckPaths()
    {
        return regex(".*/health-check.*")
            .or(regex(".*/health-check"));
    }
}
