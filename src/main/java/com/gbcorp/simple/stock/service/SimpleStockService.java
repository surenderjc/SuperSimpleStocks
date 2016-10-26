/**
 * 
 */
package com.gbcorp.simple.stock.service;

/**
 * @author user
 *
 */
public interface SimpleStockService {
	
	public double calculateDividendYield(String stockSymbol, double price) throws Exception;

	public double calculatePERatio(String stockSymbol, double price) throws Exception;
	
	public  boolean recordTrade(String stockSymbol, int quantity, String type, double price) throws Exception;
	
	public double calculateVolumeWeightedStockPrice(String stockSymbol) throws Exception;
	
	public double calculateGBCE() throws Exception;

}
