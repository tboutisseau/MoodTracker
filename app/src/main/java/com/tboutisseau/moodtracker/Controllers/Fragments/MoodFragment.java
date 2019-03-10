package com.tboutisseau.moodtracker.Controllers.Fragments;


import android.os.Bundle;
import android.os.Parcelable;
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
 * Fragment that takes its data from an arraylist of Mood objects to display
 * the proper smiley and background color
 */
public class MoodFragment extends Fragment {

    // Keys for the bundle
    private final static String MOODS_LIST = "moods_list";

    //
    private LinearLayout rootView;
    private ImageView imageView;
    private ArrayList<Mood> moodsList;


    public MoodFragment() {
        // Required empty public constructor
    }

    // Method to create a new instance of MoodFragment, and add data to its bundle
    public static MoodFragment newInstance(ArrayList<Mood> moodsList) {

        // Make new fragment
        MoodFragment moodFragment = new MoodFragment();

        // Make bundle and add data
        Bundle args = new Bundle();
        args.putParcelableArrayList(MOODS_LIST, moodsList);
        moodFragment.setArguments(args);

        return moodFragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Get the layout
        View view = inflater.inflate(R.layout.fragment_mood, container, false);

        // Get the widgets from the layout
        rootView = (LinearLayout) view.findViewById(R.id.fragment_mood_rootview);
        imageView = (ImageView) view.findViewById(R.id.mood_icon_imageview);





        return view;
    }

}
