package com.mythio.movii.model.video;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

public class Video implements Parcelable {

    public static final Creator<Video> CREATOR = new Creator<Video>() {
        @Override
        public Video createFromParcel(@NonNull Parcel in) {
            return new Video(in);
        }

        @Override
        public Video[] newArray(int size) {
            return new Video[size];
        }
    };
    @Nullable
    @SerializedName("key")
    private String key;
    @Nullable
    @SerializedName("type")
    private String type;

    private Video(Parcel in) {
        key = in.readString();
        type = in.readString();
    }

    @Nullable
    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    @Nullable
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(key);
        dest.writeString(type);
    }
}
