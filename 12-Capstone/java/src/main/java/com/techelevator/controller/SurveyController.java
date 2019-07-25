package com.techelevator.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.techelevator.dao.JDBCParkDao;
import com.techelevator.dao.JDBCSurveyDao;
import com.techelevator.model.Survey;

@Controller
public class SurveyController {
	
	@Autowired
	private JDBCParkDao parkDao;
	@Autowired
	private JDBCSurveyDao surveyDao;
	
	
	@RequestMapping(path="/survey", method= RequestMethod.GET)
	public String displaySurvey(HttpServletRequest request, ModelMap map) {
		
		request.setAttribute("parks", parkDao.getAllParks());
		if(!map.containsAttribute("survey")) {
			map.addAttribute("survey", new Survey());
		}
		
		return "survey";
	}
	
	@RequestMapping(path = "/surveyResult", method = RequestMethod.GET)
	public String displaySurveyResult(HttpServletRequest request) {
		
		request.setAttribute("results", surveyDao.getSurveyResults());
		
		return "surveyResult";
	}
	
	@RequestMapping(path = "/surveyResult", method = RequestMethod.POST)
	public String displaySurveyResult(@Valid @ModelAttribute("survey") Survey survey, BindingResult result, HttpServletRequest request) {
		if(result.hasErrors()) {
			request.setAttribute("parks", parkDao.getAllParks());
			return "/survey";
		}
		
		Survey newSurvey = new Survey();
		newSurvey.setActivityLevel(request.getParameter("activityLevel"));
		newSurvey.setEmail(request.getParameter("email"));
		newSurvey.setParkCode(request.getParameter("parkCode").toUpperCase());
		newSurvey.setStateOfResidence(request.getParameter("stateOfResidence"));
		
		surveyDao.addSurveyResultToDatabase(newSurvey);
		
		request.setAttribute("results", surveyDao.getSurveyResults());
		
		return "redirect:/surveyResult";
	}
	
	
}
