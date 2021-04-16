package edu.neu.csye6200.ui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;

/**
 * An abstract class the defines basic application behavior for the BGApp application
 * @author mgmunson
 */
public abstract class BGApp implements ActionListener {

	protected JFrame frame;
	protected MenuManager menuMgr;
	
	/**
	 * The BGApp constructor. This methis initializes the GUI by performing calls to the
	 * extending class.
	 */
	public BGApp() {
		System.out.println("BGApp constructor starting");
		initGUI();
	}
	
	/**
	 * Initialize the Graphical User Interface
	 */
    public void initGUI() {
    	frame = new JFrame();
		frame.setTitle("ABApp");

		frame.setResizable(true);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); //JFrame.DISPOSE_ON_CLOSE)
		
		// Permit the app to hear about the window opening
		//frame.addWindowListener(this); 
		
		menuMgr = new MenuManager(this);
		
		menuMgr.createDefaultActions(); // Set up default menu items
		
		frame.setJMenuBar(menuMgr.getMenuBar()); // Add a menu bar to this application
		
		frame.setLayout(new BorderLayout());
		frame.add(getNorthPanel(), BorderLayout.NORTH);
		frame.add(getCenterPanel(), BorderLayout.CENTER);
    }
 
    /**
     * Override this method to provide the control panel panel.
     * @return a JPanel, which contains the north content of of your application
     */
    public abstract JPanel getNorthPanel();
    
    /**
     * Override this method to provide the main content panel.
     * @return a JPanel, which contains the main content of of your application
     */
    public abstract JPanel getCenterPanel();

    /**
     * A convenience method that uses the Swing dispatch threat to show the UI.
     * This prevents concurrency problems during component initialization.
     */
    public void showUI() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
            	frame.setVisible(true); // The UI is built, so display it;
            }
        });
    }
    
    /**
     * Shut down the application
     */
    public void exit() {
    	frame.dispose();
    	System.exit(0);
    }

    /**
     * Override/complete this method to show an About Dialog
     */
    public void showHelp() {
    }
 
}

