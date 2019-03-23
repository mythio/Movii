package com.mythio.movii.model.video;

import com.google.gson.annotations.SerializedName;

public class Video {

    @SerializedName("key")
    private String key;

    @SerializedName("type")
    private String type;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
