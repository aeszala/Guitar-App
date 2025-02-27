package com.program;

import com.guitar_app_one_direction.Music;

public class MusicPlayer {

    public void playSong() {
        try {
            playLine1();
            Thread.sleep(300);
            playLine2();
            Thread.sleep(300);
            playLine3();
            Thread.sleep(300);
            playLine1();
            Thread.sleep(300);
            playLine4();
            Thread.sleep(300);
        }
        catch(Exception e) {
           System.out.println(e);
        }
    }

    private void playLine1() {
        Music.playNote("E");
        Music.playNote("D");
        Music.playNote("C");
        Music.playNote("D");
        Music.playNote("E");
        Music.playNote("E");
        Music.playNote("E");
    }

    private void playLine2() {
        Music.playNote("D");
        Music.playNote("D");
        Music.playNote("D");
    }

    private void playLine3() {
        Music.playNote("E");
        Music.playNote("E");
        Music.playNote("E");
    }

    private void playLine4() {
        Music.playNote("E");
        Music.playNote("D");
        Music.playNote("D");
        Music.playNote("E");
        Music.playNote("D");
        Music.playNote("C");
    }

    public static void main(String[] args) {
    MusicPlayer player = new MusicPlayer();
    player.playSong();
    }

}
