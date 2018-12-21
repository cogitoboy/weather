package org.dale.applegate.service.impl;

import org.stalesoft.data.WeatherDao;
import org.dale.applegate.model.Weather;
import org.dale.applegate.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * 
 * Weather information services for the United States.
 * 
 * @author Dale
 *
 */
@Component
public class DomesticWeatherService implements WeatherService {

  @Autowired
  @Qualifier("cachedOpenWeatherDao")
  private WeatherDao weatherDao;

  /**
   * Retrieves the weather information for specified US zipcode
   * 
   * @param zipcode 5 digit US zip code
   * @return Weather
   * 
   */
  @Override
  public Weather getWeather(String zipcode) {

    Weather weather = null;

    synchronized (weatherDao) {
      weather = weatherDao.getWeatherByZip(zipcode);
    }

    assert weather != null;

    return weather;
  }
}
