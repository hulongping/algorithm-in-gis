package com.harvey.experiment.convexhull;

import java.awt.geom.Point2D;

/**
 * Created by harveyhu on 2017/4/16.
 */
public class RPoint extends Point2D {


    private double x;
    private double y;

    private String name;


    public RPoint(){
        super();
    }

    public RPoint(double x,double y){
        super();
        this.x=x;
        this.y=y;
        this.setLocation(x,y);
    }

    public RPoint(double x,double y,String name){
        super();
        this.x=x;
        this.y=y;
        this.name=name;
        this.setLocation(x,y);
    }

    public void setLocation(double x,double y){
        this.x=x;
        this.y=y;
    }

    @Override
    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    @Override
    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String toString()
    {
        return name;
    }
}
