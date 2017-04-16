package com.harvey.experiment.convexhull;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;

/**
 * Created by harveyhu on 2017/4/16.
 */
public class HullFrame extends JFrame {

    private RPoint[] points;
    private RPoint[] hullPoints;

    public HullFrame() {
        super("Convex Hull");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.points = new RPoint[]{};
        this.hullPoints = new RPoint[]{};
    }

    public HullFrame(RPoint[] points, RPoint[] hullPoints) {
        super("Convex Hull");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.points = points;
        this.hullPoints = hullPoints;
    }

    public synchronized void setPoints(RPoint[] points) {
        this.points = points;
    }

    public synchronized void setHullPoints(RPoint[] hullPoints) {
        this.hullPoints = hullPoints;
    }

    public synchronized void paint(Graphics g) {
        //Overwrite the background with solid white
        g.setColor(Color.WHITE);
        g.fillRect(0,0,800,600);

        //Draw the points
        g.setColor(Color.BLACK);
        for(RPoint point : points) {
            int x = (int) point.getX();
            int y = (int) point.getY();
            g.fillOval(x - 2, y - 2, 4, 4);

            g.drawString(point.getName(),x,y);
        }

        //Draw the convex hull
        int xPoints[] = new int[hullPoints.length];
        int yPoints[] = new int[hullPoints.length];
        for(int i = 0; i < hullPoints.length; ++i) {
            xPoints[i] = (int) hullPoints[i].getX();
            yPoints[i] = (int) hullPoints[i].getY();

        }
        g.drawPolygon(xPoints, yPoints, hullPoints.length);
    }


}
