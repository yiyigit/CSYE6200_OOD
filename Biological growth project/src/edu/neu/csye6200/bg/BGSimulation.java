package edu.neu.csye6200.bg;

import java.util.ArrayList;
import java.util.Observable;

import edu.neu.csye6200.ui.MyCanvas;

public class BGSimulation extends Observable implements Runnable {
	protected MyCanvas myCanvas;
	protected BGGeneration bgGeneration;	
	protected int maxGeneration;
	protected long sleepTime;
	
	public BGSimulation(MyCanvas myCanvas, BGGeneration bsd) {
		this.myCanvas = myCanvas;
		this.bgGeneration = bsd;
	}
	
	public boolean isRunning = false;
	private Thread thread = null;
	private boolean done = false; // Set true to end a simulation loop
	private boolean paused = false; // Set true to pause the simulation loop
	private int ctr = 0;
	
	public BGSimulation() {
		System.out.println("Constructing the BGSimulation");
	}

	/**
	 * Start the simulation
	 */
	public void startSim(int generation,double degree, int rule, int currentSpeed) {
		System.out.println("Starting the simulation");
		isRunning = true;              //for myCanvas control button enables
		bgGeneration = new BGGeneration(generation, degree, rule);
		maxGeneration = generation;
		sleepTime = Long.valueOf(currentSpeed);
		
		myCanvas.setFinished(false);
		this.addObserver(myCanvas);
		
		done = false;
		paused = false;
		ctr = 0;
		if (thread == null) {
			thread = new Thread(this); // We are the Runnable
		}
		thread.start();
		
	}
	
	/**
	 * Toggle the paused state between off/on
	 */
	public void pauseSim() {
		paused = !paused; // Flip the paused state
		System.out.println("Pause state is " + paused);
	}
	
	
	
	/** 
	 * Stop the simulation
	 */
	public void stopSim() {
		System.out.println("Stoppin the simulation");
		myCanvas.reset();
		isRunning = false;
		if (thread == null) return;
		done = true; // Stops an existing simulation loop
		
		
	}

	@Override
	public void run() {
       runSimLoop(); // Run the simulation
       thread = null;
	}
	
	// The main simulation loop
	private void runSimLoop() {
		while(!done) {
			if (!paused)
			   updateSim(); // Advance the simulation 
			sleep(sleepTime); // Do a half second delay
		}
	}
	
	/**
	 * Perform a thread sleep
	 * @param millis time to sleep in millis
	 */
	private void sleep(long millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Perform an update to our simulation - cause growth to occur
	 */
	private void updateSim() {
		if(ctr < maxGeneration) {
			System.out.println("Updating the simulation state - growth " + ctr);
			ArrayList<BGStem> tree = bgGeneration.growAGeneration(ctr++);
			setChanged(); // Indicate that new data is available
			notifyObservers(tree);
		}
		if (ctr > 10000) done = true;
	}
}
