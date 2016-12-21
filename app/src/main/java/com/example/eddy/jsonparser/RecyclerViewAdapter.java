package com.example.eddy.jsonparser;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.eddy.jsonparser.User.User;

import java.util.ArrayList;

import static com.example.eddy.jsonparser.MainActivity.userData;

/**
 * Created by eddy on 12/20/2016.
 */

class RecyclerViewAdapter extends RecyclerView.Adapter<ViewHolder> {

    RecyclerViewAdapter(ArrayList<User> list) {
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
        holder.address.setText("Address: " + userData.get(position).address.street+ ", " + userData.get(position).address.suite + ", " + userData.get(position).address.city);
        holder.zipcode.setText("Zipcode: " + userData.get(position).address.zipcode);
        holder.geo.setText("Geo: " + userData.get(position).address.geo.lat + ", " + userData.get(position).address.geo.lng);
        holder.phone.setText("Phone: " + userData.get(position).phone);
        holder.website.setText("Website: " + userData.get(position).website);
        holder.company.setText("Company: " + userData.get(position).company.name + ", " + userData.get(position).company.catchPhrase + ", " + userData.get(position).company.bs);
    }

    @Override
    public int getItemCount() {
        return userData.size();
    }
}
