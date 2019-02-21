package org.stalesoft;

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
@ComponentScan({"org.stalesoft"})
@SpringBootApplication
@EnableCaching
@EnableScheduling
public class StalesoftApplication {

  Logger logger = LoggerFactory.getLogger(StalesoftApplication.class);

  public static void main(String[] args) {
    SpringApplication.run(StalesoftApplication.class, args);
  }

  @Bean
  public RestTemplate getRestTemplate(RestTemplateBuilder restTemplateBuilder) {
    return restTemplateBuilder.build();
  }

}
