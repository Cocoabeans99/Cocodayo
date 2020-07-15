package com.coco.Dao;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class FindPlayerByUuid {
    public static String Find(String uuid){
        String allplayers = GetStringFromJson.GetJson("C:\\Users\\Administrator\\Desktop\\KS\\KedamaAllStats.json");
        String name = "";
        //1.把字符串类型的json数组对象转化JSONArray
        JSONArray players=JSONArray.parseArray(allplayers);
        //2、循环遍历这个数组
        for(int i=0;i<players.size();i++){
            //3、把里面的对象转化为JSONObject
            JSONObject player = players.getJSONObject(i);
            // 4、属性名的方式获取到
            if(player.getString("uuid").equals(uuid)){
                name = player.getString("playername");
                break;
            }
        }
        return  name;
    }
}
