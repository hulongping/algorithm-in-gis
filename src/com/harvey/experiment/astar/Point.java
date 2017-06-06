package com.harvey.experiment.astar;

/**
 * Created by hulongping on 2017/6/6.
 */
public class Point
{
    int x, y;

    Point(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    public boolean equals(Object o)
    {
        return o != null && o instanceof Point && ((Point)o).x == x && ((Point)o).y == y;
    }
}