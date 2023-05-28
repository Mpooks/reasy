package com.example.reasy;

public class artist {

    private String name;
    private int artist_id;
    private double cost;
    private String music_genre;

    public artist(String name, int artist_id, double cost, String music_genre) {
        this.name = name;
        this.artist_id = artist_id;
        this.cost = cost;
        this.music_genre = music_genre;
    }

    public int getArtist() {
        return artist_id;
    }
}
