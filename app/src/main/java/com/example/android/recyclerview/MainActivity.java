/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.recyclerview;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.android.recyclerview.database.AppDatabase;
import com.example.android.recyclerview.database.TaskEntry;

import java.util.List;

public class MainActivity extends AppCompatActivity implements GreenAdapter.ItemClickListener {

    private static final int NUM_LIST_ITEMS = 100;
    private static final String LOG_TAG = MainActivity.class.getSimpleName();

    /*
     * References to RecyclerView and Adapter to reset the list to its
     * "pretty" state when the reset menu item is clicked.
     */
    private GreenAdapter mAdapter;
    private RecyclerView mNumbersList;
    private AppDatabase mDb;
    private List<TaskEntry> mTasks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*
         * Using findViewById, we get a reference to our RecyclerView from xml. This allows us to
         * do things like set the adapter of the RecyclerView and toggle the visibility.
         */
        mNumbersList = (RecyclerView) findViewById(R.id.rv_numbers);

        /*
         * A LinearLayoutManager is responsible for measuring and positioning item views within a
         * RecyclerView into a linear list. This means that it can produce either a horizontal or
         * vertical list depending on which parameter you pass in to the LinearLayoutManager
         * constructor. By default, if you don't specify an orientation, you get a vertical list.
         * In our case, we want a vertical list, so we don't need to pass in an orientation flag to
         * the LinearLayoutManager constructor.
         *
         * There are other LayoutManagers available to display your data in uniform grids,
         * staggered grids, and more! See the developer documentation for more details.
         */
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mNumbersList.setLayoutManager(layoutManager);

        /*
         * Use this setting to improve performance if you know that changes in content do not
         * change the child layout size in the RecyclerView
         */
        mNumbersList.setHasFixedSize(true);

        /*
         * The GreenAdapter is responsible for displaying each item in the list.
         */
//        mAdapter = new GreenAdapter(NUM_LIST_ITEMS);
        mAdapter = new GreenAdapter(this, this);
        mNumbersList.setAdapter(mAdapter);

        mDb = AppDatabase.getInstance(this.getApplicationContext());
        List<TaskEntry> entryList = mDb.taskDao().loadAllTasks();
        Log.d(LOG_TAG, "Got DB-handle. List length" + entryList.size());
    }

    @Override
    protected void onResume() {
        super.onResume();
//        List<TaskEntry> entryList = mDb.taskDao().loadAllTasks();
//        Log.d(LOG_TAG, "***** List length *****" + entryList.size());
        retreiveTasks();


    }

    private void retreiveTasks() {
        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                final List<TaskEntry> entryList = mDb.taskDao().loadAllTasks();
//                final List<TaskEntry> entryList = mDb.taskDao().loadVegetables();
                mTasks = entryList;
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mAdapter.setTasks(mTasks);
//                        int id;
//                        String category;
//                        String name;

//                        for (TaskEntry item : entryList) {
//                            id = item.getId();
//                            name = item.getName();
//                            category = item.getCategory();
//                            id = item.getId();
//                            Log.d(LOG_TAG, "" + id + ", " + name + ", " + category);
//
//                        }
                    }
                });
            }
        });

    }

    @Override
    public void onItemClickListener(int itemId) {
        Log.d(LOG_TAG, "Item clcked: " + itemId);
        Toast.makeText(this, "Item clicked:" + itemId, Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //return super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull final MenuItem item) {
        boolean my_return = true;

        switch (item.getItemId()) {
            case R.id.bn_vegetab: case R.id.bn_cans:
             case R.id.bn_cereals: case R.id.bn_drinks:
             case R.id.bn_milky:  case R.id.bn_households:
             case R.id.bn_ordinary:
                Log.d(LOG_TAG, "Menu clicked");
                break;
            default:
                my_return = super.onOptionsItemSelected(item);
        }
        retreiveQuery(item.getItemId());
        return my_return;


    }

    private void retreiveQuery(final int item) {

        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                List<TaskEntry> list = null;
                switch (item) {
                    case R.id.bn_vegetab:
                        Log.d(LOG_TAG, "Menu: vegetables");
                        list = mDb.taskDao().loadVegetables();
                        break;
                    case R.id.bn_cans:
                        Log.d(LOG_TAG, "Menu: cans");
                        list = mDb.taskDao().loadCans();
                        break;
                    case R.id.bn_cereals:
                        Log.d(LOG_TAG, "Menu: cereals");
                        list = mDb.taskDao().loadCereals();
                        break;
                    case R.id.bn_drinks:
                        Log.d(LOG_TAG, "Menu: drinks");
                        list = mDb.taskDao().loadDrinks();
                        break;
                    case R.id.bn_milky:
                        Log.d(LOG_TAG, "Menu: milky");
                        list = mDb.taskDao().loadMilky();
                        break;
                    case R.id.bn_households:
                        Log.d(LOG_TAG, "Menu: households");
                        list = mDb.taskDao().loadHouseholds();
                        break;
                    case R.id.bn_ordinary:
                        Log.d(LOG_TAG, "Menu: ordinary");
                        break;
                    default:
                        break;
                }
                if (list != null){
                    int id;
                    String category;
                    String name;

                    for (TaskEntry item : list) {
                        id = item.getId();
                        name = item.getName();
                        category = item.getCategory();
                        id = item.getId();
                        Log.d(LOG_TAG, "" + id + ", " + name + ", " + category);

                    }
                }

//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        mAdapter.setTasks(mTasks);
//                    }
//                });
            }
        });

    }

}
