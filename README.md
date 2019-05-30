# Movii
A handcrafted Android application to show everything you want to know about your favourite movies and TV shows!

* Current status:

<img src="https://github.com/mythio/Movii/blob/MVP/res/home_movie.png" width="200">    <img src="https://github.com/mythio/Movii/blob/MVP/res/home_tv.png" width="200">    <img src="https://github.com/mythio/Movii/blob/MVP/res/movie_activity.png" width="200"> <img src="https://github.com/mythio/Movii/blob/MVP/res/search_activity.png" width="200"> <img src="https://github.com/mythio/Movii/blob/MVP/res/search_results.png" width="200">

### File structure
```
.
├── activity
│   ├── Discover
│   │   ├── activity
│   │   │   ├── contract
│   │   │   │   ├── Contract.java
│   │   │   │   ├── MoviesModel.java
│   │   │   │   ├── Presenter.java
│   │   │   │   └── TvShowsModel.java
│   │   │   └── DiscoverActivity.java
│   │   └── fragment
│   │       ├── BaseDiscoverFragment.java
│   │       ├── contract
│   │       │   ├── FragmentNavigation.java
│   │       │   ├── MoviesContract.java
│   │       │   ├── MoviesPresenter.java
│   │       │   ├── OnItemClickListener.java
│   │       │   ├── TvShowsContract.java
│   │       │   └── TvShowsPresenter.java
│   │       ├── MoviesFragment.java
│   │       ├── ProfileFragment.java
│   │       └── TvShowsFragment.java
│   ├── MovieDetails
│   │   ├── contract
│   │   │   ├── Contract.java
│   │   │   ├── Model.java
│   │   │   └── Presenter.java
│   │   └── MovieDetailsActivity.java
│   ├── SearchMovie
│   │   ├── contract
│   │   │   ├── Contract.java
│   │   │   ├── Model.java
│   │   │   └── Presenter.java
│   │   └── SearchMovieActivity.java
│   ├── SearchTvShow
│   │   ├── contract
│   │   │   ├── Contract.java
│   │   │   ├── Model.java
│   │   │   └── Presenter.java
│   │   └── SearchTvShowActivity.java
│   └── TvShowDetails
│       ├── contract
│       │   ├── Contract.java
│       │   ├── Model.java
│       │   └── Presenter.java
│       └── TvShowDetailsActivity.java
├── adapter
│   ├── recyclerViewAdapter
│   │   ├── CastAdapter.java
│   │   ├── EpisodeAdapter.java
│   │   ├── MovieSearchAdapter.java
│   │   ├── RecommendedMoviesAdapter.java
│   │   └── TvShowSearchAdapter.java
│   └── viewPagerAdapter
│       ├── MovieSliderAdapter.java
│       ├── TvShowDetailsAdapter.java
│       └── TvShowSliderAdapter.java
├── model
│   ├── cast
│   │   ├── Cast.java
│   │   └── Credits.java
│   ├── collection
│   │   ├── Collection.java
│   │   └── CollectionResponse.java
│   ├── episode
│   │   └── Episode.java
│   ├── externalIds
│   │   └── ExternalIds.java
│   ├── genre
│   │   └── Genre.java
│   ├── movie
│   │   ├── Movie.java
│   │   ├── MovieOmdb.java
│   │   ├── MovieResponse.java
│   │   └── MovieTmdb.java
│   ├── season
│   │   ├── SeasonDetails.java
│   │   └── Season.java
│   ├── tvShow
│   │   ├── TvShow.java
│   │   ├── TvShowOmdb.java
│   │   ├── TvShowResponse.java
│   │   └── TvShowTmdb.java
│   └── video
│       ├── Video.java
│       └── VideoResponse.java
├── network
│   ├── EndPointsOmdb.java
│   ├── EndPointTmdb.java
│   └── RetrofitBuilder.java
└── util
    ├── Constant.java
    ├── ItemDecorator.java
    └── Utils.java

29 directories, 64 files
```

### Upcoming:
- Make the application reactive and improve the flow of data by introducing RxJava2, and RxAndroid.
- Genre section which will allow the user to view the top movies from their favourite genre.
- People fragment with top actors.
- Favourite buttons on the movies and shows.
- Profile section to keep track of the user's likings.
- and much more.
