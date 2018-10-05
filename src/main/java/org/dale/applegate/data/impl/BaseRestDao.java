package org.dale.applegate.data.impl;

import org.dale.applegate.data.CachableDao;
import org.dale.applegate.thirdparty.openweather.OpenWeather;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.client.RestTemplate;

public class BaseRestDao<T> {

	Logger logger = LoggerFactory.getLogger(BaseRestDao.class);
	
	@Autowired
	private RestTemplate restTemplate;
	
	
	public T get(String URI, Class origin) {
		System.out.println("GRABED -->");
		return (T)restTemplate.getForObject(URI, origin);
	}
	
	
}
