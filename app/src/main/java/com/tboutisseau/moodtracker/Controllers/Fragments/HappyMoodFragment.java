package com.tboutisseau.moodtracker.Controllers.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.tboutisseau.moodtracker.Models.Mood;
import com.tboutisseau.moodtracker.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class HappyMoodFragment extends Fragment {


    public HappyMoodFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_mood, container, false);

        Mood happyMood = new Mood(R.drawable.smiley_happy, R.color.light_sage, R.raw.nobook, "");

        ImageView imageView = rootView.findViewById(R.id.mood_icon_imageview);

        imageView.setImageResource(happyMood.getMoodIcon());
        rootView.setBackgroundColor(getResources().getColor(happyMood.getMoodBackgroundColor()));

        return rootView;
    }

}
