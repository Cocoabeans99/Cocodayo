package com.coco.controller;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;
import com.coco.Dao.*;
import com.coco.Entities.Player;
import com.coco.Entities.RankPlayer;
import com.coco.Entities.Stats;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Controller
public class MainController {

    @Autowired
    Player player;

    @Autowired
    Stats stats;

    /*
     * 获取txt文件中的数字，即之前的访问量
     * 传入参数为： 字符串: txtFilePath,文件的绝对路径
     */
    public static Long Get_Visit_Count(String txtFilePath) {

        try {
            //读取文件(字符流)
            BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(txtFilePath), "UTF-8"));
            //循环读取数据
            String str = null;
            StringBuffer content = new StringBuffer();
            while ((str = in.readLine()) != null) {
                content.append(str);
            }
            //关闭流
            in.close();

            //System.out.println(content);
            // 解析获取的数据
            Long count = Long.valueOf(content.toString());
            count++; // 访问量加1
            //写入相应的文件
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(txtFilePath), "UTF-8"));
            out.write(String.valueOf(count));

            //清楚缓存
            out.flush();
            //关闭流
            out.close();

            return count;
        } catch (Exception e) {
            e.printStackTrace();
            return 0L;
        }
    }

    public static String big2(double d) {
        BigDecimal d1 = new BigDecimal(Double.toString(d));
        BigDecimal d2 = new BigDecimal(Integer.toString(1));
        // 四舍五入,保留2位小数
        return d1.divide(d2, 2, BigDecimal.ROUND_HALF_UP).toString();
    }

    @RequestMapping({"/", "/index"})
    public String main(Model model){
        String txtFilePath = "C:\\Users\\Administrator\\Desktop\\KS\\count.txt";
        Long count = Get_Visit_Count(txtFilePath);
        System.out.println(count);

        JSONObject stats=JSONObject.parseObject(GetStringFromJson.GetJson("C:\\Users\\Administrator\\Desktop\\KS\\AllStats.json"));
        JSONObject stat = stats.getJSONObject("stats");

        String Stringincrease = stat.getString("INCREASE");
        String incStr = Stringincrease.substring(1, Stringincrease.length()-1);
        String[] incStrArray = incStr.split(",");
        int[] increases = new int[incStrArray.length];
        for(int i=0; i < incStrArray.length; i++)
        {
            increases[i] = Integer.parseInt(incStrArray[i]);
        }

        String Stringplayer = stat.getString("PLAYERS_LAST");
        String plyStr = Stringplayer.substring(1, Stringplayer.length()-1);
        String[] plyStrArray = plyStr.split(",");
        int[] players = new int[plyStrArray.length];
        for(int i=0; i < plyStrArray.length; i++)
        {
            players[i] = Integer.parseInt(plyStrArray[i]);
        }

        String Stringmonth = stat.getString("MONTHS_LAST");
        String monStr = Stringmonth.substring(1, Stringmonth.length()-1);
        String[] months = monStr.split(",");
        for(int i=0; i < months.length; i++)
        {
            months[i] = months[i].substring(1, months[i].length()-1);
        }

        JSONObject Newplayer=JSONObject.parseObject(GetStringFromJson.GetJson("C:\\Users\\Administrator\\Desktop\\KS\\NewPlayers.json"), Feature.OrderedField);
        JSONObject Activeplayer=JSONObject.parseObject(GetStringFromJson.GetJson("C:\\Users\\Administrator\\Desktop\\KS\\ActivePlayers.json"), Feature.OrderedField);
        List<Player> NewPlayerRank = new ArrayList<Player>();
        List<Player> ActivePlayerRank = new ArrayList<Player>();
        for (Map.Entry entry : Newplayer.entrySet()) {
            Player p = new Player();
            String name = String.valueOf(entry.getKey());
            BigDecimal bd = new BigDecimal(String.valueOf(entry.getValue()));
            p.setUuid(FindPlayerById.Find(name));
            p.setTime_start(bd.toPlainString());
            p.setName(name);
            NewPlayerRank.add(p);
        }model.addAttribute("newplayers", NewPlayerRank);
        for (Map.Entry entry : Activeplayer.entrySet()) {
            Player p = new Player();
            String name = String.valueOf(entry.getKey());
            BigDecimal bd = new BigDecimal(String.valueOf(entry.getValue())).divide(new BigDecimal("3600"),1,BigDecimal.ROUND_HALF_UP); //秒转小时
            p.setUuid(FindPlayerById.Find(name));
            p.setPlayed(bd.toPlainString());
            p.setName(name);
            ActivePlayerRank.add(p);
        }model.addAttribute("activeplayers", ActivePlayerRank);

        model.addAttribute("activeNum",stat.getString("activeNum"));
        model.addAttribute("allNum",stat.getString("allNum"));
        model.addAttribute("bannedNum",stat.getString("bannedNum"));
        model.addAttribute("increases",increases);
        model.addAttribute("months",months);
        model.addAttribute("players",players);
        model.addAttribute("thismonth",increases[increases.length-1]);

        String[] names = GetNames.get();
        model.addAttribute("names", names);
        return "index";
    }

    @RequestMapping("/rank")
    public String rank(Model model){

        System.out.println("rank");

        JSONObject PlayerJson=JSONObject.parseObject(GetStringFromJson.GetJson("C:\\Users\\Administrator\\Desktop\\KS\\Rank.json"), Feature.OrderedField);

        List<RankPlayer> redstoneRank = new ArrayList<RankPlayer>();
        List<RankPlayer> pillagerRank = new ArrayList<RankPlayer>();
        List<RankPlayer> treeRank = new ArrayList<RankPlayer>();
        List<RankPlayer> drawRank = new ArrayList<RankPlayer>();
        List<RankPlayer> aquaRank = new ArrayList<RankPlayer>();
        List<RankPlayer> villagerRank = new ArrayList<RankPlayer>();
        List<RankPlayer> witherRank = new ArrayList<RankPlayer>();
        List<RankPlayer> musicianRank = new ArrayList<RankPlayer>();
        List<RankPlayer> writerRank = new ArrayList<RankPlayer>();
        List<RankPlayer> mine_obsidianRank = new ArrayList<RankPlayer>();
        List<RankPlayer> drugRank = new ArrayList<RankPlayer>();
        List<RankPlayer> fireRank = new ArrayList<RankPlayer>();
        List<RankPlayer> scaffoldingRank = new ArrayList<RankPlayer>();
        List<RankPlayer> killplayerRank = new ArrayList<RankPlayer>();
        List<RankPlayer> deadRank = new ArrayList<RankPlayer>();
        List<RankPlayer> flyRank = new ArrayList<RankPlayer>();
        List<RankPlayer> digRank = new ArrayList<RankPlayer>();
        List<RankPlayer> killRank = new ArrayList<RankPlayer>();
        List<RankPlayer> sandRank = new ArrayList<RankPlayer>();
        List<RankPlayer> gravelRank = new ArrayList<RankPlayer>();
        List<RankPlayer> shulkerRank = new ArrayList<RankPlayer>();

        JSONObject redstone = PlayerJson.getJSONObject("redstoneRank");
        JSONObject pillager = PlayerJson.getJSONObject("pillagerRank");
        JSONObject tree = PlayerJson.getJSONObject("treeRank");
        JSONObject draw = PlayerJson.getJSONObject("drawRank");
        JSONObject aqua = PlayerJson.getJSONObject("aquaRank");
        JSONObject villager = PlayerJson.getJSONObject("villagerRank");
        JSONObject wither = PlayerJson.getJSONObject("witherRank");
        JSONObject musician = PlayerJson.getJSONObject("musicianRank");
        JSONObject writer = PlayerJson.getJSONObject("writerRank");
        JSONObject mine_obsidian = PlayerJson.getJSONObject("mine_obsidianRank");
        JSONObject drug = PlayerJson.getJSONObject("drugRank");
        JSONObject fire = PlayerJson.getJSONObject("fireRank");
        JSONObject scaffolding = PlayerJson.getJSONObject("scaffoldingRank");
        JSONObject killplayer = PlayerJson.getJSONObject("killplayerRank");
        JSONObject dead = PlayerJson.getJSONObject("deadRank");
        JSONObject fly = PlayerJson.getJSONObject("flyRank");
        JSONObject dig = PlayerJson.getJSONObject("digRank");
        JSONObject kill = PlayerJson.getJSONObject("killRank");
        JSONObject sand = PlayerJson.getJSONObject("sandRank");
        JSONObject gravel = PlayerJson.getJSONObject("gravelRank");
        JSONObject shulker = PlayerJson.getJSONObject("shulkerRank");

        for (Map.Entry entry : shulker.entrySet()) {
            RankPlayer p = new RankPlayer();
            String uuid = String.valueOf(entry.getKey());
            p.setShulker(Long.parseLong(String.valueOf(entry.getValue())));
            p.setName(FindPlayerByUuid.Find(uuid));
            shulkerRank.add(p);
        }model.addAttribute("shulkerRank", shulkerRank);

        for (Map.Entry entry : sand.entrySet()) {
            RankPlayer p = new RankPlayer();
            String uuid = String.valueOf(entry.getKey());
            p.setSand(Long.parseLong(String.valueOf(entry.getValue())));
            p.setName(FindPlayerByUuid.Find(uuid));
            sandRank.add(p);
        }model.addAttribute("sandRank", sandRank);

        for (Map.Entry entry : gravel.entrySet()) {
            RankPlayer p = new RankPlayer();
            String uuid = String.valueOf(entry.getKey());
            p.setGravel(Long.parseLong(String.valueOf(entry.getValue())));
            p.setName(FindPlayerByUuid.Find(uuid));
            gravelRank.add(p);
        }model.addAttribute("gravelRank", gravelRank);

        for (Map.Entry entry : kill.entrySet()) {
            RankPlayer p = new RankPlayer();
            String uuid = String.valueOf(entry.getKey());
            p.setKill(Long.parseLong(String.valueOf(entry.getValue())));
            p.setName(FindPlayerByUuid.Find(uuid));
            killRank.add(p);
        }model.addAttribute("killRank", killRank);

        for (Map.Entry entry : dig.entrySet()) {
            RankPlayer p = new RankPlayer();
            String uuid = String.valueOf(entry.getKey());
            p.setDig(Long.parseLong(String.valueOf(entry.getValue())));
            p.setName(FindPlayerByUuid.Find(uuid));
            digRank.add(p);
        }model.addAttribute("digRank", digRank);

        for (Map.Entry entry : fly.entrySet()) {
            RankPlayer p = new RankPlayer();
            String uuid = String.valueOf(entry.getKey());
            p.setFly_show(big2(Double.parseDouble(String.valueOf(entry.getValue())) / 1000000));
            p.setName(FindPlayerByUuid.Find(uuid));
            flyRank.add(p);
        }model.addAttribute("flyRank", flyRank);

        for (Map.Entry entry : killplayer.entrySet()) {
            RankPlayer p = new RankPlayer();
            String uuid = String.valueOf(entry.getKey());
            p.setKillplayer(Integer.parseInt(String.valueOf(entry.getValue())));
            p.setName(FindPlayerByUuid.Find(uuid));
            killplayerRank.add(p);
        }model.addAttribute("killplayerRank", killplayerRank);

        for (Map.Entry entry : dead.entrySet()) {
            RankPlayer p = new RankPlayer();
            String uuid = String.valueOf(entry.getKey());
            p.setDead(Integer.parseInt(String.valueOf(entry.getValue())));
            p.setName(FindPlayerByUuid.Find(uuid));
            deadRank.add(p);
        }model.addAttribute("deadRank", deadRank);

        for (Map.Entry entry : redstone.entrySet()) {
            RankPlayer p = new RankPlayer();
            String uuid = String.valueOf(entry.getKey());
            p.setRedstone(Integer.parseInt(String.valueOf(entry.getValue())));
            p.setName(FindPlayerByUuid.Find(uuid));
            redstoneRank.add(p);
        }model.addAttribute("redstoneRank", redstoneRank);

        for (Map.Entry entry : pillager.entrySet()) {
            RankPlayer p = new RankPlayer();
            String uuid = String.valueOf(entry.getKey());
            p.setPillager(Integer.parseInt(String.valueOf(entry.getValue())));
            p.setName(FindPlayerByUuid.Find(uuid));
            pillagerRank.add(p);
        }model.addAttribute("pillagerRank", pillagerRank);

        for (Map.Entry entry : tree.entrySet()) {
            RankPlayer p = new RankPlayer();
            String uuid = String.valueOf(entry.getKey());
            p.setTree(Integer.parseInt(String.valueOf(entry.getValue())));
            p.setName(FindPlayerByUuid.Find(uuid));
            treeRank.add(p);
        }model.addAttribute("treeRank", treeRank);

        for (Map.Entry entry : draw.entrySet()) {
            RankPlayer p = new RankPlayer();
            String uuid = String.valueOf(entry.getKey());
            p.setDraw(Integer.parseInt(String.valueOf(entry.getValue())));
            p.setName(FindPlayerByUuid.Find(uuid));
            drawRank.add(p);
        }model.addAttribute("drawRank", drawRank);

        for (Map.Entry entry : aqua.entrySet()) {
            RankPlayer p = new RankPlayer();
            String uuid = String.valueOf(entry.getKey());
            p.setAqua(Integer.parseInt(String.valueOf(entry.getValue())));
            p.setName(FindPlayerByUuid.Find(uuid));
            aquaRank.add(p);
        }model.addAttribute("aquaRank", aquaRank);

        for (Map.Entry entry : villager.entrySet()) {
            RankPlayer p = new RankPlayer();
            String uuid = String.valueOf(entry.getKey());
            p.setVillager(Integer.parseInt(String.valueOf(entry.getValue())));
            p.setName(FindPlayerByUuid.Find(uuid));
            villagerRank.add(p);
        }model.addAttribute("villagerRank", villagerRank);

        for (Map.Entry entry : wither.entrySet()) {
            RankPlayer p = new RankPlayer();
            String uuid = String.valueOf(entry.getKey());
            p.setWither(Integer.parseInt(String.valueOf(entry.getValue())));
            p.setName(FindPlayerByUuid.Find(uuid));
            witherRank.add(p);
        }model.addAttribute("witherRank", witherRank);

        for (Map.Entry entry : musician.entrySet()) {
            RankPlayer p = new RankPlayer();
            String uuid = String.valueOf(entry.getKey());
            p.setMusician(Integer.parseInt(String.valueOf(entry.getValue())));
            p.setName(FindPlayerByUuid.Find(uuid));
            musicianRank.add(p);
        }model.addAttribute("musicianRank", musicianRank);

        for (Map.Entry entry : writer.entrySet()) {
            RankPlayer p = new RankPlayer();
            String uuid = String.valueOf(entry.getKey());
            p.setWriter(Integer.parseInt(String.valueOf(entry.getValue())));
            p.setName(FindPlayerByUuid.Find(uuid));
            writerRank.add(p);
        }model.addAttribute("writerRank", writerRank);

        for (Map.Entry entry : mine_obsidian.entrySet()) {
            RankPlayer p = new RankPlayer();
            String uuid = String.valueOf(entry.getKey());
            p.setMine_obsidian(Integer.parseInt(String.valueOf(entry.getValue())));
            p.setName(FindPlayerByUuid.Find(uuid));
            mine_obsidianRank.add(p);
        }model.addAttribute("mine_obsidianRank", mine_obsidianRank);

        for (Map.Entry entry : drug.entrySet()) {
            RankPlayer p = new RankPlayer();
            String uuid = String.valueOf(entry.getKey());
            p.setDrug(Integer.parseInt(String.valueOf(entry.getValue())));
            p.setName(FindPlayerByUuid.Find(uuid));
            drugRank.add(p);
        }model.addAttribute("drugRank", drugRank);

        for (Map.Entry entry : fire.entrySet()) {
            RankPlayer p = new RankPlayer();
            String uuid = String.valueOf(entry.getKey());
            p.setFire(Integer.parseInt(String.valueOf(entry.getValue())));
            p.setName(FindPlayerByUuid.Find(uuid));
            fireRank.add(p);
        }model.addAttribute("fireRank", fireRank);

        for (Map.Entry entry : scaffolding.entrySet()) {
            RankPlayer p = new RankPlayer();
            String uuid = String.valueOf(entry.getKey());
            p.setScaffolding(Integer.parseInt(String.valueOf(entry.getValue())));
            p.setName(FindPlayerByUuid.Find(uuid));
            scaffoldingRank.add(p);
        }model.addAttribute("scaffoldingRank", scaffoldingRank);

        String[] names = GetNames.get();
        model.addAttribute("names", names);
        return "rank";
    }

