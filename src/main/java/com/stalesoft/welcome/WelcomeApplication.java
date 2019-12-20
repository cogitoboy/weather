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
 * https://dzone.com/refcardz/spring-web-flow?chapter=5
 * - SignupController.createAccount. SignupController.createAccount() signature same as if called directly from form.
 *   Goal: account.html and the signup workflow call the same createAccount controller method.
 *   https://docs.spring.io/spring-webflow/docs/current/reference/html/defining-flows.html
 *   Setting up security and users:
 *   https://medium.com/@gustavo.ponce.ch/spring-boot-spring-mvc-spring-security-mysql-a5d8545d837d
 */
 