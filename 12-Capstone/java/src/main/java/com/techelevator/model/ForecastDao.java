package com.techelevator.model;

import java.util.List;

public interface ForecastDao {
	//using these to print the forecast and weather-based recommendations
	List<Weather> get5DayForecast(String parkCode);
	String weatherBasedGearLogic(List<Weather> fiveDayWeather);
	String temperatureBasedGearLogic(List<Weather> fiveDayWeather);
	int temperatureSelectionLogic(boolean temperatureSelectionFahrenheit);
}
