package com.coco.Entities;

public class RankPlayer {
    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    private String name; //名字
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
    private double fly;
    private String fly_show;
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

    public void setFly_show(String fly_show) {
        this.fly_show = fly_show;
    }

    public String getFly_show() {
        return fly_show;
    }

    public void setFly(double fly) {
        this.fly = fly;
    }

    public double getFly() {
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
}
