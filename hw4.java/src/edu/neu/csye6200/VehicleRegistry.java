
package edu.neu.csye6200;

import java.util.ArrayList;
import java.util.HashMap;
/*
* @author:Yiyi Zhou
* ID: 001051305
*
*/
public class VehicleRegistry {
	private ArrayList<Vehicle> arr = new ArrayList<Vehicle>();
	private HashMap<String, Vehicle> map = new HashMap<String, Vehicle>();
	/*
	 * Add Vehicle into ArrayList and HashMap
	 */
	public void add(Vehicle v) {
		arr.add(v);
		String key = v.getLicense();
		map.put(key, v);
	}
	/*
	 * Remove Vehicle from ArrayList and HashMap
	 */
	public void remove(Vehicle v) {
		arr.remove(v);
		String key = v.getLicense();
		map.remove(key);
	}
	
	/*
	 * Get Vehicle from ArrayList through index
	 */
	public Vehicle get(int i) {
		return arr.get(i);
	}
	
	/*
	 * Get Vehicle from HashMap through license String
	 */
	public void getByLicense(String s) {
		if (map.containsKey(s)){
		Vehicle v = map.get(s);
		System.out.println("Get car by license:" + s + "\n"+ v.toFormattedString());
		}else {
		System.out.println("Cannot Get car by license:" + s );
		}
	}
	
	/*
	 * Loop through the ArrayList and print each Vehicle's information
	 */
	public void getAll() {
		for(Vehicle v: arr) {
			System.out.println(v.toFormattedString());
		}
	}
	
	//Test codes
	public void run() {
		Vehicle minivan = new Vehicle(4, 15, 20, "Nissan", "Frontier", 2015, "BEE 234");
	    Vehicle sportscar = new Vehicle(2, 20, 18, "Ford", "Mustang", 2011, "ABV 789");
	    Vehicle suv = new Vehicle(5, 30, 29, "BMW","M50i", 2021, "QUF 567");
	    add(minivan);
	    add(sportscar);
	    add(suv);
	    getAll();
	    remove(suv);
	    getByLicense("BEE 234");
	    getByLicense("QUF 567");
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		VehicleRegistry vr = new VehicleRegistry();
		vr.run();

	}

}
