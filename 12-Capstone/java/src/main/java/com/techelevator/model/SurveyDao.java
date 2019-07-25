package com.techelevator.model;

import java.util.List;

public interface SurveyDao {

	void addSurveyResultToDatabase(Survey newSurvey);
	List<Survey> getSurveyResults();
	
}
