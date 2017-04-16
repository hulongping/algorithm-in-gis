package com.harvey.experiment.dijkstra;

import java.util.*;


/**
 * Created by harveyhu on 2017/4/16.
 */
public class VertexNode {


    public boolean known;    //是否已经遍历过了

    public List<VertexNode> adj; //有向的连通的节点集合

    public int dist=Integer.MAX_VALUE;         //权重距离

    public VertexNode path;      //经过路径的前置节点

    public String name; //节点的名字


    public String toString()
    {
        return name;
    }


    public String getFormateLine(){
        String d="∞";
        if(this.dist<Integer.MAX_VALUE){
                d=this.dist+"";
        }

        String msg=this.toString()+"  "+this.known+"  "+d+"   ";
        if(this.path==null)
        {
            msg=msg+"null";
        }
        else
        {
            msg+=this.path.toString();
        }
        return  msg;

    }



}


