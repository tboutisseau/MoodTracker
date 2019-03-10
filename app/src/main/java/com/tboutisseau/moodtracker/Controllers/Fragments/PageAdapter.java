package com.tboutisseau.moodtracker.Controllers.Fragments;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class PageAdapter extends FragmentPagerAdapter {
    private Context mContext;

    public PageAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }


    @Override
    public int getCount() {
        return 5;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new SadMoodFragment();
            case 1:
                return new DisappointedMoodFragment();
            case 2:
                return new NormalMoodFragment();
            case 3:
                return new HappyMoodFragment();
            case 4:
                return new SuperHappyMoodFragment();
            default:
                return new HappyMoodFragment();
        }
    }

}
