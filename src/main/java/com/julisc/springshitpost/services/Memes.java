package com.julisc.springshitpost.services;

import com.julisc.springshitpost.models.Meme;

import java.util.ArrayList;
import java.util.List;

public class Memes {

    private List<Meme> memes = new ArrayList<>();

    public Memes() {

    }

    public Memes(List<Meme> memes) {
        this.memes = memes;
    }

    public void addMeme(Meme meme) {
        memes.add(meme);
    }

    public List<Meme> getMemes() {
        return memes;
    }

    public void setMemes(List<Meme> memes) {
        this.memes = memes;
    }

    public String toFile() {
        String formattedString = "";
        for(Meme meme : memes) {
            formattedString +=  "\n" +  meme.getMeme();
        }
        return formattedString;
    }
}
