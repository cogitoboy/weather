package org.dale.applegate.service.impl;

import org.dale.applegate.model.Weather;
import org.dale.applegate.service.*;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class UnitedStatesWeatherService implements WeatherService {

	@Override
	public Weather getWeather(String zipcode) {
		
		return new Weather();
	}
 
	
}
