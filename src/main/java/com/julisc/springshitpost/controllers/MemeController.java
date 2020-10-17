package com.julisc.springshitpost.controllers;

import com.julisc.springshitpost.models.Meme;
import com.julisc.springshitpost.services.MemeManager;
import com.julisc.springshitpost.services.Memes;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class MemeController{

    private MemeManager memeManager = new MemeManager();
    private Memes memes = new Memes(memeManager.scavengeForShitposts());


    @PostMapping("api/add-meme")
    public ResponseEntity<Meme> addMeme(@RequestBody Meme newMeme) throws IOException {
        System.out.println("HEAVY DUMP INCOMING");
        memes.addMeme(newMeme);
        memeManager.dumpTheLoad(newMeme);
        return ResponseEntity.ok(newMeme);
    }

    @GetMapping("api/get-all-memes")
    public ResponseEntity<Memes> getAllMemes() {
        return ResponseEntity.ok(memes);
    }
}
