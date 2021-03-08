/**
 * 
 */
package edu.neu.csye6200.agency;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author yiyizhou
 *
 */
public class RentalAgencyIO {
	private final static Logger LOGGER = Logger.getLogger("RentalAgencyLogger");
	public RentalAgencyIO() {}
	
	
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
/**
 *  writing/storing a single Vehicle to an open file
 * @param v Vehicle
 * @param f open File
 * @throws IOException 
 */
	private void saveVehicle(Vehicle v, FileWriter fw) throws IOException {
		try{
			fw.write(v.getBrand() + ",");
			fw.write(v.getFuelCap() + ",");
			fw.write(v.getLicense() + ",");
			fw.write(v.getModel() + ",");
			fw.write(v.getPassengers() + ",");
			fw.write(v.getYear() + ",");
			fw.write(v.getKpl() + ",");
			fw.write('\n');
			LOGGER.info("one car is saved");

		}catch(FileNotFoundException e) {
			e.printStackTrace();
			LOGGER.log(Level.SEVERE, e.getMessage(),e);
		}
	}
	
	private void saveTruck(TruckVehicle v, FileWriter fw) throws IOException {
		try{
			fw.write(v.getBrand() + ",");
			fw.write(v.getFuelCap() + ",");
			fw.write(v.getLicense() + ",");
			fw.write(v.getModel() + ",");
			fw.write(v.getPassengers() + ",");
			fw.write(v.getYear() + ",");
			fw.write(v.getKpl() + ",");
			fw.write(v.getHeight() + ",");
			fw.write(v.getWidth() + ",");
			fw.write(v.getCargoLength()+",");
			fw.write('\n');
			LOGGER.info("one truck is saved");

		}catch(FileNotFoundException e) {
			e.printStackTrace();
			LOGGER.log(Level.SEVERE, e.getMessage(),e);

		}
	}
	//helper method: load single vehicle from string
	private Vehicle loadVehicle(String s){
		Vehicle v;
		String[] arr = s.split(",");
			String brand = (arr[0]);
			int fuelCap = (Integer.parseInt(arr[1]));
			String license = arr[2];
			String model = arr[3];
			int passengers = Integer.parseInt(arr[4]);
			int year = Integer.parseInt(arr[5]);
			double kpl = Double.parseDouble(arr[6]);
		//if a truck, return TruckVehicle
		if (arr.length>7) {
			double height = Double.parseDouble(arr[7]);
			double width = Double.parseDouble(arr[8]);
			double cargoLength = Double.parseDouble(arr[9]);
			v = new TruckVehicle(passengers,  fuelCap,  kpl,  brand, 
			 model,  year,  license,  height,  width,  cargoLength);
		}else {
		    v = new Vehicle(passengers, fuelCap, kpl,  brand,  model,  year,  license);
		}
		LOGGER.info("one car is loaded");
		return v;
	}
	
//	save all Vehicle from a list to disk
public void save(ArrayList<Vehicle> vList, String filename) throws IOException{
		
		try {
			FileWriter fw = new FileWriter(filename);
			File f = new File(filename);
			for(Vehicle v:vList) {
				if(v.getClass().getName() == "edu.neu.csye6200.TruckVehicle") {
					saveTruck((TruckVehicle) v,fw);
				}else {
					saveVehicle(v,fw);
				}
			}
			fw.close();
			System.out.println("File written successfully");
			} catch (FileNotFoundException e) {
			e.printStackTrace();
			LOGGER.log(Level.SEVERE, e.getMessage(),e);
		}
	}

//load all vehicle/truck from file
public ArrayList<Vehicle> load(String filename) throws IOException{
	  String curr;
	  ArrayList<Vehicle> res = new ArrayList<>();
	try {
		FileReader fr = new FileReader(filename);
	    BufferedReader br = new BufferedReader(fr);
		System.out.println("file found, start reading lines");
	    while((curr = br.readLine())!=null ) {
	    	Vehicle v = loadVehicle(curr);
	    	System.out.println(v.toFormattedString());
	    	res.add(v);
	    }
	    fr.close();
	    br.close();
	} catch (FileNotFoundException e) {
		e.printStackTrace();
		LOGGER.log(Level.SEVERE, e.getMessage(),e);
	}
	return res;
}

public void test() throws IOException {
	Vehicle minivan = new Vehicle(4, 15, 20, "Nissan", "Frontier", 2015, "BEE 234");
    Vehicle sportscar = new Vehicle(2, 20, 18, "Ford", "Mustang", 2011, "ABV 789");
    TruckVehicle truck = new TruckVehicle(5, 30, 36, "Chevrolet", "Colorado", 2009, "TEE 990", 10, 5, 20);
    ArrayList<Vehicle> list = new ArrayList<>(Arrays.asList(minivan, sportscar, truck));	
	save(list, "src/edu/neu/csye6200/io/car.txt");
	load("src/edu/neu/csye6200/io/car.txt");
}


	public static void main(String[] args) {
		RentalAgencyIO io = new RentalAgencyIO();
		try{
			io.test();
			logInfo();
		}catch(IOException e) {
			e.printStackTrace();
			LOGGER.log(Level.SEVERE, e.getMessage(),e);
		}
	}

}
