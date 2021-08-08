package com.yhl.kmp;

/**
 * KMP算法实现字符串查找问题：
 * 1、next[i]的值表示下标为i的字符前的字符串最长相等前后缀的长度。
 * 2、表示该处字符不匹配时应该回溯到的字符的下标
 *
 * @author yhl
 * @create 2021-08-08 13:16
 */
public class KMPAlgorithm {
    public static void main(String[] args) {

        String str1 = "硅硅谷 尚硅谷你尚硅 尚硅谷你尚硅谷你尚硅你好";
        String str2 = "尚硅谷你尚硅你";

        int index = KMPSearch(str1, str2);

        System.out.println("index = " + index);//15

    }


    /**
     * KMP查找
     * @param str1 带查找串
     * @param str2 子串
     * @return 如果找到返回子串在长串中的下标，没有找到返回-1
     */
    public static int KMPSearch(String str1,  String str2){

        char[] s1 = str1.toCharArray();
        char[] s2 = str2.toCharArray();

        int s1Len = s1.length;
        int s2Len = s2.length;

        int i = 0;
        int j = 0;
//        int[] next = getNext(str2);//获取next数组
        int[] next = nextVal(str2);//获取next数组

        //只让子串上的j回溯，主串上的i不回溯
        while (i < s1Len && j < s2Len){
            //相等时将i指针后移j 加 1
            //由于j回溯可能会为next数组的第一个值为-1
            if (j == -1 || s1[i] == s2[j]){
                i++;
                j++;
            }else {
                //回溯到已经匹配的字符串的最大共有元素长度的下标
                j = next[j];
            }
        }

        //判断是否找到
        if (j == s2Len){
            return i - j;
        }else {
            return -1;
        }

    }


    /**
     * 返回字符串的部分匹配表
     * @param str 待计算的字符串
     * @return 返回字串的下标为i 的字符前的字符串最长相等前后缀的长度为j
     */
    public static int[] getNext(String str){

        char[] s = str.toCharArray();
        int[] next = new int[s.length];

        int sLen = s.length;

        int j = 0;
        int k = -1;
        next[0] = -1;//第一个字符前没有字符串，用-1来标识

        while (j < s.length - 1){
            //当前字符为第一个时直接后移
            //当前的字符如果等于第k个字符就让该next值等于k + 1
            if (k == -1 || s[j] == s[k]){
                k++;
                j++;
                //给字符赋next值
                next[j] = k;
            //不相等时
            }else {
                //从已经计算好的next数组中找出前k个字符中的共有元素的最长值
                k = next[k];
            }
        }


        return next;

    }

    /**
     * next数组的优化， ***为了使回溯时相同的字符不要反复判断***
     * 所以将a字符与它next的值指向的下标的值b相等时，就将a的next值指向b位的next的值
     * @param str
     * @return
     */
    public static int[] nextVal(String str){

        char[] s = str.toCharArray();

        int[] next = new int[s.length];

        int j = 0;//遍历s数组给每个字符赋next值
        int k = -1;
        next[0] = -1;

        while (j < s.length - 1){
            //当的字符和字符串第k + 1个相等时
            if (k == -1 || s[j] == s[k]){
                k++;
                j++;

                //a字符与它next的值指向的下标的值b是否相等
                //不相等，等于原来的next值
                if (s[j] != s[k]){
                    next[j] = k;
                //相等，等于next值指向位置的next值
                //用较为粗鄙语言表诉：即字符不匹配时回溯两层后对应的字符下标
                }else {
                    next[j] = next[k];
                }

            //不相等时
            }else {
                //将k回溯到前k + 1个next值，即最大共有元素
                k = next[k];
            }
        }

        return next;
    }
}
