
package edu.neu.csye6200.bg;

import java.util.ArrayList;

/**
 * BGStem 
 * @author Yiyi Zhou
 */
public class BGStem {
	private BGStem parent;
	private Double x;
	private Double y;
	private Double length;
	private Double radiant;
	private Integer currentGeneration; 
	private static int idCounter = 0; // Unique id
	private int id;
	private double scale = 0.88;
	private Integer rule;    // growth rule
		
	// Store child stems
	private ArrayList<BGStem> childlist = new ArrayList<BGStem>();
	
	
	/**
	 * Constructor
	 * @param x start x
	 * @param y start y
	 * @param length
	 * @param direction
	 * @param depth
	 */
	BGStem (Double x, Double y, Double length, Double radiant, Integer currentGeneration, Integer rule) {
		this.x = x;
		this.y = y;
		this.length = length;
		this.radiant = radiant;
		this.currentGeneration = currentGeneration;
		this.rule = rule;
		
		this.id = idCounter++;
	}
	
	
	public double getAbsoluteAngle() {
		return (parent ==null) ? radiant: parent.getAbsoluteAngle() + radiant;
	}
	public ArrayList <Double> calcEndCoordinate() {
		double groundDirection = getAbsoluteAngle();
		double xLen = length * Math.cos(groundDirection);
		double yLen = length * Math.sin(groundDirection);
		ArrayList <Double> newCoordinate = new ArrayList<Double>();
		newCoordinate.add(x+xLen);
		newCoordinate.add(y+yLen);
		return newCoordinate;
	}
	public BGStem getLeftChild(double changedRadiant) {
		ArrayList <Double> endNodes = calcEndCoordinate();
		BGStem leftStem = new BGStem(endNodes.get(0), endNodes.get(1), getLength()*scale, getRadiant()- changedRadiant, getCurrentGeneration() + 1,rule);
		return leftStem;
	}
	
	public BGStem getRightChild(double changedRadiant){
		ArrayList <Double> endNodes = calcEndCoordinate();
		BGStem rightStem = new BGStem(endNodes.get(0), endNodes.get(1), getLength()*scale, getRadiant()+ changedRadiant, getCurrentGeneration() + 1,rule);
		return rightStem;
	}

	public BGStem getMiddleChild(double changedRadiant) {
		ArrayList <Double> endNodes = calcEndCoordinate();
		BGStem middleStem= new BGStem(endNodes.get(0), endNodes.get(1), getLength()*scale, getRadiant(), getCurrentGeneration() + 1,rule);
		return middleStem;
	}
	public Double getX() {
		return x;
	}

	public void setX(Double x) {
		this.x = x;
	}

	public Double getY() {
		return y;
	}

	public void setY(Double y) {
		this.y = y;
	}

	public Double getLength() {
		return length;
	}

	public void setLength(Double length) {
		this.length = length;
	}

	public Double getRadiant() {
		return radiant;
	}

	public void setRadiant(Double radiant) {
		this.radiant = radiant;
	}
	
	public Integer getCurrentGeneration() {
		return currentGeneration;
	}

	public void setCurrentGeneration(Integer currentGeneration) {
		this.currentGeneration = currentGeneration;
	}
	
	
	public Integer getRule() {
		return rule;
	}

	public void setRule(Integer rule) {
		this.rule = rule;
	}

	public ArrayList<BGStem> getChildlist() {
		return childlist;
	}

	public void setChildlist(ArrayList<BGStem> childlist) {
		this.childlist = childlist;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public BGStem getParent() {
		return parent;
	}

	public void setParent(BGStem parent) {
		this.parent = parent;
	}
	
	public void addNewChild(BGStem stem) {
		childlist.add(stem);
	}
	public String toString() {
		return String.format("stem id: %1$-16d x coordinate: %2$-16.2f y coordinate: %3$-16.2f length: %4$-16.2f radiant: %5$-16.2f", this.id, this.x,
				this.y, this.length, this.radiant);
	}
}
