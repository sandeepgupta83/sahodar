package com.fun.guptas.sahodar;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by guptas on 3/7/2018.
 */

public class ListItemData extends Fragment {
    public String head;
    public String url_a;
    int paragraphs = 0;
    Context context;
    String response;


    public ListItemData(String head, String url, Context context) {
        this.head = head;
        this.url_a = url;
        this.context = context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.each_item, container, false);
        TextView mtextView = view.findViewById(R.id.eachItemTextView);

        get_store_data(head, url_a, context);
        return view;
    }

    public void get_store_data(String head, String url_a, Context context) {
        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(context);
        String url = url_a;

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        //mTextView.setText("Response is: "+ response.substring(0,500));
                        Pattern pattern = Pattern.compile("(<p><strong>.+?</p>)");
                        Matcher matcher = pattern.matcher(response);
                        boolean found = false;
                        while (matcher.find()) {
                            paragraphs++;
                            String block = matcher.group();
                            Log.d("EACHITEM", "Found the para" + paragraphs + block);

                        }
                        if (!found) {
                            Log.d("NOTFOUND", "No Found");
                        }
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
    }
}
