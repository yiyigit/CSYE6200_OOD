package edu.neu.csye6200;
/*
* @author:Yiyi Zhou
* ID: 001051305
*
*/
public class Vehicle {
	int passengers;  //number of passengers
	int fuelCap;	//Fuel tank size in liters
	double kpl;    //Kilometers per liter
	String brand;  //brand name
	String model;  //brand model
	int year;      //manufactured year
	String license; //License plate string
	
	public String getLicense() {
		return license;
	}

	public void setLicense(String license) {
		this.license = license;
	}

	public int getPassengers() {
		return passengers;
	}

	public void setPassengers(int passengers) {
		this.passengers = passengers;
	}

	public int getFuelCap() {
		return fuelCap;
	}

	public void setFuelCap(int fuelCap) {
		this.fuelCap = fuelCap;
	}

	public double getKpl() {
		return kpl;
	}

	public void setKpl(double kpl) {
		this.kpl = kpl;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	/**
	Vehicle constructor
	default
	*/
	public Vehicle(){
		passengers = 4;
		fuelCap = 20;
		kpl = 30.0;
		brand = "Honda";
		model = "civic";
		year = 2006;
	}
	
	
	
	/**
	Vehicle constructor
	@param passengers the number of passengers
	@param fuelCap fuel tank size in liters
	@param kpl kilometers per liter 
	@param brand the brand name
	@param model the brand model
	@param year the manufactured year
	@param license License plate
	*/
	public Vehicle(int passengers, int fuelCap, double kpl, String brand, String model, int year, String license) {
		super();
		this.passengers = passengers;
		this.fuelCap = fuelCap;
		this.kpl = kpl;
		this.brand = brand;
		this.model = model;
		this.year = year;
		this.license = license;
	}
	
	/**
	 * compute range consuming the full tank of gas
	 */
	public double calculateRange() {
		return fuelCap * kpl;
	}
	
	public String toFormattedString() {
		String res = String.format("Brand: %8s       Model: %8s\tYear: %4d\tNumber of passengers: %2d\nFuel Capacity: %4d\tKPL: %.2f\tRange: %.2f\tLicense: %8s\n " ,
				getBrand(), getModel(), getYear(), getPassengers(), getFuelCap(), getKpl(), calculateRange(), getLicense());
		return (res);
	}
	
	
}
