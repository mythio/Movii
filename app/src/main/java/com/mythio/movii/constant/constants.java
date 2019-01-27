package com.mythio.movii.constant;

import java.util.HashMap;
import java.util.Map;

public class constants {

    //    API keys for the API used
    public static final String YOUTUBE_DEVELOPER_KEY = "AIzaSyCNTF1Wn34PQtvaJIUB4_iAB2KF8Wrympg";
    public static final String TMDB_API_KEY = "a781dd694991f0ea8dcf9050ec3e7a20";
    public static final String OMDB_API_KEY = "e403207b";

    //    API endpoints
    public static final String TMDB_IMAGE = "https://image.tmdb.org/t/p";
    public static final String TMDB_MOVIES = "https://api.themoviedb.org/3/movie/";
    public static final String TMDB_TV = "https://api.themoviedb.org/3/tv/popular/";
    public static final String OMDB_GET = "https://www.omdbapi.com/?apikey=e403207b&t=";
    public static final String OMDB_GET_END = "&plot=short";

    public static final Map<Integer, String> GENRE = new HashMap<Integer, String>() {{
        put(12, "Adventure");
        put(14, "Fantasy");
        put(16, "Animation");
        put(18, "Drama");
        put(27, "Horror");
        put(28, "Action");
        put(35, "Comedy");
        put(36, "History");
        put(37, "Western");
        put(53, "Thriller");
        put(80, "Crime");
        put(99, "Documentary");
        put(878, "Science Fiction");
        put(10402, "Music");
        put(10749, "Romance");
        put(10751, "Family");
        put(10752, "War");
        put(10770, "TV Movie");
    }};

    //  Intent names
//    public static final String
}