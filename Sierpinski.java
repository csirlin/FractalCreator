package java_II_project;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class Sierpinski extends JPanel {
	static int TIMES; //number of iterations of the fractal
    static Color COLOR; //base color for the fractal
    static double r3 = Math.sqrt(3); //useful for the many calculations that use it
    
    private void doDrawing(Graphics g) {//called by paintComponent, calls the recursive method for the first time.
        Graphics2D g2d = (Graphics2D) g;
      
        g2d.drawLine(100,650,700,650);
        g2d.drawLine(100,650,400,(int) (650-(300*r3))); //initial large triangle
        g2d.drawLine(400,(int) (650-(300*r3)),700,650);
        
        if (TIMES > 0)
        		drawFractal(300, 400, (int) (650-150*r3), TIMES, g2d);
    }
    
    @Override
    public void paintComponent(Graphics g) {//called by default
        super.paintComponent(g);
        doDrawing(g);
    }
    
    public Sierpinski(int times, Color color) {//passes in parameters
    		TIMES = times;		
    		COLOR = color;
    }
    
    public static void drawFractal(double length, double x, double y, int times, Graphics g) {//recursive method
    		Color myWhite = new Color(COLOR.getRed()-((COLOR.getRed()/TIMES)*times),(COLOR.getGreen()-(COLOR.getGreen()/TIMES)*times),(COLOR.getBlue()-(COLOR.getBlue()/TIMES)*times)); //color gradient
		g.setColor(myWhite);	
		
		g.drawLine((int)Math.round(x-(length/2)), (int)Math.round(y), (int)Math.round(x+(length/2)), (int)(Math.round(y)));
    		g.drawLine((int)Math.round(x-(length/2)), (int)Math.round(y), (int)Math.round(x), (int)Math.round((y+(r3*length/2)))); //draws the 3 sides of the triangle
    		g.drawLine((int)Math.round(x), (int)Math.round((y+(r3*length/2))), (int)Math.round(x+(length/2)), (int)Math.round(y));
    		
    		if (times > 1) {
    			drawFractal(length/2.0, x, (y-(length*r3/4)), times-1, g);
    			drawFractal(length/2.0, x-(length/2), (y+(length*r3/4)), times-1, g); //method calls for the 3 triangles that come off this one
    			drawFractal(length/2.0, x+(length/2), (y+(length*r3/4)), times-1, g);
    		}
    }
}

class SierpinskiFrame extends JFrame {//Creates the frame, and passes parameters for the fractals through to the Sierpinski class.

    public SierpinskiFrame(int times, Color color) {
    	    add(new Sierpinski(times, color));
    	    setTitle("Sierpinski Triangle: " + times + " iterations, R = " + color.getRed() + ", G = " + color.getGreen() + ", B = " + color.getBlue());
        setSize(800, 800);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    }

    	public static void main(String[] args, int times, Color color) {//Main method. Creates the frame and calls the object class
        	EventQueue.invokeLater(new Runnable() {
            	@Override
            	public void run() {
            		SierpinskiFrame ex = new SierpinskiFrame(times, color);
            		ex.setVisible(true);
            	}
        });
    }
    	
}