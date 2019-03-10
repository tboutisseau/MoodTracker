package com.tboutisseau.moodtracker.Controllers.Activities;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.tboutisseau.moodtracker.Controllers.Fragments.PageAdapter;
import com.tboutisseau.moodtracker.Models.Mood;
import com.tboutisseau.moodtracker.R;
import com.tboutisseau.moodtracker.Utils.SaveDataReceiver;
import com.tboutisseau.moodtracker.Views.VerticalViewPager;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    //// Declaring variables
    private ArrayList<Mood> moodslist;

    // request code for the alarms pending intent
    public static final int ALARM_CODE = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final FloatingActionButton historyButton = findViewById(R.id.open_history_button);
        final FloatingActionButton addCommentButton = findViewById(R.id.add_comment_button);

        VerticalViewPager viewPager = findViewById(R.id.view_pager);

        PageAdapter adapter = new PageAdapter(this, getSupportFragmentManager());

        viewPager.setAdapter(adapter);

        // Set Happy mood as default
        viewPager.setCurrentItem(3);

        historyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startHistory();
            }
        });

        addCommentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAddCommentDialog();
            }
        });

        setAlarm();
    }

    /**
     * Setting the alarm to fire at 23:59:59 everyday, starting the save mood process
     */
    private void setAlarm() {

        // Make a new calendar instance
        Calendar calendar = Calendar.getInstance();

        // Set the time to 23:59:59
        calendar.get(Calendar.YEAR);
        calendar.get(Calendar.MONTH);
        calendar.get(Calendar.DAY_OF_MONTH);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);

        // Make the alarm manager
        AlarmManager alarmManager = (AlarmManager) MainActivity.this.getSystemService(ALARM_SERVICE);

        Intent intent = new Intent(MainActivity.this, SaveDataReceiver.class);

        // Make a pending intent to be called at 23:59:59
        PendingIntent pendingIntent = PendingIntent.getBroadcast(MainActivity.this, ALARM_CODE,
                intent, PendingIntent.FLAG_UPDATE_CURRENT);

        // Schedule time for the pending intent, and set the interval to a day
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);
    }


    // Method to display the dialog box to add a comment to a mood
    private void openAddCommentDialog() {
        final AlertDialog dialogBuilder = new AlertDialog.Builder(this).create();
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_comment, null);
        EditText mComment = dialogView.findViewById(R.id.edit_text_comment);
        Button cancelButton = dialogView.findViewById(R.id.cancel_button);
        Button validateButton = dialogView.findViewById(R.id.validate_button);

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogBuilder.dismiss();
            }
        });

        validateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Do something to validate the comment
            }
        });

        dialogBuilder.setView(dialogView);
        dialogBuilder.show();

    }

    // Method to start History activity
    private void startHistory() {
        Intent intent = new Intent(this, HistoryActivity.class);
        startActivity(intent);
    }
}
