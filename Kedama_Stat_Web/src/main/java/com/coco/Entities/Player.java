package com.coco.Entities;

import com.coco.Dao.TimeTrans;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class Player {
    private String uuid;
    private String name;
    private String[] names;
    private boolean banned;
    private String seen;
    private String time_start;
    private String time_last;
    private int StoneDigNum; //挖掘石头数
    private int MineralDigNum; //挖掘矿物数
    private int DiamondNum; //挖掘钻石数
    private int EmeraldNum; //挖掘绿宝石数
    private String Played; //本月游玩时长

    //以下为Rank数据
    private int redstone;//使用红石
    private int pillager;//袭击
    private int tree;//种树
    private int draw;//地毯
    private int aqua;//溺尸
    private int villager;//村民交易
    private int wither;//击杀+灵魂沙/4
    private int musician;//调试音符盒
    private int writer;//用书与笔
    private int mine_obsidian;//挖黑曜石
    private int drug;//吃河豚
    private int fire;//使用打火石
    private int scaffolding;//使用脚手架
    private int killplayer;
    private int dead;
    private long fly;
    private long dig;
    private long kill;
    private long sand;
    private long gravel;
    private long shulker;

    public void setShulker(long shulker) {
        this.shulker = shulker;
    }

    public long getShulker() {
        return shulker;
    }

    public void setSand(long sand) {
        this.sand = sand;
    }

    public long getSand() {
        return sand;
    }

    public void setGravel(long gravel) {
        this.gravel = gravel;
    }

    public long getGravel() {
        return gravel;
    }
    public void setKill(long kill) {
        this.kill = kill;
    }

    public long getKill() {
        return kill;
    }

    public void setDig(long dig) {
        this.dig = dig;
    }

    public long getDig() {
        return dig;
    }

    public void setFly(long fly) {
        this.fly = fly;
    }

    public long getFly() {
        return fly;
    }

    public void setKillplayer(int killplayer) {
        this.killplayer = killplayer;
    }

    public void setDead(int dead) {
        this.dead = dead;
    }

    public int getKillplayer() {
        return killplayer;
    }

    public int getDead() {
        return dead;
    }

    public void setRedstone(int redstone) {
        this.redstone = redstone;
    }

    public void setPillager(int pillager) {
        this.pillager = pillager;
    }

    public void setTree(int tree) {
        this.tree = tree;
    }

    public void setDraw(int draw) {
        this.draw = draw;
    }

    public void setAqua(int aqua) {
        this.aqua = aqua;
    }

    public void setVillager(int villager) {
        this.villager = villager;
    }

    public void setWither(int wither) {
        this.wither = wither;
    }

    public void setMusician(int musician) {
        this.musician = musician;
    }

    public void setWriter(int writer) {
        this.writer = writer;
    }

    public void setMine_obsidian(int mine_obsidian) {
        this.mine_obsidian = mine_obsidian;
    }

    public void setDrug(int drug) {
        this.drug = drug;
    }

    public void setFire(int fire) {
        this.fire = fire;
    }

    public void setScaffolding(int scaffolding) {
        this.scaffolding = scaffolding;
    }

    public int getRedstone() {
        return redstone;
    }

    public int getPillager() {
        return pillager;
    }

    public int getTree() {
        return tree;
    }

    public int getDraw() {
        return draw;
    }

    public int getAqua() {
        return aqua;
    }

    public int getVillager() {
        return villager;
    }

    public int getWither() {
        return wither;
    }

    public int getMusician() {
        return musician;
    }

    public int getWriter() {
        return writer;
    }

    public int getMine_obsidian() {
        return mine_obsidian;
    }

    public int getDrug() {
        return drug;
    }

    public int getFire() {
        return fire;
    }

    public int getScaffolding() {
        return scaffolding;
    }

    public void setPlayed(String played) {
        Played = played;
    }

    public String getPlayed() {
        return Played;
    }

    public void setDiamondNum(int diamondNum) {
        DiamondNum = diamondNum;
    }

    public void setEmeraldNum(int emeraldNum) {
        EmeraldNum = emeraldNum;
    }

    public int getDiamondNum() {
        return DiamondNum;
    }

    public int getEmeraldNum() {
        return EmeraldNum;
    }

    private double MineralOfStone; //矿率
    private double DiamondOfMineral; //钻石占矿物
    private double EmeraldOfMineral; //绿宝石占矿物
    private double DiamondOfStone; //钻石占石头

    public void setStoneDigNum(int stoneDigNum) {
        StoneDigNum = stoneDigNum;
    }

    public void setMineralDigNum(int mineralDigNum) {
        MineralDigNum = mineralDigNum;
    }

    public void setMineralOfStone(double mineralOfStone) {
        MineralOfStone = mineralOfStone;
    }

    public void setDiamondOfMineral(double diamondOfMineral) {
        DiamondOfMineral = diamondOfMineral;
    }

    public void setEmeraldOfMineral(double emeraldOfMineral) {
        EmeraldOfMineral = emeraldOfMineral;
    }

    public void setDiamondOfStone(double diamondOfStone) {
        DiamondOfStone = diamondOfStone;
    }

    public void setEmeraldOfStone(double emeraldOfStone) {
        EmeraldOfStone = emeraldOfStone;
    }

    public int getStoneDigNum() {
        return StoneDigNum;
    }

    public int getMineralDigNum() {
        return MineralDigNum;
    }

    public double getMineralOfStone() {
        return MineralOfStone;
    }

    public double getDiamondOfMineral() {
        return DiamondOfMineral;
    }

    public double getEmeraldOfMineral() {
        return EmeraldOfMineral;
    }

    public double getDiamondOfStone() {
        return DiamondOfStone;
    }

    public double getEmeraldOfStone() {
        return EmeraldOfStone;
    }

    private double EmeraldOfStone; //绿宝石占石头

    public void setSeen(String seen) {
        this.seen = seen;
    }

    public void setTime_start(String time_start) {
        this.time_start = time_start;
    }

    public void setTime_last(String time_last) {
        this.time_last = time_last;
    }

    public void setTime_lived(String time_lived) {
        this.time_lived = time_lived;
    }

    public String getSeen() {
        return seen;
    }

    public String getTime_start() {
        return TimeTrans.secondToTime(Long.parseLong(time_start));
    }

    public String getTime_last() {
        return TimeTrans.secondToTime(Long.parseLong(time_last));
    }

    public String getTime_lived() {
        return new BigDecimal(Double.parseDouble(time_lived)/3600).setScale(2, 1).toString();
    }

    private String time_lived;

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNames(String[] names) {
        this.names = names;
    }

    public void setBanned(boolean banned) {
        this.banned = banned;
    }

    public String getUuid() {
        return uuid;
    }

    public String getName() {
        return name;
    }

    public String[] getNames() {
        return names;
    }

    public boolean isBanned() {
        return banned;
    }
}
