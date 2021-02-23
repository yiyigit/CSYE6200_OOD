package edu.neu.csye6200;
/**
 * A special class used to test the Vehicle class
 * @author Yiyi Zhou
 * @NUID: 001051305
 *
 */
public class VehicleTest {
	/**
	 * This is where your program starts
	 * @param args
	 */
	
	public static void run() {
		Vehicle minivan = new Vehicle(4, 15, 20, "Nissan", "Frontier", 2015, "BEE 234");
	    Vehicle sportscar = new Vehicle(2, 20, 18, "Ford", "Mustang", 2011, "ABV 789");
	    Vehicle suv = new Vehicle(5, 30, 29, "BMW","M50i", 2021, "QUF 567");
	    System.out.println("Vehicle information:");
	    System.out.println(minivan.toFormattedString());
	    System.out.println(sportscar.toFormattedString());
	    System.out.println(suv.toFormattedString());
	}
	
	public static void main(String[] args) {
		run();
	}

}
