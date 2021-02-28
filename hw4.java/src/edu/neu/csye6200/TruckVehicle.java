/**
 * 
 */
package edu.neu.csye6200;

/**
 * @author yiyizhou
 *
 */
public class TruckVehicle extends Vehicle {
	/**
	 * 
	 */
	double height;
	double width;
	double cargoLength;
	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	public double getWidth() {
		return width;
	}

	public void setWidth(double width) {
		this.width = width;
	}

	public double getCargoLength() {
		return cargoLength;
	}

	public void setCargoLength(double cargoLength) {
		this.cargoLength = cargoLength;
	}


	/**
	 * @param passengers
	 * @param fuelCap
	 * @param kpl
	 * @param brand
	 * @param model
	 * @param year
	 * @param license
	 * @param height
	 * @param width
	 * @param cargoLength
	 */
	public TruckVehicle(int passengers, int fuelCap, double kpl, String brand, 
			String model, int year, String license, double height, double width, double cargoLength) {
		super(passengers, fuelCap, kpl, brand, model, year, license);
		this.height = height;
		this.width = width;
		this.cargoLength = cargoLength;
	}
	
	/**
	 * calculates the cargo area
	 */
	public double calculateCargoArea() {
		return width * cargoLength;
	}
	
	public String toFormattedString() {
		String res = super.toFormattedString();
		res += String.format("Truck height: %2.2f\tWidth: %2.2f\tCargo Length: %2.2f\nCargo Area: %2.2f\n",
				getHeight(), getWidth(), getCargoLength(),calculateCargoArea());
		return res;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
	    TruckVehicle truck = new TruckVehicle(5, 30, 36, "Chevrolet", "Colorado", 2009, "TEE 990", 10.0, 5.0, 20.0);
    	System.out.println(truck.toFormattedString());

	}

}
