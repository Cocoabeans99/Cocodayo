package coco;

import java.util.*;

public class MapSort {
    //Map 按value值从大到小排序，并取前20
    public static Map<String, Double> sortMapByValue(Map<String, Double> map, int num) {

        Map<String, Double> sortedMap = new LinkedHashMap<String, Double>();

        List<Map.Entry<String,Double>>lists=new ArrayList<Map.Entry<String,Double>>(map.entrySet());
        Collections.sort(lists,new Comparator<Map.Entry<String, Double>>() {
            public int compare(Map.Entry<String, Double> o1,Map.Entry<String, Double> o2)
            {
                double q1=o1.getValue();
                double q2=o2.getValue();
                double p=q2-q1;
                if(p>0){
                    return 1;
                }
                else if(p==0){
                    return 0;
                }
                else
                    return -1;
            }
        });

        if(lists.size()>=num){

        //lists.subList()用法

            for(Map.Entry<String, Double> set:lists.subList(0, num)){
                sortedMap.put(set.getKey(), set.getValue());
            }
        }else {
            for(Map.Entry<String, Double> set:lists){
                sortedMap.put(set.getKey(), set.getValue());
            }
        }
        return sortedMap;
    }
}
