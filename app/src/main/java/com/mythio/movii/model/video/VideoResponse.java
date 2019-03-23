package com.mythio.movii.model.video;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class VideoResponse {

    @SerializedName("results")
    private List<Video> videos = new ArrayList<>();

    public List<Video> getVideos() {
        return videos;
    }

    public void setVideos(List<Video> videos) {
        this.videos = videos;
    }
}