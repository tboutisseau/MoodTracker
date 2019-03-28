package com.tboutisseau.moodtracker.Controllers.Activities;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

    private RelativeLayout mRelativeLayout7Days;
    private RelativeLayout mRelativeLayout6Days;
    private RelativeLayout mRelativeLayout5Days;
    private RelativeLayout mRelativeLayout4Days;
    private RelativeLayout mRelativeLayout3Days;
    private RelativeLayout mRelativeLayout2Days;
    private RelativeLayout mRelativeLayout1Days;

    private ImageView mImageView7Days;
    private ImageView mImageView6Days;
    private ImageView mImageView5Days;
    private ImageView mImageView4Days;
    private ImageView mImageView3Days;
    private ImageView mImageView2Days;
    private ImageView mImageView1Days;

//    private TextView mTextView7Days;
//    private TextView mTextView6Days;
//    private TextView mTextView5Days;
//    private TextView mTextView4Days;
//    private TextView mTextView3Days;
//    private TextView mTextView2Days;
//    private TextView mTextView1Days;

    public ArrayList<Mood> historyList = new ArrayList<>();
    public ArrayList<RelativeLayout> layoutsList = new ArrayList<>();
    public ArrayList<ImageView> imageList = new ArrayList<>();
    public ArrayList<Mood> backgroundList = new ArrayList<>();
    public ArrayList<TextView> dateTextList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        initLayoutsList();
        initImageViewsList();
        initBackgroundList();
        //initDateTextList();

        if (SharedPrefsUtils.containsHistoryList(this)) {
            historyList = SharedPrefsUtils.getHistoryList(this);
            setLayouts();
            //setDateText();
            setWidth();
            displayComment();
            setWidth();

        }
    }

    private void initLayoutsList() {
        mRelativeLayout7Days = findViewById(R.id.layout_7_days);
        mRelativeLayout6Days = findViewById(R.id.layout_6_days);
        mRelativeLayout5Days = findViewById(R.id.layout_5_days);
        mRelativeLayout4Days = findViewById(R.id.layout_4_days);
        mRelativeLayout3Days = findViewById(R.id.layout_3_days);
        mRelativeLayout2Days = findViewById(R.id.layout_2_days);
        mRelativeLayout1Days = findViewById(R.id.layout_1_days);

        layoutsList.add(mRelativeLayout7Days);
        layoutsList.add(mRelativeLayout6Days);
        layoutsList.add(mRelativeLayout5Days);
        layoutsList.add(mRelativeLayout4Days);
        layoutsList.add(mRelativeLayout3Days);
        layoutsList.add(mRelativeLayout2Days);
        layoutsList.add(mRelativeLayout1Days);
    }

    private void initImageViewsList() {
        mImageView7Days = findViewById(R.id.imageview_7_days);
        mImageView6Days = findViewById(R.id.imageview_6_days);
        mImageView5Days = findViewById(R.id.imageview_5_days);
        mImageView4Days = findViewById(R.id.imageview_4_days);
        mImageView3Days = findViewById(R.id.imageview_3_days);
        mImageView2Days = findViewById(R.id.imageview_2_days);
        mImageView1Days = findViewById(R.id.imageview_1_days);

        imageList.add(mImageView7Days);
        imageList.add(mImageView6Days);
        imageList.add(mImageView5Days);
        imageList.add(mImageView4Days);
        imageList.add(mImageView3Days);
        imageList.add(mImageView2Days);
        imageList.add(mImageView1Days);
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

//    private void initDateTextList() {
//        mTextView7Days = findViewById(R.id.textview_7_days);
//        mTextView6Days = findViewById(R.id.textview_6_days);
//        mTextView5Days = findViewById(R.id.textview_5_days);
//        mTextView4Days = findViewById(R.id.textview_4_days);
//        mTextView3Days = findViewById(R.id.textview_3_days);
//        mTextView2Days = findViewById(R.id.textview_2_days);
//        mTextView1Days = findViewById(R.id.textview_1_days);
//
//        dateTextList.add(mTextView7Days);
//        dateTextList.add(mTextView6Days);
//        dateTextList.add(mTextView5Days);
//        dateTextList.add(mTextView4Days);
//        dateTextList.add(mTextView3Days);
//        dateTextList.add(mTextView2Days);
//        dateTextList.add(mTextView1Days);
//    }

    private void setLayouts() {

        for(int i = 0; i < historyList.size(); i++) {
            RelativeLayout relativeLayout = layoutsList.get(i);
            int position = historyList.get(i).getPosition();
//
//            if (position > 4) {
//                position = 4;
//            } else if (position < 0) {
//                position = 0;
//            }

            relativeLayout.setBackgroundColor(ContextCompat.getColor(this, backgroundList.get(position).getMoodBackgroundColor()));
            relativeLayout.setVisibility(View.VISIBLE);
        }
    }

//    private void setDateText() {
//        for (int i = 0; i < historyList.size(); i++) {
//            if (i == 0) {
//                dateTextList.get(0).setText("Il y a une semaine");
//            }
//            if (i == 1) {
//                dateTextList.get(0).setText("Il y a une semaine");
//                dateTextList.get(1).setText("Il y a six jours");
//            }
//            if (i == 2) {
//                dateTextList.get(0).setText("Il y a une semaine");
//                dateTextList.get(1).setText("Il y a six jours");
//                dateTextList.get(2).setText("Il y a cinq jours");
//            }
//            if (i == 3) {
//                dateTextList.get(0).setText("Il y a une semaine");
//                dateTextList.get(1).setText("Il y a six jours");
//                dateTextList.get(2).setText("Il y a cinq jours");
//                dateTextList.get(3).setText("Il y a quatre jours");
//            }
//            if (i == 4) {
//                dateTextList.get(0).setText("Il y a une semaine");
//                dateTextList.get(1).setText("Il y a six jours");
//                dateTextList.get(2).setText("Il y a cinq jours");
//                dateTextList.get(3).setText("Il y a quatre jours");
//                dateTextList.get(4).setText("Il y a trois jours");
//            }
//            if (i == 5) {
//                dateTextList.get(0).setText("Il y a une semaine");
//                dateTextList.get(1).setText("Il y a six jours");
//                dateTextList.get(2).setText("Il y a cinq jours");
//                dateTextList.get(3).setText("Il y a quatre jours");
//                dateTextList.get(4).setText("Il y a trois jours");
//                dateTextList.get(5).setText("Avant-hier");
//            }
//            if (i == 6) {
//                dateTextList.get(0).setText("Il y a une semaine");
//                dateTextList.get(1).setText("Il y a six jours");
//                dateTextList.get(2).setText("Il y a cinq jours");
//                dateTextList.get(3).setText("Il y a quatre jours");
//                dateTextList.get(4).setText("Il y a trois jours");
//                dateTextList.get(5).setText("Avant-hier");
//                dateTextList.get(5).setText("Hier");
//            }
//        }
//    }

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
