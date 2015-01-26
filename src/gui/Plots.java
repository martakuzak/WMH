package gui;

import java.awt.*;
import java.awt.font.*;
import java.awt.geom.*;

import javax.swing.*;
 
public class Plots extends JPanel {
  
    final int PAD = 20;
    
	private double [] dataX ;
	private double [] dataY;
	private String xlabel;
	private String ylabel;
	
	Plots(double [] dataX, double [] dataY, String xlabel, String ylabel) {
		this.dataX = dataX;
		this.dataY = dataY;
		this.xlabel = xlabel;
		this.ylabel = ylabel;
		
	}
    

    
    
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                            RenderingHints.VALUE_ANTIALIAS_ON);
        int w = getWidth();
        int h = getHeight();
        // Draw ordinate.
        g2.draw(new Line2D.Double(PAD, PAD, PAD, h-PAD));
        // Draw abcissa.
        g2.draw(new Line2D.Double(PAD, h-PAD, w-PAD, h-PAD));
        // Draw labels.
        Font font = g2.getFont();
        FontRenderContext frc = g2.getFontRenderContext();
        LineMetrics lm = font.getLineMetrics("0", frc);
        float sh = lm.getAscent() + lm.getDescent();
        // Ordinate label.
        String s = ylabel;
        float sy = PAD + ((h - 2*PAD) - s.length()*sh)/2 + lm.getAscent();
        for(int i = 0; i < s.length(); i++) {
            String letter = String.valueOf(s.charAt(i));
            float sw = (float)font.getStringBounds(letter, frc).getWidth();
            float sx = (PAD - sw)/2;
            g2.drawString(letter, sx, sy);
            sy += sh;
        }
        // Abcissa label.
        s = xlabel;
        sy = h - PAD + (PAD - sh)/2 + lm.getAscent();
        float sw = (float)font.getStringBounds(s, frc).getWidth();
        float sx = (w - sw)/2;
        g2.drawString(s, sx, sy);
        // Draw lines.
        double xInc = (double)(w - 2*PAD)/(dataX.length-1);
        double scale = (double)(h - 2*PAD)/getMax(dataX);
        g2.setPaint(Color.blue.darker());
        for(int i = 0; i < dataX.length-1; i++) {
            double x1 = PAD + i*xInc;
            double y1 = h - PAD - scale*dataX[i];
            double x2 = PAD + (i+1)*xInc;
            double y2 = h - PAD - scale*dataX[i+1];
            g2.draw(new Line2D.Double(x1, y1, x2, y2));
        }
        // Mark data points.
        g2.setPaint(Color.blue.darker());
        for(int i = 0; i < dataX.length; i++) {
            double x = PAD + i*xInc;
            double y = h - PAD - scale*dataX[i];
            g2.fill(new Ellipse2D.Double(x-2, y-2, 4, 4));
        }
    }
 
    private double getMax(double[] dataX) {
        double max = -Integer.MAX_VALUE;
        for(int i = 0; i < dataX.length; i++) {
            if(dataX[i] > max)
                max = dataX[i];
        }
        return max;
    }

    
}