package com.example.android.magtanium;

/**
 * Created by MAHE on 9/24/2016.
 */
public class MovieInfo {

    private String mMovieName;
    private String mPlot;
    private String mIMDBRating;

    public MovieInfo(String MovieName, String Plot, String IMDB)
    {
        mMovieName=MovieName;
        mPlot=Plot;
        mIMDBRating=IMDB;
    }

    public MovieInfo()
    {
        mMovieName="Random";
        mPlot="Random";
        mIMDBRating="Random";
    }

    public String getMovieName(){
        return  mMovieName;
    }
    public  String getPlot(){
        return mPlot;
    }
    public String getIMDBRating(){
        return mIMDBRating;
    }
}

