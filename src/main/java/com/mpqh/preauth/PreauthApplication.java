package com.mpqh.preauth;

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
@ComponentScan({"com.mpqh"})
@SpringBootApplication
@EnableCaching
@EnableScheduling
public class PreauthApplication {

  Logger logger = LoggerFactory.getLogger(PreauthApplication.class);

  public static void main(String[] args) {
    SpringApplication.run(PreauthApplication.class, args);
  }

  @Bean
  public RestTemplate getRestTemplate(RestTemplateBuilder restTemplateBuilder) {
    return restTemplateBuilder.build();
  }

}
