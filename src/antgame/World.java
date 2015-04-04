/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package antgame;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Stroke;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Hannah
 */
public class World extends JPanel
{
    private final int width = 680;     // Width of JPanel
    private final int height = 680;     // HEight of JPanel
    
    private Font font = new Font("Arial", Font.BOLD, 10);   // Font to be draw in cell
    FontMetrics metrics;
    
    public HexagonCell[][] world = new HexagonCell[width][height];  // 2 by 2 array of world class which holds cells
    
    public World()
    {
        setPreferredSize(new Dimension(width, height)); // Set size of the JPanel
    }
    
    /**
     * Paints the components onto the JPanel
     * @param g Graphics to be drawn
     */
    @Override
    public void paintComponent(Graphics g)
    {
        Graphics2D g2d = (Graphics2D) g;
        //Point origin = new Point(width / 2, height / 2);
        Point origin = new Point(width / 2, height / 2);
        
        g2d.setStroke(new BasicStroke(4.0f, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_MITER));
        g2d.setFont(font);
        metrics = g.getFontMetrics();
        
        drawHexGrid(g2d, origin, 7, 50, 1);
    }
    
    /**
     * Creates the Hexagon grid onto th JPanel
     * @param g
     * @param origin point of where the grid will be drawn on the JPanel
     * @param size size of each hexagon
     * @param radius radius of each hexagon
     * @param padding space between the each Hexagon
     */
    private void drawHexGrid(Graphics g, Point origin, int size, int radius, int padding)
    {
        double ang30 = Math.toRadians(30);
        double xOff = Math.cos(ang30) * (radius + padding);
        double yOff = Math.sin(ang30) * (radius + padding);
        int half = size / 2;

        for (int row = 0; row < size; row++) 
        {
            int cols = size - java.lang.Math.abs(row - half);

            for (int col = 0; col < cols; col++) 
            {
                int xLbl = row < half ? col - row : col - half;
                int yLbl = row - half;
                int x = (int) (origin.x + xOff * (col * 2 + 1 - cols));
                int y = (int) (origin.y + yOff * (row - half) * 3);
                
                if ((x & 1) == 0)//(y & 1) == 0 && 
                {
                    drawHex(g, xLbl, yLbl, x, y, radius);
                }
                else
                {
                    //col++;
                    drawHex(g, xLbl, yLbl, x, y, radius);
                }
                
//                int xLbl = row < half ? col - row : col - half;
//                int yLbl = row - half;
//                int x = (int) (origin.x + xOff * (col * 2 + 1 - cols));
//                int y = (int) (origin.y + yOff * (row - half) * 3);
//
//                drawHex(g, xLbl, yLbl, x, y, radius);
            }
        }
    }
    
    /**
     * Draws the hexagons onto the jPanel
     * @param g
     * @param posX
     * @param poxY
     * @param x
     * @param y
     * @param r 
     */
    private void drawHex(Graphics g, int posX, int posY, int x, int y, int r)
    {
        Graphics2D g2d = (Graphics2D) g;
        
        HexagonCell hexCell = new HexagonCell(x, y, r);
        String text = ".";
        String text1 = String.format("%s : %s", coord(posX), coord(posY));
        int w = metrics.stringWidth(text);
        int h = metrics.getHeight();
        
        if ((x & 1) == 0) //even
        {
            hexCell.setRocky(true);
        }
        
        hexCell.draw(g2d, x, y, 0, 0x008844, true);
        hexCell.draw(g2d, x, y, 2, 0xFFFFFF, false);
        
        if (hexCell.isRocky() == true)
        {
            g.setColor(new Color(0xFFFFFF));
            g.drawString(text1, x - w/2, y + h/2);
        }
        else
        {
            g.setColor(new Color(0xFFFFFF));
            g.drawString(text1, x - w/2, y + h/2);
        }
        
        world[x][y] = hexCell;
        System.out.println("World: " + x + " " +  y);
        
        
    }
    
    /**
     * Gets the coordinates of the Hexagon grid
     * @param value
     * @return 
     */
    private String coord(int value) 
    {
        return (value > 0 ? "+" : "") + Integer.toString(value);
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        JFrame f = new JFrame();
        World w = new World();
        
        f.setContentPane(w);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.pack();
        f.setLocationRelativeTo(null);
        f.setVisible(true);
    }
    
}
