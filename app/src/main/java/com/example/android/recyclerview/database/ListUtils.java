package com.example.android.recyclerview.database;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ListUtils {
    private static ListUtils mInstance;
    private static List<TaskEntry> mList;
    private static List<TaskEntry> bigList;
    private static List<Integer> index;
    private static Set<Integer> mClicked;

    public static ListUtils getInstance(List<TaskEntry> listGrocery, List<TaskEntry> list){
        if (mInstance == null){
            bigList = listGrocery;
            mList = list;
            index = new ArrayList<>();
            mClicked = new HashSet<>();
        }
        return mInstance;
    }

    public static List<TaskEntry> getmList() {
        return mList;
    }

    public static List<TaskEntry> getBigList() {
        return bigList;
    }

    public static Set<Integer> getIndex() {
        return mClicked;
    }

    public static void setmList(List<TaskEntry> mList) {
        ListUtils.mList = mList;
    }

    public static void setBigList(List<TaskEntry> bigList) {
        ListUtils.bigList = bigList;
    }

    public static void addIndex(int id){
//        index.add(id);
        mClicked.add(id);
    }

    public static void removeIndex(int id){
//        index.remove(Integer.valueOf(id)); // Suppose to remove value, not el. on position.
        mClicked.remove(Integer.valueOf(id));
    }
}
