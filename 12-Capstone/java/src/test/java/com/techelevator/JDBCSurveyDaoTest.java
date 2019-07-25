package com.techelevator;

import org.junit.Before;
import org.junit.Test;

import com.techelevator.dao.JDBCSurveyDao;
import com.techelevator.model.Survey;

import org.junit.Assert;

public class JDBCSurveyDaoTest extends DAOIntegrationTest {

	private JDBCSurveyDao dao;
	private Survey newSurvey;
	
	@Before
	public void setUp() {
		dao = new JDBCSurveyDao(getDataSource());
		newSurvey = new Survey();
		newSurvey.setParkCode("GSMNP");
		newSurvey.setEmail("jsmith@gmail.com");
		newSurvey.setActivityLevel("sedentary");
		newSurvey.setStateOfResidence("NJ");
		
	}

	@Test
	public void addSurveyResultToDatabaseAndGetAllSurveyResultsTest() {
		
		int count = dao.getSurveyResults().size();
		
		dao.addSurveyResultToDatabase(newSurvey);
		
		int newCount = count + 1;
		
		Assert.assertEquals(newCount, dao.getSurveyResults().size());
//		Assert.assertEquals(2, dao.getSurveyResults().get(0).getCount());
//		Assert.assertEquals(1, dao.getSurveyResults().get(2).getCount());
	}
	
}
