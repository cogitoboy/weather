package org.dale.applegate;

import org.dale.applegate.data.impl.OpenWeatherDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableCaching
@EnableScheduling
public class WeatherserviceApplication {
	
	Logger logger = LoggerFactory.getLogger(WeatherserviceApplication.class);
	
	//TODO: Externalize the evict delay to property file
	public static final int MAIN_CACHE_TTL = 900000;
	public static final String MAIN_CACHE = "MAIN_CACHE";

	public static void main(String[] args) {
		SpringApplication.run(WeatherserviceApplication.class, args);
	}
	
	@Bean
	public RestTemplate getRestTemplate(RestTemplateBuilder restTemplateBuilder) {
		return restTemplateBuilder.build();
	}
	
	@CacheEvict(allEntries = true, cacheNames = { WeatherserviceApplication.MAIN_CACHE })
	@Scheduled(fixedDelay = MAIN_CACHE_TTL)
	public void cacheEvict() {
		logger.debug("cache: {} evicted", WeatherserviceApplication.MAIN_CACHE);
	}
}
