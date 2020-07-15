package coco;

public class Stats {
    private int AllNum; //玩家总数
    private int BannedNum; //被ban的总数
    private int ActiveNum; //活跃玩家(当前日期减14天内有登录记录)总数
    private int[] AllNums; //历史玩家数(15天一算)
    private String[] MONTHS_LAST; //月份数组

    public void setPLAYERS_LAST(int[] PLAYERS_LAST) {
        this.PLAYERS_LAST = PLAYERS_LAST;
    }

    public void setINCREASE(int[] INCREASE) {
        this.INCREASE = INCREASE;
    }

    public int[] getPLAYERS_LAST() {
        return PLAYERS_LAST;
    }

    public int[] getINCREASE() {
        return INCREASE;
    }

    private int[] PLAYERS_LAST; //玩家数组
    private int[] INCREASE; //增长数组

    public void setMONTHS_LAST(String[] MONTHS_LAST) {
        this.MONTHS_LAST = MONTHS_LAST;
    }

    public String[] getMONTHS_LAST() {
        return MONTHS_LAST;
    }

    public void setAllNum(int allNum) {
        AllNum = allNum;
    }

    public void setBannedNum(int bannedNum) {
        BannedNum = bannedNum;
    }

    public void setActiveNum(int activeNum) {
        ActiveNum = activeNum;
    }

    public void setAllNums(int[] allNums) {
        AllNums = allNums;
    }

    public int getAllNum() {
        return AllNum;
    }

    public int getBannedNum() {
        return BannedNum;
    }

    public int getActiveNum() {
        return ActiveNum;
    }

    public int[] getAllNums() {
        return AllNums;
    }
}
