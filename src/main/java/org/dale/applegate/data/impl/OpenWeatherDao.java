package org.dale.applegate.data.impl;

import org.dale.applegate.data.WeatherDao;
import org.dale.applegate.model.Weather;
import org.springframework.stereotype.Repository;

@Repository("openWeatherDao")
public class OpenWeatherDao implements WeatherDao {

	@Override
	public Weather getWeatherByZip(String zip) {	
		return new Weather("W", 99);
	}

}
