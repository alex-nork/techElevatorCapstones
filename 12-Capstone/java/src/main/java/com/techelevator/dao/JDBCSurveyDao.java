package com.techelevator.dao;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import com.techelevator.model.Survey;
import com.techelevator.model.SurveyDao;
@Component
public class JDBCSurveyDao implements SurveyDao {

	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public JDBCSurveyDao(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	
	@Override
	public void addSurveyResultToDatabase(Survey newSurvey) {
		String sqlInsertSurveyResult = "INSERT INTO survey_result (parkcode, emailaddress, state, activitylevel) "
				+ "VALUES (?, ?, ?, ?)";
		jdbcTemplate.update(sqlInsertSurveyResult, newSurvey.getParkCode(), newSurvey.getEmail(), newSurvey.getStateOfResidence(), newSurvey.getActivityLevel());
	}

	@Override
	public List<Survey> getSurveyResults() {
		List<Survey> survey = new ArrayList<Survey>();
			String sqlGetSurveyResults = "SELECT COUNT(survey_result.parkcode), parkname " + 
					"FROM survey_result " + 
					"JOIN park ON park.parkcode = survey_result.parkcode " + 
					"GROUP BY parkname " +
					"ORDER BY count DESC, parkname";
			SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetSurveyResults);
			while(results.next()) {
				survey.add(mapRowToSurveyResults(results));
			}
		return survey;
	}
	
	private Survey mapRowToSurveyResults(SqlRowSet results) {
		Survey newSurvey = new Survey();
		//only setting parkName and count as they are the only pieces of data displayed on the survey results page
		newSurvey.setParkName(results.getString("parkname"));
		newSurvey.setCount(results.getInt("count"));
		return newSurvey;
	}

}




