package com.tboutisseau.moodtracker.Utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.tboutisseau.moodtracker.Models.Mood;

import java.util.ArrayList;

/**
 * @author
 */
public class SaveDataReceiver extends BroadcastReceiver {

    private ArrayList<Mood> moodHistoryList;

    @Override
    public void onReceive(Context context, Intent intent) {

        moodHistoryList = SharedPrefsUtils.getHistoryList(context);
        if (moodHistoryList == null) {
            moodHistoryList = new ArrayList<>();
        }

        if (SharedPrefsUtils.containsMood(context)) {
            moodHistoryList.add(new Mood(SharedPrefsUtils.getComment(context), SharedPrefsUtils.getMoodPosition(context)));
        } else {
            moodHistoryList.add(new Mood("", 5));
        }

        resetMood(context);
        removeMood();
        SharedPrefsUtils.saveHistoryList(context, moodHistoryList);

        // Log to test the receiver
        String TAG = "Save status";
        Log.i(TAG, "mood saved");

        // Toast to test the receiver
        Toast.makeText(context, "Mood saved", Toast.LENGTH_SHORT).show();

    }


    /**
     * When a new mood is saved in the array moodHistoryList, remove the keys position and comment from the preferences for the next day.
     * @param context
     */
    private void resetMood(Context context) {
        SharedPrefsUtils.removeMood(context, SharedPrefsUtils.KEY_POSITION);
        SharedPrefsUtils.removeMood(context, SharedPrefsUtils.KEY_COMMENT);
    }

    // If the size of the array of saved moods is greater than 7 remove the first entry of the list
    private void removeMood() {
        if(moodHistoryList.size() > 7) {
            moodHistoryList.remove(0);
        }
    }

}
