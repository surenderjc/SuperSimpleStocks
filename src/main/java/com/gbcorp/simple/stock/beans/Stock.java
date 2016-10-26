/*Stock pojo*
 * 
 */
package com.gbcorp.simple.stock.beans;


import com.gbcorp.simple.stock.constants.SimpleStockConstants;

/**
 * @author user
 *
 */
public class Stock {
	
	private String stockSymbol = null;
	private String stockType = SimpleStockConstants.COMMON;
	private double lastDividend = 0.0;
	private double fixedDividend = 0.0;
	private double parValue = 0.0;
	
	
	public Stock(String stockSymbol, String stockType, double lastDividend, double fixedDividend,
			double parValue) {
		this.stockSymbol = stockSymbol;
		this.stockType = stockType;
		this.lastDividend = lastDividend;
		this.fixedDividend = fixedDividend;
		this.parValue = parValue;
	}
	
	/**
	 * @return the stockSymbol
	 */
	public String getStockSymbol() {
		return stockSymbol;
	}
	/**
	 * @param stockSymbol the stockSymbol to set
	 */
	public void setStockSymbol(String stockSymbol) {
		this.stockSymbol = stockSymbol;
	}
	/**
	 * @return the stockType
	 */
	public String getStockType() {
		return stockType;
	}
	/**
	 * @param stockType the stockType to set
	 */
	public void setStockType(String stockType) {
		this.stockType = stockType;
	}
	/**
	 * @return the lastDividend
	 */
	public double getLastDividend() {
		return lastDividend;
	}
	/**
	 * @param lastDividend the lastDividend to set
	 */
	public void setLastDividend(double lastDividend) {
		this.lastDividend = lastDividend;
	}
	/**
	 * @return the fixedDividend
	 */
	public double getFixedDividend() {
		return fixedDividend;
	}
	/**
	 * @param fixedDividend the fixedDividend to set
	 */
	public void setFixedDividend(double fixedDividend) {
		this.fixedDividend = fixedDividend;
	}
	/**
	 * @return the parValue
	 */
	public double getParValue() {
		return parValue;
	}
	/**
	 * @param parValue the parValue to set
	 */
	public void setParValue(double parValue) {
		this.parValue = parValue;
	}
	/**
	 * @return the tickerPrice
	 */
	
	
	

}
