package com.example.android.magtanium;

/**
 * Created by MAHE on 9/24/2016.
 */
public class MovieInfo {

    private String mMovieName;
    private String mPlot;
    private String mIMDBRating;

    private String mActors;
    private String mDirector;
    private String mReleaseDate;

    private String mWriters;
    private String mMetascore;
    private String mGenre;

    public MovieInfo(String MovieName, String Genre, String IMDB, String Meta, String Rel, String Director, String Writers, String Actors, String Plot) {
        mMovieName = MovieName;
        mPlot = Plot;
        mIMDBRating = IMDB;

        mActors= Actors;
        mDirector=Director;
        mReleaseDate=Rel;

        mWriters=Writers;
        mMetascore=Meta;
        mGenre=Genre;

    }

    public MovieInfo() {
        mMovieName = "Random";
        mPlot = "Random";
        mIMDBRating = "Random";
    }

    //Getter Methods
    public String getMovieName() {
        return mMovieName;
    }

    public String getPlot() {
        return mPlot;
    }

    public String getIMDBRating() {
        return mIMDBRating;
    }

    public String getDirector() {
        return mDirector;
    }

    public String getMeta() {
        return mMetascore;
    }

    public String getActors() {
        return mActors;
    }

    public String getWriter() {
        return mWriters;
    }

    public String getGenre() {
        return mGenre;
    }

    public String getRelease() {
        return mReleaseDate;
    }
}

