package com.tboutisseau.moodtracker.Models;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Mood object that contains the icon, the background color, the sound, and the comment associated with each mood
 */

public class Mood implements Serializable {
    private int moodIcon;
    private int moodBackgroundColor;
    private int moodSound;
    private String comment;
    public int position;

    /** Constructor for the mood object
     *
     * @param moodIcon the smiley corresponding to the mood
     * @param moodBackgroundColor the color corresponding to the mood
     * @param moodSound the sound corresponding to the mood
     * @param position the position corresponding to the mood
     *
     */
    public Mood (int moodBackgroundColor, int moodIcon, int moodSound, int position) {
        this.moodBackgroundColor = moodBackgroundColor;
        this.moodIcon = moodIcon;
        this.moodSound = moodSound;
        this.position = position;
    }

    public Mood (String comment, int position) {
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

    public int getPosition() {
        return position;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }


}
