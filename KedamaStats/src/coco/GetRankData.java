package coco;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class GetRankData {

    // 写排名数据
    public static void GetRankStats(String data, String uuid) {
        BufferedWriter writer = null;
        File file = new File("C:\\Users\\Administrator\\Desktop\\KS\\Rank\\"+uuid+".json");
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
    public static void GetRankStats(String data) {
        BufferedWriter writer = null;
        File file = new File("C:\\Users\\Administrator\\Desktop\\KS\\Rank.json");
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

    public static void get() {

        Map<String, Map<String, Double>> Rank = new HashMap<String, Map<String, Double>>();
        Map<String, Double> redstone = new HashMap<String, Double>();
        Map<String, Double> pillager = new HashMap<String, Double>();
        Map<String, Double> tree = new HashMap<String, Double>();
        Map<String, Double> draw = new HashMap<String, Double>();
        Map<String, Double> aqua = new HashMap<String, Double>();
        Map<String, Double> villager = new HashMap<String, Double>();
        Map<String, Double> wither = new HashMap<String, Double>();
        Map<String, Double> musician = new HashMap<String, Double>();
        Map<String, Double> writer = new HashMap<String, Double>();
        Map<String, Double> mine_obsidian = new HashMap<String, Double>();
        Map<String, Double> drug = new HashMap<String, Double>();
        Map<String, Double> fire = new HashMap<String, Double>();
        Map<String, Double> scaffolding = new HashMap<String, Double>();
        Map<String, Double> killplayer = new HashMap<String, Double>();
        Map<String, Double> dead = new HashMap<String, Double>();
        Map<String, Double> fly = new HashMap<String, Double>();

        String allplayers = GetStringFromJson.GetJson("C:\\Users\\Administrator\\Desktop\\KS\\KedamaAllStats.json");
        //1.把字符串类型的json数组对象转化JSONArray
        JSONArray players = JSONArray.fromObject(allplayers);
        String uuid;
        //2、循环遍历这个数组
        for (int i = 0; i < players.size(); i++) {
            //3、把里面的对象转化为JSONObject
            JSONObject playerJson = players.getJSONObject(i);
            RankPlayer player = new RankPlayer();
            // 4、属性名的方式获取到
            uuid = playerJson.getString("uuid");
            JSONObject PlayerJson = JSONObject.fromObject(GetStringFromJson.GetJson("C:\\Users\\Administrator\\Desktop\\KS\\Player\\Kedama-" + uuid + "-Stats.json"));
            JSONObject PlayerStatJson = PlayerJson.getJSONObject("stats");
            player.setDrug(Integer.parseInt(PlayerStatJson.has("minecraft:used/minecraft:pufferfish") ? PlayerStatJson.getString("minecraft:used/minecraft:pufferfish"):"0"));
            player.setRedstone(
                    Integer.parseInt(PlayerStatJson.has("minecraft:used/minecraft:redstone") ? PlayerStatJson.getString("minecraft:used/minecraft:redstone"):"0") //红石粉
                            +Integer.parseInt(PlayerStatJson.has("minecraft:used/minecraft:redstone_block") ? PlayerStatJson.getString("minecraft:used/minecraft:redstone_block"):"0") //红石块
                            +Integer.parseInt(PlayerStatJson.has("minecraft:used/minecraft:redstone_torch") ? PlayerStatJson.getString("minecraft:used/minecraft:redstone_torch"):"0") //红石火把
                            +Integer.parseInt(PlayerStatJson.has("minecraft:used/minecraft:repeater") ? PlayerStatJson.getString("minecraft:used/minecraft:repeater"):"0") //中继器
                            +Integer.parseInt(PlayerStatJson.has("minecraft:used/minecraft:comparator") ? PlayerStatJson.getString("minecraft:used/minecraft:comparator"):"0") //比较器
                            +Integer.parseInt(PlayerStatJson.has("minecraft:used/minecraft:observer") ? PlayerStatJson.getString("minecraft:used/minecraft:observer"):"0") //侦测器
                            +Integer.parseInt(PlayerStatJson.has("minecraft:used/minecraft:sticky_piston") ? PlayerStatJson.getString("minecraft:used/minecraft:sticky_piston"):"0") //粘性活塞
                            +Integer.parseInt(PlayerStatJson.has("minecraft:used/minecraft:piston") ? PlayerStatJson.getString("minecraft:used/minecraft:piston"):"0") //活塞
                            +Integer.parseInt(PlayerStatJson.has("minecraft:used/minecraft:slime_block") ? PlayerStatJson.getString("minecraft:used/minecraft:slime_block"):"0") //粘液块
                            +Integer.parseInt(PlayerStatJson.has("minecraft:used/minecraft:lever") ? PlayerStatJson.getString("minecraft:used/minecraft:lever"):"0") //拉杆
                            +Integer.parseInt(PlayerStatJson.has("minecraft:used/minecraft:hopper") ? PlayerStatJson.getString("minecraft:used/minecraft:hopper"):"0") //漏斗
            );
            player.setAqua(Integer.parseInt(PlayerStatJson.has("minecraft:killed/minecraft:drowned") ? PlayerStatJson.getString("minecraft:killed/minecraft:drowned"):"0"));
            player.setFire(Integer.parseInt(PlayerStatJson.has("minecraft:used/minecraft:flint_and_steel") ? PlayerStatJson.getString("minecraft:used/minecraft:flint_and_steel"):"0"));
            player.setMine_obsidian(Integer.parseInt(PlayerStatJson.has("minecraft:mined/minecraft:obsidian") ? PlayerStatJson.getString("minecraft:mined/minecraft:obsidian"):"0"));
            player.setDraw(
                    Integer.parseInt(PlayerStatJson.has("minecraft:used/minecraft:black_carpet") ? PlayerStatJson.getString("minecraft:used/minecraft:black_carpet"):"0")
                            +Integer.parseInt(PlayerStatJson.has("minecraft:used/minecraft:light_gray_carpet") ? PlayerStatJson.getString("minecraft:used/minecraft:light_gray_carpet"):"0")
                            +Integer.parseInt(PlayerStatJson.has("minecraft:used/minecraft:yellow_carpet") ? PlayerStatJson.getString("minecraft:used/minecraft:yellow_carpet"):"0")
                            +Integer.parseInt(PlayerStatJson.has("minecraft:used/minecraft:blue_carpet") ? PlayerStatJson.getString("minecraft:used/minecraft:blue_carpet"):"0")
                            +Integer.parseInt(PlayerStatJson.has("minecraft:used/minecraft:green_carpet") ? PlayerStatJson.getString("minecraft:used/minecraft:green_carpet"):"0")
                            +Integer.parseInt(PlayerStatJson.has("minecraft:used/minecraft:gray_carpet") ? PlayerStatJson.getString("minecraft:used/minecraft:gray_carpet"):"0")
                            +Integer.parseInt(PlayerStatJson.has("minecraft:used/minecraft:magenta_carpet") ? PlayerStatJson.getString("minecraft:used/minecraft:magenta_carpet"):"0")
                            +Integer.parseInt(PlayerStatJson.has("minecraft:used/minecraft:light_blue_carpet") ? PlayerStatJson.getString("minecraft:used/minecraft:light_blue_carpet"):"0")
                            +Integer.parseInt(PlayerStatJson.has("minecraft:used/minecraft:cyan_carpet") ? PlayerStatJson.getString("minecraft:used/minecraft:cyan_carpet"):"0")
                            +Integer.parseInt(PlayerStatJson.has("minecraft:used/minecraft:orange_carpet") ? PlayerStatJson.getString("minecraft:used/minecraft:orange_carpet"):"0")
                            +Integer.parseInt(PlayerStatJson.has("minecraft:used/minecraft:red_carpet") ? PlayerStatJson.getString("minecraft:used/minecraft:red_carpet"):"0")
                            +Integer.parseInt(PlayerStatJson.has("minecraft:used/minecraft:brown_carpet") ? PlayerStatJson.getString("minecraft:used/minecraft:brown_carpet"):"0")
                            +Integer.parseInt(PlayerStatJson.has("minecraft:used/minecraft:lime_carpet") ? PlayerStatJson.getString("minecraft:used/minecraft:lime_carpet"):"0")
                            +Integer.parseInt(PlayerStatJson.has("minecraft:used/minecraft:white_carpet") ? PlayerStatJson.getString("minecraft:used/minecraft:white_carpet"):"0")
                            +Integer.parseInt(PlayerStatJson.has("minecraft:used/minecraft:pink_carpet") ? PlayerStatJson.getString("minecraft:used/minecraft:pink_carpet"):"0")
                            +Integer.parseInt(PlayerStatJson.has("minecraft:used/minecraft:purple_carpet") ? PlayerStatJson.getString("minecraft:used/minecraft:purple_carpet"):"0")
            );
            player.setMusician(Integer.parseInt(PlayerStatJson.has("minecraft:custom/minecraft:tune_noteblock") ? PlayerStatJson.getString("minecraft:custom/minecraft:tune_noteblock"):"0"));
            player.setPillager(
                    Integer.parseInt(PlayerStatJson.has("minecraft:killed/minecraft:pillager") ? PlayerStatJson.getString("minecraft:killed/minecraft:pillager"):"0")
                            +Integer.parseInt(PlayerStatJson.has("minecraft:killed/minecraft:vindicator") ? PlayerStatJson.getString("minecraft:killed/minecraft:vindicator"):"0")
                            +Integer.parseInt(PlayerStatJson.has("minecraft:killed/minecraft:ravager") ? PlayerStatJson.getString("minecraft:killed/minecraft:ravager"):"0")
                            +Integer.parseInt(PlayerStatJson.has("minecraft:killed/minecraft:witch") ? PlayerStatJson.getString("minecraft:killed/minecraft:witch"):"0")
                            +Integer.parseInt(PlayerStatJson.has("minecraft:killed/minecraft:evoker") ? PlayerStatJson.getString("minecraft:killed/minecraft:evoker"):"0")
            );
            player.setTree(
                    Integer.parseInt(PlayerStatJson.has("minecraft:used/minecraft:jungle_sapling") ? PlayerStatJson.getString("minecraft:used/minecraft:jungle_sapling"):"0")
                            +Integer.parseInt(PlayerStatJson.has("minecraft:used/minecraft:oak_sapling") ? PlayerStatJson.getString("minecraft:used/minecraft:oak_sapling"):"0")
                            +Integer.parseInt(PlayerStatJson.has("minecraft:used/minecraft:spruce_sapling") ? PlayerStatJson.getString("minecraft:used/minecraft:spruce_sapling"):"0")
                            +Integer.parseInt(PlayerStatJson.has("minecraft:used/minecraft:acacia_sapling") ? PlayerStatJson.getString("minecraft:used/minecraft:acacia_sapling"):"0")
                            +Integer.parseInt(PlayerStatJson.has("minecraft:used/minecraft:dark_oak_sapling") ? PlayerStatJson.getString("minecraft:used/minecraft:dark_oak_sapling"):"0")
                            +Integer.parseInt(PlayerStatJson.has("minecraft:used/minecraft:birch_sapling") ? PlayerStatJson.getString("minecraft:used/minecraft:birch_sapling"):"0")
            );
            player.setVillager(Integer.parseInt(PlayerStatJson.has("minecraft:custom/minecraft:traded_with_villager") ? PlayerStatJson.getString("minecraft:custom/minecraft:traded_with_villager"):"0"));
            player.setScaffolding(Integer.parseInt(PlayerStatJson.has("minecraft:used/minecraft:scaffolding") ? PlayerStatJson.getString("minecraft:used/minecraft:scaffolding"):"0"));
            player.setWither(
                    Integer.parseInt(PlayerStatJson.has("minecraft:killed/minecraft:wither") ? PlayerStatJson.getString("minecraft:killed/minecraft:wither"):"0") //凋零
                        +(int)((Integer.parseInt(PlayerStatJson.has("minecraft:used/minecraft:soul_sand") ? PlayerStatJson.getString("minecraft:used/minecraft:soul_sand"):"0")-Integer.parseInt(PlayerStatJson.has("minecraft:killed/minecraft:wither") ? PlayerStatJson.getString("minecraft:killed/minecraft:wither"):"0")*4)*0.8/4) //80%灵魂沙除4
            );
            player.setWriter(Integer.parseInt(PlayerStatJson.has("minecraft:used/minecraft:writable_book") ? PlayerStatJson.getString("minecraft:used/minecraft:writable_book"):"0"));
            player.setKillplayer(Integer.parseInt(PlayerStatJson.has("minecraft:custom/minecraft:player_kills") ? PlayerStatJson.getString("minecraft:custom/minecraft:player_kills"):"0"));
            player.setDead(Integer.parseInt(PlayerStatJson.has("minecraft:custom/minecraft:deaths") ? PlayerStatJson.getString("minecraft:custom/minecraft:deaths"):"0"));
            player.setFly(Long.parseLong(PlayerStatJson.has("minecraft:custom/minecraft:aviate_one_cm") ? PlayerStatJson.getString("minecraft:custom/minecraft:aviate_one_cm"):"0")); //cm转km

            JSONObject minejson = JSONObject.fromObject(player);
            GetRankStats(minejson.toString(), uuid);

            if(PlayerJson.getJSONObject("data").getString("banned").equals("false")){ //没被ban的计入排名
                if(player.getFly() < 0){
                    player.setFly(Long.parseUnsignedLong(Integer.toBinaryString((int)player.getFly()), 2));
                }
                redstone.put(uuid, (double)player.getRedstone());
                pillager.put(uuid, (double)player.getPillager());
                tree.put(uuid,(double)player.getTree());
                draw.put(uuid, (double)player.getDraw());
                aqua.put(uuid, (double)player.getAqua());
                villager.put(uuid, (double)player.getVillager());
                wither.put(uuid, (double)player.getWither());
                musician.put(uuid, (double)player.getMusician());
                writer.put(uuid, (double)player.getWriter());
                mine_obsidian.put(uuid, (double)player.getMine_obsidian());
                drug.put(uuid, (double)player.getDrug());
                fire.put(uuid, (double)player.getFire());
                scaffolding.put(uuid, (double)player.getScaffolding());
                killplayer.put(uuid, (double)player.getKillplayer());
                dead.put(uuid, (double)player.getDead());
                fly.put(uuid, (double)player.getFly());
            }
            System.out.println(uuid);
        }
        System.out.println("写入排名数据");
        Map<String, Double> redstoneRank = MapSort.sortMapByValue(redstone, 10);
        Map<String, Double> pillagerRank = MapSort.sortMapByValue(pillager, 10);
        Map<String, Double> treeRank = MapSort.sortMapByValue(tree, 10);
        Map<String, Double> drawRank = MapSort.sortMapByValue(draw, 10);
        Map<String, Double> aquaRank = MapSort.sortMapByValue(aqua, 10);
        Map<String, Double> villagerRank = MapSort.sortMapByValue(villager, 10);
        Map<String, Double> witherRank = MapSort.sortMapByValue(wither, 10);
        Map<String, Double> musicianRank = MapSort.sortMapByValue(musician, 10);
        Map<String, Double> writerRank = MapSort.sortMapByValue(writer, 10);
        Map<String, Double> mine_obsidianRank = MapSort.sortMapByValue(mine_obsidian, 10);
        Map<String, Double> drugRank = MapSort.sortMapByValue(drug, 10);
        Map<String, Double> fireRank = MapSort.sortMapByValue(fire, 10);
        Map<String, Double> scaffoldingRank = MapSort.sortMapByValue(scaffolding, 10);
        Map<String, Double> killplayerRank = MapSort.sortMapByValue(killplayer, 10);
        Map<String, Double> deadRank = MapSort.sortMapByValue(dead, 10);
        Map<String, Double> flyRank = MapSort.sortMapByValue(fly, 10);

        Rank.put("redstoneRank", redstoneRank);
        Rank.put("pillagerRank", pillagerRank);
        Rank.put("treeRank", treeRank);
        Rank.put("drawRank", drawRank);
        Rank.put("aquaRank", aquaRank);
        Rank.put("villagerRank", villagerRank);
        Rank.put("witherRank", witherRank);
        Rank.put("musicianRank", musicianRank);
        Rank.put("writerRank", writerRank);
        Rank.put("mine_obsidianRank", mine_obsidianRank);
        Rank.put("drugRank", drugRank);
        Rank.put("fireRank", fireRank);
        Rank.put("scaffoldingRank", scaffoldingRank);
        Rank.put("killplayerRank", killplayerRank);
        Rank.put("deadRank", deadRank);
        Rank.put("flyRank", flyRank);
        JSONObject rankjson = JSONObject.fromObject(Rank);
        GetRankStats(rankjson.toString());
        System.out.println("写入排名");
    }
}
