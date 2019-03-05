package com.tboutisseau.moodtracker.Controllers.Fragments;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.tboutisseau.moodtracker.Models.Mood;

import java.util.HashMap;

public class PageAdapter extends FragmentStatePagerAdapter {

    private HashMap<Integer, Mood> moodsMap = new HashMap<>();


    public PageAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        return null;
    }

    @Override
    public int getCount() {
        return 0;
    }
}
