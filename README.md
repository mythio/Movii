# Movii

[![Codacy Badge](https://api.codacy.com/project/badge/Grade/5e1a18cd33e341a6ab15e8713baa155b)](https://app.codacy.com/app/mythio/Movii?utm_source=github.com&utm_medium=referral&utm_content=mythio/Movii&utm_campaign=Badge_Grade_Dashboard)

A handcrafted Android application to show everything you want to know about your favourite movies and TV shows!

* Current status:
<p align="center">
<img src="https://github.com/mythio/Movii/blob/MVP/res/home_movie.png" width="200"> <img src="https://github.com/mythio/Movii/blob/MVP/res/home_tv.png" width="200"> <img src="https://github.com/mythio/Movii/blob/MVP/res/movie_activity.png" width="200"> <img src="https://github.com/mythio/Movii/blob/MVP/res/cast.png" width="200"> <img src="https://github.com/mythio/Movii/blob/MVP/res/search_activity.png" width="200"> <img src="https://github.com/mythio/Movii/blob/MVP/res/search_results.png" width="200">
</p>

### File structure
```
.
├── activity
│   ├── discover
│   │   ├── activity
│   │   │   ├── contract
│   │   │   │   ├── Contract.java
│   │   │   │   └── Presenter.java
│   │   │   └── DiscoverActivity.java
│   │   └── fragment
│   │       ├── BaseDiscoverFragment.java
│   │       ├── contract
│   │       │   ├── Contract.java
│   │       │   ├── FragmentNavigation.java
│   │       │   └── Presenter.java
│   │       ├── MoviesFragment.java
│   │       ├── ProfileFragment.java
│   │       └── TvShowsFragment.java
│   ├── MainActivity.java
│   ├── moviedetails
│   │   ├── CastBottomDialog.java
│   │   ├── castdialogcontract
│   │   │   ├── Contract.java
│   │   │   └── Presenter.java
│   │   ├── contract
│   │   │   ├── Contract.java
│   │   │   └── Presenter.java
│   │   └── MovieDetailsActivity.java
│   ├── searchmovie
│   │   ├── contract
│   │   │   ├── Contract.java
│   │   │   └── Presenter.java
│   │   └── SearchMovieActivity.java
│   ├── searchtvshow
│   │   ├── contract
│   │   │   ├── Contract.java
│   │   │   └── Presenter.java
│   │   └── SearchTvShowActivity.java
│   └── tvshowdetails
│       ├── contract
│       │   ├── Contract.java
│       │   └── Presenter.java
│       └── TvShowDetailsActivity.java
├── adapter
│   ├── cast
│   │   ├── CastAdapter.java
│   │   └── CastPresenter.java
│   ├── Contract.java
│   ├── popularmovies
│   │   ├── PopularMovieAdapter.java
│   │   └── PopularMoviePresenter.java
│   ├── populartvshow
│   │   ├── PopularTvshowAdapter.java
│   │   └── PopularTvshowPresenter.java
│   ├── recommendationmovies
│   │   ├── RecommendedMoviesAdapter.java
│   │   └── RecommendedMoviesPresenter.java
│   ├── searchmovie
│   │   ├── SearchMovieAdapter.java
│   │   └── SearchMoviePresenter.java
│   └── searchtvshow
│       ├── SearchTvShowAdapter.java
│       └── SearchTvShowPresenter.java
├── BasePresenter.java
├── BaseView.java
├── model
│   ├── cast
│   │   ├── Cast.java
│   │   ├── CastMovies.java
│   │   └── Credits.java
│   ├── collection
│   │   ├── Collection.java
│   │   └── CollectionResponse.java
│   ├── episode
│   │   └── Episode.java
│   ├── externalids
│   │   └── ExternalIds.java
│   ├── genre
│   │   └── Genre.java
│   ├── movie
│   │   ├── Movie.java
│   │   └── MovieResponse.java
│   ├── season
│   │   ├── SeasonDetails.java
│   │   └── Season.java
│   ├── tvshow
│   │   ├── TvShow.java
│   │   └── TvShowResponse.java
│   └── video
│       ├── Video.java
│       └── VideoResponse.java
├── network
│   ├── ApiTmdb.java
│   ├── ApiVideoSpider.java
│   └── RetrofitBuilder.java
└── util
    ├── App.java
    ├── CarouselLayoutManager.java
    ├── Constants.java
    ├── ItemClickListener.java
    └── ItemDecorator.java

34 directories, 65 files
```