//    @RequestMapping("/check")
//    public String check(Model model){
//
//        System.out.println("check");
//
//        JSONObject PlayerJson=JSONObject.parseObject(GetStringFromJson.GetJson("C:\\Users\\Administrator\\Desktop\\KS\\MineCheckRank.json"), Feature.OrderedField);
//        List<Player> diamondcheck = new ArrayList<Player>();
//        List<Player> emeraldcheck = new ArrayList<Player>();
//        JSONObject DiamondCheckJson = PlayerJson.getJSONObject("DiamondPersentRank");
//        JSONObject EmeraldCheckJson = PlayerJson.getJSONObject("EmeraldPersentRank");
//        for (Map.Entry entry : DiamondCheckJson.entrySet()) {
//            Player p = new Player();
//            p.setDiamondOfMineral(Double.parseDouble(String.valueOf(entry.getValue()).substring(0, 5)));
//            String uuid = String.valueOf(entry.getKey());
//            JSONObject Json=JSONObject.parseObject(GetStringFromJson.GetJson("C:\\Users\\Administrator\\Desktop\\KS\\MineCheck\\"+uuid+".json"));
//            p.setDiamondNum(Json.getInteger("diamondNum"));
//            p.setName(FindPlayerByUuid.Find(uuid));
//            p.setUuid(uuid);
//            diamondcheck.add(p);
//        }
//        for(Map.Entry entry : EmeraldCheckJson.entrySet()){
//            Player p = new Player();
//            p.setEmeraldOfMineral(Double.parseDouble(String.valueOf(entry.getValue()).substring(0, 5)));
//            String uuid = String.valueOf(entry.getKey());
//            JSONObject Json=JSONObject.parseObject(GetStringFromJson.GetJson("C:\\Users\\Administrator\\Desktop\\KS\\MineCheck\\"+uuid+".json"));
//            p.setEmeraldNum(Json.getInteger("emeraldNum"));
//            p.setName(FindPlayerByUuid.Find(uuid));
//            p.setUuid(uuid);
//            emeraldcheck.add(p);
//        }
//        model.addAttribute("diamondcheck", diamondcheck);
//        model.addAttribute("emeraldcheck", emeraldcheck);
//
//        String[] names = GetNames.get();
//        model.addAttribute("names", names);
//        return "check";
//    }

    @RequestMapping("/player")
    public String player(@RequestParam(value="playerid") String id, Model model){
        //System.out.println(id+"1");
        String uuid = FindPlayerById.Find(id); //获取玩家uuid
        if(uuid!=""){
            System.out.println("查询"+id);
            //System.out.println("C:\\Users\\Administrator\\Desktop\\KS\\Player\\Kedama-" + uuid + "-Stats.json");
            JSONObject PlayerJson=JSONObject.parseObject(GetStringFromJson.GetJson("C:\\Users\\Administrator\\Desktop\\KS\\Player\\Kedama-" + uuid + "-Stats.json"));
            JSONObject PlayerDataJson = PlayerJson.getJSONObject("data");
            JSONObject PlayerStatJson = PlayerJson.getJSONObject("stats");

            player.setName(PlayerDataJson.getString("playername"));
            player.setUuid(PlayerDataJson.getString("uuid_short"));
            player.setBanned(PlayerDataJson.getBoolean("banned"));
            //player.setNames(PlayerDataJson.getJSONObject(j)("playername"));
            player.setSeen(PlayerDataJson.getString("seen"));
            player.setTime_start(PlayerDataJson.getString("time_start"));
            player.setTime_last(PlayerDataJson.getString("time_last"));
            player.setTime_lived(PlayerDataJson.getString("time_lived"));
            player.setDrug(Integer.parseInt(PlayerStatJson.containsKey("minecraft:used/minecraft:pufferfish") ? PlayerStatJson.getString("minecraft:used/minecraft:pufferfish"):"0"));
            player.setRedstone(
                    Integer.parseInt(PlayerStatJson.containsKey("minecraft:used/minecraft:redstone") ? PlayerStatJson.getString("minecraft:used/minecraft:redstone"):"0") //红石粉
                            +Integer.parseInt(PlayerStatJson.containsKey("minecraft:used/minecraft:redstone_block") ? PlayerStatJson.getString("minecraft:used/minecraft:redstone_block"):"0") //红石块
                            +Integer.parseInt(PlayerStatJson.containsKey("minecraft:used/minecraft:redstone_torch") ? PlayerStatJson.getString("minecraft:used/minecraft:redstone_torch"):"0") //红石火把
                            +Integer.parseInt(PlayerStatJson.containsKey("minecraft:used/minecraft:repeater") ? PlayerStatJson.getString("minecraft:used/minecraft:repeater"):"0") //中继器
                            +Integer.parseInt(PlayerStatJson.containsKey("minecraft:used/minecraft:comparator") ? PlayerStatJson.getString("minecraft:used/minecraft:comparator"):"0") //比较器
                            +Integer.parseInt(PlayerStatJson.containsKey("minecraft:used/minecraft:observer") ? PlayerStatJson.getString("minecraft:used/minecraft:observer"):"0") //侦测器
                            +Integer.parseInt(PlayerStatJson.containsKey("minecraft:used/minecraft:sticky_piston") ? PlayerStatJson.getString("minecraft:used/minecraft:sticky_piston"):"0") //粘性活塞
                            +Integer.parseInt(PlayerStatJson.containsKey("minecraft:used/minecraft:piston") ? PlayerStatJson.getString("minecraft:used/minecraft:piston"):"0") //活塞
                            +Integer.parseInt(PlayerStatJson.containsKey("minecraft:used/minecraft:slime_block") ? PlayerStatJson.getString("minecraft:used/minecraft:slime_block"):"0") //粘液块
                            +Integer.parseInt(PlayerStatJson.containsKey("minecraft:used/minecraft:lever") ? PlayerStatJson.getString("minecraft:used/minecraft:lever"):"0") //拉杆
                            +Integer.parseInt(PlayerStatJson.containsKey("minecraft:used/minecraft:hopper") ? PlayerStatJson.getString("minecraft:used/minecraft:hopper"):"0") //漏斗
            );
            player.setAqua(Integer.parseInt(PlayerStatJson.containsKey("minecraft:killed/minecraft:drowned") ? PlayerStatJson.getString("minecraft:killed/minecraft:drowned"):"0"));
            player.setFire(Integer.parseInt(PlayerStatJson.containsKey("minecraft:used/minecraft:flint_and_steel") ? PlayerStatJson.getString("minecraft:used/minecraft:flint_and_steel"):"0"));
            player.setMine_obsidian(Integer.parseInt(PlayerStatJson.containsKey("minecraft:mined/minecraft:obsidian") ? PlayerStatJson.getString("minecraft:mined/minecraft:obsidian"):"0"));
            player.setDraw(
                    Integer.parseInt(PlayerStatJson.containsKey("minecraft:used/minecraft:black_carpet") ? PlayerStatJson.getString("minecraft:used/minecraft:black_carpet"):"0")
                            +Integer.parseInt(PlayerStatJson.containsKey("minecraft:used/minecraft:light_gray_carpet") ? PlayerStatJson.getString("minecraft:used/minecraft:light_gray_carpet"):"0")
                            +Integer.parseInt(PlayerStatJson.containsKey("minecraft:used/minecraft:yellow_carpet") ? PlayerStatJson.getString("minecraft:used/minecraft:yellow_carpet"):"0")
                            +Integer.parseInt(PlayerStatJson.containsKey("minecraft:used/minecraft:blue_carpet") ? PlayerStatJson.getString("minecraft:used/minecraft:blue_carpet"):"0")
                            +Integer.parseInt(PlayerStatJson.containsKey("minecraft:used/minecraft:green_carpet") ? PlayerStatJson.getString("minecraft:used/minecraft:green_carpet"):"0")
                            +Integer.parseInt(PlayerStatJson.containsKey("minecraft:used/minecraft:gray_carpet") ? PlayerStatJson.getString("minecraft:used/minecraft:gray_carpet"):"0")
                            +Integer.parseInt(PlayerStatJson.containsKey("minecraft:used/minecraft:magenta_carpet") ? PlayerStatJson.getString("minecraft:used/minecraft:magenta_carpet"):"0")
                            +Integer.parseInt(PlayerStatJson.containsKey("minecraft:used/minecraft:light_blue_carpet") ? PlayerStatJson.getString("minecraft:used/minecraft:light_blue_carpet"):"0")
                            +Integer.parseInt(PlayerStatJson.containsKey("minecraft:used/minecraft:cyan_carpet") ? PlayerStatJson.getString("minecraft:used/minecraft:cyan_carpet"):"0")
                            +Integer.parseInt(PlayerStatJson.containsKey("minecraft:used/minecraft:orange_carpet") ? PlayerStatJson.getString("minecraft:used/minecraft:orange_carpet"):"0")
                            +Integer.parseInt(PlayerStatJson.containsKey("minecraft:used/minecraft:red_carpet") ? PlayerStatJson.getString("minecraft:used/minecraft:red_carpet"):"0")
                            +Integer.parseInt(PlayerStatJson.containsKey("minecraft:used/minecraft:brown_carpet") ? PlayerStatJson.getString("minecraft:used/minecraft:brown_carpet"):"0")
                            +Integer.parseInt(PlayerStatJson.containsKey("minecraft:used/minecraft:lime_carpet") ? PlayerStatJson.getString("minecraft:used/minecraft:lime_carpet"):"0")
                            +Integer.parseInt(PlayerStatJson.containsKey("minecraft:used/minecraft:white_carpet") ? PlayerStatJson.getString("minecraft:used/minecraft:white_carpet"):"0")
                            +Integer.parseInt(PlayerStatJson.containsKey("minecraft:used/minecraft:pink_carpet") ? PlayerStatJson.getString("minecraft:used/minecraft:pink_carpet"):"0")
                            +Integer.parseInt(PlayerStatJson.containsKey("minecraft:used/minecraft:purple_carpet") ? PlayerStatJson.getString("minecraft:used/minecraft:purple_carpet"):"0")
            );
            player.setMusician(Integer.parseInt(PlayerStatJson.containsKey("minecraft:used/minecraft:note_block") ? PlayerStatJson.getString("minecraft:used/minecraft:note_block"):"0"));
            player.setPillager(
                    Integer.parseInt(PlayerStatJson.containsKey("minecraft:killed/minecraft:pillager") ? PlayerStatJson.getString("minecraft:killed/minecraft:pillager"):"0")
                            +Integer.parseInt(PlayerStatJson.containsKey("minecraft:killed/minecraft:vindicator") ? PlayerStatJson.getString("minecraft:killed/minecraft:vindicator"):"0")
                            +Integer.parseInt(PlayerStatJson.containsKey("minecraft:killed/minecraft:ravager") ? PlayerStatJson.getString("minecraft:killed/minecraft:ravager"):"0")
                            +Integer.parseInt(PlayerStatJson.containsKey("minecraft:killed/minecraft:witch") ? PlayerStatJson.getString("minecraft:killed/minecraft:witch"):"0")
                            +Integer.parseInt(PlayerStatJson.containsKey("minecraft:killed/minecraft:evoker") ? PlayerStatJson.getString("minecraft:killed/minecraft:evoker"):"0")
            );
            player.setTree(
                    Integer.parseInt(PlayerStatJson.containsKey("minecraft:used/minecraft:jungle_sapling") ? PlayerStatJson.getString("minecraft:used/minecraft:jungle_sapling"):"0")
                            +Integer.parseInt(PlayerStatJson.containsKey("minecraft:used/minecraft:oak_sapling") ? PlayerStatJson.getString("minecraft:used/minecraft:oak_sapling"):"0")
                            +Integer.parseInt(PlayerStatJson.containsKey("minecraft:used/minecraft:spruce_sapling") ? PlayerStatJson.getString("minecraft:used/minecraft:spruce_sapling"):"0")
                            +Integer.parseInt(PlayerStatJson.containsKey("minecraft:used/minecraft:acacia_sapling") ? PlayerStatJson.getString("minecraft:used/minecraft:acacia_sapling"):"0")
                            +Integer.parseInt(PlayerStatJson.containsKey("minecraft:used/minecraft:dark_oak_sapling") ? PlayerStatJson.getString("minecraft:used/minecraft:dark_oak_sapling"):"0")
                            +Integer.parseInt(PlayerStatJson.containsKey("minecraft:used/minecraft:birch_sapling") ? PlayerStatJson.getString("minecraft:used/minecraft:birch_sapling"):"0")
            );
            player.setVillager(Integer.parseInt(PlayerStatJson.containsKey("minecraft:custom/minecraft:traded_with_villager") ? PlayerStatJson.getString("minecraft:custom/minecraft:traded_with_villager"):"0"));
            player.setScaffolding(Integer.parseInt(PlayerStatJson.containsKey("minecraft:used/minecraft:scaffolding") ? PlayerStatJson.getString("minecraft:used/minecraft:scaffolding"):"0"));
            player.setWither(Integer.parseInt(PlayerStatJson.containsKey("minecraft:killed/minecraft:wither") ? PlayerStatJson.getString("minecraft:killed/minecraft:wither"):"0"));
            player.setWriter(Integer.parseInt(PlayerStatJson.containsKey("minecraft:used/minecraft:writable_book") ? PlayerStatJson.getString("minecraft:used/minecraft:writable_book"):"0"));
            player.setKillplayer(Integer.parseInt(PlayerStatJson.containsKey("minecraft:custom/minecraft:player_kills") ? PlayerStatJson.getString("minecraft:custom/minecraft:player_kills"):"0"));
            player.setDead(Integer.parseInt(PlayerStatJson.containsKey("minecraft:custom/minecraft:deaths") ? PlayerStatJson.getString("minecraft:custom/minecraft:deaths"):"0"));
            player.setFly(
                    (long)Double.parseDouble(
                        String.valueOf(
                                Long.parseUnsignedLong(
                                        Integer.toBinaryString(
                                                (int)Long.parseLong(
                                                        PlayerStatJson.containsKey("minecraft:custom/minecraft:aviate_one_cm") ? PlayerStatJson.getString("minecraft:custom/minecraft:aviate_one_cm"):"0"
                                                )
                                        ), 2
                                )
                        )
                    ) / 1000
            );
            player.setSand(Integer.parseInt(PlayerStatJson.containsKey("minecraft:mined/minecraft:sand") ? PlayerStatJson.getString("minecraft:mined/minecraft:sand"):"0"));
            player.setGravel(Integer.parseInt(PlayerStatJson.containsKey("minecraft:mined/minecraft:gravel") ? PlayerStatJson.getString("minecraft:mined/minecraft:gravel"):"0"));
            player.setShulker(Long.parseLong(PlayerStatJson.containsKey("minecraft:killed/minecraft:shulker") ? PlayerStatJson.getString("minecraft:killed/minecraft:shulker"):"0"));

            long dignum = 0;
            if(PlayerJson.containsKey("stats_source")) {
                JSONObject PlayerStatSourceJson0 = PlayerJson.getJSONObject("stats_source");
                if(PlayerStatSourceJson0.containsKey("stats")) {
                    JSONObject PlayerStatSourceJson1 = PlayerStatSourceJson0.getJSONObject("stats");
                    if(PlayerStatSourceJson1.containsKey("minecraft:mined")) {
                        JSONObject PlayerStatSourceJson = PlayerStatSourceJson1.getJSONObject("minecraft:mined");
                        Iterator<String> it = PlayerStatSourceJson.keySet().iterator();
                        while(it.hasNext()){
                            // 获得key
                            String key = it.next();
                            String value = PlayerStatSourceJson.getString(key);
                            dignum += Long.parseLong(value);
                        }
                        player.setDig(dignum); //挖掘总数
                    }
                }
            }

            long killnum = 0;
            if(PlayerJson.containsKey("stats_source")) {
                JSONObject PlayerStatSourceJson0 = PlayerJson.getJSONObject("stats_source");
                if(PlayerStatSourceJson0.containsKey("stats")) {
                    JSONObject PlayerStatSourceJson1 = PlayerStatSourceJson0.getJSONObject("stats");
                    if(PlayerStatSourceJson1.containsKey("minecraft:killed")) {
                        JSONObject PlayerStatSourceJson = PlayerStatSourceJson1.getJSONObject("minecraft:killed");
                        Iterator<String> it = PlayerStatSourceJson.keySet().iterator();
                        while(it.hasNext()){
                            // 获得key
                            String key = it.next();
                            String value = PlayerStatSourceJson.getString(key);
                            killnum += Long.parseLong(value);
                        }
                        player.setKill(killnum); //杀怪总数
                    }
                }
            }

            JSONObject PlayerMineJson=JSONObject.parseObject(GetStringFromJson.GetJson("C:\\Users\\Administrator\\Desktop\\KS\\MineCheck\\" + uuid + ".json"));
            player.setStoneDigNum(Integer.parseInt(PlayerMineJson.getString("stoneDigNum")));
            player.setMineralDigNum(Integer.parseInt(PlayerMineJson.getString("mineralDigNum")));
            player.setDiamondNum(Integer.parseInt(PlayerMineJson.getString("diamondNum")));
            player.setEmeraldNum(Integer.parseInt(PlayerMineJson.getString("emeraldNum")));
            player.setMineralOfStone(Double.parseDouble(PlayerMineJson.getString("mineralOfStone")));
            player.setDiamondOfStone(Double.parseDouble(PlayerMineJson.getString("diamondOfStone")));
            player.setDiamondOfMineral(Double.parseDouble(PlayerMineJson.getString("diamondOfMineral")));
            player.setEmeraldOfStone(Double.parseDouble(PlayerMineJson.getString("emeraldOfStone")));
            player.setEmeraldOfMineral(Double.parseDouble(PlayerMineJson.getString("emeraldOfMineral")));

            model.addAttribute("player", player);

            String[] names = GetNames.get();
            model.addAttribute("names", names);
            return "player";
        }else{
            return "404";
        }
    }
}
