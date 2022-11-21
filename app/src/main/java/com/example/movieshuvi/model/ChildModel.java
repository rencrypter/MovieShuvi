package com.example.movieshuvi.model;

import java.io.Serializable;

public class ChildModel implements Serializable {
    public String poster_path;
    public int id;

   public ChildModel(){

    }

    public ChildModel(String poster_path, int id) {
        this.poster_path = poster_path;
        this.id = id;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
