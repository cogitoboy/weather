package org.dale.applegate.data;

import org.dale.applegate.model.Weather;

public interface WeatherDao extends CachableRestDao {

	public Weather getWeatherByZip(String zip);
	
}
