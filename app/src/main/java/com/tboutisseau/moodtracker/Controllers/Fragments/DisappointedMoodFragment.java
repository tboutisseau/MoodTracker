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
public class DisappointedMoodFragment extends Fragment {


    public DisappointedMoodFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_mood, container, false);

        Mood disappointedMood = new Mood(R.drawable.smiley_disappointed, R.color.warm_grey, R.raw.nobook, "");

        ImageView imageView = rootView.findViewById(R.id.mood_icon_imageview);

        imageView.setImageResource(disappointedMood.getMoodIcon());
        rootView.setBackgroundColor(getResources().getColor(disappointedMood.getMoodBackgroundColor()));

        return rootView;
    }

}
