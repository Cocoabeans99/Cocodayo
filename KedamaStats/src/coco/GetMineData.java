package coco;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GetMineData {
    // 写矿透数据
    public static void GetMineStats(String data, String uuid) {
        BufferedWriter writer = null;
        File file = new File("C:\\Users\\Administrator\\Desktop\\KS\\MineCheck\\"+uuid+".json");
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

    // 写排名数据
    public static void GetMineRankStats(String data) {
        BufferedWriter writer = null;
        File file = new File("C:\\Users\\Administrator\\Desktop\\KS\\MineCheckRank.json");
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

    public static void get(){
        Map<String, Map<String, Double>> MineRank = new HashMap<String, Map<String, Double>>();
        Map<String, Double> diamondpersentrank = new HashMap<String, Double>(); //钻率排行
        Map<String, Double> emeraldpersentrank = new HashMap<String, Double>(); //绿宝石率排行
        String allplayers = GetStringFromJson.GetJson("C:\\Users\\Administrator\\Desktop\\KS\\KedamaAllStats.json");
        //1.把字符串类型的json数组对象转化JSONArray
        JSONArray players=JSONArray.fromObject(allplayers);
        String uuid;
        //2、循环遍历这个数组
        for(int i=0;i<players.size();i++) {
            //3、把里面的对象转化为JSONObject
            JSONObject playerJson = players.getJSONObject(i);
            MinePlayer player = new MinePlayer();
            // 4、属性名的方式获取到
            uuid = playerJson.getString("uuid");
            JSONObject PlayerJson = JSONObject.fromObject(GetStringFromJson.GetJson("C:\\Users\\Administrator\\Desktop\\KS\\Player\\Kedama-" + uuid + "-Stats.json"));
            JSONObject PlayerStatJson = PlayerJson.getJSONObject("stats");
            //System.out.println(Integer.parseInt(PlayerStatJson.has("minecraft:mined/minecraft:stone") ? PlayerStatJson.getString("minecraft:mined/minecraft:stone"):"1"));
            player.setStoneDigNum(Integer.parseInt(PlayerStatJson.has("minecraft:mined/minecraft:stone") ? PlayerStatJson.getString("minecraft:mined/minecraft:stone"):"1"));
            player.setMineralDigNum(Integer.parseInt(PlayerStatJson.has("minecraft:mined/minecraft:coal_ore") ? PlayerStatJson.getString("minecraft:mined/minecraft:coal_ore"):"1") //煤矿
                    +Integer.parseInt(PlayerStatJson.has("minecraft:mined/minecraft:iron_ore") ? PlayerStatJson.getString("minecraft:mined/minecraft:iron_ore"):"0") //铁矿
                    +Integer.parseInt(PlayerStatJson.has("minecraft:mined/minecraft:redstone_ore") ? PlayerStatJson.getString("minecraft:mined/minecraft:redstone_ore"):"0") //红石矿
                    +Integer.parseInt(PlayerStatJson.has("minecraft:mined/minecraft:gold_ore") ? PlayerStatJson.getString("minecraft:mined/minecraft:gold_ore"):"0") //金矿
                    +Integer.parseInt(PlayerStatJson.has("minecraft:mined/minecraft:diamond_ore") ? PlayerStatJson.getString("minecraft:mined/minecraft:diamond_ore"):"0") //钻石矿
                    +Integer.parseInt(PlayerStatJson.has("minecraft:mined/minecraft:lapis_ore") ? PlayerStatJson.getString("minecraft:mined/minecraft:lapis_ore"):"0") //青金石矿
                    +Integer.parseInt(PlayerStatJson.has("minecraft:mined/minecraft:emerald_ore") ? PlayerStatJson.getString("minecraft:mined/minecraft:emerald_ore"):"0") //绿宝石矿
            );
            player.setDiamondNum(Integer.parseInt(PlayerStatJson.has("minecraft:mined/minecraft:diamond_ore") ? PlayerStatJson.getString("minecraft:mined/minecraft:diamond_ore"):"0"));
            player.setEmeraldNum(Integer.parseInt(PlayerStatJson.has("minecraft:mined/minecraft:emerald_ore") ? PlayerStatJson.getString("minecraft:mined/minecraft:emerald_ore"):"0"));
            player.setMineralOfStone(100 * (double)player.getMineralDigNum()/(player.getStoneDigNum()+player.getMineralDigNum()));
            player.setDiamondOfMineral(100 * Double.parseDouble(PlayerStatJson.has("minecraft:mined/minecraft:diamond_ore") ? PlayerStatJson.getString("minecraft:mined/minecraft:diamond_ore"):"0")/player.getMineralDigNum());
            player.setDiamondOfStone(100 * Double.parseDouble(PlayerStatJson.has("minecraft:mined/minecraft:diamond_ore") ? PlayerStatJson.getString("minecraft:mined/minecraft:diamond_ore"):"0")/(player.getStoneDigNum()+player.getMineralDigNum()));
            player.setEmeraldOfMineral(100 * Double.parseDouble(PlayerStatJson.has("minecraft:mined/minecraft:emerald_ore") ? PlayerStatJson.getString("minecraft:mined/minecraft:emerald_ore"):"0")/player.getMineralDigNum());
            player.setEmeraldOfStone(100 * Double.parseDouble(PlayerStatJson.has("minecraft:mined/minecraft:emerald_ore") ? PlayerStatJson.getString("minecraft:mined/minecraft:emerald_ore"):"0")/(player.getStoneDigNum()+player.getMineralDigNum()));
            JSONObject minejson = JSONObject.fromObject(player);
            GetMineStats(minejson.toString(), uuid);
            //map.put(uuid,player);
            if(PlayerJson.getJSONObject("data").getString("banned").equals("false") && player.getDiamondNum() >= 100){ //没被ban的计入排名
                diamondpersentrank.put(uuid, player.getDiamondOfMineral());
            }
            if(PlayerJson.getJSONObject("data").getString("banned").equals("false") && player.getEmeraldNum() >= 50){ //没被ban的计入排名
                emeraldpersentrank.put(uuid, player.getEmeraldOfMineral());
            }
        }
        System.out.println("写入挖矿数据");
        Map<String, Double> DiamondPersentRank = MapSort.sortMapByValue(diamondpersentrank, 20);
        Map<String, Double> EmeraldPersentRank = MapSort.sortMapByValue(emeraldpersentrank, 20);
        MineRank.put("DiamondPersentRank", DiamondPersentRank);
        MineRank.put("EmeraldPersentRank", EmeraldPersentRank);
        JSONObject rankjson = JSONObject.fromObject(MineRank);
        GetMineRankStats(rankjson.toString());
        System.out.println("写入挖矿排名");
    }
}
