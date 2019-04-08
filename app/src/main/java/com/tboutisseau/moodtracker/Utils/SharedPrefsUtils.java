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

    /**
     * keys to store the color, comment, position, and historylist in the sharedPreferences
     */

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

    /**
     * Put the mood background color (as a resource not int) into the shared preferences using the appropriate key
     */

    public static void saveColor (Context context, int backgroundColor) {
        setSharedPrefs(context);
        editor.putInt(BACKGROUND_COLOR, backgroundColor);
        editor.apply();
    }

    /**
     * Retrieve the mood background color from the shared preferences
     */

    public static int getColor (Context context) {
        setSharedPrefs(context);
        return sharedPreferences.getInt(BACKGROUND_COLOR, R.color.default_black);
    }

    /**
     * Put the comment string into the shared preferences using the appropriate key
     * @param context
     * @param comment string containing the mood comment
     */
    public static void saveComment (Context context, String comment) {
        setSharedPrefs(context);
        editor.putString(KEY_COMMENT, comment);
        editor.apply();
    }

    /**
     * Retrieve the comment string from the shared preferences
     */

    public static String getComment(Context context) {
        setSharedPrefs(context);
        return sharedPreferences.getString(KEY_COMMENT, "");
    }

    /**
     * Boolean to check if shared prefs contains a comment string
     * @return true if a comment exists in shared prefs
     */

    public static boolean containsComment (Context context) {
        setSharedPrefs(context);
        return sharedPreferences.contains(KEY_COMMENT);
    }

    /**
     * Put the mood position int into shared prefs using the appropriate key
     * @param context
     * @param position of the mood in the moodsList ArrayList (0=sad, ...,  4=super happy, 5 = default)
     */

    public static void saveMoodPosition(Context context, int position) {
        setSharedPrefs(context);
        editor.putInt(KEY_POSITION, position);
        editor.apply();
    }

    /**
     * Retrieve the mood position int from shared prefs
     * Default value 3 corresponds to the happy mood
     * @param context
     * @return int position of the saved mood
     */
    public static int getMoodPosition(Context context) {
        setSharedPrefs(context);
        return sharedPreferences.getInt(KEY_POSITION, 3);
    }

    /**
     * Boolean to check if shared prefs contains a mood position (i.e a mood) (return false if not)
     * @param context
     * @return true if a position exists in shared prefs
     */
    public static boolean containsMood(Context context) {
        setSharedPrefs(context);
        return sharedPreferences.contains(KEY_POSITION);
    }


    /**
     * Used to remove the keys from shared prefs
     * @param context
     * @param key the key to remove from shared prefs (KEY_COMMENT, KEY_POSITION, BACKGROUND_COLOR)
     */
    static void removeMood(Context context, String key) {
        setSharedPrefs(context);
        editor.remove(key);
        editor.apply();
    }

    /**
     * Storing the historyList in shared prefs using Gson object, since we have to serialize the historyList ArrayList
     * to be able to store it in shard prefs
     * @param context
     * @param moodHistoryList
     */
    static void saveHistoryList(Context context, ArrayList moodHistoryList) {
        setSharedPrefs(context);
        Gson gson = new Gson();
        String json = gson.toJson(moodHistoryList);
        editor.putString(KEY_LIST, json);
        editor.apply();
    }

    /**
     * Retrieve the historyList from shared prefs using deserialization provided by Gson
     * @param context
     * @return ArrayList containing the saved moods
     */
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

    /**
     * Boolean to check if shared prefs contains a historyList
     * @param context
     * @return true if historyList exists in shared prefs
     */
    public static boolean containsHistoryList(Context context) {
        setSharedPrefs(context);
        return sharedPreferences.contains(KEY_LIST);
    }

    /**
     * Method to reset the SharedPreferences. Only used for development purposes.
     * @param context
     */
    public static void clearPreferences(Context context) {
        setSharedPrefs(context);
        editor.clear();
        editor.apply();
    }

}
