package org.dale.applegate.service;

import org.dale.applegate.data.WeatherDao;
import org.dale.applegate.model.Weather;

public interface WeatherService {
	
	public Weather getWeather(String zipcode);

}
