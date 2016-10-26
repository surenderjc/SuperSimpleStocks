/**
 * 
 */
package com.gbcorp.simple.stock.data;

import java.util.ArrayList;
import java.util.HashMap;

import com.gbcorp.simple.stock.beans.Stock;
import com.gbcorp.simple.stock.beans.Trade;
import com.gbcorp.simple.stock.service.impl.SimpleStockServiceImpl;

/**
 * @author user
 *
 */
public class StocksData {
	
	private static StocksData instance = null;

	  public static StocksData getInstance() {
	    if (instance == null) {
	      instance = new StocksData();
	    }
	    return instance;
	  }

	private HashMap<String, Stock> stocks = null;
	
	/**
	 * 
	 */
	public StocksData() {
		stocks = new HashMap<String, Stock>();
		Stock stock1 = new Stock("TEA", "COMMON", Double.parseDouble("0"),
				Double.parseDouble("0"), Double.parseDouble("100"));
		Stock stock2 = new Stock("POP", "COMMON", Double.parseDouble("8"),
				Double.parseDouble("0"), Double.parseDouble("100"));
		Stock stock3 = new Stock("ALE", "COMMON", Double.parseDouble("23"),
				Double.parseDouble("0"), Double.parseDouble("60"));
		Stock stock4 = new Stock("GIN", "Preferred", Double.parseDouble("8"),
				Double.parseDouble("2"), Double.parseDouble("100"));
		Stock stock5 = new Stock("JOE", "COMMON", Double.parseDouble("13"),
				Double.parseDouble("0"), Double.parseDouble("250"));
		
		stocks.put("TEA", stock1);
		stocks.put("POP", stock2);
		stocks.put("ALE", stock3);
		stocks.put("GIN", stock4);
		stocks.put("JOE", stock5);
	}
	/**
	 * @return the stocks
	 */
	public HashMap<String, Stock> getStocks() {
		return stocks;
	}
	/**
	 * @param stocks the stocks to set
	 */
	public void setStocks(HashMap<String, Stock> stocks) {
		this.stocks = stocks;
	}
	
	
	/*
	 * 
	 */
	public Stock getStockBySymbol(String stockSymbol){
		Stock stock = null;
		try{
			if(stockSymbol!=null){
				stock = stocks.get(stockSymbol);
			}
		}catch(Exception exception){
			//logger.error("An error has occurred recovering the stock object for the stock symbol: "+stockSymbol+".", exception);
		}
		return stock;
	}


}
