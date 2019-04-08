package com.tboutisseau.moodtracker.Controllers.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.tboutisseau.moodtracker.Models.Mood;
import com.tboutisseau.moodtracker.R;
import com.tboutisseau.moodtracker.Utils.SharedPrefsUtils;

import java.util.ArrayList;
import java.util.List;

public class ChartActivity extends AppCompatActivity {

    private ArrayList<Mood> historylist = new ArrayList<>();

    private float sadDay = 0;
    private float disappointedDay = 0;
    private float normalDay = 0;
    private float happyDay = 0;
    private float superHappyDay = 0;
    private float noMoodDay = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);

        historylist = SharedPrefsUtils.getHistoryList(this);

        setupPieChart();
    }

    /**
     *
     */
    private void setupPieChart() {
        for (int i = 0; i < historylist.size(); i++) {
            switch (historylist.get(i).getPosition()) {
                case 0 :
                    sadDay++;
                    break;
                case 1 :
                    disappointedDay++;
                    break;
                case 2 :
                    normalDay++;
                    break;
                case 3 :
                    happyDay++;
                    break;
                case 4 :
                    superHappyDay++;
                    break;
                case 5 :
                    noMoodDay++;
                    break;
            }
        }

        // Add data from the history list to the chart, setting the labels to the name of the mood
        List<PieEntry> pieEntryList = new ArrayList<>();

            if (sadDay != 0) {
                pieEntryList.add(new PieEntry(sadDay, getResources().getString(R.string.sad_mood)));
            }
            if (disappointedDay != 0) {
                pieEntryList.add(new PieEntry(disappointedDay, getResources().getString(R.string.disappointed_mood)));
            }
            if (normalDay != 0) {
                pieEntryList.add(new PieEntry(normalDay, getResources().getString(R.string.normal_mood)));
            }
            if (happyDay != 0) {
                pieEntryList.add(new PieEntry(happyDay, getResources().getString(R.string.happy_mood)));
            }
            if (superHappyDay != 0) {
                pieEntryList.add(new PieEntry(superHappyDay, getResources().getString(R.string.super_happy_mood)));
            }
            if (noMoodDay != 0) {
                pieEntryList.add(new PieEntry(noMoodDay, getResources().getString(R.string.no_mood)));
            }


        PieDataSet pieDataSet = new PieDataSet(pieEntryList, "Moods of the week");
        PieData pieData = new PieData(pieDataSet);

        ArrayList<Integer> colors = new ArrayList<>();

        // Setting the proper colors for the saved mood
        if (sadDay != 0) {
            colors.add(getResources().getColor(R.color.faded_red));
        }
        if (disappointedDay != 0) {
            colors.add(getResources().getColor(R.color.warm_grey));
        }
        if (normalDay != 0) {
            colors.add(getResources().getColor(R.color.cornflower_blue_65));
        }
        if (happyDay != 0) {
            colors.add(getResources().getColor(R.color.light_sage));
        }
        if (superHappyDay != 0) {
            colors.add(getResources().getColor(R.color.banana_yellow));
        }
        if (noMoodDay != 0) {
            colors.add(getResources().getColor(R.color.default_black));
        }


        pieDataSet.setColors(colors);

        PieChart pieChart = findViewById(R.id.mood_pie_chart);
        pieChart.setData(pieData);

        // Styling the pie chart
        pieDataSet.setSliceSpace(4f);
        pieChart.animateY(800, Easing.EaseInCirc);
        pieChart.setDrawHoleEnabled(true);
        pieChart.setHoleRadius(30f);
        pieChart.setTransparentCircleRadius(36f);
        pieChart.setCenterText(getResources().getString(R.string.moods_of_the_week));
        pieChart.setCenterTextRadiusPercent(46f);
        pieChart.setEntryLabelColor(getResources().getColor(R.color.medium_grey));

        // Disabling the legend
        Legend legend = pieChart.getLegend();
        legend.setEnabled(false);

        pieChart.invalidate();
    }

}
