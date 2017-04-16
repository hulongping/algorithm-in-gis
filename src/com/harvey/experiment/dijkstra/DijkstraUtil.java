package com.harvey.experiment.dijkstra;


import java.security.PublicKey;
import java.util.*;

/**
 * Created by harveyhu on 2017/4/16.
 */
public class DijkstraUtil {


    //将最短路径打印出来
    public void printPath(VertexNode v) {
        if (v.path != null) {
            printPath(v.path);
            System.out.print(" to ");
        }
        System.out.print(v.toString());
    }


    private HashMap<String,Integer> linkedCost;

    //构建初始化的有向连接表
    public List<VertexNode> buildInitLinkList()
    {

        List<VertexNode> list = new ArrayList<VertexNode>();
        VertexNode v1=getNewNodeByName("v1");
        VertexNode v2=getNewNodeByName("v2");
        VertexNode v3=getNewNodeByName("v3");
        VertexNode v4=getNewNodeByName("v4");
        VertexNode v5=getNewNodeByName("v5");
        VertexNode v6=getNewNodeByName("v6");
        VertexNode v7=getNewNodeByName("v7");

        linkedCost=new HashMap<String,Integer>();
        buildGraphic(v1,v4,1);
        buildGraphic(v1,v2,2);

        buildGraphic(v2,v4,3);
        buildGraphic(v2,v5,10);

        buildGraphic(v3,v1,3);
        buildGraphic(v3,v6,5);

        buildGraphic(v4,v3,2);
        buildGraphic(v4,v5,2);
        buildGraphic(v4,v6,8);
        buildGraphic(v4,v7,4);

        buildGraphic(v5,v7,6);
        buildGraphic(v7,v6,1);

        list.add(v1);
        list.add(v2);
        list.add(v3);
        list.add(v4);
        list.add(v5);
        list.add(v6);
        list.add(v7);

        return list;

    }

    private void buildGraphic(VertexNode from,VertexNode to,int dist)
    {
        from.adj.add(to);
        linkedCost.put(from.toString()+"_to_"+to.toString(),dist);
    }



    private VertexNode getNewNodeByName(String name){
        VertexNode vn=new VertexNode();
        vn.name=name;
        vn.known=false;
        vn.adj=new ArrayList<>();
        vn.path=null;
        vn.dist=Integer.MAX_VALUE;
        return vn;
    }


    private int costDistance(VertexNode from,VertexNode to){
        String key=from.toString()+"_to_"+to.toString();
        if(linkedCost.containsKey(key)) {
            return linkedCost.get(key);
        }
        else
            return Integer.MAX_VALUE;
    }

    private void decrease(VertexNode w){
        for (VertexNode node:w.adj) {
            int dist=w.dist+costDistance(w,node);
            if(dist<node.dist){
                node.dist=dist;
                node.path=w;
            }
        }
    }






    public void solvePath(VertexNode s,VertexNode destNode,List<VertexNode> list) {
        Queue<VertexNode> q = new LinkedList<>();
        s.dist=0;
        s.known=true;
        decrease(s);
        printLinkedGraphic(list);

        q.add(s);

        while (!q.isEmpty()){
            VertexNode v=q.poll();

            for(VertexNode w :v.adj){
                if(!w.known){
                    int cvw=costDistance(v,w);
                    if((v.dist+cvw)<=w.dist)
                    {
                        w.known=true;
                        w.dist=v.dist+cvw;
                        w.path=v;
                        decrease(w);
                        q.add(w);
                        printLinkedGraphic(list);
                    }
                }
            }

        }


        printPath(destNode);




    }

    private void printLinkedGraphic(List<VertexNode> list)
    {

        System.out.println("=========================");

        for(int i=0;i<list.size();i++)
        {
            VertexNode vn=list.get(i);
            System.out.println(vn.getFormateLine());
        }
    }


    public static void main(String[] args){

        DijkstraUtil dij=new DijkstraUtil();
        List<VertexNode> list = dij.buildInitLinkList();
        VertexNode start=list.get(0);
        VertexNode end=list.get(5);

        dij.solvePath(start,end,list);

        System.out.println();
        System.out.println("Done");

    }



}


