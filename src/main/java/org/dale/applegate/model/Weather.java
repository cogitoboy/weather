package org.dale.applegate.model;

public class Weather {

	String windDirection;
	Integer windSpeed;
	
	public Weather() {}
	
	public Weather(String windDirection, Integer windSpeed) {
		this.windDirection = windDirection;
		this.windSpeed = windSpeed;
	}
	
	public void setWindDirection(String windDirection) {
		this.windDirection = windDirection;
	}
	
	public String getWindDirection() {
		return windDirection;
	}
	
	public void setWindSpeed(Integer windSpeed) {
		this.windSpeed = windSpeed;
	}
	
	public Integer getWindSpeed() {
		return windSpeed;
	}
}

