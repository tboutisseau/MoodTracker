package com.tboutisseau.moodtracker.Controllers.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.tboutisseau.moodtracker.Models.Mood;
import com.tboutisseau.moodtracker.R;
import com.tboutisseau.moodtracker.Utils.SharedPrefsUtils;

import java.util.ArrayList;
import java.util.List;

public class ChartActivity extends AppCompatActivity {

    private ArrayList<Mood> historylist = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);

        historylist = SharedPrefsUtils.getHistoryList(this);

        setupPieChart();
    }

    /**
     * Add data from the history list to the chart, setting the labels to the name of the mood
     */
    private void setupPieChart() {
        List<PieEntry> pieEntryList = new ArrayList<>();
        for (int i = 0; i < historylist.size(); i++) {
            switch (historylist.get(i).getPosition()) {
                case 0 :
                    pieEntryList.add(new PieEntry(1f, getResources().getString(R.string.sad_mood)));
                    break;
                case 1 :
                    pieEntryList.add(new PieEntry(1f, getResources().getString(R.string.disappointed_mood)));
                    break;
                case 2 :
                    pieEntryList.add(new PieEntry(1f, getResources().getString(R.string.normal_mood)));
                    break;
                case 3 :
                    pieEntryList.add(new PieEntry(1f, getResources().getString(R.string.happy_mood)));
                    break;
                case 4 :
                    pieEntryList.add(new PieEntry(1f, getResources().getString(R.string.super_happy_mood)));
                    break;
                case 5 :
                    pieEntryList.add(new PieEntry(1f, getResources().getString(R.string.no_mood)));
                    break;
            }
        }

        PieDataSet pieDataSet = new PieDataSet(pieEntryList, "Moods of the week");
        PieData pieData = new PieData(pieDataSet);

        ArrayList<Integer> colors = new ArrayList<>();

        // Setting the proper colors for the saved mood
        for (int j = 0; j < historylist.size(); j++) {
            switch (historylist.get(j).getPosition()) {
                case 0:
                    colors.add(getResources().getColor(R.color.faded_red));
                    break;
                case 1:
                    colors.add(getResources().getColor(R.color.warm_grey));
                    break;
                case 2:
                    colors.add(getResources().getColor(R.color.cornflower_blue_65));
                    break;
                case 3:
                    colors.add(getResources().getColor(R.color.light_sage));
                    break;
                case 4:
                    colors.add(getResources().getColor(R.color.banana_yellow));
                    break;
                case 5:
                    colors.add(getResources().getColor(R.color.default_black));
                    break;
            }
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
