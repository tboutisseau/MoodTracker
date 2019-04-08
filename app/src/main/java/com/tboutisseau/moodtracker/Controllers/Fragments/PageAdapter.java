package com.tboutisseau.moodtracker.Controllers.Fragments;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.tboutisseau.moodtracker.Models.Mood;

import java.util.List;

public class PageAdapter extends FragmentPagerAdapter {
    private final List<Mood> moodsList;

    public PageAdapter(Context context, FragmentManager fm, List<Mood> moodsList) {
            super(fm);
            this.moodsList = moodsList;
    }

    @Override
    public int getCount() {
        return moodsList.size();
    }

    @Override
    public Fragment getItem(int position) {
        Mood mood = moodsList.get(position);
        return MoodFragment.newInstance(mood);
    }
}
