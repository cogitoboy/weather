package org.stalesoft.data;

import org.stalesoft.model.Weather;

public interface WeatherDao {

  public Weather getWeatherByZip(String zip);

}
