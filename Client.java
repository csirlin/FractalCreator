package java_II_project;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Client extends JFrame {
	
	public static void main(String[] args) { // main method. creates a "Client" object, which opens the fractal menu
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				Client ex = new Client(args);
				ex.setVisible(true);
			}
		});
	}
	
	//fields to be set and passed into fractal constructors by the Client class
	static int iterations;
	static Color color;
	static double ratio = 2;
	static int number = 4;
	static int type;

	public Client(String[] args) { // creates the main frame where the user customizes their fractal parameters,
								  // and calls the appropriate fractal constructors
		// basic frame setup:
		setTitle("Fractal Program:");
		setSize(800, 800);
		setLocationRelativeTo(null); 
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setLayout(null);

		// Title setup

		Font titleFont = new Font("", Font.BOLD, 50);// title font
		Font subtitleFont = new Font("", Font.BOLD, 20);// other font

		JLabel title = new JLabel("FRACTAL CREATOR");// title
		title.setFont(titleFont);
		title.setBounds(150, 25, 500, 50);
		title.setVisible(true);
		add(title);

		JLabel subtitle = new JLabel("Select a fractal and fill in parameter values,");// subtitle line 1
		subtitle.setFont(subtitleFont);
		subtitle.setBounds(175, 60, 500, 100);
		subtitle.setVisible(true);
		add(subtitle);

		JLabel subtitle2 = new JLabel("then click the \"Make Fractal\" button."); // line 2
		subtitle2.setFont(subtitleFont);
		subtitle2.setBounds(210, 95, 500, 100);
		subtitle2.setVisible(true);
		add(subtitle2);

		// Creating buttons, fill in boxes, and drop-down menus to fill in the parameters
		
		String[] fractalStrings = { "-Select a Fractal-", "Circle Fractal", "Sierpinski Triangle", 
				"Circle Variant (with Angles)", "Dragon Curve", "Hilbert Curve" };
		JComboBox fractalTypes = new JComboBox(fractalStrings); // drop-down menu (combo box) to select the fractal type
		fractalTypes.setSelectedIndex(0);
		fractalTypes.setBounds(275, 200, 250, 25);
		fractalTypes.setVisible(true);
		add(fractalTypes);

		JLabel iterationsString = new JLabel("Iterations:"); // Iterations string
		iterationsString.setFont(subtitleFont);
		iterationsString.setBounds(50, 300, 200, 30);
		iterationsString.setVisible(false);
		add(iterationsString);

		SpinnerNumberModel iterationsList = new SpinnerNumberModel(0, 0, 8, 1);
		SpinnerNumberModel iterationsList2 = new SpinnerNumberModel(0, 0, 24, 1); //2nd model for dragon curve, which requires many iterations, 
																				//and it takes a very long time to run if the value is high for the other fractals
		JSpinner iterationsSpinner = new JSpinner(iterationsList); // Iterations selection JSpinner
		iterationsSpinner.setBounds(50, 340, 100, 40);
		iterationsSpinner.setFont(subtitleFont);
		iterationsSpinner.setVisible(false);
		add(iterationsSpinner);

		JLabel colorString = new JLabel("Color:"); // Color string
		colorString.setFont(subtitleFont);
		colorString.setBounds(192, 300, 200, 30);
		colorString.setVisible(false);
		add(colorString);

		String[] colorList = { "-Select a color-", "Red", "Orange", "Yellow", "Green", "Blue", "Purple", "Black" };
		JComboBox colorBox = new JComboBox(colorList); // Color selection drop-down menu (combo box)
		colorBox.setSelectedIndex(0);
		colorBox.setBounds(187, 348, 150, 25);
		colorBox.setVisible(false);
		add(colorBox);

		JLabel ratioString = new JLabel("Circle size ratio:"); //Ratio string
		ratioString.setFont(subtitleFont);
		ratioString.setBounds(363, 300, 200, 30);
		ratioString.setVisible(false);
		add(ratioString);

		SpinnerNumberModel ratioList = new SpinnerNumberModel(2, -10, 10, 0.01);
		JSpinner ratioSpinner = new JSpinner(ratioList); //Ratio selection JSpinner
		ratioSpinner.setBounds(363, 340, 100, 40);
		ratioSpinner.setFont(subtitleFont);
		ratioSpinner.setVisible(false);
		add(ratioSpinner);

		JLabel numberString = new JLabel("Number of circles:"); //Number string
		numberString.setBounds(562, 300, 200, 30);
		numberString.setFont(subtitleFont);
		numberString.setVisible(false);
		add(numberString);

		SpinnerNumberModel numberList = new SpinnerNumberModel(4, 0, 12, 1);
		JSpinner numberSpinner = new JSpinner(numberList); //Number selection JSpinner
		numberSpinner.setBounds(562, 340, 100, 40);
		numberSpinner.setFont(subtitleFont);
		numberSpinner.setVisible(false);
		add(numberSpinner);

		JButton go = new JButton("Run Fractal"); //button to run the program
		go.setBounds(320, 500, 160, 70);
		go.setFont(subtitleFont);
		go.setVisible(false);
		add(go);

		// Action listeners for the above combo boxes, spinners, and buttons

		fractalTypes.addActionListener(new ActionListener() {// makes the applicable parameter boxes viewable depending
																// on which fractal is selected
			public void actionPerformed(ActionEvent e) {
				int index = fractalTypes.getSelectedIndex();

				if (colorBox.getSelectedIndex() == 0 || index == 0)
					go.setVisible(false);
				else
					go.setVisible(true);

				if (index == 0) { // default -Select- option, everything invisible
					iterationsString.setVisible(false);
					iterationsSpinner.setVisible(false);
					colorString.setVisible(false);
					colorBox.setVisible(false);
					ratioString.setVisible(false);
					ratioSpinner.setVisible(false);
					numberString.setVisible(false);
					numberSpinner.setVisible(false);
					go.setVisible(false);
				} 
				
				else if (index == 1) { // circle fractal, show iterations, color, and ratio options
					iterationsString.setVisible(true);
					iterationsSpinner.setVisible(true);
					colorString.setVisible(true);
					colorBox.setVisible(true);
					ratioString.setVisible(true);
					ratioSpinner.setVisible(true);
					numberString.setVisible(false);
					numberSpinner.setVisible(false);

				} 
				
				else if (index == 2 || index == 4 || index == 5) { //Sierpinski triangle, Dragon curve, Hilbert curve. show iterations and color options
					iterationsString.setVisible(true);
					iterationsSpinner.setVisible(true);
					colorString.setVisible(true);
					colorBox.setVisible(true);
					ratioString.setVisible(false);
					ratioSpinner.setVisible(false);
					numberString.setVisible(false);
					numberSpinner.setVisible(false);
				} 
				
				else if (index == 3) { //circle variant fractal, show iterations, color, ratio, and number options
					iterationsString.setVisible(true);
					iterationsSpinner.setVisible(true);
					colorString.setVisible(true);
					colorBox.setVisible(true);
					ratioString.setVisible(true);
					ratioSpinner.setVisible(true);
					numberString.setVisible(true);
					numberSpinner.setVisible(true);
				}
				if (index == 4) 
					iterationsSpinner.setModel(iterationsList2);
				else
					iterationsSpinner.setModel(iterationsList);
				type = index; //sets the "type" value, which later determines which fractal is constructed
			}
		});

		iterationsSpinner.addChangeListener(new ChangeListener() { //sets the "iterations" value whenever the iteration spinner value is changed
			public void stateChanged(ChangeEvent e) { //later passed to the fractal constructor
				int index = (int) iterationsSpinner.getValue();
				if (index >= 0)
					iterations = index;
			}
		});

		colorBox.addActionListener(new ActionListener() { //sets the "color" value whenever the color combo box value is changed
			public void actionPerformed(ActionEvent e) { //later passed to the fractal constructor
				int index = colorBox.getSelectedIndex();
				if (index > 0)
					color = setColor((String) colorBox.getSelectedItem());
				if (colorBox.getSelectedIndex() == 0)
					go.setVisible(false);
				else
					go.setVisible(true);
			}
		});

		ratioSpinner.addChangeListener(new ChangeListener() { //sets the "ratio" value whenever the ratio spinner value is changed
			public void stateChanged(ChangeEvent e) { // later passed to the fractal constructor
				ratio = (double) ratioSpinner.getValue();
			}
		});

		numberSpinner.addChangeListener(new ChangeListener() { //sets the "number" value whenever the number spinner value is changed
			public void stateChanged(ChangeEvent e) { // later passed to the fractal constructor
				number = (int) numberSpinner.getValue();
			}
		});

		go.addActionListener(new ActionListener() { // "run fractal" button listener. calls the appropriate constructor using the parameter values 										
			public void actionPerformed(ActionEvent e) {
				
				//make sure all the values are updated
				iterations = (int) iterationsSpinner.getValue();
				color = setColor((String) colorBox.getSelectedItem()); 
				ratio = (double) ratioSpinner.getValue();
				number = (int) numberSpinner.getValue();
				
				if (colorBox.getSelectedIndex() > 0) {
					if (type == 1)
						CircleFrame.main(args, iterations, ratio, color);
					else if (type == 2)
						SierpinskiFrame.main(args, iterations, color);
					else if (type == 3)
						CircleVariantFrame.main(args, iterations, ratio, color, number);
					else if (type == 4)
						DragonFrame.main(args, iterations, color);
					else if (type == 5)
						Hilbert2Frame.main(args, iterations, color);
				}
			}
		});
		
	}

	public static Color setColor(String s) { //creates and returns a "Color" object using a passed in string
		Color newColor;
		if (s.equals("Red"))
			newColor = new Color(255, 0, 0);
		else if (s.equals("Orange"))
			newColor = new Color(255, 150, 0);
		else if (s.equals("Yellow"))
			newColor = new Color(255, 255, 0);
		else if (s.equals("Green"))
			newColor = new Color(0, 255, 0);
		else if (s.equals("Blue"))
			newColor = new Color(0, 0, 255);
		else if (s.equals("Purple"))
			newColor = new Color(255, 0, 255);
		else //if a different or an invalid color is passed in, the method will return black
			newColor = new Color(0, 0, 0);
		return newColor;
	}
	
}