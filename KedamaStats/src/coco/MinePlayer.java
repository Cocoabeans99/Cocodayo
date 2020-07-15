package coco;

import java.math.BigDecimal;

public class MinePlayer {
    private int StoneDigNum; //挖掘石头数
    private int MineralDigNum; //挖掘矿物数
    private int DiamondNum; //挖掘钻石数
    private int EmeraldNum; //挖掘绿宝石数

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



}
