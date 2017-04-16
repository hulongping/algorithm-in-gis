package com.harvey.experiment.convexhull;

import java.util.*;

/**
 * Created by harveyhu on 2017/4/16.
 */
public class GrahamScan {



    private Stack<RPoint> hull = new Stack<RPoint>();
    private RPoint[] points;

    public GrahamScan(RPoint[] pts){
        this.points=pts;
        //相对于坐标原点角的那个点
        Arrays.sort(points, new PointComparator());



        hull.push(points[0]);
        Arrays.sort(points, new PointComparator(points[0]));

        for(int i=0;i<points.length;i++){
            points[i].setName(""+i);
        }


        hull.push(points[1]);
        hull.push(points[2]);

        for(int i = 3; i < points.length; ++i){
            RPoint top=hull.peek();
            RPoint nextToTop=hull.get(hull.size()-2);

            while (polarAngle(points[i],nextToTop,top)>=0&&hull.size()>=3){
                hull.pop();
                top=hull.peek();
                nextToTop=hull.get(hull.size()-2);
            }
            hull.push(points[i]);
        }
    }

    public RPoint[] getHull() {
        RPoint[] pointArray = new RPoint[]{};
        return hull.toArray(pointArray);
    }

    /**
     *
     * @param p0 起始的原点 x1*y2-y1*x2
     * @param p1
     * @param p2
     * @return p1-p0和p2-p0向量的夹角
     * 如果a与b夹角小于180度（顺时针），那么这个值就是正值，大于180度就是负值。
     */
    private double polarAngle(RPoint p0, RPoint p1, RPoint p2) {
        //(p1 - p0) * (p2 - p0) = (x1 - x0)(y2 - y0) - (x2 - x0)(y1 - y0)
        return (p1.getX() - p0.getX()) * (p2.getY() - p0.getY()) - (p2.getX() - p0.getX()) * (p1.getY() - p0.getY());
    }




}
