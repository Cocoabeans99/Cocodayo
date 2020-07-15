package com.coco.Dao;

public class GetNames {
    public static String[] get(){
        String nameString = GetStringFromJson.GetJson("C:\\Users\\Administrator\\Desktop\\KS\\Names.txt");

        String[] names = nameString.substring(1, nameString.length() - 1).split(",");

        return names;
    }
}
