package com.coco.Dao;

public class InsertArray {
    public static String[] insert(String[] arr, String str)
    {
        int size = arr.length;
        String[] tmp = new String[size + 1];
        System.arraycopy(arr, 0, tmp, 0, size);
        tmp[size - 1] = str;
        return tmp;
    }
}
