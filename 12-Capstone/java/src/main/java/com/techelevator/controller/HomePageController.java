package com.techelevator.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.techelevator.model.ForecastDao;
import com.techelevator.model.Park;
import com.techelevator.model.ParkDao;
import com.techelevator.model.Weather;

@SessionAttributes({"temperatureFahrenheit", "parkCode"})

@Controller
public class HomePageController {
	@Autowired
	private ParkDao parkDao;
	
	@Autowired
	private ForecastDao forecastDao;
	private List<Weather> forecast;
	
	@RequestMapping("/")
	public String homePage(HttpServletRequest request) {
		request.setAttribute("parks", parkDao.getAllParks());
		return "homePage";
		
	}
	
	@RequestMapping(path = "/parkDetail", method = RequestMethod.GET)
	public String showParkDetailsGet(HttpServletRequest request, ModelMap map) {
		String parkCode = request.getParameter("parkCode").toUpperCase();
		Park park = parkDao.getParkDetails(parkCode);
		map.addAttribute("parkCode", parkCode);
		
		forecast = forecastDao.get5DayForecast(parkCode);
		
		String gearMessage = forecastDao.weatherBasedGearLogic(forecast);
		String weatherMessage = forecastDao.temperatureBasedGearLogic(forecast);
		
		request.setAttribute("weatherGear", weatherMessage);
		request.setAttribute("gear", gearMessage);
		request.setAttribute("park", park);
		request.setAttribute("forecast", forecast);
		
		return "parkDetail";
	}
	
	
	@RequestMapping(path = "/parkDetail", method = RequestMethod.POST)
	public String showParkDetails(@RequestParam boolean temperatureFahrenheit, HttpServletRequest request, ModelMap map) {

		map.addAttribute("temperatureFahrenheit", temperatureFahrenheit);
		
		String gearMessage = forecastDao.weatherBasedGearLogic(forecast);
		request.setAttribute("gear", gearMessage);
		
		return "redirect:/parkDetail";
	}

}

