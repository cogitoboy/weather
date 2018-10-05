package org.dale.applegate.web.api.v1;

import org.dale.applegate.model.Weather;
import org.dale.applegate.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("admin")
public class CacheController {

	@Autowired
	private WeatherService weatherService;
	
	@RequestMapping( value = "/cache", method = RequestMethod.DELETE)
	public void clearCache() {
	
		weatherService.clearCache();

	}

}