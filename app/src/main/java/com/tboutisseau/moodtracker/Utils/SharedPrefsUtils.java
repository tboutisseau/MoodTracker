package com.tboutisseau.moodtracker.Utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.tboutisseau.moodtracker.Models.Mood;
import com.tboutisseau.moodtracker.R;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Objects;

public class SharedPrefsUtils {

    // keys to store the color, comment, position, and historylist in the sharedPreferences
    private static final String SHARED_PREFS = "SHARED_PREFS";
    static  final String BACKGROUND_COLOR = "BACKGROUND_COLOR";
    static final String KEY_COMMENT = "KEY_COMMENT";
    static final String KEY_POSITION = "KEY_POSITION";
    private static final String  KEY_LIST = "KEY_LIST";

    private static SharedPreferences sharedPreferences;
    private static SharedPreferences.Editor editor;


    private static void setSharedPrefs(Context context) {
        sharedPreferences = context.getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public static void saveColor (Context context, int backgroundColor) {
        setSharedPrefs(context);
        editor.putInt(BACKGROUND_COLOR, backgroundColor);
        editor.apply();
    }

    public static int getColor (Context context) {
        setSharedPrefs(context);
        return sharedPreferences.getInt(BACKGROUND_COLOR, R.color.default_black);
    }

    public static void saveComment (Context context, String comment) {
        setSharedPrefs(context);
        editor.putString(KEY_COMMENT, comment);
        editor.apply();
    }

    public static String getComment(Context context) {
        setSharedPrefs(context);
        return sharedPreferences.getString(KEY_COMMENT, "");
    }

    public static boolean containsComment (Context context) {
        setSharedPrefs(context);
        return sharedPreferences.contains(KEY_COMMENT);
    }

    public static void saveMoodPosition(Context context, int position) {
        setSharedPrefs(context);
        editor.putInt(KEY_POSITION, position);
        editor.apply();
    }

    // Default value 3 corresponds to the happy mood
    public static int getMoodPosition(Context context) {
        setSharedPrefs(context);
        return sharedPreferences.getInt(KEY_POSITION, 3);
    }

    public static boolean containsMood(Context context) {
        setSharedPrefs(context);
        return sharedPreferences.contains(KEY_POSITION);
    }

    static void removeMood(Context context, String key) {
        setSharedPrefs(context);
        editor.remove(key);
        editor.apply();
    }

    // Storing the historyList in the preferences using Gson object
    static void saveHistoryList(Context context, ArrayList moodHistoryList) {
        setSharedPrefs(context);
        Gson gson = new Gson();
        String json = gson.toJson(moodHistoryList);
        editor.putString(KEY_LIST, json);
        editor.apply();
    }

    public static ArrayList<Mood> getHistoryList(Context context) {
        setSharedPrefs(context);
        ArrayList<Mood> mHistoryList;
        Gson gson = new Gson();
        String json = sharedPreferences.getString(KEY_LIST, "");
        Type type = new TypeToken<ArrayList<Mood>>() {}.getType();
        if (Objects.requireNonNull(json).equals("")) {
            return null;
        }

        mHistoryList = gson.fromJson(json, type);
        return mHistoryList;
    }

    public static boolean containsHistoryList(Context context) {
        setSharedPrefs(context);
        return sharedPreferences.contains(KEY_LIST);
    }

    // Method to reset the SharedPreferences. Only for development purposes.
    public static void clearPreferences(Context context) {
        setSharedPrefs(context);
        editor.clear();
        editor.apply();
    }

}
