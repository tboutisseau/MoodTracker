package com.tboutisseau.moodtracker;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class ImageAdapter extends PagerAdapter {
    private Context mContext;
    private int[] mImageIds = new int[] {R.mipmap.smiley_super_happy, R.mipmap.smiley_happy, R.mipmap.smiley_normal,
            R.mipmap.smiley_disappointed, R.mipmap.smiley_sad};

    ImageAdapter (Context context) {
        mContext = context;
    }

    @Override
    public int getCount() {
        return mImageIds.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == o;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        ImageView imageView = new ImageView(mContext);
        imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        imageView.setImageResource(mImageIds[position]);
        container.addView(imageView, 0);

        // Change the background color depending on the selected mood
        switch (position) {
            case 0 :
                imageView.setBackgroundColor(mContext.getResources().getColor(R.color.banana_yellow));
                break;
            case 1 :
                imageView.setBackgroundColor(mContext.getResources().getColor(R.color.light_sage));
                break;
            case 2 :
                imageView.setBackgroundColor(mContext.getResources().getColor(R.color.cornflower_blue_65));
                break;
            case 3 :
                imageView.setBackgroundColor(mContext.getResources().getColor(R.color.warm_grey));
                break;
            case 4 :
                imageView.setBackgroundColor(mContext.getResources().getColor(R.color.faded_red));
                break;
        }

        return imageView;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((ImageView) object);
    }
}
