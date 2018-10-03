package org.dale.applegate.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Weather {

	String windDirection;
	Double windSpeed;
	
	public Weather() {}
	
	public Weather(String windDirection, Double windSpeed) {
		this.windDirection = windDirection;
		this.windSpeed = windSpeed;
	}
	
	public void setWindDirection(String windDirection) {
		this.windDirection = windDirection;
	}
	
	public String getWindDirection() {
		return windDirection;
	}
	
	public void setWindSpeed(Double windSpeed) {
		this.windSpeed = windSpeed;
	}
	
	public Double getWindSpeed() {
		return windSpeed;
	}
}
