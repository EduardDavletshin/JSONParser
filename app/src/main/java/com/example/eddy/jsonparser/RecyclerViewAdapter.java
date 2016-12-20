package com.example.eddy.jsonparser;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import static com.example.eddy.jsonparser.MainActivity.userData;

/**
 * Created by eddy on 12/20/2016.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<ViewHolder> {

    public RecyclerViewAdapter(ArrayList<User> list) {
        userData = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.users_card_view, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.name.setText(userData.get(position).name);
        holder.username.setText("Username: " + userData.get(position).username);
        holder.email.setText("Email: " + userData.get(position).email);
        holder.address.setText("Address: " + userData.get(position).street + ", " + userData.get(position).suite + ", " + userData.get(position).city);
        holder.zipcode.setText("Zipcode: " + userData.get(position).zipcode);
        holder.geo.setText("Geo: " + userData.get(position).lat + ", " + userData.get(position).lng);
        holder.phone.setText("Phone: " + userData.get(position).phone);
        holder.website.setText("Website: " + userData.get(position).website);
        holder.company.setText("Company: " + userData.get(position).companyName + ", " + userData.get(position).catchPhrase + ", " + userData.get(position).bs);
    }

    @Override
    public int getItemCount() {
        return userData.size();
    }
}
