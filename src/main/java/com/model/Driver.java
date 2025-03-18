package com.model;

public class Driver {

    public static void main(String[] args) {
        Song song = Songlist.getInstance().getSong("Moonlight Sonata");
        if (song != null)
            song.play();
    }
}