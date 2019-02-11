package com.mythio.movii.model;

import java.io.Serializable;

public class Person implements Serializable {
    private String name;
    private String profile_path;
    private int id;

    public Person(String name, String profile_path, int id) {
        this.name = name;
        this.profile_path = profile_path;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfile_path() {
        return profile_path;
    }

    public void setProfile_path(String profile_path) {
        this.profile_path = profile_path;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
