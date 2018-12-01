package com.fun.guptas.sahodar;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by guptas on 3/1/2018.
 */

public class MyFragment extends Fragment {
    ArrayList<ListItem> mListItems = new ArrayList<>();
    RecyclerView mRecyclerView;
    RecyclerView.Adapter mAdapter;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        ArrayList<ListItem> mListItems = new ArrayList<>();

        View view = inflater.inflate(R.layout.my_fragment_layout, container, false);

        mRecyclerView = view.findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

//        mAdapter  = new MyAdapter(mListItems, getActivity());
        mRecyclerView.setAdapter(mAdapter);
        get_store_data();
        Log.d("END", "DATA LOADED AND SET THE ADAPTOR");

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mAdapter  = new MyAdapter(mListItems, context);
    }

    private void onLoadItems() {
        //mAdapter.loadNewData(mListItems);
//        mAdapter = new MyAdapter(mListItems, this);
        mRecyclerView.setAdapter(mAdapter);
    }

    public void get_store_data() {
        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(getActivity());
        String url = "http://www.shoneekapoor.com/articles/";
        //Perform Volley request and store the data
        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        //mTextView.setText("Response is: "+ response.substring(0,500));
                        Pattern pattern = Pattern.compile("<li class=\"blog-item span12 format-standard\">(.+?)<\\/li>");
                        Matcher matcher = pattern.matcher(response);
                        boolean found = false;
                        int articleCount = 0;
                        while (matcher.find()) {
                            //System.out.println("Found the text" + matcher.group());
                            String block = matcher.group();
                            Log.d("BLOCK", "Found the text" + block);

                            String paragraph;
                            String heading;
                            //String url;
                            String heading_final;
                            String url_final;


                            Pattern pattern_p = Pattern.compile("<p>(.+?)</p>");
                            Matcher matcher_p = pattern_p.matcher(block);
                            while (matcher_p.find()) {
                                paragraph = matcher_p.group();
                                paragraph = paragraph.replaceAll("<[^>]*>", "");
                                Log.d("PARAGRAPH", "TEXT P " + paragraph);

                                Pattern pattern_h2 = Pattern.compile("<h2><a href(.+?)<\\/h2>");
                                Matcher matcher_h2 = pattern_h2.matcher(block);
                                while (matcher_h2.find()) {
                                    heading = matcher_h2.group();
                                    Log.d("HEADING", "Text H2" + heading);

                                    Pattern pattern_url = Pattern.compile("<a href=\"(.+?)\">(.+?)<\\/a><\\/h2>");
                                    Matcher matcher_url = pattern_url.matcher(heading);
                                    while (matcher_url.find()) {
                                        url_final = matcher_url.group(1);
                                        heading_final = matcher_url.group(2);
                                        Log.d("URL", "Text URL" + url_final);
                                        Log.d("FINAL PARA", "Text Final P" + heading_final);
                                        mListItems.add(new ListItem(heading_final, paragraph, url_final, block));
                                    }
                                }
                            }
                            found = true;
                            articleCount++;
                        }
                        if (!found) {
                            Log.d("NOTFOUND", "No Found");
                        }
                        onLoadItems();

                    }

                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //mTextView.setText("That didn't work!");
                Log.d("ERROR", "Could not fetch!");
            }
        });
        // Add the request to the RequestQueue.
        queue.add(stringRequest);
        //return;
    }

}
