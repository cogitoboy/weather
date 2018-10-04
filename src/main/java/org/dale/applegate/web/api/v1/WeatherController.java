package org.dale.applegate.web.api.v1;

import org.dale.applegate.model.Weather;
import org.dale.applegate.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1")
public class WeatherController {

	
	@Autowired
	private WeatherService weatherService;
	
	@RequestMapping( value = "/wind/{zipcode}", method = RequestMethod.GET)
	public Weather getWeather(@PathVariable String zipcode) {
		//TODO: Validate the path parameter - need to do whatever openweather does on invalid addresses.
		return weatherService.getWeather(zipcode);
		
	}
}
