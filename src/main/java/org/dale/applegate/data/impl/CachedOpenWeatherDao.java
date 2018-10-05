package org.dale.applegate.data.impl;

import org.dale.applegate.data.CachableDao;
import org.dale.applegate.data.WeatherDao;
import org.dale.applegate.model.Weather;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Primary;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Repository;

/**
 * Cache decorator for the OpenWeatherService
 * 
 * @author dale
 *
 */
@Repository("cachedOpenWeatherDao")
@Primary
public class CachedOpenWeatherDao implements WeatherDao, CachableDao {

  Logger logger = LoggerFactory.getLogger(CachedOpenWeatherDao.class);

  public static final String CACHE_ID = "simple_cache";
  public static final int CACHE_TTL = 900000;

  @Autowired
  @Qualifier("openWeatherDao")
  WeatherDao weatherDao;


  @Cacheable(CACHE_ID)
  public Weather getWeatherByZip(String zip) {
    return weatherDao.getWeatherByZip(zip);
  }

  @CacheEvict(allEntries = true, cacheNames = {CACHE_ID})
  @Scheduled(fixedDelay = CACHE_TTL)
  public void cacheEvict() {
    logger.debug("cache: {} evicted", CACHE_ID);
  }
}
