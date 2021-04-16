/**
 * 
 */
package edu.neu.csye6200.bg;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Line2D;
import java.util.ArrayList;

/** 
 * BGRule: process input BGGenerations and produce output generations
 * @author Yiyi Zhou
 */
public class BGRule {
	
	public ArrayList<BGStem> stemList = new ArrayList<>(); // To store all stems	
	private Double changedRadiant;
	private Integer rule;
	private double height = 0.0;

	public BGRule(Double changedRadiant, Integer rule) {
		this.changedRadiant = changedRadiant;	
		this.rule = rule;
	}
	
	public double getHeight() {
		return height;
	}


	public void setHeight(double height) {
		this.height = height;
	}

	
	public Double getRadiant() {
		return changedRadiant;
	}

	
	public void setRadiant(Double radians) {
		this.changedRadiant = radians;
	}

	
	public ArrayList<BGStem> getStemList() {
		return stemList;
	}

	
	public void setStemList(ArrayList<BGStem> stemList) {
		this.stemList = stemList;
	}

	
	public Integer getRule() {
		return rule;
	}

	
	public void setRule(Integer num) {
		this.rule = num;
	}
	
	
	
	/**
	 * recursively add child stems 
	 * @param stem the parent stem on which child stems grow
	 */
	public void grow(BGStem stem, int rule) {		
		setRule(rule);
		for (BGStem s: stem.getChildlist()) {			
			if (s.getChildlist().isEmpty())   //if leaf node, add to child stem
				addChildStem(s);
			else {
				grow(s, rule);
				}
				
		}
	}
	/**
	 * Add all child stems to stemList
	 * @param stem the parent stem on which child stems grow
	 */
	public void addChildStem(BGStem stem) {
		ArrayList<BGStem> currentChildrenStems = growByRule(stem).getChildlist();
		for (BGStem s: currentChildrenStems) {
			stemList.add(s);
			setHeight(Math.max(s.getY(), getHeight()));
		}
	}
	
	/**
	 * Add child stems that originate at parent stems' tip in terms of relative angles and lengths
	 * @param stem the parent stem on which child stems grow
	 * @return stem the parent stem on which child stems grow 
	 */
	public BGStem growByRule(BGStem stem) {
		
		BGStem leftStem = stem.getLeftChild(changedRadiant);
		BGStem rightStem = stem.getRightChild(changedRadiant);
		BGStem middleStem= stem.getMiddleChild(changedRadiant);


		// according to the rule grow and add to stem list
		switch(rule) {
		
		// grow 2 branches		
		case 1:	
			stem.addNewChild(leftStem);
			stem.addNewChild(rightStem);
			System.out.println(leftStem.toString());
			System.out.println(rightStem.toString());
			break;
			
		// grow 3 branches		
		case 2:			
			stem.addNewChild(middleStem);
			stem.addNewChild(leftStem);
			stem.addNewChild(rightStem);
			System.out.println(leftStem.toString());
			System.out.println(middleStem.toString());
			System.out.println(rightStem.toString());			
			break;
		}
		
		return stem;
	}
	

	
}
