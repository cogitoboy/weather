package com.stalesoft.welcome;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;


@ImportResource("classpath:application-context.xml")
@ComponentScan({"com.stalesoft.welcome"})
@SpringBootApplication
@EnableCaching
@EnableScheduling
public class WelcomeApplication {

  Logger logger = LoggerFactory.getLogger(WelcomeApplication.class);

  public static void main(String[] args) {
    SpringApplication.run(WelcomeApplication.class, args);
  }

  @Bean
  public RestTemplate getRestTemplate(RestTemplateBuilder restTemplateBuilder) {
    return restTemplateBuilder.build();
  }

}


/*
 * - integrating webflow into application  
 * https://www.baeldung.com/spring-web-flow
 * https://stackoverflow.com/questions/49824189/spring-boot-webflow-mvc-thymeleaf-config
 */
 