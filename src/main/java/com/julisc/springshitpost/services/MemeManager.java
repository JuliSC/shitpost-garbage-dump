package com.julisc.springshitpost.services;

import com.julisc.springshitpost.models.Meme;

import java.io.*;
import java.util.ArrayList;

import java.util.List;
import java.util.Scanner;

public class MemeManager {

    public MemeManager(){

    }

    public void dumpTheLoad(Meme load) throws IOException {
        File garbageDump = new File("src/main/resources/static/assets/files/actual-fucking-garbage.txt");

        PrintWriter garbageTruck = new PrintWriter(new FileWriter(garbageDump, true));
        garbageTruck.println(load.getMeme());
        garbageTruck.close();
    }

    public List<Meme> scavengeForShitposts() {
        try {
            File garbageDump = new File("src/main/resources/static/assets/files/actual-fucking-garbage.txt");
            if(!garbageDump.exists()){
                garbageDump.getParentFile().mkdirs();
                garbageDump.createNewFile();
            }

            List<Meme> shitpostBackpack = new ArrayList<>();
            Scanner shitpostFinder = new Scanner(garbageDump);
            while(shitpostFinder.hasNextLine()) {
                shitpostBackpack.add(new Meme(shitpostFinder.nextLine()));
            }
            return shitpostBackpack;
        } catch(Exception e) {
            System.out.println(e);
            return new ArrayList<Meme>();
        }
    }
}
