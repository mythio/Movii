# Movii

[![Codacy Badge](https://api.codacy.com/project/badge/Grade/5e1a18cd33e341a6ab15e8713baa155b)](https://app.codacy.com/app/mythio/Movii?utm_source=github.com&utm_medium=referral&utm_content=mythio/Movii&utm_campaign=Badge_Grade_Settings)

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
│   ├── MainActivity.java
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
│   │   ├── Cast
│   │   │   ├── CastAdapter.java
│   │   │   └── CastPresenter.java
│   │   ├── Contract.java
│   │   ├── RecommendedMovies
│   │   │   ├── RecommendedMoviesAdapter.java
│   │   │   └── RecommendedMoviesPresenter.java
│   │   ├── SearchMovie
│   │   │   ├── SearchMovieAdapter.java
│   │   │   └── SearchMoviePresenter.java
│   │   └── SearchTvShow
│   │       ├── SearchTvShowAdapter.java
│   │       └── SearchTvShowPresenter.java
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

33 directories, 69 files
```

### Upcoming:
- Make the application reactive and improve the flow of data by introducing RxJava2, and RxAndroid.
- Genre section which will allow the user to view the top movies from their favourite genre.
- People fragment with top actors.
- Favourite buttons on the movies and shows.
- Profile section to keep track of the user's likings.
- and much more.
