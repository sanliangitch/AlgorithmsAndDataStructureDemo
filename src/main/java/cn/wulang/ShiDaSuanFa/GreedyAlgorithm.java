package cn.wulang.ShiDaSuanFa;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/**
 * 贪心算法
 *
 * @author wulang
 * @create 2019/8/26/7:23
 */
public class GreedyAlgorithm {
    public static void main(String[] args) {
        HashMap<String, HashSet<String>> broadcasts = new HashMap<>();
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

        broadcasts.put("k1",hashSet1);
        broadcasts.put("k2",hashSet2);
        broadcasts.put("k3",hashSet3);
        broadcasts.put("k4",hashSet4);
        broadcasts.put("k5",hashSet5);

        //存放所有的地区
        HashSet<String> allAreas = new HashSet<>();
        allAreas.add("北京");
        allAreas.add("上海");
        allAreas.add("天津");
        allAreas.add("广州");
        allAreas.add("深圳");
        allAreas.add("成都");
        allAreas.add("杭州");
        allAreas.add("大连");

        //存放选择的电台集合
        ArrayList<String> selects = new ArrayList<>();

        //定义一个临时的集合，在遍历过程中，存放在遍历过程中覆盖的地区和没存放覆盖地区的交集
        HashSet<String> tempSet = new HashSet<>();

        //定义一个maxKey，指向在一次遍历过程中，能够覆盖最大未覆盖地区的对应的电台的key
        String maxKey = null;
        //如果maxKey不等于空，则会加入到selects
        while (allAreas.size() != 0){
            //每进行一次 maxKey 要值为空
            maxKey = null;
            for (String key : broadcasts.keySet()){
                //tempSet 也要删除
                tempSet.clear();
                HashSet<String> areas = broadcasts.get(key);
                tempSet.addAll(areas);
                //求出 tempSet 和 allAreas 集合的交集，交集赋给 tempSet
                tempSet.retainAll(allAreas);
                //如果当前这个集合包含的未覆盖地区的数量，比maxKey指向的地区集合还多
                //就需要重置 manKey
                /**
                 * 贪心算法，每次都选最优的
                 */
                if (tempSet.size() > 0 &&
                        (maxKey == null || tempSet.size() > broadcasts.get(maxKey).size())){
                    maxKey = key;
                }
            }
            if (maxKey != null){
                selects.add(maxKey);
                //将maxKey指向的电台覆盖的地区，从 allAreas 中清除
                allAreas.removeAll(broadcasts.get(maxKey));
            }
        }
        System.out.println(selects);
    }
}
