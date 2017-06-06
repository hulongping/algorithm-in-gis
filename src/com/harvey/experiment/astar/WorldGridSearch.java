package com.harvey.experiment.astar;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hulongping on 2017/6/6.
 */
public class WorldGridSearch {


    public static String ascGridFile="D:\\ocean.asc";

    // 地图元素
    static final char START   = 'S';  // 起点
    static final char END     = 'E';  // 终点
    static final char SPACE   = '0';  // 空地
    static final char WALL    = '1';  // 墙
    static final char VISITED = '-';  // 被访问过
    static final char ON_PATH = '@';  // 在结果路径上

    public  static  void main(String[] args){

        WorldGridSearch search = new WorldGridSearch(ascGridFile,122.34,31.05,117.0,12.0);
    }


    private char[][] MAP=null;
    private Point MAX_PNT=null;
    private Point START_PNT=null;
    private Point END_PNT=null;
    private int NCOLS;
    private int NROWS;
    private double XLLCENTER;
    private double YLLCENTER;
    private double YSTART;
    private double CELLSIZE;


    public WorldGridSearch(String ascfile, Double startlon, Double startLat, Double endLon, Double endLat)
    {
        genMap(ascfile);
        int slonIdx=getLonIndex(startlon);
        int slatIdx=getLatIndex(startLat);
        MAP[slonIdx][slatIdx]=START;
        START_PNT=new Point(slonIdx,slatIdx);

        int elonIdx=getLonIndex(endLon);
        int elatIdx=getLatIndex(endLat);
        MAP[elonIdx][elatIdx]=END;
        END_PNT=new Point(elonIdx,elatIdx);

        List<String> path = search();
        for(int i=0;i<path.size();i++)
        {
            System.out.println(path.get(i));
        }

    }


    private void  genMap(String ascfile) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(ascfile));
            NCOLS=Integer.parseInt(br.readLine().split(" ")[1]);
            NROWS=Integer.parseInt(br.readLine().split(" ")[1]);
            XLLCENTER=Double.parseDouble(br.readLine().split(" ")[1]);
            YLLCENTER=Double.parseDouble(br.readLine().split(" ")[1]);

            CELLSIZE=Double.parseDouble(br.readLine().split(" ")[1]);
            YSTART=(NROWS-1)*CELLSIZE+YLLCENTER;


            MAP = new char[NCOLS][NROWS];
            MAX_PNT=new Point(NCOLS,NROWS);

            String line = br.readLine();
            line=br.readLine();
            int idx = 0;
            while(line!=null && line.length()>1) {
                char[] cs = line.replace(" ", "").toCharArray();
                for (int i = 0; i < cs.length; i++) {
                    MAP[i][idx] = cs[i];

                }
                line = br.readLine();
                idx++;
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }



    /**
     * 搜索算法
     */
    private List<String> search()
    {
        final MinHeap heap = new MinHeap(); // 用最小堆来记录扩展的点
        final int[][] directs = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}, {1, 1}, {-1,1}, {1, -1}, {-1, -1}}; // 可以扩展的8个方向

        heap.add(new Data(START_PNT, 0, 0, null)); // 把起始点放入堆
        Data lastData = null; // 找到的最后一个点的数据,用来反推路径

        for (boolean finish = false; !finish && !heap.isEmpty(); )
        {
            final Data data = heap.getAndRemoveMin(); // 取出f值最小的点
            final Point point = data.point;
            if (MAP[point.x][point.y] == SPACE) // 将取出的点标识为已访问点
            {
                MAP[point.x][point.y] = VISITED;
            }

            for (int[] d : directs) // 遍历各个方向的点
            {
                final Point newPnt = new Point(point.x + d[0], point.y + d[1]);
                if (newPnt.x >= 0 && newPnt.x < MAX_PNT.x && newPnt.y >= 0 && newPnt.y < MAX_PNT.y)
                {
                    char e = MAP[newPnt.x][newPnt.y];
                    if (e == END) // 如果是终点,则跳出循环,不用再找
                    {
                        lastData = data;
                        finish = true;
                        break;
                    }
                    if (e != SPACE) // 如果不是空地,就不需要再扩展
                    {
                        continue;
                    }

                    final Data inQueueData = heap.find(newPnt);
                    double directionCoast=directCost(d);
                    if (inQueueData != null) // 如果在堆里,则更新g值
                    {
                        if (inQueueData.g > data.g + directionCoast)
                        {
                            inQueueData.g = data.g + directionCoast;
                            inQueueData.parent = data;
                        }
                    }
                    else // 如果不在堆里,则放入堆中
                    {
                        double h = hManhattanDistance(newPnt);
                        Data newData = new Data(newPnt, data.g + directionCoast, h, data);
                        heap.add(newData);
                    }
                }
            }
        }

        List<String> paths=new ArrayList<>();

        // 反向找出路径
        for (Data pathData = lastData; pathData != null; )
        {
            Point pnt = pathData.point;
            if (MAP[pnt.x][pnt.y] == VISITED)
            {
                MAP[pnt.x][pnt.y] = ON_PATH;
                double lon=getLongitudeByIdx(pnt.x);
                double lat=getLatitudeByIdx(pnt.y);
                paths.add(lon+","+lat);

            }
            pathData = pathData.parent;

        }

        return paths;
    }


    static double directCost(int[] pnt){

        double d= Math.sqrt(Math.pow(pnt[0], 2) + Math.pow(pnt[0] , 2));

        return d;

    }


   private double hManhattanDistance(Point pnt)
    {
        return Math.pow(pnt.x - END_PNT.x, 2) + Math.pow(pnt.y - END_PNT.y, 2);
    }


    private int getLonIndex(double lon)
    {
        double lonGap=Math.abs(lon-XLLCENTER);
        double lonIndex=lonGap/CELLSIZE;
        long idx =Math.round(lonIndex);
        return (int)idx;

    }

    private int getLatIndex(double lat)
    {
        double latGap=Math.abs(lat-YSTART);
        double latIndex=latGap/CELLSIZE;
        long idx =Math.round(latIndex);
        return (int)idx;

    }


    private double getLongitudeByIdx(int idx){
        return XLLCENTER+idx*CELLSIZE;
    }

    private double getLatitudeByIdx(int idx){
        return YSTART-idx*CELLSIZE;
    }





}



