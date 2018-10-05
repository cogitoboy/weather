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

public class SimpleCachableDao<T> implements CachableDao {

	public static final String CACHE_ID = "simple_cache";
	public static final int CACHE_TTL = 900000;
	
	Logger logger = LoggerFactory.getLogger(SimpleCachableDao.class);
	
	@Autowired
	private RestTemplate restTemplate;
	
	
	@Cacheable(CACHE_ID)
	public T get(String URI, Class origin) {
		System.out.println("GRABED -->");
		return (T)restTemplate.getForObject(URI, origin);
	}
	
	@CacheEvict(allEntries = true, cacheNames = { CACHE_ID })
	@Scheduled(fixedDelay = CACHE_TTL)
	public void cacheEvict() {
		System.out.println("<-- EVICTED");
		logger.debug("cache: {} evicted", CACHE_ID);
	}
}
