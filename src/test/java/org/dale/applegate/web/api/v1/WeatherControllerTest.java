package org.dale.applegate.web.api.v1;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import org.dale.applegate.exception.ResourceNotFoundException;
import org.dale.applegate.model.Weather;
import org.dale.applegate.service.WeatherService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class WeatherControllerTest {

  private static final String VALID_ZIP = "89104";
  private static final String VALID_LONG_ZIP = "89104-1200";
  private static final String VALID_CANADA_ZIP = "M5P 3H4";
  private static final String VALID_WIND_DIRECTION = "N";
  private static final String INVALID_ZIP = "00001";
  private static final String INVALID_GIBBERISH = "sd$523 __3!@#$$A(%*";
  private static final Double VALID_WIND_SPEED = 0.15;
  private static Weather VALID_WEATHER = new Weather(VALID_WIND_DIRECTION, VALID_WIND_SPEED);

  @Mock
  WeatherService weatherServiceMock;

  @InjectMocks
  WeatherController weatherController;

  @Test
  public void testGetWeather() {

    when(weatherServiceMock.getWeather(VALID_ZIP)).thenReturn(VALID_WEATHER);
    assertEquals(VALID_WIND_DIRECTION, weatherController.getWeather(VALID_ZIP).getWindDirection());
    assertEquals(VALID_WIND_SPEED, weatherController.getWeather(VALID_ZIP).getWindSpeed());

  }

  @Test
  public void testGetWeatherValidLongZip() {

    when(weatherServiceMock.getWeather(VALID_LONG_ZIP)).thenReturn(VALID_WEATHER);
    assertEquals(VALID_WIND_DIRECTION,
        weatherController.getWeather(VALID_LONG_ZIP).getWindDirection());
    assertEquals(VALID_WIND_SPEED, weatherController.getWeather(VALID_LONG_ZIP).getWindSpeed());

  }

  @Test(expected = ResourceNotFoundException.class)
  public void testGetWeatherCanadaZip() {

    when(weatherServiceMock.getWeather(VALID_CANADA_ZIP))
        .thenThrow(new ResourceNotFoundException(1L, "Zipcode not found"));
    weatherController.getWeather(VALID_CANADA_ZIP);

  }

  @Test(expected = ResourceNotFoundException.class)
  public void testGetWeatherInvalidZip() {

    when(weatherServiceMock.getWeather(INVALID_ZIP))
        .thenThrow(new ResourceNotFoundException(1L, "Zipcode not found"));
    weatherController.getWeather(INVALID_ZIP);

  }

  @Test(expected = ResourceNotFoundException.class)
  public void testGetWeatherGibberishZip() {

    when(weatherServiceMock.getWeather(INVALID_GIBBERISH))
        .thenThrow(new ResourceNotFoundException(1L, "Zipcode not found"));
    weatherController.getWeather(INVALID_GIBBERISH);

  }

}
