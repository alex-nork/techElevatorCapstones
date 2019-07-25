package com.techelevator;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.techelevator.dao.JDBCParkDao;



public class JDBCParkDaoTest extends DAOIntegrationTest {
	
	private JDBCParkDao dao;
	
	@Before
	public void setup() {
		dao = new JDBCParkDao(getDataSource());
	}
	
	@Test
	public void getAllParksTest() {
		
		int numberOfParks = dao.getAllParks().size();
		
		Assert.assertEquals(numberOfParks, dao.getAllParks().size());
	}
	
	@Test
	public void getParkDetailsTest() {
		//Tests for MRNP
		String mrnpElevation = dao.getParkDetails("MRNP").getElevation();
		String mrnpState = dao.getParkDetails("MRNP").getState();
		String mrnpAcreage = dao.getParkDetails("MRNP").getAcreage();
		double mrnpMilesOfTrail = dao.getParkDetails("MRNP").getMilesOfTrail();
		String mrnpNumberOfCampsites = dao.getParkDetails("MRNP").getNumberOfCampsites();
		String mrnpClimate = dao.getParkDetails("MRNP").getClimate();
		int mrnpYearFounded = dao.getParkDetails("MRNP").getYearFounded();
		String mrnpAnnualVisitors = dao.getParkDetails("MRNP").getVisitorCount();
		String mrnpQuote = dao.getParkDetails("MRNP").getQuote();
		String mrnpQuoteSource = dao.getParkDetails("MRNP").getQuoteSource();
		String mrnpParkDescription = dao.getParkDetails("MRNP").getDescription();
		int mrnpEntryFee = dao.getParkDetails("MRNP").getEntryFee();
		String mrnpNumberOfAnimalSpecies = dao.getParkDetails("MRNP").getNumberOfAnimalSpecies();
		
		Assert.assertEquals(mrnpElevation, dao.getParkDetails("MRNP").getElevation());
		Assert.assertEquals(mrnpState, dao.getParkDetails("MRNP").getState());
		Assert.assertEquals(mrnpAcreage, dao.getParkDetails("MRNP").getAcreage());
		Assert.assertEquals(mrnpMilesOfTrail, dao.getParkDetails("MRNP").getMilesOfTrail(), 0);
		Assert.assertEquals(mrnpNumberOfCampsites, dao.getParkDetails("MRNP").getNumberOfCampsites());
		Assert.assertEquals(mrnpClimate, dao.getParkDetails("MRNP").getClimate());
		Assert.assertEquals(mrnpYearFounded, dao.getParkDetails("MRNP").getYearFounded());
		Assert.assertEquals(mrnpAnnualVisitors, dao.getParkDetails("MRNP").getVisitorCount());
		Assert.assertEquals(mrnpQuote, dao.getParkDetails("MRNP").getQuote());
		Assert.assertEquals(mrnpQuoteSource, dao.getParkDetails("MRNP").getQuoteSource());
		Assert.assertEquals(mrnpParkDescription, dao.getParkDetails("MRNP").getDescription());
		Assert.assertEquals(mrnpEntryFee, dao.getParkDetails("MRNP").getEntryFee());
		Assert.assertEquals(mrnpNumberOfAnimalSpecies, dao.getParkDetails("MRNP").getNumberOfAnimalSpecies());
		
		String gtnpClimate = dao.getParkDetails("GTNP").getClimate();
		Assert.assertEquals(gtnpClimate, dao.getParkDetails("GTNP").getClimate());
	}

}
