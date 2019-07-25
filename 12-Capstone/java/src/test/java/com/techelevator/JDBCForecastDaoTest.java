package com.techelevator;

import java.util.List;

import  org.junit.Assert;

import org.junit.Before;
import org.junit.Test;

import com.techelevator.dao.JDBCForecastDao;
import com.techelevator.dao.JDBCParkDao;
import com.techelevator.model.Weather;


public class JDBCForecastDaoTest  extends DAOIntegrationTest{
	
	private JDBCForecastDao dao;
	

	@Before
	public void setUp() {
		dao = new JDBCForecastDao(getDataSource());
		
	}

	@Test
	public void get5DayForecastTest() {
		int numberOfDaysInForecast = dao.get5DayForecast("GNP").size();
		int gnpTodayForecast = dao.get5DayForecast("GNP").get(0).getLowTemperatureFahrenheit();
		String mrnpDay4Forecast = dao.get5DayForecast("MRNP").get(3).getForecast();
		
		Assert.assertEquals(numberOfDaysInForecast, dao.get5DayForecast("GNP").size());
		Assert.assertEquals(gnpTodayForecast, dao.get5DayForecast("GNP").get(0).getLowTemperatureFahrenheit());
		Assert.assertEquals(mrnpDay4Forecast, dao.get5DayForecast("MRNP").get(3).getForecast());
	}
	
	@Test
	public void weatherBasedGearLogicTest() {
		
		List<Weather> forecast = dao.get5DayForecast("GNP");
		String gnpGearToBring = dao.weatherBasedGearLogic(forecast);
		Assert.assertEquals(gnpGearToBring, dao.weatherBasedGearLogic(forecast));
		
		List<Weather> forecastGSMNP = dao.get5DayForecast("GSMNP");
		String gsmnpGearToBring = dao.weatherBasedGearLogic(forecastGSMNP);
		Assert.assertEquals(gsmnpGearToBring, dao.weatherBasedGearLogic(forecastGSMNP));
		
		List<Weather> forecastENP = dao.get5DayForecast("ENP");
		String enpGearToBring = dao.weatherBasedGearLogic(forecastENP);
		Assert.assertEquals(enpGearToBring, dao.weatherBasedGearLogic(forecastENP));
		
		List<Weather> forecastGTNP = dao.get5DayForecast("GTNP");
		String gtnpGearToBring = dao.weatherBasedGearLogic(forecastGTNP);
		Assert.assertEquals(gtnpGearToBring, dao.weatherBasedGearLogic(forecastGTNP));
		
	}
	
	@Test
	public void temperatureBasedGearLogicTest() {
		List<Weather> forecast = dao.get5DayForecast("GCNP");
		List<Weather> forecastENP = dao.get5DayForecast("ENP");
		List<Weather> forecastGTNP = dao.get5DayForecast("GTNP");
		List<Weather> forecastRMNP = dao.get5DayForecast("RMNP");
		List<Weather> forecastYNP = dao.get5DayForecast("YNP");
		List<Weather> forecastMRNP = dao.get5DayForecast("MRNP");
		
		String gcnpGearToBring = dao.temperatureBasedGearLogic(forecast);
		
		Assert.assertEquals(gcnpGearToBring, dao.temperatureBasedGearLogic(forecast));
		
		String enpGearToBring = dao.temperatureBasedGearLogic(forecastENP);
		
		Assert.assertEquals(enpGearToBring, dao.temperatureBasedGearLogic(forecastENP));
		
		String gtnpGearToBring = dao.temperatureBasedGearLogic(forecastGTNP);
		
		Assert.assertEquals(gtnpGearToBring, dao.temperatureBasedGearLogic(forecastGTNP));
		
		String rmnpGearToBring = dao.temperatureBasedGearLogic(forecastRMNP);
		
		Assert.assertEquals(rmnpGearToBring, dao.temperatureBasedGearLogic(forecastRMNP));
		
		String ynpGearToBring = dao.temperatureBasedGearLogic(forecastYNP);
		
		Assert.assertEquals(ynpGearToBring, dao.temperatureBasedGearLogic(forecastYNP));
		
		String mrnpGearToBring = dao.temperatureBasedGearLogic(forecastMRNP);
		
		Assert.assertEquals(mrnpGearToBring, dao.temperatureBasedGearLogic(forecastMRNP));
	}
	
	@Test
	public void tempConversionTest() {
		Assert.assertEquals(0, dao.farenheitToCelcius(32));
		Assert.assertEquals(100, dao.farenheitToCelcius(212));
		Assert.assertEquals(-5, dao.farenheitToCelcius(23));
		Assert.assertEquals(40, dao.farenheitToCelcius(104));
	}

}
