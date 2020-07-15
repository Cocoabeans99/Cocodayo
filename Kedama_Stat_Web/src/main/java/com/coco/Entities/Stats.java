package com.coco.Entities;

import org.springframework.stereotype.Component;

@Component
public class Stats {
    private int AllNum; //玩家总数
    private int BannedNum; //被ban的总数
    private int ActiveNum; //活跃玩家(当前日期减14天内有登录记录)总数
    private int[] AllNums; //历史玩家数(15天一算)

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
