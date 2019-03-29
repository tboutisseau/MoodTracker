package com.tboutisseau.moodtracker.Controllers.Activities;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.tboutisseau.moodtracker.Controllers.Fragments.PageAdapter;
import com.tboutisseau.moodtracker.Models.Mood;
import com.tboutisseau.moodtracker.R;
import com.tboutisseau.moodtracker.Utils.SaveDataReceiver;
import com.tboutisseau.moodtracker.Utils.SharedPrefsUtils;
import com.tboutisseau.moodtracker.Views.VerticalViewPager;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    //// Declaring variables
    private List<Mood> moodsList;
    private MediaPlayer mMediaPlayer;

    private int mPosition;

    // request code for the alarms pending intent
    public static final int ALARM_CODE = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final FloatingActionButton historyButton = findViewById(R.id.open_history_button);
        final FloatingActionButton addCommentButton = findViewById(R.id.add_comment_button);

        initData();

        setUpViewPager();

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
     * Initiate the list of 5 moods
     */
    private void initData() {
        if (moodsList == null) {
            moodsList = new ArrayList<>();
            Mood sadMood = new Mood(R.color.faded_red, R.drawable.smiley_sad, R.raw.sound1, 0);
            Mood disappointedMood = new Mood(R.color.warm_grey, R.drawable.smiley_disappointed, R.raw.sound2, 1);
            Mood normalMood = new Mood(R.color.cornflower_blue_65, R.drawable.smiley_normal, R.raw.sound3, 2);
            Mood happyMood = new Mood(R.color.light_sage, R.drawable.smiley_happy, R.raw.sound4, 3);
            Mood superHappyMood = new Mood(R.color.banana_yellow, R.drawable.smiley_super_happy, R.raw.sound5, 4);

            moodsList.add(sadMood);
            moodsList.add(disappointedMood);
            moodsList.add(normalMood);
            moodsList.add(happyMood);
            moodsList.add(superHappyMood);
        }
    }

    /**
     * Method to set up the viewpager, play a sound and save the position on page selected
     */
    private void setUpViewPager() {
        final VerticalViewPager viewPager = findViewById(R.id.view_pager);

        PageAdapter adapter = new PageAdapter(this, getSupportFragmentManager(), moodsList);

        viewPager.setAdapter(adapter);

        mPosition = SharedPrefsUtils.getMoodPosition(this);

        viewPager.setCurrentItem(mPosition);

        VerticalViewPager.OnPageChangeListener pageChangeListener = new ViewPager.SimpleOnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                SharedPrefsUtils.saveMoodPosition(MainActivity.this, position);
                Toast.makeText(MainActivity.this, "position saved", Toast.LENGTH_SHORT).show();

                Mood currentMood = moodsList.get(position);
                int sound = currentMood.getMoodSound();

                if (mMediaPlayer != null) {
                    mMediaPlayer.release();
                }

                mMediaPlayer = MediaPlayer.create(MainActivity.this, sound);

                mMediaPlayer.start();

            }
        };

        viewPager.addOnPageChangeListener(pageChangeListener);
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
        calendar.set(Calendar.HOUR_OF_DAY, 10);
        calendar.set(Calendar.MINUTE, 25);
        calendar.set(Calendar.SECOND, 00);

        // Make the alarm manager
        AlarmManager alarmManager = (AlarmManager) MainActivity.this.getSystemService(ALARM_SERVICE);

        Intent intent = new Intent(MainActivity.this, SaveDataReceiver.class);

        // Make a pending intent to be called at 23:59:59
        PendingIntent pendingIntent = PendingIntent.getBroadcast(MainActivity.this, ALARM_CODE,
                intent, PendingIntent.FLAG_UPDATE_CURRENT);

        // Schedule time for the pending intent, and set the interval to a day
        alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), 5*60*1000, pendingIntent);
    }


    // Method to display the dialog box to add a comment to a mood
    private void openAddCommentDialog() {
        final AlertDialog dialogBuilder = new AlertDialog.Builder(this).create();
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_comment, null);
        final EditText mComment = dialogView.findViewById(R.id.edit_text_comment);

        if (SharedPrefsUtils.containsComment(getApplicationContext())) {
            mComment.setText(SharedPrefsUtils.getComment(this));
        }

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
                SharedPrefsUtils.saveComment(MainActivity.this, mComment.getText().toString());
                if (SharedPrefsUtils.containsComment(getBaseContext())) {
                    Toast.makeText(MainActivity.this, "comment saved", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "new comment saved", Toast.LENGTH_SHORT).show();
                }
                dialogBuilder.dismiss();
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

    @Override
    protected void onPause() {
        super.onPause();

    }

    /**
     * Release the MediaPlayer when the activity stops to free up memory
     */
    @Override
    public void onStop() {
        super.onStop();
        if (mMediaPlayer != null) {
            mMediaPlayer.release();
        }

    }

}
