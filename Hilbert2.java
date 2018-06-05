package java_II_project;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class Hilbert2 extends JPanel {
    static int TIMES; //number of iterations of the fractal
    static Color COLOR; //base color for the fractal

    	private void doDrawing(Graphics g) {//calls the recursive method
        	Graphics2D g2d = (Graphics2D) g;
        	if (TIMES > 0)
        		drawHilbert2(200, 600, 400, 1.5*Math.PI, TIMES, g2d);
    	}
    	
    	@Override
    	public void paintComponent(Graphics g) {//called by default
    		super.paintComponent(g);
    		doDrawing(g);
    	}
    	
    	public Hilbert2(int times, Color color) {//Constructor
    		TIMES = times;		
    		COLOR = color;
    	}
    
    	public static void drawHilbert2(double x, double y, double length, double angle, int times, Graphics g) {//recursive method
    		Graphics2D g2d = (Graphics2D) g;
    		if (times >= 2) {//draws the connecting pieces in between sections. Otherwise the fractal would just be the arcs without connections 
    			Color myWhite = new Color(COLOR.getRed()-((COLOR.getRed()/TIMES)), (COLOR.getGreen()-(COLOR.getGreen()/TIMES)),(COLOR.getBlue()-(COLOR.getBlue()/TIMES)));
        		g2d.setColor(myWhite);//sets color
    			
        		//values for the start and end of each connection line
    			double x1 = (-2+(1/(Math.pow(2, times-2))));
    			double y1 = (2-(1/(Math.pow(2, times-2))));
    			double x2 = -x1;
    			double y2 = (2+(1/(Math.pow(2, times-2))));
    			
    			double x3 = y1;
    			double y3 = y2;
    			double x4 = y2;
    			double y4 = y2;
    			
    			double x5 = (6-(1/(Math.pow(2, times-2))));
    			double y5 = y2;
    			double x6 = x5;
    			double y6 = y1;
    			
    			//1st connection
    			g2d.drawLine((int)Math.round(x+((length*Math.sqrt(x1*x1+y1*y1)/4.0)*Math.cos(angle+Math.atan(x1/y1)))), (int)Math.round(y+((length*Math.sqrt(x1*x1+y1*y1)/4.0)*Math.sin(angle+Math.atan(x1/y1)))), (int)Math.round(x+(length*Math.sqrt(x2*x2+y2*y2)/4.0)*Math.cos(angle-Math.atan(x2/y2))), (int)Math.round(y+(length*Math.sqrt(x2*x2+y2*y2)/4.0)*Math.sin(angle-Math.atan(x2/y2))));
        		//2nd connection
    			g2d.drawLine((int)Math.round(x+(length*Math.sqrt(x3*x3+y3*y3)/4.0)*Math.cos(angle+Math.atan(x3/y3))), (int)Math.round(y+(length*Math.sqrt(x3*x3+y3*y3)/4.0)*Math.sin(angle+Math.atan(x3/y3))), (int)Math.round(x+(length*Math.sqrt(x4*x4+y4*y4)/4.0)*Math.cos(angle+Math.atan(x4/y4))), (int)Math.round(y+(length*Math.sqrt(x4*x4+y4*y4)/4.0)*Math.sin(angle+Math.atan(x4/y4))));
    			//3rd connection
    			g2d.drawLine((int)Math.round(x+(length*Math.sqrt(x5*x5+y5*y5)/4.0)*Math.cos(angle+Math.atan(x5/y5))), (int)Math.round(y+(length*Math.sqrt(x5*x5+y5*y5)/4.0)*Math.sin(angle+Math.atan(x5/y5))), (int)Math.round(x+(length*Math.sqrt(x6*x6+y6*y6)/4.0)*Math.cos(angle+Math.atan(x6/y6))), (int)Math.round(y+(length*Math.sqrt(x6*x6+y6*y6)/4.0)*Math.sin(angle+Math.atan(x6/y6))));
    		}
    
    		Color myWhite = new Color(COLOR.getRed()-((COLOR.getRed()/TIMES)*times*1/1), (COLOR.getGreen()-(COLOR.getGreen()/TIMES)*times*1/1),(COLOR.getBlue()-(COLOR.getBlue()/TIMES)*times*1/1)); 
    		g2d.setColor(myWhite);	//sets color
    		
    		if (times == 1) {//draws the base piece (base case)
    			g2d.drawLine((int)Math.round(x), (int)Math.round(y), (int)Math.round((x+(length*Math.cos(angle)))), (int)Math.round((y+(length*Math.sin(angle)))));
        		g2d.drawLine((int)Math.round(x+(length*Math.cos(angle))), (int)Math.round(y+(length*Math.sin(angle))), (int)Math.round(x+(length*(Math.cos(angle)+Math.cos(angle+.5*Math.PI)))), (int)Math.round(y+(length*(Math.sin(angle)+Math.sin(angle+.5*Math.PI)))));
        		g2d.drawLine((int)Math.round(x+(length*(Math.cos(angle)+Math.cos(angle+.5*Math.PI)))), (int)Math.round(y+(length*(Math.sin(angle)+Math.sin(angle+.5*Math.PI)))), (int)Math.round(x+(length*Math.cos(angle+.5*Math.PI))), (int)Math.round(y+(length*Math.sin(angle+.5*Math.PI))));
    		}
    		else if (times > 1){//calls the previous iteration of the method 4 times, in the correct angle and location (recursive case)
    			drawHilbert2((x+((length*Math.sqrt(2)/4.0)*Math.cos(angle-0.25*Math.PI))), (y+((length*Math.sqrt(2)/4.0)*Math.sin(angle-.25*Math.PI))), length/2, angle+.5*Math.PI, times-1, g2d);
    			drawHilbert2((x+(length*Math.sqrt(10)/4.0)*Math.cos(angle-Math.atan(1.0/3))), (y+(length*Math.sqrt(10)/4.0)*Math.sin(angle-Math.atan(1.0/3))), length/2, angle, times-1, g2d);
    			drawHilbert2((x+(length*Math.sqrt(18)/4.0)*Math.cos(angle+Math.atan(1))), (y+(length*Math.sqrt(18)/4.0)*Math.sin(angle+Math.atan(1))), length/2, angle, times-1, g2d);
    			drawHilbert2((x+(length*Math.sqrt(26)/4.0)*Math.cos(angle+.5*Math.PI+Math.atan(.2))), (y+(length*Math.sqrt(26)/4.0)*Math.sin(angle+.5*Math.PI+Math.atan(.2))), length/2, angle-.5*Math.PI, times-1, g2d);
    		}	
    }
    	
}

class Hilbert2Frame extends JFrame { //Creates the frame, and passes parameters for the fractals through to the Hilbert2 class.

    public Hilbert2Frame(int times, Color color) {
    	    add(new Hilbert2(times, color));
    	    setTitle("Hilbert Curve: " + times + " iterations, R = " + color.getRed() + ", G = " + color.getGreen() + ", B = " + color.getBlue());
        setSize(800, 820);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    }

    public static void main(String[] args, int times, Color color) {//Main method. Creates the frame and calls the object class
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                Hilbert2Frame ex = new Hilbert2Frame(times, color);
                ex.setVisible(true);
            }
        });
    }
    
}