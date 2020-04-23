package com.example.android.recyclerview.database;

import android.content.Context;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;

import static android.app.PendingIntent.getActivity;

public class JSONuse {

    private static String TAG = JSONuse.class.getSimpleName();

    public static String loadJSONFromAsset(Context context) {
        String json = null;
        try {
//            InputStream is = getActivity().getAssets().open("yourfilename.json");
            InputStream is = context.getAssets().open("ListL00.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    public static JSONArray readJSONArray(String jsonStr) {
        JSONArray jsonArray = null;
        try {
            jsonArray = new JSONArray(jsonStr);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonArray;
    }

    public static TaskEntry readJObject(JSONArray array, int i) {

        int id;
        String name;
        String category;
        float cost;
        float amount;
        try {
            JSONObject ob = (JSONObject) array.get(i);
            id = Integer.valueOf((String) ob.get("ID"));
            name = (String) ob.get("name");
            amount = Float.valueOf((String) ob.get("amount"));
            cost = Float.valueOf((String) ob.get("cost"));
            category = (String) ob.get("category");
            Log.d(TAG, "" + id + "," + name + "," + cost + "," + amount + "," + category);
            return new TaskEntry(id, name, cost, amount, category);
        } catch (Exception e) {
            e.printStackTrace();

        }
        return null;
    }


}
