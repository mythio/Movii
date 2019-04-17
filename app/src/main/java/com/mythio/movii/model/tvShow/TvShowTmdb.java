package com.mythio.movii.model.tvShow;

import com.google.gson.annotations.SerializedName;
import com.mythio.movii.model.externalIds.ExternalIds;
import com.mythio.movii.model.genre.Genre;
import com.mythio.movii.model.season.Season;
import com.mythio.movii.model.season.SeasonDetails;
import com.mythio.movii.model.video.VideoResponse;

import java.util.ArrayList;

public class TvShowTmdb {

    @SerializedName("backdrop_path")
    private String backdropPath;

    @SerializedName("genres")
    private ArrayList<Genre> genres = null;

    @SerializedName("id")
    private Integer id;

    @SerializedName("name")
    private String name;

    @SerializedName("number_of_seasons")
    private Integer numberOfSeasons;

    @SerializedName("overview")
    private String overview;

    @SerializedName("poster_path")
    private String posterPath;

    @SerializedName("seasons")
    private ArrayList<Season> seasons = null;

    @SerializedName("vote_average")
    private Double voteAverage;

    @SerializedName("vote_count")
    private Integer voteCount;

    @SerializedName("videos")
    private VideoResponse videoResponse;

    @SerializedName("external_ids")
    private ExternalIds externalIds;

    @SerializedName("season/0")
    private SeasonDetails season0;

    @SerializedName("season/1")
    private SeasonDetails season1;

    @SerializedName("season/2")
    private SeasonDetails season2;

    @SerializedName("season/3")
    private SeasonDetails season3;

    @SerializedName("season/4")
    private SeasonDetails season4;

    @SerializedName("season/5")
    private SeasonDetails season5;

    @SerializedName("season/6")
    private SeasonDetails season6;

    @SerializedName("season/7")
    private SeasonDetails season7;

    @SerializedName("season/8")
    private SeasonDetails season8;

    @SerializedName("season/9")
    private SeasonDetails season9;

    @SerializedName("season/10")
    private SeasonDetails season10;

    @SerializedName("season/11")
    private SeasonDetails season11;

    @SerializedName("season/12")
    private SeasonDetails season12;

    @SerializedName("season/13")
    private SeasonDetails season13;

    @SerializedName("season/14")
    private SeasonDetails season14;

    @SerializedName("season/15")
    private SeasonDetails season15;

    @SerializedName("season/16")
    private SeasonDetails season16;

    @SerializedName("season/17")
    private SeasonDetails season17;

    public String getBackdropPath() {
        return backdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    public ArrayList<Genre> getGenres() {
        return genres;
    }

    public void setGenres(ArrayList<Genre> genres) {
        this.genres = genres;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Integer getNumberOfSeasons() {
        return numberOfSeasons;
    }

    public void setNumberOfSeasons(Integer numberOfSeasons) {
        this.numberOfSeasons = numberOfSeasons;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public ArrayList<Season> getSeasons() {
        return seasons;
    }

    public void setSeasons(ArrayList<Season> seasons) {
        this.seasons = seasons;
    }

    public Double getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(Double voteAverage) {
        this.voteAverage = voteAverage;
    }

    public Integer getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(Integer voteCount) {
        this.voteCount = voteCount;
    }

    public VideoResponse getVideoResponse() {
        return videoResponse;
    }

    public void setVideoResponse(VideoResponse videoResponse) {
        this.videoResponse = videoResponse;
    }

    public ExternalIds getExternalIds() {
        return externalIds;
    }

    public void setExternalIds(ExternalIds externalIds) {
        this.externalIds = externalIds;
    }

    public SeasonDetails getSeason0() {
        return season0;
    }

    public void setSeason0(SeasonDetails season0) {
        this.season0 = season0;
    }

    public SeasonDetails getSeason1() {
        return season1;
    }

    public void setSeason1(SeasonDetails season1) {
        this.season1 = season1;
    }

    public SeasonDetails getSeason2() {
        return season2;
    }

    public void setSeason2(SeasonDetails season2) {
        this.season2 = season2;
    }

    public SeasonDetails getSeason3() {
        return season3;
    }

    public void setSeason3(SeasonDetails season3) {
        this.season3 = season3;
    }

    public SeasonDetails getSeason4() {
        return season4;
    }

    public void setSeason4(SeasonDetails season4) {
        this.season4 = season4;
    }

    public SeasonDetails getSeason5() {
        return season5;
    }

    public void setSeason5(SeasonDetails season5) {
        this.season5 = season5;
    }

    public SeasonDetails getSeason6() {
        return season6;
    }

    public void setSeason6(SeasonDetails season6) {
        this.season6 = season6;
    }

    public SeasonDetails getSeason7() {
        return season7;
    }

    public void setSeason7(SeasonDetails season7) {
        this.season7 = season7;
    }

    public SeasonDetails getSeason8() {
        return season8;
    }

    public void setSeason8(SeasonDetails season8) {
        this.season8 = season8;
    }

    public SeasonDetails getSeason9() {
        return season9;
    }

    public void setSeason9(SeasonDetails season9) {
        this.season9 = season9;
    }

    public SeasonDetails getSeason10() {
        return season10;
    }

    public void setSeason10(SeasonDetails season10) {
        this.season10 = season10;
    }

    public SeasonDetails getSeason11() {
        return season11;
    }

    public void setSeason11(SeasonDetails season11) {
        this.season11 = season11;
    }

    public SeasonDetails getSeason12() {
        return season12;
    }

    public void setSeason12(SeasonDetails season12) {
        this.season12 = season12;
    }

    public SeasonDetails getSeason13() {
        return season13;
    }

    public void setSeason13(SeasonDetails season13) {
        this.season13 = season13;
    }

    public SeasonDetails getSeason14() {
        return season14;
    }

    public void setSeason14(SeasonDetails season14) {
        this.season14 = season14;
    }

    public SeasonDetails getSeason15() {
        return season15;
    }

    public void setSeason15(SeasonDetails season15) {
        this.season15 = season15;
    }

    public SeasonDetails getSeason16() {
        return season16;
    }

    public void setSeason16(SeasonDetails season16) {
        this.season16 = season16;
    }

    public SeasonDetails getSeason17() {
        return season17;
    }

    public void setSeason17(SeasonDetails season17) {
        this.season17 = season17;
    }
}
