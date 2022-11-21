package com.example.movieshuvi.ui.movie;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import com.example.movieshuvi.R;
import com.example.movieshuvi.adapter.ParentAdapter;
import com.example.movieshuvi.databinding.FragmentMovieBinding;
import com.example.movieshuvi.model.ChildModel;
import com.example.movieshuvi.model.ParentModel;
import com.github.ybq.android.spinkit.SpinKitView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MovieFragment extends Fragment {
    private SpinKitView spinKit;

    RecyclerView recyclerView;
    ArrayList<ParentModel> parentModelArrayList;
    ArrayList<ChildModel> childModelArrayList;
    ArrayList<ChildModel> popularList;
    ArrayList<ChildModel> topRatedList;
    ArrayList<ChildModel> upcommingList;

    private FragmentMovieBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        MovieViewModel homeViewModel =
                new ViewModelProvider(this).get(MovieViewModel.class);

        binding = FragmentMovieBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        spinKit = root.findViewById(R.id.spin_kit);
        recyclerView = root.findViewById(R.id.rv_parent);
        parentModelArrayList = new ArrayList<>();
        childModelArrayList = new ArrayList<>();
        popularList = new ArrayList<>();
        topRatedList = new ArrayList<>();
        upcommingList = new ArrayList<>();


        parentModelArrayList.add(new ParentModel("Popular Movies", popularList));


        parentModelArrayList.add(new ParentModel("Top Rated Movies", topRatedList));


        parentModelArrayList.add(new ParentModel("Upcoming Movies", upcommingList));

        String URL = "https://api.themoviedb.org/3/movie/popular?api_key=cb994cf72f880d17a42ecb76f90642ab&language=en-US&page=1";
        String URL1 = "https://api.themoviedb.org/3/movie/top_rated?api_key=cb994cf72f880d17a42ecb76f90642ab&language=en-US&page=1";
        String URL2 = "https://api.themoviedb.org/3/movie/upcoming?api_key=cb994cf72f880d17a42ecb76f90642ab&language=en-US&page=1";

        RequestQueue requestQueue, requestQueue1, requestQueue2;
        requestQueue = Volley.newRequestQueue(getContext());
        requestQueue1 = Volley.newRequestQueue(getContext());
        requestQueue2 = Volley.newRequestQueue(getContext());
        JsonObjectRequest request_json = new JsonObjectRequest(URL, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            //Process os success response
                            Log.d("response", response.toString());


                            JSONArray jsonArray = response.getJSONArray("results");

                            for (int i = 0; i < jsonArray.length(); i++) {

                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                ChildModel childModel = new ChildModel();

                                childModel.setPoster_path(jsonObject.getString("poster_path"));
                                childModel.setId(jsonObject.getInt("id"));

                                popularList.add(childModel);


                            }
                            ParentAdapter parentAdapter = new ParentAdapter(parentModelArrayList, getContext());
                            recyclerView.setAdapter(parentAdapter);
                            parentAdapter.notifyDataSetChanged();


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.e("Error: ", error.getMessage());

            }
        });
        requestQueue.add(request_json);

        JsonObjectRequest request_json1 = new JsonObjectRequest(URL1, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            //Process os success response
                            Log.d("response", response.toString());


                            JSONArray jsonArray = response.getJSONArray("results");

                            for (int i = 0; i < jsonArray.length(); i++) {

                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                ChildModel childModel = new ChildModel();

                                childModel.setPoster_path(jsonObject.getString("poster_path"));
                                childModel.setId(jsonObject.getInt("id"));

                                topRatedList.add(childModel);


                            }
                            ParentAdapter parentAdapter = new ParentAdapter(parentModelArrayList, getContext());
                            recyclerView.setAdapter(parentAdapter);
                            parentAdapter.notifyDataSetChanged();


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.e("Error: ", error.getMessage());

            }
        });
        requestQueue1.add(request_json1);
        JsonObjectRequest request_json2 = new JsonObjectRequest(URL2, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            //Process os success response
                            Log.d("response", response.toString());


                            JSONArray jsonArray = response.getJSONArray("results");

                            for (int i = 0; i < jsonArray.length(); i++) {

                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                ChildModel childModel = new ChildModel();

                                childModel.setPoster_path(jsonObject.getString("poster_path"));
                                childModel.setId(jsonObject.getInt("id"));

                                upcommingList.add(childModel);


                            }
                            ParentAdapter parentAdapter = new ParentAdapter(parentModelArrayList, getContext());
                            recyclerView.setAdapter(parentAdapter);
                            parentAdapter.notifyDataSetChanged();
                            spinKit.setVisibility(View.GONE);


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.e("Error: ", error.getMessage());

            }
        });
        requestQueue2.add(request_json2);



        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));




        return root;

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}