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

}
