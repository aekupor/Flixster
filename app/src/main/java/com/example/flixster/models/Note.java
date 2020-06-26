package com.example.flixster.models;

public class Note {
    String rating;
    String title;

    public Note(String rating, String title) {
        this.rating = rating;
        this.title = title;
    }

    public String getRating() {
        return rating;
    }

    public String getTitle() {
        return title;
    }
}
