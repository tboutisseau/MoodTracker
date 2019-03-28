package com.tboutisseau.moodtracker.Utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.tboutisseau.moodtracker.Models.Mood;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class SharedPrefsUtils {

    public static final String SHARED_PREFS = "SHARED_PREFS";
    public static final String KEY_COMMENT = "KEY_COMMENT";
    public static final String KEY_POSITION = "KEY_POSITION";
    public static final String  KEY_LIST = "KEY_LIST";


    public static void saveComment (Context context, String comment) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_COMMENT, comment);
        editor.apply();
    }

    public static String getComment(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_COMMENT, "");
    }

    public static boolean containsComment (Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        return sharedPreferences.contains(KEY_COMMENT);
    }

    public static void saveMoodPosition(Context context, int position) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(KEY_POSITION, position);
        editor.apply();
    }

    // Default value 3 corresponds to the happy mood
    public static int getMoodPosition(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        return sharedPreferences.getInt(KEY_POSITION, 3);
    }

    public static boolean containsMood(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        return sharedPreferences.contains(KEY_POSITION);
    }

    public static void removeMood(Context context, String prefsName, String key) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(prefsName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(key);
        editor.apply();
    }

    public static void saveHistoryList(Context context, ArrayList moodHistoryList) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(moodHistoryList);
        editor.putString(KEY_LIST, json);
        editor.apply();
    }

    public static ArrayList<Mood> getHistoryList(Context context) {
        ArrayList<Mood> mHistoryList;
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString(KEY_LIST, "");
        Type type = new TypeToken<ArrayList<Mood>>() {}.getType();
        if (json.equals("")) {
            return null;
        }

        mHistoryList = gson.fromJson(json, type);
        return mHistoryList;
    }

    public static boolean containsHistoryList(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        return sharedPreferences.contains(KEY_LIST);
    }

    public static void clearPreferences(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }

}
