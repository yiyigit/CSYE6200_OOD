
package edu.neu.csye6200.agency;
import java.util.Arrays;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.HashMap;
/*
* @author:Yiyi Zhou
* ID: 001051305
*
*/
public class VehicleRegistry {
	private ArrayList<Vehicle> arr = new ArrayList<Vehicle>();
	private HashMap<String, Vehicle> map = new HashMap<String, Vehicle>();
	private VehicleRegistry instance = null;
	private final static Logger LOGGER = Logger.getLogger("VehicleRegistryLogger");
	
	//Log information messages and send your log messages to disk
		private static void logInfo(){
			try {
			FileHandler fileHandler = new FileHandler("src/edu/neu/csye6200/agency/logInfo.txt", true);
			LOGGER.addHandler(fileHandler);
			}catch(IOException e) {
				LOGGER.log(Level.SEVERE, e.getMessage(),e);
			}catch(SecurityException se) {
				LOGGER.log(Level.SEVERE, se.getMessage(),se);
			}
		}
	//Singleton pattern
	private VehicleRegistry() {}
	public VehicleRegistry getInstance() {
		if(instance == null) {
			instance = new VehicleRegistry();
		}
		return instance;
	}

	
	//Add Vehicle into ArrayList and HashMap
	public void add(Vehicle v) {
		arr.add(v);
		String key = v.getLicense();
		map.put(key, v);
		LOGGER.info("added car");
	}
	
	//Remove Vehicle from ArrayList and HashMap
	public void remove(Vehicle v) {
		arr.remove(v);
		String key = v.getLicense();
		map.remove(key);
		LOGGER.info("car removed");
	}
	
	//Get Vehicle from ArrayList through index
	public Vehicle get(int i) {
		return arr.get(i);
	}
	
	// Get vehicle by license string
	public Vehicle getByLicense(String s) {
		Vehicle v = null;
		if (map.containsKey(s)){
		 v = map.get(s);
		 LOGGER.info("Get car by license:" + s);
		}else {
		LOGGER.info("Cannot get car by license:" + s );
		}
		return v;
	}
	
	// Remove vehicle by license string
	public void removeByLicense(String s) {
		if (map.containsKey(s)){
			Vehicle v = map.get(s);
			map.remove(s);
			arr.remove(v);
			LOGGER.info("Removed the car by license:" + s);
			}else {
			LOGGER.info("This license doesn't relate to a car in registry:" + s );
			}
	}
	private int compareString(String first, String second) {
		for(int i=0; i<first.length();i++) {
			if(first.charAt(i)<second.charAt(i)) {
				return -1;
			}else if(first.charAt(i)>second.charAt(i)) {
				return 1;
			}
		}
		return 0;
	}
	public void sortByLicense() {
		ArrayList<String> licenseList = new ArrayList<>();
		for(Vehicle v:arr) {
			String l = v.getLicense();
			licenseList.add(l);
		}
		quickSort(licenseList,0,licenseList.size()-1);
		
		for(int i=0; i<licenseList.size();i++) {
			Vehicle v = getByLicense(licenseList.get(i));
			arr.set(i, v);
		}
		LOGGER.info("Sorted by license");
		LOGGER.info("Licenses: " + Arrays.toString(licenseList.toArray()));
	}
	private void quickSort(ArrayList<String> list, int start, int end) {
		if (start >= end) return;
		int mid = start + (end - start) /2;
		String pivot = list.get(mid);
		int left = start, right = end;
		while(left <=right) {
			while(left <= right && compareString(list.get(left),pivot) <0) {
				left +=1;
			}
			while(left<= right && compareString(list.get(right),pivot) >0) {
				right -=1;
			}
			if(left <= right) {
				String temp = list.get(left);
				list.set(left, list.get(right));
				list.set(right, temp);
				left +=1;
				right -=1;
			}
		}
		if(start < right) {
			quickSort(list,start,right);
		}
		if(end > left) {
			quickSort(list,left,end);	
		}
	}
	//Loop through the ArrayList and print each Vehicle's information
	public void getAll() {
		for(int i=0; i<arr.size();i++) {
			Vehicle v = arr.get(i);
			System.out.println("Number " + (i+1) + "\n" + v.toFormattedString());
		}
	}
	
	//Test codes
	public void run() {
		
	    Vehicle v1 = new Vehicle(3,17, 25, "Toyota","Jupiter",2016,"YUU 157");
	    Vehicle v2 = new Vehicle(4,14,30,"BMW","X5",2020,"UYN 908");
	    Vehicle v3 = new Vehicle(3,19,36,"Audi","A4",2012,"NKK 745");
	    Vehicle v4 = new Vehicle(4,18,41,"Chevrolet","Earth",2014,"EFB 146");
	    Vehicle v5 = new Vehicle(4,20,39,"Dodge","Challenger",2016,"BHE 462");
	    Vehicle v6 = new Vehicle(2,30,48,"Buick","Envision",2019,"PEB 936");
	    Vehicle v7 = new Vehicle(2,35,40,"Bentley","Continental",2017,"TCL 051");
	    Vehicle minivan = new Vehicle(4, 15, 20, "Nissan", "Frontier", 2015, "BEE 234");
	    Vehicle sportscar = new Vehicle(2, 20, 18, "Ford", "Mustang", 2011, "ABV 789");
	    Vehicle suv = new Vehicle(5, 30, 29, "BMW","M50i", 2021, "QUF 567");
	    
	    add(v1);
	    add(v2);
	    add(v3);
	    add(v4);
	    add(v5);
	    add(v6);
	    add(v7);
	    add(minivan);
	    add(sportscar);
	    add(suv);
	    
	    getAll();
	    sortByLicense();
	    getAll();
	    
//	    remove(suv);
//	    getByLicense("BEE 234");
//	    getByLicense("QUF 567");
	}

	
	/**
	 * @param args
	 */
	public static void main(String[] args){
		VehicleRegistry vr = new VehicleRegistry();
		LOGGER.info("This is Vehicle Registry information");
		vr.run();
		logInfo();

	}

}
