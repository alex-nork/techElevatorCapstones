package com.techelevator.dao;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import com.techelevator.model.Park;
import com.techelevator.model.ParkDao;
@Component
public class JDBCParkDao implements ParkDao {

	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public JDBCParkDao(DataSource datasource) {
		this.jdbcTemplate = new JdbcTemplate(datasource);
	}

	@Override
	public List<Park> getAllParks() {
		List<Park> parks = new ArrayList<Park>();
		String parkSearchSql = "SELECT parkcode, parkname, state, acreage, elevationinfeet, milesoftrail, numberofcampsites, "
				+ "climate, yearfounded, annualvisitorcount, inspirationalquote, inspirationalquotesource, parkdescription, entryfee, numberofanimalspecies "
				+ "FROM park";
		SqlRowSet results = jdbcTemplate.queryForRowSet(parkSearchSql);
		while (results.next()) {
			parks.add(mapRowToParkSet(results));
		}
		return parks;
		
	}

	@Override
	//parkCode is passed as a parameter when the user selects the park from the home page
	public Park getParkDetails(String parkCode) {
		Park park = new Park();
		String parkSearchSql = "SELECT parkcode, parkname, state, acreage, elevationinfeet, milesoftrail, numberofcampsites, "
				+ "climate, yearfounded, annualvisitorcount, inspirationalquote, inspirationalquotesource, parkdescription, entryfee, numberofanimalspecies "
				+ "FROM park "
				+ "WHERE parkcode = ?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(parkSearchSql, parkCode);
		if (results.next()) {
			park = mapRowToParkSet(results);
		}
		return park;	
		}

	
	private Park mapRowToParkSet(SqlRowSet results) {
		//formatter is used to put commas into numbers in the thousands and greater
		DecimalFormat formatter = new DecimalFormat("###,###,###");
		Park newPark = new Park();
		//setting parkCode to lower case in order to retrieve the correct image
		//changed to uppercase in the SurveyJDBC in order to insert and then retrieve the appropriate record
		newPark.setParkCode(results.getString("parkcode").toLowerCase());
		newPark.setParkName(results.getString("parkname"));
		newPark.setState(results.getString("state"));
		newPark.setAcreage(formatter.format(results.getInt("acreage")));
		newPark.setElevation(formatter.format(results.getInt("elevationinfeet")));
		newPark.setMilesOfTrail(results.getDouble("milesoftrail"));
		newPark.setNumberOfCampsites(formatter.format(results.getInt("numberofcampsites")));
		newPark.setClimate(results.getString("climate"));
		newPark.setYearFounded(results.getInt("yearfounded"));
		newPark.setVisitorCount(formatter.format(results.getInt("annualvisitorcount")));
		newPark.setQuote(results.getString("inspirationalquote"));
		newPark.setQuoteSource(results.getString("inspirationalquotesource"));
		newPark.setDescription(results.getString("parkdescription"));
		newPark.setEntryFee(results.getInt("entryfee"));
		newPark.setNumberOfAnimalSpecies(formatter.format(results.getInt("numberofanimalspecies")));
		
		return newPark;
	}
	
}
