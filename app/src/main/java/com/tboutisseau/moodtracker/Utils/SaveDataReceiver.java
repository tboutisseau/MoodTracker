package com.tboutisseau.moodtracker.Utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

/**
 * @author
 */
public class SaveDataReceiver extends BroadcastReceiver {
    private String TAG = "Save status";

    @Override
    public void onReceive(Context context, Intent intent) {

        // Method to save the mood of the day
        saveData();

        // Log to test the receiver
        Log.i(TAG, "mood saved");

        // Toast to test the receiver
        Toast.makeText(context, "Mood saved", Toast.LENGTH_SHORT).show();

    }

    private void saveData() {
    }

}
