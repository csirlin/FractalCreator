package java_II_project;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class Circle extends JPanel {//creates the fractal
	static double RATE_OF_CHANGE; //ratio from one circle to the next
    static int TIMES; //number of iterations of the fractal
    static Color COLOR; //base color for the fractal
    
    private void doDrawing(Graphics g) {//called by paintComponent, calls the recursive method for the first time.
        Graphics2D g2d = (Graphics2D) g;
        if (TIMES != 0) {
        		drawCircle(400,395,100,TIMES,g2d);
        }
    }
    
    @Override
    public void paintComponent(Graphics g) {//called automatically
        super.paintComponent(g);
        doDrawing(g);
    }
    
    public Circle(double ratio, int times, Color color) {//sets the instance variables
        RATE_OF_CHANGE = ratio;
    		TIMES = times;		
    		COLOR = color;
    }
    
    public static void drawCircle(double x, double y, double r, int times, Graphics g) {//recursive drawing method
    		Graphics2D g2d = (Graphics2D) g;
    		double n = RATE_OF_CHANGE;
    		if (times > 0) {
        		Color myWhite = new Color(COLOR.getRed()-((COLOR.getRed()/TIMES)*times),(COLOR.getGreen()-(COLOR.getGreen()/TIMES)*times),(COLOR.getBlue()-(COLOR.getBlue()/TIMES)*times)); //sets color in a gradient from dark to light
        		g2d.setColor(myWhite);
        		
        		g2d.drawOval((int)Math.round(x-r), (int)Math.round(y-r), (int)Math.round(2*r), (int)Math.round(2*r)); //draws the circle
        		
        		//method calls for the circles that come off of this one
        		drawCircle(x-(r + (r/n)), y, r/n, times-1, g2d); //left
        		drawCircle(x, y+(r + (r/n)), r/n, times-1, g2d); //bottom
        		drawCircle(x+(r + (r/n)), y, r/n, times-1, g2d); //right
        		drawCircle(x, y-(r + (r/n)), r/n, times-1, g2d); //top
        }
    }
    
}

class CircleFrame extends JFrame { //Creates the frame, and passes parameters for the fractals through to the Circle class.

	public CircleFrame(int times, double ratio, Color color) {
    	    add(new Circle(ratio, times, color));
        setTitle("Circle Fractal: " + times + " iterations, R = " + color.getRed() + ", G = " + color.getGreen() + ", B = " + color.getBlue() + ", " + ratio + " circle size ratio");
        setSize(800, 800);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    }

    public static void main(String[] args, int times, double ratio, Color color) {//Main method. Creates the frame and calls the object class
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                CircleFrame ex = new CircleFrame(times, ratio, color);
                ex.setVisible(true);
            }
        });
    }
    
}