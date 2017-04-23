# 本项目用于学习记录一些常用的数据结构算法

## resource
参考的PDF

## Dijkstra算法
常用语GIS中的求解有向图的最短路径
com.harvey.experiment.

## ConvexHull算法 Graham Scan算法

    Graham Scan算法是一种十分简单高效的二维凸包算法，能够在O(nlogn)的时间内找到凸包。
    算法的做法是先定下一个起点，一般是最左边的点和最右边的点，然后一个个点扫过去，
    如果新加入的点和之前已经找到的点所构成的“壳”凸性没有变化，就继续扫，
    否则就把已经找到的最后一个点删去，再比较凸性，直到凸性不发生变化。
    分别扫描上下两个“壳”，合并在一起，凸包就找到了。
 参考链接[https://segmentfault.com/a/1190000000488339]   
 
## Delaunay三角
Delaunay 三角剖分是对平面有限点集P的三角剖分DT，P中的点不在任意一个DT三角形外接圆里。 它满足两个重要准则：
* 空圆特性。Delaunay 三角网是唯一的（任意四点不能共圆），在 Delaunay 三角形网中任一三角形的外接圆范围内不会有其它点存在
* 最大化最小角特性。在散点集可能形成的三角剖分中，Delaunay三角剖分所形成的三角形的最小角最大。

所有三角形并集为点集的凸包。
[参考文章](http://www.cnblogs.com/zhiyishou/p/4430017.html)
[前端JS版本](https://github.com/ironwallaby/delaunay)
