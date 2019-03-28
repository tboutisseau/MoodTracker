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
    private String TAG = "Save status";

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


        // Method to save the mood of the day
        updateData();

        // Log to test the receiver
        Log.i(TAG, "mood saved");

        // Toast to test the receiver
        Toast.makeText(context, "Mood saved", Toast.LENGTH_SHORT).show();

    }

    private void updateData() {
        //TODO
        //charger historylist
        // décaler d'un cran faire de la place pour le nouvel element
        // ajouter l'element
        // sauvegarder liste.
    }

    /**
     * When a new mood is saved in the array moodHistoryList, remove the keys position and comment from the preferences for the next day.
     * @param context
     */
    public void resetMood(Context context) {
        SharedPrefsUtils.removeMood(context, SharedPrefsUtils.SHARED_PREFS, SharedPrefsUtils.KEY_POSITION);
        SharedPrefsUtils.removeMood(context, SharedPrefsUtils.SHARED_PREFS, SharedPrefsUtils.KEY_COMMENT);
    }

    // If the size of the array of saved moods is greater than 6 remove the first entry of the list
    public void removeMood() {
        if(moodHistoryList.size() > 7) {
            moodHistoryList.remove(0);
        }
    }

}
