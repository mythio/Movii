package com.mythio.movii.model.video;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class VideoResponse {

    @SerializedName("results")
    private ArrayList<Video> videos = new ArrayList<>();

    public ArrayList<Video> getVideos() {
        return videos;
    }

    public void setVideos(ArrayList<Video> videos) {
        this.videos = videos;
    }
}