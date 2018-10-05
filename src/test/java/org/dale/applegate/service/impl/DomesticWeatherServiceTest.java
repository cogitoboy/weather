package org.dale.applegate.service.impl;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import org.dale.applegate.data.impl.OpenWeatherDao;
import org.dale.applegate.exception.ResourceNotFoundException;
import org.dale.applegate.model.Weather;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class DomesticWeatherServiceTest {

  private static final String VALID_ZIP = "89104";
  private static final String VALID_LONG_ZIP = "89104-1200";
  private static final String VALID_CANADA_ZIP = "M5P 3H4";
  private static final String VALID_WIND_DIRECTION = "N";
  private static final String INVALID_ZIP = "00001";
  private static final String INVALID_GIBBERISH = "sd$523 __3!@#$$A(%*";
  private static final Double VALID_WIND_SPEED = 0.15;
  private static Weather VALID_WEATHER = new Weather(VALID_WIND_DIRECTION, VALID_WIND_SPEED);

  @Mock
  OpenWeatherDao weatherDaoMock;

  @InjectMocks
  private DomesticWeatherService weatherService;

  @Test
  public void testGetWeather() {
    when(weatherDaoMock.getWeatherByZip(VALID_ZIP)).thenReturn(VALID_WEATHER);
    assertEquals(VALID_WIND_DIRECTION, weatherService.getWeather(VALID_ZIP).getWindDirection());
    assertEquals(VALID_WIND_SPEED, weatherService.getWeather(VALID_ZIP).getWindSpeed());

  }


  @Test
  public void testGetWeatherValidLongZip() {
    when(weatherDaoMock.getWeatherByZip(VALID_LONG_ZIP)).thenReturn(VALID_WEATHER);
    assertEquals(VALID_WIND_DIRECTION,
        weatherService.getWeather(VALID_LONG_ZIP).getWindDirection());
    assertEquals(VALID_WIND_SPEED, weatherService.getWeather(VALID_LONG_ZIP).getWindSpeed());
  }

  @Test(expected = ResourceNotFoundException.class)
  public void testGetWeatherCanadaZip() {
    when(weatherDaoMock.getWeatherByZip(VALID_CANADA_ZIP))
        .thenThrow(new ResourceNotFoundException(1L, "Zipcode not found"));
    weatherService.getWeather(VALID_CANADA_ZIP);
  }

  @Test(expected = ResourceNotFoundException.class)
  public void testGetWeatherInvalidZip() {
    when(weatherDaoMock.getWeatherByZip(INVALID_ZIP))
        .thenThrow(new ResourceNotFoundException(1L, "Zipcode not found"));
    weatherService.getWeather(INVALID_ZIP);
  }

  @Test(expected = ResourceNotFoundException.class)
  public void testGetWeatherGibberishZip() {
    when(weatherDaoMock.getWeatherByZip(INVALID_GIBBERISH))
        .thenThrow(new ResourceNotFoundException(1L, "Zipcode not found"));
    weatherService.getWeather(INVALID_GIBBERISH);
  }



}
