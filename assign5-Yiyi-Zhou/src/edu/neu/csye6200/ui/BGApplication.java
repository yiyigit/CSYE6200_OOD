/**
 * 
 */
package edu.neu.csye6200.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowEvent;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Logger;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import edu.neu.csye6200.bg.BGGeneration;
import edu.neu.csye6200.bg.BGSimulation;

/** 
 * BGApplication: The application of Growth Simulation, extends from BGApp
 * @author Yiyi Zhou
 */
public class BGApplication extends BGApp{

	// Panels
	protected static MyCanvas bgPanel;
	protected static JPanel mainPanel;
	protected JPanel northPanel;
	
	// Buttons
	protected JButton startBtn;
	protected JButton pauseresumeBtn;
	protected JButton stopBtn;
	protected JButton clearBtn;
		
	// Rule Selection
	protected Integer currentRule;
	protected JLabel ruleText;
	protected JComboBox<String> ruleChoice;
	
	// Input number of generations
	protected Integer numOfGeneration;
    protected JLabel geneNumText;
	protected JTextField geneNumInput;

	//radiant selection
	protected Integer currentDegree;
	protected JLabel degreeText;
	protected JComboBox<String> degreeChoice;
	//	speed selection
	protected Integer currentSpeed;
	protected JLabel speedText;
	protected JComboBox<String> speedChoice;
	
	
	protected BGGeneration bgGeneration;
	protected BGSimulation bs = new BGSimulation(bgPanel,bgGeneration);

	
	private static Logger log = Logger.getLogger(BGApplication.class.getName());// log
		
	/**
     * Constructor
     */
    public BGApplication() {
    	
    	frame.setSize(1000, 1000);
    	frame.setTitle("BGApplication");

    	showUI();
    }
    
    
    /**
     * Get center Panel
     * @return mainPanel the main panel
     */
    @Override
    public JPanel getCenterPanel() {
       	mainPanel = new JPanel();
    	mainPanel.setLayout(new BorderLayout());
    	
    	bgPanel = new MyCanvas();
    	mainPanel.add(BorderLayout.CENTER, bgPanel);

    	return mainPanel;
    }

	
    /**
     * Get northPanel
     * @return northPanel the panel on which are all the control buttons
     */
	public JPanel getNorthPanel() {
		northPanel = new JPanel();
    	northPanel.setLayout(new FlowLayout());
    	
    	//  add  generation input
     	geneNumText = new JLabel("max growth");
    	northPanel.add(geneNumText);
    	geneNumInput = new JTextField(3);
    	northPanel.add(geneNumInput);
    	
    	//  rule choice 
    	ruleText = new JLabel("rule:");
    	northPanel.add(ruleText);
    	String[] strArr = new String[] {"1","2"} ;
    	ruleChoice = new JComboBox<String>(strArr);
    	northPanel.add(ruleChoice);
    	
    	//degree choice
    	degreeText = new JLabel("growth degree:");
    	northPanel.add(degreeText);
    	String[] raArr = new String[] {"25","30","45"} ;
    	degreeChoice = new JComboBox<String>(raArr);
    	northPanel.add(degreeChoice);
    	
    	//speed choice
       	speedText = new JLabel("growth speed:");
    	northPanel.add(speedText);
    	String[] speedArr = new String[] {"slow","normal","fast"} ;
    	speedChoice = new JComboBox<String>(speedArr);
    	northPanel.add(speedChoice);
    	
    	//default select rule 1
    	currentRule = 1;
    	// When choosing rules
    	ruleChoice.addItemListener(new ItemListener() {
    		@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					currentRule = Integer.parseInt((String)e.getItem()) ;
					System.out.println("Current rule is:" + currentRule);
				}
			}
    		
    	});
    	
    	//default select 25 degrees
    	currentDegree = 25;
    	// When choosing degree
    	degreeChoice.addItemListener(new ItemListener() {
    		@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					currentDegree = Integer.parseInt((String)e.getItem()) ;
					System.out.println("Current degree is:" + currentDegree);
				}
			}
    		
    	});
    	
    	//default select 1.2s delay
    	currentSpeed = 1200;
    	// When choosing speed
    	speedChoice.addItemListener(new ItemListener() {
    		@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					switch((String)(e.getItem())) {
					case "slow":
						currentSpeed = 1200;
						break;
					case"normal":
						currentSpeed = 800;
						break;
					case"fast":
						currentSpeed = 500;
						break;
				}
				System.out.println("Current speed is:" + currentSpeed);
			}
    	}});

    	// add buttons    	
    	startBtn = new JButton("Start");
    	pauseresumeBtn = new JButton("Pause/Resume");
    	stopBtn = new JButton("Stop");
    	
    	startBtn.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    			System.out.println("pressed Start, rule choice:" +currentRule + " max generation: " + numOfGeneration
    					+ " degree: " + currentDegree +" speed is: " + currentSpeed);
    			numOfGeneration = Integer.valueOf(geneNumInput.getText());
    			bs.startSim(numOfGeneration, currentDegree, currentRule,currentSpeed);
    			updateButtonState();
    			
    		}
    	});
    	
    	pauseresumeBtn.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    			System.out.println("pressed Pause/Resume");
    			bs.pauseSim();
    		}
    	});
    	
    	stopBtn.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    			System.out.println("Stop pressed");
    			bs.stopSim();
       			updateButtonState();    			
    			geneNumInput.setText("");
    		}
    	});
    	
    	northPanel.add(startBtn);
    	northPanel.add(pauseresumeBtn);
    	northPanel.add(stopBtn);
 
    	return northPanel;
    }

	private void updateButtonState() {
		startBtn.setEnabled(!bs.isRunning);
		stopBtn.setEnabled(bs.isRunning);
		pauseresumeBtn.setEnabled(bs.isRunning);
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
	/**
	 * The main method to start the BGApplication
	 * @param args
	 */
	public static void main(String[] args) {
		BGApplication app = new BGApplication();
		System.out.print("my app is up!");
	}





}
