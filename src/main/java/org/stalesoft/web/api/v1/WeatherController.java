package org.stalesoft.web.api.v1;

import org.stalesoft.model.Weather;
import org.stalesoft.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 
 * Weather services api.
 * 
 * @author Dale
 *
 */
@RestController
@RequestMapping("api/v1")
public class WeatherController {


  @Autowired
  private WeatherService weatherService;

  @RequestMapping(value = "/wind/{zipcode}", method = RequestMethod.GET)
  public Weather getWeather(@PathVariable String zipcode) {

    // No input validation on zipcode - path parameters especially on a GET should not be validated.
    // See OpenWeather API GETs
    return weatherService.getWeather(zipcode);

  }
}
