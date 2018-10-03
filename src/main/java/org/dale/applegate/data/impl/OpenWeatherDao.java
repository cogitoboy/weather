package org.dale.applegate.data.impl;

import org.dale.applegate.data.WeatherDao;
import org.dale.applegate.model.Weather;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.annotation.JsonProperty;

@Repository("openWeatherDao")
public class OpenWeatherDao implements WeatherDao {

	//TODO: Externalize these api key to property file
	private static final String API_KEY = "20be12f473c5e794a681fa66f6a46f4b";
	private static final String ZIPCODE_US_QUERY = "https://api.openweathermap.org/data/2.5/weather?zip=?,APPID= ?";
	
	@Bean
	public RestTemplate getRestTemplate(RestTemplateBuilder restTemplateBuilder) {
		return restTemplateBuilder.build();
	}
	
	@Override
	public Weather getWeatherByZip(String zipcode) {	
		
		//convert to big decimal
		return new Weather("W", "0.99");
	}

	public class OpenWeatherDTO{
		@JsonProperty(value="wind.speed")
		private String windSpeed;
		@JsonProperty(value="wind.speed")
		private String windDegree;
		
		public String getWindDegree() {
			return windDegree;
		}
		public String getWindSpeed() {
			return windSpeed;
		}
		//{//"coord":{"lon":-122.09,"lat":37.39},
		 //	"sys":{"type":3,"id":168940,"message":0.0297,"country":"US","sunrise":1427723751,"sunset":1427768967},
		 //	"weather":[{"id":800,"main":"Clear","description":"Sky is Clear","icon":"01n"}],
		//	"base":"stations",
		//	"main":{"temp":285.68,"humidity":74,"pressure":1016.8,"temp_min":284.82,"temp_max":286.48},
		//	"wind":{"speed":0.96,"deg":285.001},
		//	"clouds":{"all":0},
		//	"dt":1427700245,
		//	"id":0,
		//	"name":"Mountain View",
		//	"cod":200}
	}
}
