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

    /** Constructor for the mood object
     *
     * @param moodIcon
     * @param moodBackgroundColor
     * @param moodSound
     * @param comment
     *
     */
    public Mood (int moodBackgroundColor, int moodIcon, int moodSound, String comment) {
        this.moodBackgroundColor = moodBackgroundColor;
        this.moodIcon = moodIcon;
        this.moodSound = moodSound;
        this.comment = comment;
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
