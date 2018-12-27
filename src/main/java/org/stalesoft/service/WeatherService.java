package org.stalesoft.service;

import org.stalesoft.model.Weather;

public interface WeatherService {

  public Weather getWeather(String zipcode);

}
