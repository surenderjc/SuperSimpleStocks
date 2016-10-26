package com.gbcorp.simple.stock.service;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.gbcorp.simple.stock.service.impl.SimpleStockServiceImpl;

public class SimpleStockServiceTest {

	Logger logger = Logger.getLogger(SimpleStockServiceTest.class);
	SimpleStockServiceImpl service = null;
	@Before
	public void setUp() throws Exception {
		service = SimpleStockServiceImpl.getInstance();
	}

	@Test
	public void calculateDividendYieldWithNullStockSymbal()  {
		
		logger.info("Start  calculateDividendYieldWithNullStockSymbal Test ...");
		double dividendYield;
		try {
			dividendYield = service.calculateDividendYield("", 2);
			Assert.assertTrue(dividendYield > 0.0);
		} catch (Exception e) {
			Assert.assertEquals("The stock symbol is not supported.", e.getMessage());
		}
		
		logger.info("Finish calculateDividendYieldWithNullStockSymbal Test ...OK");
	}
	
	@Test
	public void calculateDividendYieldWithPriceZero()  {
		
		logger.info("Start  calculateDividendYieldWithNullStockSymbal Test ...");
		double dividendYield;
		try {
			dividendYield = service.calculateDividendYield("GIN", 0);
			Assert.assertTrue(dividendYield > 0.0);
		} catch (Exception e) {
			Assert.assertEquals("The price for the stock should not be zero", e.getMessage());
		}
		
		logger.info("Finish calculateDividendYieldWithNullStockSymbal Test ...OK");
	}
	
	@Test
	public void calculateDividendYield()  {
		
		logger.info("Start  calculateDividendYield Test ...");
		double dividendYield;
		try {
			dividendYield = service.calculateDividendYield("GIN", 2);
			logger.info("The dividendYield value : "+dividendYield);
			Assert.assertTrue(dividendYield > 0.0);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.assertTrue(false);
		}
		
		logger.info("Finish calculateDividendYield Test ...OK");
	}
	
	@Test
	public void calculatePERatioWithEmptyStockSymbol()  {
		
		logger.info("Start  calculatePERatioWithEmptyStockSymbol Test ...");
		double peRatio;
		try {
			peRatio = service.calculatePERatio("", 2);
			logger.info("The PERatio value : "+peRatio);
			Assert.assertTrue(peRatio > 0.0);
		} catch (Exception e) {
			Assert.assertEquals("The stock symbol is not supported.", e.getMessage());
		}
		
		logger.info("Finish calculatePERatioWithEmptyStockSymbol Test ...OK");
	}
	
	@Test
	public void calculatePERatioWithPriceZero()  {
		
		logger.info("Start  calculatePERatioWithPriceZero Test ...");
		double peRatio;
		try {
			peRatio = service.calculatePERatio("GIN", 0);
			logger.info("The PERatio value : "+peRatio);
			Assert.assertTrue(peRatio > 0.0);
		} catch (Exception e) {
			Assert.assertEquals("The price for the stock should not be zero", e.getMessage());
		}
		
		logger.info("Finish calculatePERatioWithPriceZero Test ...OK");
	}
	
	@Test
	public void calculatePERatio()  {
		
		logger.info("Start  calculatePERatio Test ...");
		double peRatio;
		try {
			peRatio = service.calculatePERatio("GIN", 2);
			logger.info("The PERatio value : "+peRatio);
			Assert.assertTrue(peRatio > 0.0);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.assertTrue(false);
		}
		
		logger.info("Finish calculatePERatio Test ...OK");
	}
	
	@Test
	public void recordTrades()  {
		
		logger.info("Start  recordTrades Test ...");
		try {
			service = new SimpleStockServiceImpl();
			boolean result = service.recordTrade("GIN", 2, "BUY",50);
			Assert.assertTrue(result);
			result = service.recordTrade("GIN", 5, "SELL",40);
			Assert.assertTrue(result);
			Assert.assertEquals(2, service.getAllTrades().size());
			if(result){
				logger.info("The Trades recorded successfully");
			}
		} catch (Exception e) {
			e.printStackTrace();
			Assert.assertTrue(false);
		}
		
		logger.info("Finish recordTrades Test ...OK");
	}
	
	
	@Test
	public void calculateVolumeWeightedStockPrice()  {
		
		logger.info("Start  calculateVolumeWeightedStockPrice Test ...");
		try {
			service = SimpleStockServiceImpl.getInstance();
			boolean result = service.recordTrade("GIN", 2, "BUY",50);
			Assert.assertTrue(result);
			result = service.recordTrade("GIN", 5, "SELL",40);
			Assert.assertTrue(result);
			Assert.assertEquals(2, service.getAllTrades().size());
			double value = service.calculateVolumeWeightedStockPrice("GIN");
			Assert.assertEquals(42.77, value,0.10);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.assertTrue(false);
		}
		
		logger.info("Finish calculateVolumeWeightedStockPrice Test ...OK");
	}
	
	@Test
	public void calculateVolumeWeightedStockPriceWithoutTrades()  {
		
		logger.info("Start  calculateVolumeWeightedStockPriceWithoutTrades Test ...");
		try {
			service = new SimpleStockServiceImpl();
			double value = service.calculateVolumeWeightedStockPrice("GIN");
			Assert.assertEquals(0, value,0);
		} catch (Exception e) {
			Assert.assertEquals("Volume Weighted Stock Price: No trades available", e.getMessage());
		}
		
		logger.info("Finish calculateVolumeWeightedStockPriceWithoutTrades Test ...OK");
	}
	
	@Test
	public void calculateGBCEwithoutTrades()  {
		
		logger.info("Start  calculateGBCEwithoutTrades Test ...");
		try {
			service = new SimpleStockServiceImpl();
			double value = service.calculateGBCE();
			Assert.assertEquals(0, value,0);
		} catch (Exception e) {
			Assert.assertEquals("Unable to calculate GBCE: No trades available", e.getMessage());
		}
		
		logger.info("Finish calculateGBCEwithoutTrades Test ...OK");
	}
	
	@Test
	public void calculateGBCE()  {
		
		logger.info("Start  calculateGBCE Test ...");
		try {
			service = SimpleStockServiceImpl.getInstance();
			/*boolean result = service.recordTrade("GIN", 2, "BUY",50);
			Assert.assertTrue(result);
			result = service.recordTrade("GIN", 5, "SELL",40);
			Assert.assertTrue(result);*/
			Assert.assertEquals(2, service.getAllTrades().size());
			double value = service.calculateGBCE();
			Assert.assertEquals(44.72, value,0.10);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.assertTrue(false);
		}
		
		logger.info("Finish calculateGBCE Test ...OK");
	}

}
