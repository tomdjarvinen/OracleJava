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
		/*
		try {
			readInputFile("testOrder.txt");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
	}
	
	public static void readInputFile(String inputFilePath) throws FileNotFoundException{

		Scanner input = new Scanner(new File(inputFilePath)).useDelimiter(";");
		String name = input.next();
		String address = input.next();
		String date = input.nextLine();
		System.out.println(name + address + date);
		List<WoodOrder> orders = new ArrayList<WoodOrder>();
		while(input.hasNext())
		{
			input.useDelimiter("\\p{Punct}");
			orders.add(new WoodOrder(input.next(), input.nextInt()));
		}
	}
	
	public Double deliveryTime(){
		Double deliveryETA = 0.0;
		return deliveryETA;
	}
	
}
