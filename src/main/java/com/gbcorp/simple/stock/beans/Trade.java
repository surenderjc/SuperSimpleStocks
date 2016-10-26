/**
 * 
 */
package com.gbcorp.simple.stock.beans;

import java.util.Date;

/**
 * @author user
 *
 */
public class Trade {

	

		  private Stock stock;

		  private Date timestamp;

		  private int quantity;

		  private String type;

		  private double price;

		  public Trade(Stock stock, Date timestamp, int quantity, String type, double price) {
		    super();
		    this.stock = stock;
		    this.timestamp = timestamp;
		    this.quantity = quantity;
		    this.type = type;
		    this.price = price;
		  }

		/**
		 * @return the stock
		 */
		public Stock getStock() {
			return stock;
		}

		/**
		 * @param stock the stock to set
		 */
		public void setStock(Stock stock) {
			this.stock = stock;
		}

		/**
		 * @return the timestamp
		 */
		public Date getTimestamp() {
			return timestamp;
		}

		/**
		 * @param timestamp the timestamp to set
		 */
		public void setTimestamp(Date timestamp) {
			this.timestamp = timestamp;
		}

		/**
		 * @return the quantity
		 */
		public int getQuantity() {
			return quantity;
		}

		/**
		 * @param quantity the quantity to set
		 */
		public void setQuantity(int quantity) {
			this.quantity = quantity;
		}

		/**
		 * @return the type
		 */
		public String getType() {
			return type;
		}

		/**
		 * @param type the type to set
		 */
		public void setType(String type) {
			this.type = type;
		}

		/**
		 * @return the price
		 */
		public double getPrice() {
			return price;
		}

		/**
		 * @param price the price to set
		 */
		public void setPrice(double price) {
			this.price = price;
		}

		  
		  

		

}
