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
		
		try {
			readInputFile("testOrder.txt");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static void readInputFile(String inputFilePath) throws FileNotFoundException{

		Scanner input = new Scanner(new File(inputFilePath)).useDelimiter(";");
		String name = input.next();
		String address = input.next();
		String date = input.nextLine();
	
		ArrayList<WoodOrder> orders = new ArrayList<WoodOrder>();
		while(input.hasNext())
		{
			input.useDelimiter("\\p{Punct}");
			orders.add(new WoodOrder(input.next(), input.nextInt()));
		}
		double delivery = deliveryTime(orders);
		System.out.print(delivery);
	}
	//returns estimated delivery time for a set of items to be ordered.
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
	public static int matchType(WoodOrder order)
	{
		for(int i=0; i < woodTypes.size(); i++)
		{
			System.out.println(order.getType() + ":"+ woodTypes.get(i).getType());
			if(order.getType().equalsIgnoreCase(woodTypes.get(i).getType()));
			{
				return i;
			}	
		}
		return -1;
	}
	
}
