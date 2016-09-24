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

    TextView tv, tv1, tv2;
    String sentence;
    MovieInfo defaultmo= new MovieInfo();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);

        Intent i=getIntent();
        sentence=i.getStringExtra("Movie name");
        tv = (TextView) findViewById(R.id.description);
        tv1= (TextView) findViewById(R.id.name);
        tv2= (TextView) findViewById(R.id.imdb);
        StringBuilder QUERY = new StringBuilder();
        QUERY.append("http://www.omdbapi.com/?t=");
        sentence.toLowerCase();
        sentence = sentence.replace(' ', '+');
        QUERY.append(sentence);
        QUERY.append("&y=&plot=full&r=json\\");
        YodaAsyncTask yoda = new YodaAsyncTask();
        yoda.execute(QUERY.toString());


    }

    private class YodaAsyncTask extends AsyncTask<String, Void, MovieInfo> {
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
            tv.setText(quote);
            tv1.setText(name);
            tv1.setText(imdb);
        }
    }

    private static MovieInfo extractMovie(String jsonResp) {
        String plot = "";
        String title = "";
        String imdb = "";

        if (TextUtils.isEmpty(jsonResp)) {
            return null;
        }

        try {
            JSONObject baseJsonResponse = new JSONObject(jsonResp);
            plot = baseJsonResponse.optString("Plot");
            title = baseJsonResponse.optString("Title");
            imdb =baseJsonResponse.optString("imdbRating");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        MovieInfo movie1 = new MovieInfo(title, plot, imdb);
        return movie1;
    }

}
