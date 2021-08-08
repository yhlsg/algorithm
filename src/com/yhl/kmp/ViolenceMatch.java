package com.yhl.kmp;

/**
 * 暴力匹配解决字符串问题
 * @author yhl
 * @create 2021-08-08 9:06
 */
public class ViolenceMatch {

    public static void main(String[] args) {

        String str1 = "硅硅谷 尚硅谷你尚硅 尚硅谷你尚硅谷你尚硅你好";
        String str2 = "尚硅谷你尚硅你";

        int index = violenceMatch(str1, str2);
        System.out.println("index : " + index);//index : 15

    }

    /**
     * 暴力匹配
     * @param str1 表示原字符
     * @param str2 表示待匹配的字符
     * @return 匹配到返回首字母的下标，没有匹配到返回-1
     */
    public static int violenceMatch(String str1, String str2){

        char[] s1 = str1.toCharArray();
        char[] s2 = str2.toCharArray();

        int s1Len = s1.length;
        int s2Len = s2.length;

        //s1和s2上面的指针
        int i = 0;
        int j = 0;
        while (i < s1Len && j < s2Len){//确保指针不越界
            //匹配上向后匹配
            if (s1[i] == s2[j]){
                i++;
                j++;
            //没有匹配上，i回溯，j归0
            }else {
                i = i - (j - 1);
                j = 0;
            }
        }

        //判断是否匹配上
        if (j == s2Len){
            return i - j;
        }else {
            return -1;
        }

    }
}
