package java_II_project;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class Dragon extends JPanel {
    static int TIMES; //number of iterations of the fractal
    static Color COLOR; //base color for the fractal
    static double r2 = Math.sqrt(2); //useful for calculations
    
    private void doDrawing(Graphics g) {//calls recursive method
        Graphics2D g2d = (Graphics2D) g;
        if (TIMES > 0) 
        		drawDragon(300, 530, 300*r2, -.25*Math.PI, TIMES-1, g2d, 1);
    }
    
    @Override
    public void paintComponent(Graphics g) { //called automatically
        super.paintComponent(g);
        doDrawing(g);
    }
    
    public Dragon(int times, Color color) { //constructor
    		TIMES = times;		
    		COLOR = color;
    }
    
    public static void drawDragon(double x, double y, double length, double angle, int times, Graphics g, int whichWay) { //recursive method
    		Graphics2D g2d = (Graphics2D) g;
    		if (times == 0) { //only draws a line at the very end
    			Color myWhite = new Color(COLOR.getRed()-((COLOR.getRed()/TIMES)*times*1/1), (COLOR.getGreen()-(COLOR.getGreen()/TIMES)*times*1/1),(COLOR.getBlue()-(COLOR.getBlue()/TIMES)*times*1/1)); 
    			g2d.setColor(myWhite);//sets color
    			g.drawLine((int)Math.round(x), (int)Math.round(y), (int)Math.round(x+(length*Math.cos(angle))) , (int)Math.round(y+(length*Math.sin(angle))));
        }
        	if (times > 0) {//otherwise, it calls itself
        		if (whichWay == 1) {
        			drawDragon(x, y, length/r2, angle-.25*Math.PI, times-1, g, 1);
            		drawDragon(x+(length/r2)*Math.cos(angle-.25*Math.PI), y+(length/r2)*Math.sin(angle-.25*Math.PI), length/r2, angle+.25*Math.PI, times-1, g, 2);
        		}
        		else {
        			drawDragon(x, y, length/r2, angle+.25*Math.PI, times-1, g, 1);
            		drawDragon(x+(length/r2)*Math.cos(angle+.25*Math.PI), y+(length/r2)*Math.sin(angle+.25*Math.PI), length/r2, angle-.25*Math.PI, times-1, g, 2);
        		}	
        }   
    }
	 
}

class DragonFrame extends JFrame { //Creates the frame, and passes parameters for the fractals through to the Dragon class.

    public DragonFrame(int times, Color color) {
    	    add(new Dragon(times, color));
    	    setTitle("Dragon Curve: " + times + " iterations, R = " + color.getRed() + ", G = " + color.getGreen() + ", B = " + color.getBlue());
        setSize(800, 800);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    }

    public static void main(String[] args, int times, Color color) {//Main method. Creates the frame and calls the object class
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                DragonFrame ex = new DragonFrame(times, color);
                ex.setVisible(true);
            }
        });
    }
    
}