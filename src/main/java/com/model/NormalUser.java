package com.model;

import java.util.ArrayList;

public class NormalUser {
    private int points;
    private int streak;
    private int ranking;
    private ArrayList<Song> publishedSongs;

    public NormalUser(){
        this.points = 0;
        this.streak = 0;
        this.ranking = 0;
        this.publishedSongs = new ArrayList<>();
    }

    public int getPoints() {
        return points;
    }


    public void setPoints(int points){
        this.points = points;
    }

    public int getStreak() {
        return streak;
    }

    public void setStreak(int streak){
        this.streak = streak;
    }

    public int getRanking(){
        return ranking;
    }

    public void setRanking(int ranking){
        this.ranking = ranking;
    }

    public ArrayList<Song> getPublishedSongs(){
        return publishedSongs;
    }
    
    public void publishSong(Song song) {
        if (song != null) {
            publishedSongs.add(song);
            System.out.println("Song '" + song.getTitle() + "' has been published.");
        } else {
            System.out.println("Invalid song. Cannot publish.");
        }
    }

}
