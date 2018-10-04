package org.dale.applegate.service.impl;

import org.dale.applegate.data.WeatherDao;
import org.dale.applegate.model.Weather;
import org.dale.applegate.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * 
 * Weather searvices for US based locales.
 * 
 * @author Dale
 *
 */
@Component
public class UnitedStatesWeatherService implements WeatherService {

	@Autowired
	@Qualifier("openWeatherDao")
	private WeatherDao weatherDao;
	
	@Override
	public Weather getWeather(String zipcode) {
		
		return weatherDao.getWeatherByZip(zipcode);
		
	}
 
}
