package com.fun.guptas.sahodar;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by guptas on 1/17/2018.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    public MyAdapter(ArrayList<ListItem> listItems, Context context) {
//    public MyAdapter(ArrayList<ListItem> listItems, MyFragment context) {
        this.listItems = listItems;
        this.context = context;
        this.comm = (Comm) context;
    }

    private ArrayList<ListItem> listItems;
    //private MyFragment context;
    private Context context;
    private Comm comm;


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final ListItem listItem = listItems.get(position);

        holder.textViewHead.setText(listItem.getHead());
        holder.textViewDesc.setText(listItem.getDesc());
        holder.eachitem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String eachUrl = listItem.getUrl().toString();
                String eachHead = listItem.getHead().toString();
                Toast.makeText(context, eachUrl, Toast.LENGTH_SHORT).show();
                Log.d("ONCLICK", "BUTTON CLICKED");
                comm.getEachData(eachUrl,eachHead);
            }
        });

    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    public void loadNewData(ArrayList<ListItem> x){
        listItems = x;
        notifyDataSetChanged();
    }



    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textViewHead;
        public TextView textViewDesc;
        LinearLayout eachitem;


        public ViewHolder(View itemView) {
            super(itemView);

            textViewHead = itemView.findViewById(R.id.textViewHead);
            textViewDesc = itemView.findViewById(R.id.textViewDesc);
            eachitem = itemView.findViewById(R.id.eachitem);
        }
    }

    interface Comm {
        void getEachData(String url, String head);
    }
}
