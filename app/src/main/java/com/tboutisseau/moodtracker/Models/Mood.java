package com.tboutisseau.moodtracker.Models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Mood object that contains the icon, the background color, the sound, and the comment associated with each mood
 */

public class Mood {
    private int moodIcon;
    private int moodBackgroundColor;
    private int moodSound;
    private String comment;
    private int position;

    /** Constructor for the mood object
     *
     * @param moodIcon
     * @param moodBackgroundColor
     * @param moodSound
     * @param position
     *
     */
    public Mood (int moodBackgroundColor, int moodIcon, int moodSound, int position) {
        this.moodBackgroundColor = moodBackgroundColor;
        this.moodIcon = moodIcon;
        this.moodSound = moodSound;
        this.position = position;
    }

    protected Mood (String comment, int position) {
        this.comment = comment;
        this.position = position;
    }

    public Mood (int moodBackgroundColor) {
        this.moodBackgroundColor = moodBackgroundColor;
    }


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


}
