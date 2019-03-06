package com.tboutisseau.moodtracker.Models;

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
     */
    public Mood (int moodIcon, int moodBackgroundColor, int moodSound, String comment) {
        this.moodIcon = moodIcon;
        this.moodBackgroundColor = moodBackgroundColor;
        this.moodSound = moodSound;
        this.comment = comment;
    }

    public int getMoodIcon() {
        return moodIcon;
    }

    public void setMoodIcon(int moodIcon) {
        this.moodIcon = moodIcon;
    }

    public int getMoodBackgroundColor() {
        return moodBackgroundColor;
    }

    public void setMoodBackgroundColor(int moodBackgroundColor) {
        this.moodBackgroundColor = moodBackgroundColor;
    }

    public int getMoodSound() {
        return moodSound;
    }

    public void setMoodSound(int moodSound) {
        this.moodSound = moodSound;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
