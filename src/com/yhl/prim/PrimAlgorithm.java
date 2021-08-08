package com.yhl.prim;

/**
 * 普利姆算法解决修路问题
 * @author yhl
 * @create 2021-08-08 21:36
 */
public class PrimAlgorithm {
    public static void main(String[] args) {

        //创建图
        int verxs = 7;
        char[] data = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int[][] weight = {
                {10000,5,7,10000,10000,10000,2},
                {5,10000,10000,9,10000,10000,3},
                {7,10000,10000,10000,8,10000,10000},
                {10000,9,10000,10000,10000,4,10000},
                {10000,10000,8,10000,10000,5,4},
                {10000,10000,10000,4,5,10000,6},
                {2,3,10000,10000,4,6,10000}
        };

        MGraph graph = new MGraph(verxs);
        minTree minTree = new minTree();
        minTree.createGraph(graph, verxs, data, weight);
        //输出图的邻接矩阵
        minTree.showGraph(graph);

        //测试最小生成树
        minTree.prim(graph, 6);

    }
}

//最小生成树
class minTree{

    /**
     * 使用普利姆算法生成最小生成树
     * @param graph 图
     * @param v 开始访问的顶点 'A' -> 0, 'B' -> 1 ....
     */
    public void prim(MGraph graph, int v){

        //创建数组visited来记录顶点是否被访问
        //0表示没有访问过，1表示访问过了
        int[] visited = new int[graph.verxs];

        //将开始访问的顶点标记为访问过
        visited[v] = 1;

        //创建一个变量记录当前的最短路径，初始值为一个大值
        int minWeight = 10000;

        //创建两个变量记录边的两个顶点
        int h1 = -1;
        int h2 = -1;

        //记录总路径
        int sum = 0;

        //由于n个顶点的最小生成树有n - 1个边，所以遍历graph.verxs - 1次
        for (int k = 1; k < graph.verxs; k++) {

            //遍历当前已经访问过的顶点所能到达没有访问的顶点的边的最小值
            for (int i = 0; i < graph.verxs; i++) {
                for (int j = 0; j < graph.verxs; j++) {
                    //判断i和j是否访问
                    //graph.weight[i][i]表示i顶点到j顶点的距离
                    if (visited[i] == 1 && visited[j] == 0 && graph.weight[i][j] < minWeight){
                        minWeight = graph.weight[i][j];
                        h1 = i;
                        h2 = j;
                    }
                }
            }

            //将顶点和边长输出
            System.out.println("<" + graph.data[h1] + ", " + graph.data[h2] + ">"  + "权值" + minWeight);
            sum += minWeight;
            //将h2即新连接的顶点标记为已经访问
            visited[h2] = 1;
            //将minWeight初始化
            minWeight = 10000;
        }

        System.out.println("总路径为：" + sum);

    }

    /**
     * 初始化图
     * @param graph 图对象
     * @param verxs 图的顶点个数
     * @param data 图的顶点的信息
     * @param weight 图的边的权值
     */
    public void createGraph(MGraph graph, int verxs, char[] data, int[][] weight){
        //给图对象的赋值
        for (int i = 0; i < verxs; i++) {
            graph.data[i] = data[i];
            for (int j = 0; j < verxs; j++) {
                graph.weight[i][j] = weight[i][j];
            }
        }
    }

    /**
     * 展示图的邻接矩阵
     * @param graph 图对象
     */
    public void showGraph(MGraph graph){

        //遍历图的邻接矩阵
        for (int i = 0; i < graph.weight.length; i++) {
            for (int j = 0; j < graph.weight[i].length; j++) {
                System.out.print(graph.weight[i][j] + ", ");
            }
            //每输出一行换行
            System.out.println();
        }
    }
}


//图
class MGraph{
    int verxs;//顶点的个数
    char[] data;//顶点的信息
    int[][] weight;//边的权值，也就是图的邻接矩阵

    public MGraph(int verxs) {
        this.verxs = verxs;
        data = new char[verxs];
        weight = new int[verxs][verxs];
    }
}
