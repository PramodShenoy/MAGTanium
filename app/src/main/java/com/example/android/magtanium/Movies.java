package com.example.android.magtanium;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class Movies extends android.support.v4.app.Fragment{


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //Returning the layout file after inflating
        //Change R.layout.tab1 in you classes
        View v= inflater.inflate(R.layout.activity_movies, container, false);
        Button btn = (Button)v.findViewById(R.id.se_mov);
        final EditText et= (EditText) v.findViewById(R.id.ed_text);

        btn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                Intent i= new Intent(getActivity(), Movie.class);
                i.putExtra("Movie name", et.getText().toString());
                startActivity(i);
            }
        });
        return v;
    }
}
