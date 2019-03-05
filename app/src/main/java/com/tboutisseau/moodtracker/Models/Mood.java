package com.tboutisseau.moodtracker.Models;

public class Mood {
    private int moodIcon;
    private int moodBackgroundColor;
    private int moodSound;

    public Mood (int moodIcon, int moodBackgroundColor, int moodSound) {
        this.moodIcon = moodIcon;
        this.moodBackgroundColor = moodBackgroundColor;
        this.moodSound = moodSound;
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
}
