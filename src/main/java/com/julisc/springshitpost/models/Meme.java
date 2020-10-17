package com.julisc.springshitpost.models;

public class Meme {

    private String meme;

    public Meme(String meme) {
        this.meme = meme;
    }

    public Meme() {
    }

    public String getMeme() {
        return meme;
    }

    public void setMeme(String meme) {
        this.meme = meme;
    }
}
