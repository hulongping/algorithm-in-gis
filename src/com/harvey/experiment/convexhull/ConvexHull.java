package com.harvey.experiment.convexhull;

import java.util.Random;
import java.awt.geom.Point2D;


/**
 * Created by harveyhu on 2017/4/16.
 */
public class ConvexHull {


    public static void main(String[] args){


        int numPoints = 10;
        boolean graphics = true;
        boolean graham = true;

        Random rand = new Random();

        HullFrame frame = new HullFrame();
        if(graphics) {
            frame.setSize(800,600);
            frame.setVisible(true);
        }

        RPoint[] points = new RPoint[numPoints];
        for(int j = 0; j < numPoints; ++j) {
            int x =(int)(rand.nextDouble() * 780);
            int y =(int)( rand.nextDouble() * 555);

            points[j] = new RPoint(x + 10, y + 35);
        }
        RPoint[] hullPoints = new RPoint[]{};
        GrahamScan grahamScan = new GrahamScan(points);
        hullPoints = grahamScan.getHull();

        frame.setPoints(points);
        frame.setHullPoints(hullPoints);
        frame.repaint();








    }
}
