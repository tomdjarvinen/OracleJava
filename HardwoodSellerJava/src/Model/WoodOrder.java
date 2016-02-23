//Class defining the elements of each individual item in an order
package model;

import java.util.List;

public class WoodOrder {
	String type;
	int boardFeet;
	double price;
	double deliveryTime;
	//default constructor
	public WoodOrder()
	{
		this.type="Empty";
		this.boardFeet = 0;
		this.price= 0;
		this.deliveryTime = 0;
	}
	/**
	 * Constructor of a WoodOrder element with parameters
	 * @param type Name of the wood type
	 * @param boardFeet amount of wood ordered
	 */
	public WoodOrder(String type, int boardFeet)
	{
		this.type= type;
		this.boardFeet = boardFeet;
		this.price= 0;
		this.deliveryTime = 0;
	}
	/**
	 * Constructor of a WoodOrder element with parameters
	 * @param type Name of the wood type
	 * @param boardFeet amount of wood ordered
	 * @param price price of order
	 * @param deliveryTime estimated delivery time of order
	 */
	public WoodOrder(String type, int boardFeet, double price, double deliveryTime)
	{
		this.type= type;
		this.boardFeet = boardFeet;
		this.price= price;
		this.deliveryTime = deliveryTime;
	}	
	public String getType()
	{
		return type;
	}
	public int getFeet()
	{
		return boardFeet;
	}
	public double getPrice()
	{
		return price;
	}
	public double getTime()
	{
		return deliveryTime;
	}
	public void setPrice(double priceIn)
	{
		price = priceIn;
	}
	public void setTime(double time)
	{
		deliveryTime = time;
	}
}
