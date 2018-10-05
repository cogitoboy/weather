package org.dale.applegate.service.impl;

import org.dale.applegate.data.WeatherDao;
import org.dale.applegate.model.Weather;
import org.dale.applegate.service.CachableService;
import org.dale.applegate.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.CacheAnnotationParser;
import org.springframework.stereotype.Component;

/**
 * 
 * Weather information services for the United States.
 * 
 * @author Dale
 *
 */
@Component
public class UnitedStatesWeatherService implements WeatherService, CachableService {

	@Autowired
	@Qualifier("openWeatherDao")
	private WeatherDao weatherDao;
	
	/**
	 * Retrieves the weather information for specified US zipcode
	 * 
	 * @param zipcode 5 digit US zip code
	 * @return Weather
	 * 
	 */
	@Override
	public synchronized Weather getWeather(String zipcode) { //synchronized for thread safety when using basic cachable daos
		
		return weatherDao.getWeatherByZip(zipcode);
		
	}

	/**
	 * This method is to clear the cache of any daos.
	 * 
	 * Note: this is a cross cutting concern and is a little clumsy here.
	 * Something like injection would be a better approach to providing this
	 * cachable implementation than using inheritance. 
	 * 
	 */
	@Override
	public synchronized void clearCache() {
		
		weatherDao.cacheEvict();
		
	}
 
}
