package com.tboutisseau.moodtracker.Controllers.Activities;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.tboutisseau.moodtracker.Models.Mood;
import com.tboutisseau.moodtracker.R;
import com.tboutisseau.moodtracker.Utils.SharedPrefsUtils;

import java.util.ArrayList;

public class HistoryActivity extends AppCompatActivity {

    private ArrayList<Mood> historyList = new ArrayList<>();
    private final ArrayList<RelativeLayout> layoutsList = new ArrayList<>();
    private final ArrayList<ImageView> imageList = new ArrayList<>();
    private final ArrayList<Mood> backgroundList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        initLayoutsList();
        initImageViewsList();
        initBackgroundList();

        if (SharedPrefsUtils.containsHistoryList(this)) {
            historyList = SharedPrefsUtils.getHistoryList(this);
            setLayouts();
            setWidth();
            displayComment();
            setWidth();

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

    private void initBackgroundList() {
        Mood sadMood = new Mood(R.color.faded_red);
        Mood disapointedMood = new Mood(R.color.warm_grey);
        Mood normalMood = new Mood(R.color.cornflower_blue_65);
        Mood happyMood = new Mood(R.color.light_sage);
        Mood superHappyMood = new Mood (R.color.banana_yellow);
        Mood defaultMood = new Mood (R.color.default_orange);

        backgroundList.add(sadMood);
        backgroundList.add(disapointedMood);
        backgroundList.add(normalMood);
        backgroundList.add(happyMood);
        backgroundList.add(superHappyMood);
        backgroundList.add(defaultMood);
    }

    private void setLayouts() {

        for(int i = 0; i < historyList.size(); i++) {
            RelativeLayout relativeLayout = layoutsList.get(i);
            int position = historyList.get(i).getPosition();

            relativeLayout.setBackgroundColor(ContextCompat.getColor(this, backgroundList.get(position).getMoodBackgroundColor()));
            relativeLayout.setVisibility(View.VISIBLE);
        }
    }

    private int getScreenWidth() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.widthPixels;
    }

    private void setWidth() {

        for (int i = 0; i < historyList.size(); i++) {
            RelativeLayout relativeLayout = layoutsList.get(i);

            ViewGroup.LayoutParams layoutParams = relativeLayout.getLayoutParams();
            int position = historyList.get(i).position;

            screenWidth(position, layoutParams);
        }
    }

    private void screenWidth(int position, ViewGroup.LayoutParams layoutParams) {
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
}
