package com.yhl.divedeandconquer;

/**
 * 通过分治算法来递归实现汉诺塔
 * 步骤：
 * 一、当只有一个圆盘时
 * A -> c
 * 二、当有多个圆盘时 要将n个圆盘从A -> c
 * 1、先将A上的 n-1 个圆盘移动到B上
 * 2、再将A上的第 n 个圆盘移动到C上
 * 3、最后将B上的 n-1 个圆盘移动到C上即可
 *
 * @author yhl
 * @create 2021-08-06 21:45
 */
public class HanNuoTower {
    public static void main(String[] args) {

        hanNuoTower(5, 'A', 'B', 'C');

    }

    /**
     * 分治算法递归实现汉诺塔
     * @param n 盘的个数
     * @param a a，b，c表示三个柱子
     * @param b
     * @param c
     */
    public static void hanNuoTower(int n, char a, char b, char c){
        //只有一个盘时，从a -> c
        if (n == 1){
            System.out.println("第" + n + "个圆盘从" + a + "-> " + c);
        }else{
            //先将n - 1个盘从 a -> b
            hanNuoTower(n - 1, a, c, b);
            //再将第n个盘从 a -> c
            System.out.println("第" + n + "个圆盘从" + a + "-> " + c);
            //最后将n-1个盘从 b -> c
            hanNuoTower(n - 1, b, a, c);
        }

    }
}
