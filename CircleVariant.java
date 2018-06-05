package java_II_project;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class CircleVariant extends JPanel {
	static double RATE_OF_CHANGE; //ratio from one circle to the next
    static int TIMES; //number of iterations of the fractal
    static Color COLOR; //base color for the fractal
    static int NUMBER;
    
    private void doDrawing(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        if (TIMES > 0)
        		drawCircleVariant(400,400,100,TIMES,g2d);   
    }
    
    @Override
    public void paintComponent(Graphics g) {//called by default
        super.paintComponent(g);
        doDrawing(g);
    }
    
    public CircleVariant(double ratio, int times, Color color, int number) {//passes in parameters
        RATE_OF_CHANGE = ratio;
    		TIMES = times;		
    		COLOR = color;
    		NUMBER = number;
    }
    
    public static void drawCircleVariant(double x, double y, double r, int times, Graphics g) {//recursive method
    		double n = RATE_OF_CHANGE;
    		Graphics2D g2d = (Graphics2D) g;
        if (times > 0) {
        		Color myWhite = new Color(COLOR.getRed()-((COLOR.getRed()/TIMES)*times),(COLOR.getGreen()-(COLOR.getGreen()/TIMES)*times),(COLOR.getBlue()-(COLOR.getBlue()/TIMES)*times)); 
        		g2d.setColor(myWhite);//sets color
        		
        		g2d.drawOval((int)Math.round(x-r), (int)Math.round(y-r), (int)Math.round(2*r), (int)Math.round(2*r));//draws the circle
        		
        		//calls the method. runs the correct number of times (depending on the number of circles requested) 
        		for (int i=0;i<NUMBER;i++) { //turns by the correct angle each time to create a ful rotation at the end of the for loop
        			drawCircleVariant(x+(r + (r/n))*Math.cos(i*(2*Math.PI/NUMBER)), y+(r + (r/n))*Math.sin(i*(2*Math.PI/NUMBER)), r/n, times-1, g2d); //left
        		}
        }  
    }
  
}

class CircleVariantFrame extends JFrame { //Creates the frame, and passes parameters for the fractals through to the CircleVariant class.

    public CircleVariantFrame(int times, double ratio, Color color, int number) {
    	    add(new CircleVariant(ratio, times, color, number));
    	    setTitle("Circle Variant Fractal: " + times + " iterations, R = " + color.getRed() + ", G = " + color.getGreen() + ", B = " + color.getBlue() + ", " + ratio + " circle size ratio, " + number + " circles");
        setSize(800, 800);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    }

    public static void main(String[] args, int times, double ratio, Color color, int number) {//Main method. Creates the frame and calls the object class
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                CircleVariantFrame ex = new CircleVariantFrame(times, ratio, color, number);
                ex.setVisible(true);
            }
        });
    }
    
}