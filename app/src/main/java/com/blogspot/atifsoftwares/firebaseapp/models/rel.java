package com.blogspot.atifsoftwares.firebaseapp.models;

import android.graphics.drawable.Drawable;

public class rel {
    private String name;
    private String text;
    private Drawable d;

    public void setText(String text) {
        this.text = text;
    }

    public void setD(Drawable d) {
        this.d = d;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getText() {
        return text;
    }

    public Drawable getD() {
        return d;
    }

    public String getName() {
        return name;
    }
}
