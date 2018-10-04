package org.dale.applegate.data;

import org.dale.applegate.model.Weather;

public interface WeatherDao extends RestDao {

	public Weather getWeatherByZip(String zip);
	
}
