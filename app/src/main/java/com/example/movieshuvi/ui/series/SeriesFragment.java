package com.example.movieshuvi.ui.series;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


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
import com.example.movieshuvi.databinding.FragmentSeriesBinding;
import com.example.movieshuvi.model.ChildModel;
import com.example.movieshuvi.model.ParentModel;
import com.github.ybq.android.spinkit.SpinKitView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class SeriesFragment extends Fragment {
    private SpinKitView spinKit;

    RecyclerView recyclerView;
    ArrayList<ParentModel> parentModelArrayList;
    ArrayList<ChildModel> childModelArrayList;
    ArrayList<ChildModel> popularList;
    ArrayList<ChildModel> topRatedList;


    private FragmentSeriesBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        SeriesViewModel dashboardViewModel =
                new ViewModelProvider(this).get(SeriesViewModel.class);

        binding = FragmentSeriesBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        spinKit = root.findViewById(R.id.spin_kit);
        recyclerView = root.findViewById(R.id.rv_parent);
        parentModelArrayList = new ArrayList<>();
        childModelArrayList = new ArrayList<>();
        popularList = new ArrayList<>();
        topRatedList = new ArrayList<>();


        parentModelArrayList.add(new ParentModel("Popular TVseries", popularList));


        parentModelArrayList.add(new ParentModel("Top Rated Series", topRatedList));


        String URL = "https://api.themoviedb.org/3/tv/popular?api_key=cb994cf72f880d17a42ecb76f90642ab&language=en-US&page=1";
        String URL1 = "https://api.themoviedb.org/3/tv/top_rated?api_key=cb994cf72f880d17a42ecb76f90642ab&language=en-US&page=1";

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
        requestQueue1.add(request_json1);


        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));


        return root;

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}