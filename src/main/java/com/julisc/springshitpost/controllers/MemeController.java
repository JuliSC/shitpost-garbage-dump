package com.julisc.springshitpost.controllers;

import com.julisc.springshitpost.models.Meme;
import com.julisc.springshitpost.services.MemeManager;
import com.julisc.springshitpost.services.Memes;

import org.springframework.context.annotation.Scope;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.SessionScope;


import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.SQLException;


@RestController
public class MemeController{

    private MemeManager memeManager = new MemeManager();
    private Memes memes = new Memes();

    @PostMapping("api/add-meme")
    public ResponseEntity<Meme> addMeme(@RequestBody Meme newMeme) throws URISyntaxException, SQLException, IOException {

        System.out.println("HEAVY DUMP INCOMING");
        memes.add(newMeme);
        return ResponseEntity.ok(newMeme);
    }

    @GetMapping("api/get-all-memes")
    public ResponseEntity<Memes> getAllMemes() {
        return ResponseEntity.ok(memes);
    }

    @PostConstruct
    public void start() throws Exception {
        System.out.println("Starting...");
       memes =  memeManager.scavengeForShitposts();
    }

    @PreDestroy
    public void dump() throws Exception {
        memeManager.dumpTheLoad(memes);
        System.out.println("Shutting down...");
    }
}
