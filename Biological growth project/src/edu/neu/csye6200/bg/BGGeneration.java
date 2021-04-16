package edu.neu.csye6200.bg;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Observable;
import java.util.logging.Logger;

import javax.swing.JFrame;

import edu.neu.csye6200.ui.MyCanvas;

/**
 * BGGeneration: hold a full tree/leaf
 * @author Yiyi Zhou
 */
public class BGGeneration extends Observable {
	
	private BGStem root;
	private Integer maxGeneration;  //number of generations
	private double changedRadiant;
	private Integer rule;
	private BGRule bgRule;	
	private double height;

	private double DEG2RAD = Math.PI / 180.0;
	
 	private Logger log = Logger.getLogger(BGGeneration.class.getName());  // log

	/**
	 * Constructor
	 * @param layer the layer of BGGeneration
	 */
	public BGGeneration(Integer generation, double degree, Integer rule) {
		this.root = new BGStem(0.0, 0.0, 120.0, Math.PI/2, 0, rule);
		this.maxGeneration = generation;
		this.changedRadiant = DEG2RAD*degree;
		this.rule = rule;
		bgRule = new BGRule(changedRadiant, rule);
	}
	
	
	public BGStem getRoot() {
		return root;
	}
	
	public void setRoot(BGStem root) {
		this.root = root;
	}
	public Integer getMaxGeneration() {
		return maxGeneration;
	}

	public void setMaxGeneration(Integer depth) {
		this.maxGeneration = depth;
	}

	public ArrayList<BGStem> growAGeneration(int i){	
		//first generation -- root
		if (i == 0 && i< maxGeneration) {
			bgRule.getStemList().add(getRoot());	
		}
		// Root grows firstly
		else if (i == 1 && i< maxGeneration ) {
			bgRule.addChildStem(getRoot());
		}
		
		else if (i < maxGeneration){
			bgRule.grow(getRoot(),rule);
		} 
		
		System.out.println("Rule: " + bgRule.getRule());
		System.out.println("create branches: "+ bgRule.getStemList().size());
		
		height = bgRule.getHeight();
		log.info("The height of this tree now is: " + height);
		
		return bgRule.getStemList();
	}


}
