package com.techelevator.model;

public class Weather {
	
	private int highTemperatureFahrenheit;
	private int highTemperatureCelcius;
	private int lowTemperatureFahrenheit;
	private int lowTemperatureCelcius;
	private String forecast;
	private int fiveDayForecastValue;
	private boolean temperatureSelectionFahrenheit;
//	private int lowTempCelcius;
	
	
	public int getHighTemperatureFahrenheit() {
		return highTemperatureFahrenheit;
	}
	public void setHighTemperatureFahrenheit(int highTemperatureFahrenheit) {
		this.highTemperatureFahrenheit = highTemperatureFahrenheit;
	}
	public int getLowTemperatureFahrenheit() {
		return lowTemperatureFahrenheit;
	}
	public void setLowTemperatureFahrenheit(int lowTemperatureFahrenheit) {
		this.lowTemperatureFahrenheit = lowTemperatureFahrenheit;
	}
	public String getForecast() {
		return forecast;
	}
	public void setForecast(String forecast) {
		this.forecast = forecast;
	}
	public int getFiveDayForecastValue() {
		return fiveDayForecastValue;
	}
	public void setFiveDayForecastValue(int fiveDayForecastValue) {
		this.fiveDayForecastValue = fiveDayForecastValue;
	}
	public int getHighTemperatureCelcius() {
		return highTemperatureCelcius;
	}
	public void setHighTemperatureCelcius(int highTemperatureCelcius) {
		this.highTemperatureCelcius = highTemperatureCelcius;
	}
	public int getLowTemperatureCelcius() {
		return lowTemperatureCelcius;
	}
	public void setLowTemperatureCelcius(int lowTemperatureCelcius) {
		this.lowTemperatureCelcius = lowTemperatureCelcius;
	}
	public boolean isTemperatureSelectionFahrenheit() {
		return temperatureSelectionFahrenheit;
	}
	public void setTemperatureSelectionFahrenheit(boolean temperatureSelectionFahrenheit) {
		this.temperatureSelectionFahrenheit = temperatureSelectionFahrenheit;
	}
	
//	public int getLowTempCelcius(boolean temperatureSelectionFahrenheit) {
//		if (!temperatureSelectionFahrenheit) {
//			return getLowTemperatureCelcius();
//		}
//		else {
//			return getLowTemperatureFahrenheit();
//		}
//	}

}
