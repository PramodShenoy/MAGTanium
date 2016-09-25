package com.example.android.magtanium;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

public class Movie extends AppCompatActivity {

    TextView tv, tv1, tv2, tv3, tv4, tv5, tv6, tv7, tv8, tv9;
    String sentence;
    MovieInfo defaultmo= new MovieInfo();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);

        Intent i=getIntent();
        sentence=i.getStringExtra("Movie name");

        tv1= (TextView) findViewById(R.id.movie_name);
        tv2= (TextView) findViewById(R.id.imdb_rating);

        tv3 = (TextView) findViewById(R.id.metscore);
        tv4= (TextView) findViewById(R.id.genre);
        tv5= (TextView) findViewById(R.id.release_date);

        tv6 = (TextView) findViewById(R.id.director_name);
        tv7= (TextView) findViewById(R.id.writer_name);
        tv8= (TextView) findViewById(R.id.actors);
        tv9= (TextView) findViewById(R.id.movie_plot);


        StringBuilder QUERY = new StringBuilder();
        QUERY.append("http://www.omdbapi.com/?t=");
        sentence.toLowerCase();
        sentence = sentence.replace(' ', '+');
        QUERY.append(sentence);
        QUERY.append("&y=&plot=full&r=json\\");
        MovieAsyncTask movie_search = new MovieAsyncTask();
        movie_search.execute(QUERY.toString());


    }

    private class MovieAsyncTask extends AsyncTask<String, Void, MovieInfo> {
        @Override
        protected MovieInfo doInBackground(String... urls) {

            if (urls.length < 1 || urls[0] == null) {
                return null;
            }
            String jsonresp = QueryUtils.getData(urls[0]);
            if (jsonresp != null) {
                return extractMovie(jsonresp);
            }
            else
                return defaultmo;

        }

        @Override
        protected void onPostExecute(MovieInfo mov) {
            String quote = mov.getPlot();
            String name= mov.getMovieName();
            String imdb= mov.getIMDBRating();

            String genre = mov.getGenre();
            String meta= mov.getMeta();
            String rel= mov.getRelease();

            String dir = mov.getDirector();
            String writer= mov.getWriter();
            String actor= mov.getActors();

            tv9.setText(quote);
            tv1.setText(name);
            tv2.setText(imdb);
            tv3.setText(meta);
            tv4.setText(genre);
            tv5.setText(rel);
            tv6.setText(dir);
            tv7.setText(writer);
            tv8.setText(actor);
        }
    }

    private static MovieInfo extractMovie(String jsonResp) {
        String plot = "";
        String title = "";
        String imdb = "";
        String genre="";
        String meta="";
        String actors="";
        String director="";
        String writers="";
        String rel="";

        if (TextUtils.isEmpty(jsonResp)) {
            return null;
        }

        try {
            JSONObject baseJsonResponse = new JSONObject(jsonResp);
            plot = baseJsonResponse.optString("Plot");
            title = baseJsonResponse.optString("Title");
            imdb =baseJsonResponse.optString("imdbRating");

            genre = baseJsonResponse.optString("Genre");
            meta = baseJsonResponse.optString("Metascore");
            actors =baseJsonResponse.optString("Actors");

            director = baseJsonResponse.optString("Director");
            writers = baseJsonResponse.optString("Writer");
            rel =baseJsonResponse.optString("Released");

        } catch (JSONException e) {
            e.printStackTrace();
        }
        MovieInfo movie1 = new MovieInfo(title, genre, imdb, meta, rel, director, writers, actors, plot);
        return movie1;
    }

}
