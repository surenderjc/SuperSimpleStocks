package com.gbcorp.simple.stock.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.gbcorp.simple.stock.beans.Stock;
import com.gbcorp.simple.stock.beans.Trade;
import com.gbcorp.simple.stock.constants.SimpleStockConstants;
import com.gbcorp.simple.stock.data.StocksData;
import com.gbcorp.simple.stock.service.SimpleStockService;

/*
 * 
 */
public class SimpleStockServiceImpl implements SimpleStockService{

	Logger logger = Logger.getLogger(SimpleStockServiceImpl.class);
	
	private static SimpleStockServiceImpl instance = null;

	  public static SimpleStockServiceImpl getInstance() {
	    if (instance == null) {
	      instance = new SimpleStockServiceImpl();
	    }
	    return instance;
	  }
	  
	  private  Map<String, List<Trade>> tradeMap = new HashMap<String, List<Trade>>();
	  /*
	   * This method is calculate the dividend yield
	   * (non-Javadoc)
	   * @see com.gbcorp.simple.stock.service.SimpleStockService#calculateDividendYield(java.lang.String, double)
	   */
	public double calculateDividendYield(String stockSymbol, double price)
			throws Exception {
		logger.info("Start  calculateDividendYield method ...");
		double dividendYield = -1.0;
		Stock stock = getStock(stockSymbol);
		if(stockSymbol==null || "".equalsIgnoreCase(stockSymbol) ){
			throw new Exception("The stock symbol is not supported.");
		}
		
		if(price <= 0.0){
			throw new Exception("The price for the stock should not be zero");
		}
		
		if( stock.getStockType().equalsIgnoreCase(SimpleStockConstants.COMMON)){
			dividendYield = stock.getLastDividend() / price;
		}else{
			
			dividendYield = (stock.getFixedDividend() * stock.getParValue() ) / price;
		}
		logger.info("End of calculateDividendYield method");
		return dividendYield;
		
	}
	
	/*
	 * This method is calculate the P/E Ratio
	 * (non-Javadoc)
	 * @see com.gbcorp.simple.stock.service.SimpleStockService#calculatePERatio(java.lang.String, double)
	 */
	public double calculatePERatio(String stockSymbol, double price)
			throws Exception {
		logger.info("Start calculatePERatio method ...");
		double peRatio = -1.0;
		if(stockSymbol==null){
			throw new Exception("The stock symbol is not supported.");
		}
		
		if(price <= 0.0){
			throw new Exception("The price for the stock should not be zero");
		}
		double dividendYield = calculateDividendYield(stockSymbol,price);
		if(price > 0.0 && dividendYield > 0.0){
			peRatio = price / dividendYield;	
		}
		logger.info("End of calculatePERatio method");
		return peRatio;
	}
	
	/*
	 * This method is record a trade, with timestamp, quantity, buy or sell indicator and price
	 * (non-Javadoc)
	 * @see com.gbcorp.simple.stock.service.SimpleStockService#recordTrade(java.lang.String, int, java.lang.String, double)
	 */
	public boolean recordTrade(String stockSymbol, int quantity, String type,
			double price) throws Exception {
		logger.info("Start recordTrade method ...");
		Stock stock = getStock(stockSymbol);
	    Trade trade = new Trade(stock, Calendar.getInstance().getTime(),
	        quantity, type, price);
	    
	    List<Trade> trades = new ArrayList<Trade>();
	    if (tradeMap.containsKey(trade.getStock().getStockSymbol())) {
	        trades = tradeMap.get(trade.getStock().getStockSymbol());
	    }
	    trades.add(trade);
	    tradeMap.put(trade.getStock().getStockSymbol(), trades);
	    logger.info("Trade Recorded");
	    logger.info("End of recordTrade method");
		return true;
	  }
	
	/*
	 * This method is calculate Volume Weighted Stock Price based on trades in past 5 minutes
	 * (non-Javadoc)
	 * @see com.gbcorp.simple.stock.service.SimpleStockService#calculateVolumeWeightedStockPrice(java.lang.String)
	 */
	public double calculateVolumeWeightedStockPrice(String stockSymbol)
			throws Exception {
		logger.info("Start calculateVolumeWeightedStockPrice method ...");
		double result = 0;
		Stock stock = getStock(stockSymbol);
	    List<Trade> trades = getTradesWithin5Min(stock, 5);
	    if (trades == null || trades.isEmpty()) {
	    	logger.info("Volume Weighted Stock Price: No trades available");
	      throw new Exception("Volume Weighted Stock Price: No trades available");
	    } else {
	    	double sumOfPriceQuantity = 0;
	        int sumOfQuantity = 0;
	        for (Trade trade : trades) {
	            sumOfPriceQuantity = sumOfPriceQuantity + (trade.getPrice() * trade.getQuantity());
	            sumOfQuantity = sumOfQuantity + trade.getQuantity();
	          }
	         result = sumOfPriceQuantity / sumOfQuantity;
	         result = Math.round(result*100)/100D;
	         System.out.println("Volume Weighted Stock Price: " + result);
	        logger.info("Volume Weighted Stock Price: " + result);
	    }
	    logger.info("End of calculateVolumeWeightedStockPrice method");
		return result;
	  }

	/*
	 * This method is calculate the GBCE All Share Index using the geometric
	 * mean of the Volume Weighted Stock Price
	 */
	public double calculateGBCE() throws Exception {
		logger.info("Start calculateGBCE method ...");
	    double totalPrice = 1;
	    double result = 0;
	    List<Trade> trades = getAllTrades();
	    if (trades == null || trades.isEmpty()) {
	    	logger.info("Unable to calculate GBCE: No trades available");
	        throw new Exception("Unable to calculate GBCE: No trades available");
	      } else {
		    for (Trade trade : trades) {
		    	totalPrice = totalPrice * trade.getPrice();
		    }
		    result = Math.pow(totalPrice, (1D / trades.size()));
		    result = Math.round(result*100)/100D;
		    logger.info("GBCE Value: " + result);
	      }
	    logger.info("End of calculateGBCE method");
		return result;
	  }
	
	 public Stock getStock(String stockSymbol) {
		 StocksData stocks = StocksData.getInstance();
			Stock stock = stocks.getStockBySymbol(stockSymbol);
		return stock;
	}

	public List<Trade> getTradesWithin5Min(Stock stock, int minutes) {
		    List<Trade> result = new ArrayList<Trade>();
		    Date beforedate = getDateXMinutesBefore(minutes);
		    List<Trade> trades = tradeMap.get(stock.getStockSymbol());
		    if(trades != null){
			    Iterator<Trade> it = trades.iterator();
			   
			    while (it.hasNext()) {
			      Trade trade = it.next();
			      if (trade.getTimestamp().after(beforedate)) { 
			    	  result.add(trade);;
			      }
			      
			    }
		    }
		    return result;
		  }


	public Date getDateXMinutesBefore(int minutes){
	    Calendar c = Calendar.getInstance();
	    c.add(Calendar.MINUTE, -minutes);
	    return c.getTime();
	  }
	
	
	public List<Trade> getAllTrades() {
	    List<Trade> result = new ArrayList<Trade>();
	    for (String stockSymbol: tradeMap.keySet()) {
	      result.addAll(tradeMap.get(stockSymbol));
	    }
	    return result;
	  }

}
