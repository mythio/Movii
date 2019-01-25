package com.mythio.movii.constant;

import java.util.HashMap;
import java.util.Map;

public class constants {

    //    API keys for the API's used
    public static final String YOUTUBE_DEVELOPER_KEY = "AIzaSyCNTF1Wn34PQtvaJIUB4_iAB2KF8Wrympg";
    public static final String TMDB_API_KEY = "a781dd694991f0ea8dcf9050ec3e7a20";
    public static final String OMDB_API_KEY = "e403207b";

    //    API endpoints
    public static final String TMDB_IMAGE = "https://image.tmdb.org/t/p/";
    public static final String TMDB_MOVIES_POPULAR = "https://api.themoviedb.org/3/movie/popular?api_key=a781dd694991f0ea8dcf9050ec3e7a20";
    public static final String TMDB_MOVIES_VIDEO = "https://api.themoviedb.org/3/movie/";
    public static final String TMDB_MOVIES_VIDEO_END = "/videos?api_key=a781dd694991f0ea8dcf9050ec3e7a20";
    public static final String OMDB_GET = "https://www.omdbapi.com/?apikey=e403207b&t=";
    public static final String OMDB_GET_END = "&plot=full";

    public static final Map<Integer, String> GENRE = new HashMap<Integer, String>() {{
        put(28, "Action");
        put(12, "Adventure");
        put(16, "Animation");
        put(35, "Comedy");
        put(80, "Crime");
        put(99, "Documentary");
        put(18, "Drama");
        put(10751, "Family");
        put(14, "Fantasy");
        put(36, "History");
        put(27, "Horror");
        put(10749, "Romance");
        put(878, "Science Fiction");
        put(10770, "TV Movie");
        put(53, "Thriller");
        put(10752, "War");
        put(37, "Western");
        put(10402, "Music");
    }};

}