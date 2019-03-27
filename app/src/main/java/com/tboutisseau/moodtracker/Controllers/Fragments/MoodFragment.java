package com.tboutisseau.moodtracker.Controllers.Fragments;


import android.os.Bundle;
import android.os.Parcelable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tboutisseau.moodtracker.Models.Mood;
import com.tboutisseau.moodtracker.R;

import java.util.ArrayList;

/**
 * Fragment that takes its data from a Mood object to display
 * the proper smiley and background color
 */
public class MoodFragment extends Fragment {

    private ConstraintLayout rootView;
    private ImageView imageView;
    private Mood mood;

    public MoodFragment() {
        // Required empty public constructor
    }

    /**
     * Method to create a new instance of MoodFragment
     * @param mood
     * @return
     */
    public static MoodFragment newInstance(Mood mood) {

        //TODO moodfragment.setBundle

        // Make new fragment
        MoodFragment moodFragment = new MoodFragment();

        moodFragment.mood = mood;

        return moodFragment;
    }


    /**
     * Create the view and assign the smiley and background color to it
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Get the layout of the fragment
        View view = inflater.inflate(R.layout.fragment_mood, container, false);

        // Get the widgets from the layout
        rootView = view.findViewById(R.id.fragment_mood_rootview);
        imageView = view.findViewById(R.id.mood_icon_imageview);

        // Set the widgets with the proper values
        rootView.setBackgroundResource(mood.getMoodBackgroundColor());
        imageView.setImageResource(mood.getMoodIcon());

        return view;
    }

}
