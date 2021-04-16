package edu.neu.csye6200.ui;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Logger;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;
import javax.swing.JPanel;

import edu.neu.csye6200.bg.BGGeneration;
import edu.neu.csye6200.bg.BGStem;

/**
 * BGPanel: paint growth simulation for each generation
 * @author Yiyi Zhou
 */
public class MyCanvas extends JPanel implements Observer{

	private Dimension size;
	private int offsetX;
	private int offsetY;
	private int treeHeight = 0;
	private int treeBranches = 0;
    public ArrayList<BGStem> stemList;  // Stores all stems
    private static int counter = 0;
 	private Logger log = Logger.getLogger(MyCanvas.class.getName());  // log
    private boolean finished = false;
    AffineTransform oldTrans;
	public boolean isFinished() {
		return finished;
	}

	public void setFinished(boolean finished) {
		this.finished = finished;
	}

	public ArrayList<BGStem> getStemList() {
		return stemList;
	}

	public void setStemList(ArrayList<BGStem> stemList) {
		this.stemList = stemList;
	}
	
	public MyCanvas() {
		this.stemList = new ArrayList<BGStem>();
	}
	
	
	/**
	 * perform an update to our simulation - cause growth to occur
	 * @param bs Observable BGGenerationSet
	 * @param change refers to stemList
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void update(Observable bgSet, Object obj) {
		System.out.println("my canvas updating");
		stemList = (ArrayList<BGStem>) obj;
		this.repaint();
	}
	
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		initalDraw();
		if (!finished) {
			drawBG(g);
		}
	}
	
	//initiate graphics and offset point
	public void initalDraw() {
		size = getSize();
		setBackground(Color.WHITE);
		size = getSize();
		offsetX = size.width/2;
		offsetY = size.height-10;
	}
	

	public void drawBG(Graphics g) {
		
		log.info("Drawing BG " + counter++);
		Graphics2D g2d = (Graphics2D) g;
		oldTrans = g2d.getTransform();

		g2d.translate(offsetX, offsetY); // start from offset x and y
		
		drawStemList(g2d, stemList);	// start to draw stems
		
		
		drawStatics(g2d);                // show statics
	}
	
	private void drawStemList(Graphics2D g2d, ArrayList<BGStem> stemList) {
		g2d.scale(1, -1);  // Flip
		// Draw stems according to coordinates
		treeBranches = 0;
		for (BGStem stem: stemList) {
			drawStemLine(g2d, stem);
		}
	}
	/**
	 * A convenience routine to draw a line
	 * @param g2 the 2D Graphics context
	 * @param stem the stem to be painted
	 */
	private void drawStemLine(Graphics2D g2d, BGStem stem) {
		
		// Calculate the start x, end x, start y, end y
		double stX = stem.getX();
		double endX = stem.getLength() * Math.cos(stem.getRadiant()) + stem.getX();
		double stY = stem.getY();
		double endY = stem.getLength() * Math.sin(stem.getRadiant()) + stem.getY();
		
		setStyle(g2d);
		
		g2d.drawLine((int)stX, (int)stY, (int)endX, (int)endY);
		treeHeight = (int)Math.max(treeHeight, endY);
		treeBranches ++;
	}	
	  
	private void setStyle(Graphics2D g2) {
		int age = stemList.get(stemList.size()-1).getCurrentGeneration();
		BasicStroke stroke = new BasicStroke((float)age);
		g2.setStroke(stroke);
		int red = 30 + age *20;
		if(red > 255) red =255;
		Color col = new Color(red, 120,10);
		g2.setColor(col);
	}
	
	private void drawStatics(Graphics2D g2d) {
		g2d.setTransform(oldTrans);  //initial g2d
		
		g2d.setColor(Color.BLACK);
		g2d.setFont(new Font("Times Roman", Font.BOLD, 15));
		g2d.drawString("Tree statics", 10, 30);
		g2d.drawString("height: " + (int) treeHeight, 10,60);
		g2d.drawString(treeBranches + " stems", 10, 90);
	}

	public void reset() {
		setFinished(true);
		repaint();
		treeHeight = 0;
		treeBranches = 0;
	}
}
