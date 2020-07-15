package coco;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.io.*;
import java.text.ParseException;

public class GetPlayerName {

    // 写玩家名字数据
    public static void WriteNames(String data) {
        BufferedWriter writer = null;
        File file = new File("C:\\Users\\Administrator\\Desktop\\KS\\Names.txt");
        if (!file.getParentFile().exists()) { // 如果父目录不存在，创建父目录
            file.getParentFile().mkdirs();
        }
        if (file.exists()) { // 如果已存在,删除旧文件
            file.delete();
        }
        // 如果文件不存在，则新建一个
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        // 写入
        try {
            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, false), "UTF-8"));
            writer.write(data);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (writer != null) {
                    writer.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void get() throws ParseException {

        String[] names = new String[20000];

        //遍历全部玩家
        String allplayers = GetStringFromJson.GetJson("C:\\Users\\Administrator\\Desktop\\KS\\KedamaAllStats.json");

        String name = "";

        //1.把字符串类型的json数组对象转化JSONArray
        JSONArray players=JSONArray.fromObject(allplayers);

        //2、循环遍历这个数组
        for(int i=0;i<players.size();i++) {
            //3、把里面的对象转化为JSONObject
            JSONObject player = players.getJSONObject(i);
            // 4、属性名的方式获取到
            name = player.getString("playername");
            names[i] = name;
        }

        String[] names_last = java.util.Arrays.copyOf(names, players.size());
        WriteNames(names_last.toString());
    }
}