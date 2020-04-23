package com.example.android.recyclerview.database;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import org.json.JSONArray;

import java.util.HashMap;

@Entity(tableName = "task")
public class TaskEntry {

    @PrimaryKey
    private int id;
    private String name;
    private float cost;
    private float amount;
    private String category;


    @Ignore
    public TaskEntry(String name, float cost, float amount, String category) {
        this.name = name;
        this.cost = cost;
        this.amount = amount;
        this.category = category;
    }

    public TaskEntry(int id, String name, float cost, float amount, String category) {
        this.id = id;
        this.name = name;
        this.cost = cost;
        this.amount = amount;
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public static TaskEntry[] populateDAta(JSONArray jsonArray) {
        int LEN = jsonArray.length();
        TaskEntry[] taskEntries = new TaskEntry[LEN];
        for (int i = 0; i < LEN; i++) {
            taskEntries[i] = JSONuse.readJObject(jsonArray,i);

        }
        return taskEntries;
    }
}
