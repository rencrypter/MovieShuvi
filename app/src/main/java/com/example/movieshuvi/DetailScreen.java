package com.example.movieshuvi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.example.movieshuvi.adapter.ChildAdapter;
import com.example.movieshuvi.adapter.DetailAdapter;
import com.example.movieshuvi.adapter.ParentAdapter;
import com.example.movieshuvi.model.ChildModel;
import com.example.movieshuvi.model.DetailModel;
import com.github.ybq.android.spinkit.SpinKitView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;

public class DetailScreen extends AppCompatActivity {
    ChildModel model;
    RecyclerView recyclerView;

    SpinKitView spinKit;

    ArrayList<DetailModel> ulist;
    ArrayList<DetailModel> uList2;
    DetailAdapter detailAdapter;

    RatingBar ratingBar;
    TextView title, overview;
    ImageView poster;
    int movieId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ulist = new ArrayList<>();
        uList2 = new ArrayList<>();

        recyclerView = findViewById(R.id.rView);

        spinKit = findViewById(R.id.spin_kit);
        ratingBar = findViewById(R.id.rb);
        title = findViewById(R.id.textView);
        poster = findViewById(R.id.imageView);
        overview = findViewById(R.id.textView2);
        model = (ChildModel) getIntent().getSerializableExtra("model");
        movieId = model.getId();
        Log.d("TAGf", "onCreate: " + movieId);


        RequestQueue requestQueue;
        requestQueue = Volley.newRequestQueue(this);

        String URL = "https://api.themoviedb.org/3/movie/" + movieId + "?api_key=cb994cf72f880d17a42ecb76f90642ab&language=en-US";


        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(URL, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {


                try {


                    DetailModel dModel = new DetailModel();

                    dModel.setPoster_path(response.getString("poster_path"));
                    dModel.setOriginal_name(response.getString("original_title"));
                    dModel.setOverview(response.getString("overview"));
                    dModel.setVote_average(response.getLong("vote_average"));

                    ulist.add(dModel);
                    title.setText(dModel.getOriginal_name());
                    overview.setText(dModel.getOverview());
                    float rating = dModel.getVote_average() / 2;

                    ratingBar.setRating(rating);
                    Glide.with(getApplicationContext())
                            .load("https://image.tmdb.org/t/p/w500" + dModel.getPoster_path())
                            .into(poster);
                    spinKit.setVisibility(View.GONE);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {


            }
        });

        requestQueue.add(jsonObjectRequest);


        RequestQueue requestQueue1;
        requestQueue1 = Volley.newRequestQueue(this);

        String URL1 = "https://api.themoviedb.org/3/movie/" + movieId + "?api_key=cb994cf72f880d17a42ecb76f90642ab&language=en-US";


        JsonObjectRequest jsonObjectRequest1 = new JsonObjectRequest(URL1, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {

                try {
                    JSONArray jsonArray = response.getJSONArray("production_companies");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject obj = jsonArray.getJSONObject(i);


                        DetailModel dModel = new DetailModel();

                        dModel.setPoster_path(obj.getString("logo_path"));

                        uList2.add(dModel);
                        Log.d("TAGp", "onResponse: " + dModel.getPoster_path());


                    }
                    detailAdapter = new DetailAdapter(uList2, getApplicationContext());
                    recyclerView.setHasFixedSize(true);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));
                    recyclerView.setAdapter(detailAdapter);
                    detailAdapter.notifyDataSetChanged();


                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {


            }
        });

        requestQueue1.add(jsonObjectRequest1);


    }


}
