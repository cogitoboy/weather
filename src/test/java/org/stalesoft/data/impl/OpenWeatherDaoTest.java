package org.stalesoft.data.impl;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import org.stalesoft.model.Weather;
import org.stalesoft.service.helper.WeatherHelper;
import org.stalesoft.thirdparty.openweather.OpenWeather;
import org.stalesoft.thirdparty.openweather.Wind;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.web.client.RestTemplate;
import org.stalesoft.data.impl.OpenWeatherDao;

@RunWith(MockitoJUnitRunner.class)
public class OpenWeatherDaoTest {

  private static final String ZIPCODE = "12345";
  
  private static final Double WIND_DEG = 11.15;
  private static final Double WIND_SPEED = 1.05;
  private static final String WIND_DIRECTION = "N";

  @Mock
  RestTemplate restTemplate;

  @Mock
  WeatherHelper weatherHelper;

  @Mock
  Wind openWind;

  @InjectMocks
  OpenWeatherDao openWeatherDao;

  @Test
  @Ignore
  public void testGetWeatherByZip() {
    OpenWeather openWeather = getGoodOpenWeather();

    //!!! Having trouble mocking out the resttemplate call. just not matching. Needs to be fixed,
    when(restTemplate.getForObject(ArgumentMatchers.any(), ArgumentMatchers.any()))
        .thenReturn(openWeather);

    when(weatherHelper.convertWindDegreeToDirection(WIND_DEG)).thenReturn(WIND_DIRECTION);

    Weather weather = openWeatherDao.getWeatherByZip(ZIPCODE);

    assertEquals(WIND_SPEED, weather.getWindSpeed());
    assertEquals(WIND_DIRECTION, weather.getWindDirection());
  }

  private OpenWeather getGoodOpenWeather() {

    OpenWeather openWeather = new OpenWeather();

    // set wind
    Wind wind = new Wind();

    wind.setDeg(WIND_DEG);
    wind.setSpeed(WIND_SPEED);
    openWeather.setWind(wind);

    return openWeather;
  }
}
