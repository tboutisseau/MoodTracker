package com.tboutisseau.moodtracker.Controllers.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.tboutisseau.moodtracker.Models.Mood;
import com.tboutisseau.moodtracker.R;
import com.tboutisseau.moodtracker.Utils.SharedPrefsUtils;

import java.util.ArrayList;

public class HistoryActivity extends AppCompatActivity {

    /**
     * Defining variables
     * historyList : the list of saved moods
     * layoutsList : list of relative layouts representing the saved mood for each day
     * imageList : list of images representing the comment icon for each day
     * textviewsList : list of textViews representing the name of the day
     */
    private ArrayList<Mood> historyList = new ArrayList<>();
    private final ArrayList<RelativeLayout> layoutsList = new ArrayList<>();
    private final ArrayList<ImageView> imageList = new ArrayList<>();
    private final ArrayList<TextView> textviewsList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        initLayoutsList();
        initImageViewsList();
        initTextviewsList();

        if (SharedPrefsUtils.containsHistoryList(this)) {
            historyList = SharedPrefsUtils.getHistoryList(this);
            setLayouts();
            setDateText();
            setWidth();
            displayComment();
            diplayNomoodSaved();

        }
    }

    private void initLayoutsList() {
        RelativeLayout relativeLayout7Days = findViewById(R.id.layout_7_days);
        RelativeLayout relativeLayout6Days = findViewById(R.id.layout_6_days);
        RelativeLayout relativeLayout5Days = findViewById(R.id.layout_5_days);
        RelativeLayout relativeLayout4Days = findViewById(R.id.layout_4_days);
        RelativeLayout relativeLayout3Days = findViewById(R.id.layout_3_days);
        RelativeLayout relativeLayout2Days = findViewById(R.id.layout_2_days);
        RelativeLayout relativeLayout1Days = findViewById(R.id.layout_1_days);

        layoutsList.add(relativeLayout7Days);
        layoutsList.add(relativeLayout6Days);
        layoutsList.add(relativeLayout5Days);
        layoutsList.add(relativeLayout4Days);
        layoutsList.add(relativeLayout3Days);
        layoutsList.add(relativeLayout2Days);
        layoutsList.add(relativeLayout1Days);
    }

    private void initImageViewsList() {
        ImageView imageView7Days = findViewById(R.id.imageview_7_days);
        ImageView imageView6Days = findViewById(R.id.imageview_6_days);
        ImageView imageView5Days = findViewById(R.id.imageview_5_days);
        ImageView imageView4Days = findViewById(R.id.imageview_4_days);
        ImageView imageView3Days = findViewById(R.id.imageview_3_days);
        ImageView imageView2Days = findViewById(R.id.imageview_2_days);
        ImageView imageView1Days = findViewById(R.id.imageview_1_days);

        imageList.add(imageView7Days);
        imageList.add(imageView6Days);
        imageList.add(imageView5Days);
        imageList.add(imageView4Days);
        imageList.add(imageView3Days);
        imageList.add(imageView2Days);
        imageList.add(imageView1Days);
    }

    private void initTextviewsList() {
        textviewsList.add((TextView) findViewById(R.id.textview_7_days));
        textviewsList.add((TextView) findViewById(R.id.textview_6_days));
        textviewsList.add((TextView) findViewById(R.id.textview_5_days));
        textviewsList.add((TextView) findViewById(R.id.textview_4_days));
        textviewsList.add((TextView) findViewById(R.id.textview_3_days));
        textviewsList.add((TextView) findViewById(R.id.textview_2_days));
        textviewsList.add((TextView) findViewById(R.id.textview_1_days));
    }


    /**
     * Method to set the proper background color on the bar representing the saved mood.
     */
    private void setLayouts() {

        for(int i = 0; i < historyList.size(); i++) {
            RelativeLayout relativeLayout = layoutsList.get(i);
            int backgroundColor = historyList.get(i).getMoodBackgroundColor();

            relativeLayout.setBackgroundResource(backgroundColor);
            relativeLayout.setVisibility(View.VISIBLE);
        }
    }

    private void setDateText() {

        for (int i = 0; i < historyList.size(); i++) {
            if (i == 0) {
                textviewsList.get(0).setText(R.string.one_days_ago);
            }
            if (i == 1) {
                textviewsList.get(0).setText(R.string.two_days_ago);
                textviewsList.get(1).setText(R.string.one_days_ago);
            }

            if (i == 2) {
                textviewsList.get(0).setText(R.string.three_days_ago);
                textviewsList.get(1).setText(R.string.two_days_ago);
                textviewsList.get(2).setText(R.string.one_days_ago);

            }

            if (i == 3) {
                textviewsList.get(0).setText(R.string.four_days_ago);
                textviewsList.get(1).setText(R.string.three_days_ago);
                textviewsList.get(2).setText(R.string.two_days_ago);
                textviewsList.get(3).setText(R.string.one_days_ago);
            }

            if (i == 4) {
                textviewsList.get(0).setText(R.string.five_days_ago);
                textviewsList.get(1).setText(R.string.four_days_ago);
                textviewsList.get(2).setText(R.string.three_days_ago);
                textviewsList.get(3).setText(R.string.two_days_ago);
                textviewsList.get(4).setText(R.string.one_days_ago);
            }

            if (i == 5) {
                textviewsList.get(0).setText(R.string.six_days_ago);
                textviewsList.get(1).setText(R.string.five_days_ago);
                textviewsList.get(2).setText(R.string.four_days_ago);
                textviewsList.get(3).setText(R.string.three_days_ago);
                textviewsList.get(4).setText(R.string.two_days_ago);
                textviewsList.get(5).setText(R.string.one_days_ago);
            }

            if (i == 6) {
                textviewsList.get(0).setText(R.string.seven_days_ago);
                textviewsList.get(1).setText(R.string.six_days_ago);
                textviewsList.get(2).setText(R.string.five_days_ago);
                textviewsList.get(3).setText(R.string.four_days_ago);
                textviewsList.get(4).setText(R.string.three_days_ago);
                textviewsList.get(5).setText(R.string.two_days_ago);
                textviewsList.get(6).setText(R.string.one_days_ago);
            }
        }
    }

    /**
     * method to get the width of the device screen
     * @return the width of the device screen in pixels
     */
    private int getScreenWidth() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.widthPixels;
    }

    /**
     * Method to set the width of the bar
     */
    private void setWidth() {

        for (int i = 0; i < historyList.size(); i++) {
            RelativeLayout relativeLayout = layoutsList.get(i);

            ViewGroup.LayoutParams layoutParams = relativeLayout.getLayoutParams();
            int position = historyList.get(i).position;

            moodScreenWidth(position, layoutParams);
        }
    }

    /**
     * Method to define the width of the bar depending on the mood
     * @param position position of the mood
     * @param layoutParams parameters of the relative layout representing one saved mood
     */
    private void moodScreenWidth(int position, ViewGroup.LayoutParams layoutParams) {
        int screenWidth = getScreenWidth();

        switch (position) {
            case 0 :
                layoutParams.width = screenWidth / 5;
                break;
            case 1 :
                layoutParams.width = (2 * screenWidth) / 5;
                break;
            case 2 :
                layoutParams.width = (3 * screenWidth) / 5;
                break;
            case 3 :
                layoutParams.width = (4 * screenWidth) / 5;
                break;
            case 4 :
                layoutParams.width = screenWidth;
                break;
            default :
                layoutParams.width = screenWidth;
                break;
        }
    }


    /**
     * Method to display the comment icon if a comment is saved.
     * On icon clicked, displays the comment in a toast message.
     */
    private void displayComment() {

        for(int i = 0; i < historyList.size(); i++) {
            ImageView imageView = imageList.get(i);

            String comment = historyList.get(i).getComment();

            if(!comment.isEmpty()) {
                imageView.setVisibility(View.VISIBLE);
                final int finalI = i;

                imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getApplicationContext(), historyList.get(finalI).getComment(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }
    }

    /**
     * Method to display the text NO MOOD SAVED when the default mood was saved that day
     */
    private void diplayNomoodSaved() {
        for(int i = 0; i < historyList.size(); i++) {
            TextView textView = textviewsList.get(i);

            if (historyList.get(i).getPosition() == 5) {
                textView.append(" - " + getResources().getString(R.string.no_mood_saved));
                textView.setTextColor(getResources().getColor(R.color.no_mood_saved_text));
            }
        }
    }
}
