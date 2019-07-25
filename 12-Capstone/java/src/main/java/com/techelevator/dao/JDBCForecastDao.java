package com.techelevator.dao;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import com.techelevator.model.ForecastDao;
import com.techelevator.model.Weather;

@Component
public class JDBCForecastDao implements ForecastDao {

	private JdbcTemplate jdbcTemplate;
	
	//defining this list of weather so that it is accessible from multiple methods below
	private List<Weather> fiveDayWeather;

	@Autowired
	public JDBCForecastDao(DataSource datasource) {
		this.jdbcTemplate = new JdbcTemplate(datasource);
	}

	@Override
	//parkCode is passed as a parameter when the user selects the park for further details
	public List<Weather> get5DayForecast(String parkCode) {
		fiveDayWeather = new ArrayList<Weather>();
		String weatherSearchSql = "SELECT parkcode, fivedayforecastvalue, low, high, forecast " + "FROM weather "
				+ "WHERE parkcode = ?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(weatherSearchSql, parkCode);
		while (results.next()) {
			fiveDayWeather.add(mapRowToSet(results));
		}
		return fiveDayWeather;
	}

	@Override
	public String weatherBasedGearLogic(List<Weather> fiveDayWeather) {
		//a different string will be returned based on the first item in the fiveDayWeather list
		String day1Forecast = fiveDayWeather.get(0).getForecast();

		if (day1Forecast.equals("snow")) {
			return "Pack snowshoes";
		} else if (day1Forecast.equals("rain")) {
			return "Pack rain gear and waterproof shoes";
		} else if (day1Forecast.equals("thunderstorms")) {
			return "Seek shelter and avoid hiking on exposed ridges";
		} else if (day1Forecast.equals("sunny")) {
			return "Pack sunblock";
		} else {

		}
		return null;
	}

	@Override
	public String temperatureBasedGearLogic(List<Weather> fiveDayWeather) {
		//using both f and c temperatures to determine appropriate suggestion, if any
		int highTemperatureFahrenheit = fiveDayWeather.get(0).getHighTemperatureFahrenheit();
		int highTemperatureCelcius = fiveDayWeather.get(0).getHighTemperatureCelcius();
		int lowTemperatureFahrenheit = fiveDayWeather.get(0).getLowTemperatureFahrenheit();
		int lowTemperatureCelcius = fiveDayWeather.get(0).getLowTemperatureCelcius();

		//using "if" instead of "if..else if" so that multiple suggestions can be displayed
		if (highTemperatureFahrenheit > 75 || highTemperatureCelcius > farenheitToCelcius(75)) {
			return "Bring an extra gallon of water";
		}
		if (lowTemperatureFahrenheit < 20 || lowTemperatureCelcius < farenheitToCelcius(20)) {
			return "Danger: Exposure to frigid temperatures";
		}
		if ((highTemperatureFahrenheit - lowTemperatureFahrenheit > 20) || (highTemperatureCelcius - lowTemperatureCelcius > 20)) {
			return "Wear breathable layers";
		} else {
			return null;
		}
	}

	@Override
	public int temperatureSelectionLogic(boolean temperatureSelectionFahrenheit) {
	
		return 0;
	}
	
	
	private Weather mapRowToSet(SqlRowSet results) {
		Weather newForecast = new Weather();
		newForecast.setFiveDayForecastValue(results.getInt("fivedayforecastvalue"));
		newForecast.setForecast(stringToCamelCase(results.getString("forecast")));
		newForecast.setLowTemperatureFahrenheit(results.getInt("low"));
		newForecast.setLowTemperatureCelcius(farenheitToCelcius(results.getInt("low")));
		newForecast.setHighTemperatureFahrenheit(results.getInt("high"));
		newForecast.setHighTemperatureCelcius(farenheitToCelcius(results.getInt("high")));
		return newForecast;
	}
	
	//this method allows us to present the partly cloudy forecast picture (database has forecast as "partly cloudy" and image is named "partlyCloudy"
	private String stringToCamelCase(String forecast) {
		//new string to hold camelCase name
		String newString = "";
		//new String array to hold the items from the forecast String once split
		String[] camelCaseForecast = forecast.split(" ");
		/*
		 * if the above array has more than one string in it
		 * make a new string with the first letter upper case and another string containing the rest of the letters
		 * concatenate the upper case letter and other letters onto the first word in the array 
		 */
		if (camelCaseForecast.length > 1) {
			String firstLetter = camelCaseForecast[1].substring(0, 1).toUpperCase();
			String otherLetters = camelCaseForecast[1].substring(1);
			newString += camelCaseForecast[0] + firstLetter + otherLetters;
			return newString;
		} else {
			return forecast;
		}
	}
	
	//made public so that it could be referred to for testing
	public int farenheitToCelcius(int farenheitTemperature) {
		
		double celciusTemperature = (farenheitTemperature - 32) / (1.8);
		
		int celciusTemp = (int) celciusTemperature;
		
		return celciusTemp;
		
	}

	
}
