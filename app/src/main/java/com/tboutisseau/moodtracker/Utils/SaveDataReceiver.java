package com.tboutisseau.moodtracker.Utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.tboutisseau.moodtracker.Models.Mood;
import com.tboutisseau.moodtracker.R;

import java.util.ArrayList;

/**
 * @author Todd
 * BroadcastReceiver used to add the mood of the day stored in shared prefs
 * (or the default one if none was saved that day) to the historyList.
 * Resets the shared prefs and limits the size of historyList to 7 (days).
 */
public class SaveDataReceiver extends BroadcastReceiver {

    private ArrayList<Mood> moodHistoryList;

    @Override
    public void onReceive(Context context, Intent intent) {

        /**
         * Retrieve the ArrayList used to store the moods for 7 days from shared prefs
         * If it does not exist, create a new one
         */
        moodHistoryList = SharedPrefsUtils.getHistoryList(context);
        if (moodHistoryList == null) {
            moodHistoryList = new ArrayList<>();
        }

        /**
         * If a mood is saved in the SharedPreferences add its position and possible comment to the history list
         * Else add a default mood (position 5) with an empty comment
         */
        if (SharedPrefsUtils.containsMood(context)) {
            moodHistoryList.add(new Mood(SharedPrefsUtils.getColor(context), SharedPrefsUtils.getComment(context), SharedPrefsUtils.getMoodPosition(context)));
        } else {
            moodHistoryList.add(new Mood(R.color.default_black,"", 5));
        }

        resetMood(context);
        controlListSize();
        SharedPrefsUtils.saveHistoryList(context, moodHistoryList);

        /**
         * Log to test that the receiver triggers
         */
        String TAG = "Save status";
        Log.i(TAG, "mood saved");

        // Toast to test the receiver
        //Toast.makeText(context, "Mood saved", Toast.LENGTH_SHORT).show();
    }

    /**
     * After a new mood has been saved in the array moodHistoryList, remove the keys position ,comment, and color from shared prefs.
     * So the next day shared prefs is ready to save new keys
     * @param context
     */
    private void resetMood(Context context) {
        SharedPrefsUtils.removeMood(context, SharedPrefsUtils.BACKGROUND_COLOR);
        SharedPrefsUtils.removeMood(context, SharedPrefsUtils.KEY_POSITION);
        SharedPrefsUtils.removeMood(context, SharedPrefsUtils.KEY_COMMENT);
    }

    /**
     * If the size of the ArrayList of saved moods is greater than 7 remove the first entry of the list.
     * That way it stores only the last 7 moods.
     */
    private void controlListSize() {
        if(moodHistoryList.size() > 7) {
            moodHistoryList.remove(0);
        }
    }

}
