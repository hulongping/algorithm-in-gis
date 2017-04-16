package com.harvey.experiment.convexhull;

import java.awt.geom.Point2D;
import java.util.*;

/**
 * Created by harveyhu on 2017/4/16.
 */
public class PointComparator implements Comparator<Point2D>{


    private RPoint origin;

    public PointComparator(){

    }

    public PointComparator(RPoint origin){
        this.origin=origin;
    }


    //对于二维向量p1=(x1,y2)和p2=(x2,y2)，a×b定义为x1*y2-y1*x2。
    // 而它的几何意义就是|p1||p2|sin<p1,p2>。
    // 如果a与b夹角小于180度（逆时针），那么这个值就是正值，大于180度就是负值。
    public int compare(Point2D p1,Point2D p2){
        double angle;
        if(origin != null)
            angle = (p1.getX() - origin.getX()) * (p2.getY() - origin.getY()) - (p2.getX() - origin.getX()) * (p1.getY() - origin.getY());
        else
            angle = p1.getX()*p2.getY() - p1.getY()*p2.getX();
        return (int) angle;
    }
}
