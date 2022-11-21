package com.example.movieshuvi.model;

public class DetailModel {
    String poster_path, overview, original_name;
    float vote_average;

    public DetailModel() {

    }


    public DetailModel(String poster_path, String overview, String original_name, float vote_average) {
        this.poster_path = poster_path;
        this.overview = overview;
        this.original_name = original_name;
        this.vote_average = vote_average;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getOriginal_name() {
        return original_name;
    }

    public void setOriginal_name(String original_name) {
        this.original_name = original_name;
    }

    public float getVote_average() {
        return vote_average;
    }

    public void setVote_average(float vote_average) {
        this.vote_average = vote_average;
    }
}
