package com.model;

import com.guitar_app_one_direction.Music;


public class Driver {

    public static void main(String[] args) {
        Song song = Songlist.getInstance().getSong("Moonlight Sonata");
        if (song != null)
            song.play();
    }

    
  
}