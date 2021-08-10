package com.yhl.kruskalcase;

import java.util.Arrays;

/**
 * 克鲁斯卡尔算法实现公交站问题
 * @author yhl
 * @create 2021-08-09 9:14
 */
public class KruskalCaseAlgorithm {

    private int edgeNum ;//边的个数
    private char[] vertexs;//顶点数组
    private int[][] weight;//邻接矩阵

    private static final int INF = Integer.MAX_VALUE;//边长初始值

    public static void main(String[] args) {
        //创建图
        char[] vertexs = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int[][] weight = {
                /*A*//*B*//*C*//*D*//*E*//*F*//*G*/
                /*A*/ { 0, 12, INF, INF, INF, 16, 14},
                /*B*/ { 12, 0, 10, INF, INF, 7, INF},
                /*C*/ { INF, 10, 0, 3, 5, 6, INF},
                /*D*/ { INF, INF, 3, 0, 4, INF, INF},
                /*E*/ { INF, INF, 5, 4, 0, 2, 8},
                /*F*/ { 16, 7, 6, INF, 2, 0, 9},
                /*G*/ { 14, INF, INF, INF, 8, 9, 0}};

        //输出图
        KruskalCaseAlgorithm kruskalCase= new KruskalCaseAlgorithm(vertexs, weight);
        kruskalCase.print();

        //创建边的数组，并排序后输出
        EData[] edges = kruskalCase.getEdges();
        kruskalCase.sortEdges(edges);

        System.out.println("排序后的边的数组为：" + Arrays.toString(edges));

        //克鲁斯卡尔算法求最小生成树
        kruskalCase.kruskalCase();
    }

    //克鲁斯卡尔算法求最小生成树
    public void kruskalCase(){

        int index = 0;//用于记录边的指针
        EData[] edges = getEdges();//获取所有的边
        int[] ends = new int[vertexs.length];//最小生成树的结果
        EData[] res = new EData[vertexs.length - 1];//储存最小生成树

        //将所有边的集合按从小到大排序
        sortEdges(edges);

        //遍历所有的边，获取最小生成树
        for (EData edge : edges){
            //边开始顶点的下标
            int p1 = getIndex(edge.start);
            //边结束顶点的下标
            int p2 = getIndex(edge.end);

            //开始顶点的终点
            int m = getEnd(ends, p1);
            //边结束顶点的终点
            int n = getEnd(ends, p2);

            //判断是否产生回路
            if (m != n){
                //没产生回路
                ends[m] = n;//将终点转向n
                //将该边加入结果集中
                res[index++] = edge;
            }
        }

        //输出最小生成树
        for (int i = 0; i < res.length; i++) {
            System.out.println(res[i]);
        }
    }

    /**
     * 找到输入顶点的终点
     * @param ends 记录所有已加入最小生成树的顶点的下一个点的下标
     * @param i 待查找到顶点
     * @return 返回该顶点的终点
     */
    public int getEnd(int[] ends, int i){

        //遍历直到找到终点即最后一个点的后一个，已经在一条路上的终点相同
        while (ends[i] != 0){
            i = ends[i];
        }

        return i;
    }



    public KruskalCaseAlgorithm(char[] vertexs, int[][] weight) {
        //统计边的个数
        for (int i = 0; i < weight.length; i++) {
            for (int j = i + 1; j < weight.length; j++) {
                if (weight[i][j] != INF){
                    edgeNum++;
                }
            }
        }

        //使用赋值的方法，不影响外面的数组
//        this.vertexs = vertexs;
        this.vertexs = new char[vertexs.length];
        for (int i = 0; i < vertexs.length; i++) {
            this.vertexs[i] = vertexs[i];
        }

//        this.weight = weight;
        this.weight = new int[weight.length][weight.length];
        for (int i = 0; i < weight.length; i++) {
            for (int j = 0; j < weight[i].length; j++) {
                this.weight[i][j] = weight[i][j];
            }
        }
    }

    /**
     * 对边的数组进行排序(冒泡)
     * @param edges 待排序的数组
     */
    public void sortEdges(EData[] edges){

        //创建辅助变量用于交换
        EData temp = null;

        for (int i = 0; i < edges.length - 1; i++) {
            for (int j = 0; j < edges.length - 1 - i; j++) {
                //如果前面的数大于后面的数，交换
                if (edges[j].weight > edges[j + 1].weight){
                    temp = edges[j];
                    edges[j] = edges[j + 1];
                    edges[j + 1] = temp;
                }
            }

        }
    }

    /**
     * 创建当前图的所有边信息的集合
     * @return
     */
    public EData[] getEdges(){
        //创建EData数组
        EData[] edges = new EData[edgeNum];
        //用于向edges中添加边的指针
        int index = 0;

        //遍历邻接矩阵，找出所有边
        for (int i = 0; i < weight.length; i++) {
            for (int j = i + 1; j < weight.length; j++) {
                if (weight[i][j] != INF){
                    edges[index++] = new EData(vertexs[i], vertexs[j], weight[i][j]);
                }
            }
        }

        return edges;

    }

    /**
     * 获取指定顶点对应的下标
     * @param vertex 指定顶点
     * @return 含有该顶点返回下标，不含有则返回-1
     */
    public int getIndex(char vertex){
        for (int i = 0; i < vertexs.length; i++) {
            if (vertexs[i] == vertex){
                return i;
            }
        }

        return -1;
    }

    //输出邻接矩阵
    public void print(){
        for (int i = 0; i < weight.length; i++) {
            for (int j = 0; j < weight.length; j++) {
                System.out.printf("%15d", weight[i][j]);
            }

            System.out.println();
        }
    }
}

//边类，记录边的信息
class EData{
    char start;//开始顶点
    char end;//结束顶点
    int weight;//边的权值

    public EData(char start, char end, int weight) {
        this.start = start;
        this.end = end;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "EData{" +
                "start=" + start +
                ", end=" + end +
                ", weight=" + weight +
                '}';
    }
}
