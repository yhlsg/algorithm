package com.yhl.greedy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * 贪心算法的实现
 * @author yhl
 * @create 2021-08-08 16:50
 */
public class GreedyAlgorithm {
    public static void main(String[] args) {

        //创建广播
        HashSet<String> hashSet1 = new HashSet<>();
        hashSet1.add("北京");
        hashSet1.add("上海");
        hashSet1.add("天津");

        HashSet<String> hashSet2 = new HashSet<>();
        hashSet2.add("广州");
        hashSet2.add("北京");
        hashSet2.add("深圳");

        HashSet<String> hashSet3 = new HashSet<>();
        hashSet3.add("成都");
        hashSet3.add("上海");
        hashSet3.add("杭州");

        HashSet<String> hashSet4 = new HashSet<>();
        hashSet4.add("上海");
        hashSet4.add("天津");

        HashSet<String> hashSet5 = new HashSet<>();
        hashSet5.add("杭州");
        hashSet5.add("大连");

        //创建广播的集合
        HashMap<String, HashSet<String>> broadcasts = new HashMap<>();
        broadcasts.put("k1", hashSet1);
        broadcasts.put("k2", hashSet2);
        broadcasts.put("k3", hashSet3);
        broadcasts.put("k4", hashSet4);
        broadcasts.put("k5", hashSet5);
        
        //创建所有地区的集合
        HashSet<String> cities = new HashSet<>();
        ArrayList<String> allAreas = new ArrayList<>();

        for (Map.Entry<String, HashSet<String>> k : broadcasts.entrySet()){
            HashSet<String> city = k.getValue();
            cities.addAll(city);
        }

        allAreas.addAll(cities);


        //调用贪心算法求出需要的结果集
        HashSet<String> greedyResult = greedy(broadcasts, allAreas);
        System.out.println("所需要的广播有：" + greedyResult);//所需要的广播有：[k1, k2, k3, k5]


    }


    /**
     * 使用贪心算法解决集合覆盖问题
     * @param broadcasts 每个广播所能覆盖的地区map
     * @param allAreas 所有的要覆盖的地区
     * @return 返回需要的广播set集合
     */
    public static HashSet<String> greedy(Map<String, HashSet<String>> broadcasts,  ArrayList<String> allAreas){

        //记录结果
        HashSet<String> selects = new HashSet<>();

        //需要使用一个辅助集合，来记录该广播覆盖了多少剩余下的要覆盖的地区
        HashSet<String> tempSet = new HashSet<>();
        //用一个String变量记录当前能覆盖最多地区的广播名称
        String maxKey = null;
        //记录最大覆盖的数量
        int num = 0;

        //循环遍历将所有的要覆盖的地区都覆盖到
        while (allAreas.size() != 0){
            //每次while之前都要将maxkey和num置空
            maxKey = null;
            num = 0;

            //遍历每个广播找出当前最多覆盖的广播
            for (String s : broadcasts.keySet()){
                //每次for循环时都要将tempSet置空
                tempSet.clear();

                HashSet<String> area = broadcasts.get(s);

                tempSet.addAll(area);
                //获取该广播包含的未覆盖的地区
                tempSet.retainAll(allAreas);

                //如果该广播覆盖的未覆盖的地区 > 当前的最大值
                if (tempSet.size() > 0 && (maxKey == null || tempSet.size() > num)){//体现了贪心算法
                    maxKey = s;
                    num = tempSet.size();
                }
            }

            //将本次查询的最优结果放入集合中
            if (maxKey != null){
                selects.add(maxKey);
                //将以添加广播中的地区从allAreas中移除
                allAreas.removeAll(broadcasts.get(maxKey));
            }
        }

        return selects;
    }
}
