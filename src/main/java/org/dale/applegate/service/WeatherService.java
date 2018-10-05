package org.dale.applegate.service;

import org.dale.applegate.model.Weather;

public interface WeatherService extends CachableService {
	
	public Weather getWeather(String zipcode);

}
