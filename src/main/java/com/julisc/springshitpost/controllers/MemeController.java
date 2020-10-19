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
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.List;

@RestController
public class MemeController{

    private MemeManager memeManager = new MemeManager();
    private Memes memes = new Memes();


    @PostMapping("api/add-meme")
    public ResponseEntity<Meme> addMeme(@RequestBody Meme newMeme) throws URISyntaxException, SQLException, IOException {

        System.out.println("HEAVY DUMP INCOMING");
        memes.addMeme(newMeme);
        memeManager.dumpTheLoad(newMeme);
        return ResponseEntity.ok(newMeme);
    }

    @GetMapping("api/get-all-memes")
    public ResponseEntity<Memes> getAllMemes() throws SQLException, URISyntaxException {
        memes.setMemes(memeManager.scavengeForShitposts());
        return ResponseEntity.ok(memes);
    }
}
