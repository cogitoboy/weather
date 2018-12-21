package org.stalesoft.data;

import org.dale.applegate.model.Weather;

public interface WeatherDao {

  public Weather getWeatherByZip(String zip);

}
