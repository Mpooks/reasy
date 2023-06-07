package com.example.reasy;

import android.content.Context;
import android.database.Cursor;

import java.sql.SQLDataException;
import java.util.ArrayList;

public class artist {

    private String name;
    private int artist_id;
    private double cost;
    private String music_genre;

    public artist(int artist_id, String name, double cost, String music_genre) {
        this.name = name;
        this.artist_id = artist_id;
        this.cost = cost;
        this.music_genre = music_genre;
    }

    public int getArtist() {
        return artist_id;
    }

    public String getMusicGenre() {
        return music_genre;
    }

    public String getName() {
        return name;
    }
    public static ArrayList<artist> getArtists(Context c){
        DatabaseManager dbm = new DatabaseManager(c);
        ArrayList<artist> ar=new ArrayList<>();
        try {
            dbm.open();
            Cursor cu=dbm.fetchA();
            if (cu.moveToFirst()) {
                do {
                    ar.add(new artist(cu.getInt(0),cu.getString(1),cu.getDouble(2),cu.getString(3)));
                } while (cu.moveToNext());
            }
            cu.close();
            dbm.close();
        } catch (SQLDataException e) {
            throw new RuntimeException(e);
        }
        return ar;
    }
}
