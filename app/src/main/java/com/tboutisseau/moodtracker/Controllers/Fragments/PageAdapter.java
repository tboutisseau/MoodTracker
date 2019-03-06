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
        if (position == 0) {
            return new SadMoodFragment();
        } else if (position == 1) {
            return new DisappointedMoodFragment();
        } else if (position == 2) {
            return new NormalMoodFragment();
        } else if (position == 3) {
            return new HappyMoodFragment();
        } else {
            return new SuperHappyMoodFragment();
        }
    }

}
