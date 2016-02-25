/**
 * 
 */
package controller;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import model.*;

/**
 * @author Esteban
 *
 */
public class HardwoodSeller {

	//list holds delivery times and prices for all wood types sold
	public static List<WoodItem> woodTypes;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		woodTypes = new ArrayList<WoodItem>();
		woodTypes.add(new WoodItem("Cherry", 2.5, 5.95));
		woodTypes.add(new WoodItem("Curly Maple", 1.5, 6.00));
		woodTypes.add(new WoodItem("Genuine Mahogany", 3.0, 9.60));
		woodTypes.add(new WoodItem("Wenge", 5., 22.35));
		woodTypes.add(new WoodItem("White Oak", 2.3, 6.70));
		woodTypes.add(new WoodItem("Sawdust", 1., 1.5));
		System.out.print("Enter a file path: ");
		Scanner a = new Scanner(System.in);
		String fileName = a.nextLine();
		try {
			readInputFile(fileName);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	//processes order found in inputFilePath
	public static void readInputFile(String inputFilePath) throws FileNotFoundException{
//needs error detection for type mismatch
		Scanner input = new Scanner(new File(inputFilePath)).useDelimiter(";");
		String name = input.next();
		String address = input.next();
		//need to advance past the semicolon
		input.useDelimiter("\\p{Alnum}");
		input.next();
		String date = input.nextLine();
	
		ArrayList<WoodOrder> orders = new ArrayList<WoodOrder>();
		input.useDelimiter("\\p{Punct}");
		double priceTotal = 0;
		while(input.hasNext())
		{
			orders.add(new WoodOrder(input.next(), input.nextInt()));
			//sets price of most recent order using rate from WoodOrder
			orders.get(orders.size()-1).setPrice(woodTypes.get(matchType(orders.get(orders.size()-1))).getPrice());
			priceTotal += orders.get(orders.size()-1).getPrice();
		}
		double delivery = deliveryTime(orders);
		System.out.printf("Name: " + name + "\nDelivery address: " + address
				+ "\nDate: " + date + "\nEstimated delivery time: " + delivery + " hours\nTotal price: $%.2f\n", priceTotal);
		for(int i = 0; i<orders.size(); i++)
		{
			WoodOrder temp = orders.get(i);
			System.out.printf("Item " + (i+1) + ": " +temp.getType() + ";" + temp.getFeet() + " BF;$%.2f\n", orders.get(i).getPrice());
		}
	}
	//returns estimated delivery time for an order.
	public static Double deliveryTime(ArrayList<WoodOrder> items){
		Double deliveryETA = 0.0;
		for(int i = 0; i < items.size();i++ )
		{
			if(items.get(i).getFeet() >=500)
			{
				deliveryETA = Math.max(deliveryETA, 5.5*
						woodTypes.get(matchType(items.get(i))).getBaseDeliveryTime());
			}
			else
			{
				deliveryETA = Math.max(deliveryETA, 
						(1+items.get(i).getFeet()/100)*woodTypes.get(matchType(items.get(i))).getBaseDeliveryTime());
			}
		}
		return deliveryETA;
	}
	//returns the index of woodTypes that shares a type with parameter order.
	public static int matchType(WoodOrder order)
	{
		for(int i=0; i < woodTypes.size(); i++)
		{
			if(order.getType().equalsIgnoreCase(woodTypes.get(i).getType()))
			{
				return i;
			}	
		}
		return -1;
	}
	
}
