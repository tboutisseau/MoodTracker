package com.tboutisseau.moodtracker.Models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Mood object that contains the icon, the background color, the sound, and the comment associated wich each mood
 * implements Parcelable to send the object to the fragment
 */

public class Mood implements Parcelable {
    private int moodIcon;
    private int moodBackgroundColor;
    private int moodSound;
    private String comment;

    /** Constructor for the mood object
     *
     * @param moodIcon
     * @param moodBackgroundColor
     * @param moodSound
     * @param comment
     *
     */
    public Mood (int moodIcon, int moodBackgroundColor, int moodSound, String comment) {
        this.moodIcon = moodIcon;
        this.moodBackgroundColor = moodBackgroundColor;
        this.moodSound = moodSound;
        this.comment = comment;
    }

    // Write to parcel
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(moodIcon);
        dest.writeInt(moodBackgroundColor);
        dest.writeInt(moodSound);
    }

    // Read from parcel
    protected Mood(Parcel in) {
        moodIcon = in.readInt();
        moodBackgroundColor = in.readInt();
        moodSound = in.readInt();
    }

    // Parcel creator
    public static final Parcelable.Creator<Mood> CREATOR = new Parcelable.Creator<Mood>() {
        public Mood createFromParcel(Parcel in) {
            return new Mood(in);
        }

        @Override
        public Mood[] newArray(int size) {
            return new Mood[size];
        }
    };

    // Getters and setters
    public int getMoodIcon() {
        return moodIcon;
    }

    public int getMoodBackgroundColor() {
        return moodBackgroundColor;
    }

    public int getMoodSound() {
        return moodSound;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public int describeContents() {
        return 0;
    }

}
