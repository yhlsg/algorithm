package com.yhl.dynamic;

import com.sun.deploy.ui.UIFactory;

/**
 * 动态规划算法解决背包分配问题
 * @author yhl
 * @create 2021-08-07 19:51
 */
public class KnapsackProblem {
    public static void main(String[] args) {

        int[] w = {1, 4, 3};//物品所占背包的空间
        int[] val = {1500, 3000, 2000};//物品的价值

        int m = 4;//背包的容量
        int n = w.length;//物品的个数

        //创建二维数组，v[i][j]表示在i个物品，j的背包空间中所能装下的最大物品价值
        int[][] v = new int[n + 1][m + 1];

        //创建二维数组来记录添加的物品
        int[][] path = new int[n + 1][m + 1];

        //将v数组的第一行和第一列都赋值为0
        for (int i = 0; i < v[0].length; i++) {
            v[0][i] = 0;
        }

        for (int i = 0; i < v.length; i++) {
            v[i][0] = 0;
        }

        //动态规划算法处理
        for (int i = 1; i < v.length; i++) {
            for (int j = 1; j < v[i].length; j++) {
                //当物品占用的空间大于背包的空间时
                if (w[i - 1] > j){
                    v[i][j] = v[i - 1][j];
                }else {
//                    v[i][j] = Math.max(v[i - 1][j], val[i - 1] + v[i - 1][j - w[i - 1]]);
                    if (v[i - 1][j] < val[i - 1] + v[i - 1][j - w[i - 1]]){
                        v[i][j] = val[i - 1] + v[i - 1][j - w[i - 1]];
                        //将该物品标记
                        path[i][j] = 1;
                    }else {
                        v[i][j] = v[i - 1][j];
                    }
                }
            }
        }

        //遍历二维数组
        for (int i = 0; i < v.length; i++) {
            for (int j = 0; j < v[i].length; j++) {
                System.out.print(v[i][j] + " ");
            }

            System.out.println();
        }

        System.out.println("*************************");

        //输出物品组合的方案
//        for (int i = 0; i < path.length; i++) {
//            for (int j = 0; j < path[i].length; j++) {
//                if (path[i][j] == 1){
//                    System.out.println("将第" + i + "个物品添加到背包中");
//                }
//            }
//        }

        int i = path.length - 1;
        int j = path[0].length - 1;

        while (i > 0 && j > 0){
            //说明将该物品添加到背包中，并将j扣除相应的容量
            if (path[i][j] == 1){
                System.out.println("将第" + i + "个物品添加到背包中");
                j -= w[i - 1];
            }
            i--;
        }
    }
}
