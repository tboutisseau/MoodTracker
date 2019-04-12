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
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.tboutisseau.moodtracker.Controllers.Fragments.PageAdapter;
import com.tboutisseau.moodtracker.Models.Mood;
import com.tboutisseau.moodtracker.R;
import com.tboutisseau.moodtracker.Utils.SaveDataReceiver;
import com.tboutisseau.moodtracker.Utils.SharedPrefsUtils;
import com.tboutisseau.moodtracker.Widget.VerticalViewPager;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    //// Declaring variables
    private List<Mood> moodsList;
    private MediaPlayer mMediaPlayer;

    // request code for the alarms pending intent
    private static final int ALARM_CODE = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final FloatingActionButton historyButton = findViewById(R.id.open_history_button);
        final FloatingActionButton addCommentButton = findViewById(R.id.add_comment_button);
        final FloatingActionButton pieChartButton = findViewById(R.id.chart_button);

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

        pieChartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startChartActivity();
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
            Mood sadMood = new Mood(R.color.faded_red, R.drawable.smiley_sad, R.raw.sad_sound, 0);
            Mood disappointedMood = new Mood(R.color.warm_grey, R.drawable.smiley_disappointed, R.raw.disapointed_sound, 1);
            Mood normalMood = new Mood(R.color.cornflower_blue_65, R.drawable.smiley_normal, R.raw.normal_sound, 2);
            Mood happyMood = new Mood(R.color.light_sage, R.drawable.smiley_happy, R.raw.happy_sound, 3);
            Mood superHappyMood = new Mood(R.color.banana_yellow, R.drawable.smiley_super_happy, R.raw.superhappy_sound, 4);

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

        // Creating the PageAdapter and passing it the list of the 5 moods to cycle through
        PageAdapter adapter = new PageAdapter(this, getSupportFragmentManager(), moodsList);
        viewPager.setAdapter(adapter);

        // Declaring an int position to retrieve the position in shared prefs
        int position = SharedPrefsUtils.getMoodPosition(this);

        // If shared prefs contains a position for the mood then we restore it in the ViewPager
        // Else we set it to the default position for happy mood wich is 3
        if (SharedPrefsUtils.containsMood(this)) {
            viewPager.setCurrentItem(position);
        } else {
            viewPager.setCurrentItem(3);
        }

        // Declaring the OnPageChangeListener
        VerticalViewPager.OnPageChangeListener pageChangeListener = new ViewPager.SimpleOnPageChangeListener() {

            // Setting up pageChangeListener to save the mood position and color each time a new page is selected
            @Override
            public void onPageSelected(int position) {
                SharedPrefsUtils.saveColor(MainActivity.this, moodsList.get(position).getMoodBackgroundColor());
                SharedPrefsUtils.saveMoodPosition(MainActivity.this, position);
                //Toast.makeText(MainActivity.this, "position saved", Toast.LENGTH_SHORT).show();

                // creating int sound that retrieves the sound of the selected mood
                int sound = moodsList.get(position).getMoodSound();

                // Release the mediaPlayer if it exists to free up memory
                if (mMediaPlayer != null) {
                    mMediaPlayer.release();
                }

                // Creating the mediaPlayer to play the proper sound
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
        calendar.set(Calendar.HOUR_OF_DAY, 14);
        calendar.set(Calendar.MINUTE, 36);
        calendar.set(Calendar.SECOND, 0);

        // Make the alarm manager
        AlarmManager alarmManager = (AlarmManager) MainActivity.this.getSystemService(ALARM_SERVICE);

        Intent intent = new Intent(MainActivity.this, SaveDataReceiver.class);

        // Make a pending intent to be called at 23:59:59
        PendingIntent pendingIntent = PendingIntent.getBroadcast(MainActivity.this, ALARM_CODE,
                intent, PendingIntent.FLAG_UPDATE_CURRENT);

        // Schedule time for the pending intent, and set the interval to a day
        alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), 3*60*1000, pendingIntent);
    }

    /**
     * displaying the dialog box to add a comment to a mood
     */
    private void openAddCommentDialog() {
        final AlertDialog dialogBuilder = new AlertDialog.Builder(this).create();
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_comment, null);
        final EditText mComment = dialogView.findViewById(R.id.edit_text_comment);

        // display the comment prevoiusly saved in the edit text of the dialog
        if (SharedPrefsUtils.containsComment(getApplicationContext())) {
            mComment.setText(SharedPrefsUtils.getComment(this));
        }

        // Getting the buttons
        Button cancelButton = dialogView.findViewById(R.id.cancel_button);
        Button validateButton = dialogView.findViewById(R.id.validate_button);

        // Setting up the cancel button
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogBuilder.dismiss();
            }
        });

        // Setting up the validation button
        validateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPrefsUtils.saveComment(MainActivity.this, mComment.getText().toString());
//                if (SharedPrefsUtils.containsComment(getBaseContext())) {
//                    Toast.makeText(MainActivity.this, "comment saved", Toast.LENGTH_SHORT).show();
//                } else {
//                    Toast.makeText(MainActivity.this, "new comment saved", Toast.LENGTH_SHORT).show();
//                }
                dialogBuilder.dismiss();
            }
        });

        dialogBuilder.setView(dialogView);
        dialogBuilder.show();

    }

    /**
     * Method to start History activity
     */
    private void startHistory() {
        Intent intent = new Intent(this, HistoryActivity.class);
        startActivity(intent);
    }

    /**
     * Method to start the chart activity
     */
    private void startChartActivity() {
        Intent intent = new Intent(this, ChartActivity.class);
        startActivity(intent);
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
