package com.example.android.recyclerview.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface TaskDao {
    @Query("SELECT * FROM task ORDER BY id")
    List<TaskEntry> loadAllTasks();

    @Insert
    void insertTask(TaskEntry taskEntry);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateTask(TaskEntry taskEntry);

    @Delete
    void deleteTask(TaskEntry taskEntry);

    @Insert
    void insertAll(TaskEntry... taskEntries);

    @Query("SELECT * FROM task WHERE category='VEGETABLE'")
    List<TaskEntry> loadVegetables();

    @Query("SELECT * FROM task WHERE category='DRINKS'")
    List<TaskEntry> loadDrinks();

    @Query("SELECT * FROM task WHERE category='CANS'")
    List<TaskEntry> loadCans();

    @Query("SELECT * FROM task WHERE category='HOUSEHOLDS'")
    List<TaskEntry> loadHouseholds();

    @Query("SELECT * FROM task WHERE category='CEREALS'")
    List<TaskEntry> loadCereals();

    @Query("SELECT * FROM task WHERE category='MILKY'")
    List<TaskEntry> loadMilky();

}
