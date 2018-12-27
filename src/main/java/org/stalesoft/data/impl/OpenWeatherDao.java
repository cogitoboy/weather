package org.stalesoft.data.impl;

import org.stalesoft.data.WeatherDao;
import org.stalesoft.exception.DaoException;
import org.stalesoft.exception.ResourceNotFoundException;
import org.stalesoft.model.Weather;
import org.stalesoft.service.helper.WeatherHelper;
import org.stalesoft.thirdparty.openweather.OpenWeather;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.HttpClientErrorException;

@Repository("openWeatherDao")
public class OpenWeatherDao extends BaseRestDao<OpenWeather> implements WeatherDao {

  Logger logger = LoggerFactory.getLogger(OpenWeatherDao.class);

  // TODO: Externalize these api key to the property file
  private static final String API_KEY = "20be12f473c5e794a681fa66f6a46f4b";
  private static final String ZIPCODE_US_QUERY =
      "https://api.openweathermap.org/data/2.5/weather?zip=%s,us&APPID=%s";

  @Autowired
  private WeatherHelper weatherHelper;


  @Override
  public Weather getWeatherByZip(String zipcode) {

    logger.info(String.format(ZIPCODE_US_QUERY, zipcode, API_KEY));

    OpenWeather weather = null;

    try {

      weather = get(String.format(ZIPCODE_US_QUERY, zipcode, API_KEY), OpenWeather.class);

    } catch (HttpClientErrorException e) {
      // TODO: Parse the response for resource not found versus other possible errors
      throw new ResourceNotFoundException(2L, e.getMessage());
    } catch (Exception e) {
      throw new DaoException(1L, e.getMessage());
    }

    String windDirection = weatherHelper.convertWindDegreeToDirection(weather.getWind().getDeg());
    Double windSpeed = weather.getWind().getSpeed();

    logger.debug("windDirection={},windSpeed={}", windDirection, windSpeed);

    return new Weather(windDirection, windSpeed);
  }
}
