/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package antgame;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.Stroke;

/**
 *
 * @author Hannah
 */
public class HexagonCell extends Polygon
{
    public static final int sides = 6;          // How many sides on the shape (6 for hexagon)
    
    private Point[] points = new Point[sides];  // Creating the points of the Hexagon
    private Point center = new Point(0, 0);     // Center point of the Hexagon
    private int radius;                         // Radius of the Hexagon
    private int rotation = 90;                  //
    
    boolean rocky;                              // Rocky cell
    
    /**
     * Intizialing variables
     * @param center
     * @param radius 
     */
    public HexagonCell(Point center, int radius)
    {
        npoints = sides;
        xpoints = new int[sides];
        ypoints = new int[sides];
        
        this.center = center;
        this.radius = radius;
        
        updatePoints();
    }
    
    /**
     * The point(center) of the Hexagon and it's radius
     * @param x
     * @param y
     * @param radius 
     */
    public HexagonCell(int x, int y, int radius)
    {
        this(new Point(x, y), radius);
    }
    
    /**
     * Sets cell to true or false if rocky
     * @param r 
     */
    public void setRocky(boolean r)
    {
        this.rocky = r;
    }
    
    /**
     * Checks if cell is rocky
     * @return 
     */
    public boolean isRocky()
    {
        return rocky;
    }
    
    /**
     * Find the angle on the hexagon
     * @param fraction
     * @return 
     */
    private double findAngle(double fraction) 
    {
        return fraction * Math.PI * 2 + Math.toRadians((rotation + 180) % 360);
    }
    
    /**
     * find the point on the Hexagon
     * @param angle
     * @return 
     */
    private Point findPoint(double angle) 
    {
        int x = (int) (center.x + Math.cos(angle) * radius);
        int y = (int) (center.y + Math.sin(angle) * radius);

        return new Point(x, y);
    }
    
    /**
     * Update each point on the Hexagon
     */
    protected void updatePoints() 
    {
        for (int p = 0; p < sides; p++) 
        {
            double angle = findAngle((double) p / sides);
            Point point = findPoint(angle);
            xpoints[p] = point.x;
            ypoints[p] = point.y;
            points[p] = point;
        }
    }
    
    /**
     * Draws out the Hexagon
     * @param g
     * @param x
     * @param y
     * @param lineThickness
     * @param colorValue
     * @param filled 
     */
    public void draw(Graphics2D g, int x, int y, int lineThickness, int colorValue, boolean filled) 
    {
        // Store before changing.
        Stroke tmpS = g.getStroke();
        Color tmpC = g.getColor();

        g.setColor(new Color(colorValue));
        g.setStroke(new BasicStroke(lineThickness, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_MITER));

        if (filled)
            g.fillPolygon(xpoints, ypoints, npoints);
        else
            g.drawPolygon(xpoints, ypoints, npoints);

        // Set values to previous when done.
        g.setColor(tmpC);
        g.setStroke(tmpS);
    }
}
