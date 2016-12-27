package com.example.eddy.jsonparser;

import android.content.Context;
import android.content.res.Resources;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.eddy.jsonparser.User.User;

import java.util.ArrayList;

/**
 * Created by eddy on 12/20/2016.
 */

class RecyclerViewAdapter extends RecyclerView.Adapter<ViewHolder> {

    static ArrayList<User> userData;

    RecyclerViewAdapter(ArrayList<User> list) {
        userData = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.users_card_view, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        User dataPosition = userData.get(position);
        Resources resources = holder.username.getResources();
        holder.name.setText(dataPosition.name);
        holder.username.setText(String.format("%s %s", resources.getString(R.string.username), dataPosition.username));
        holder.email.setText(String.format("%s %s", resources.getString(R.string.email), dataPosition.email));
        holder.address.setText(String.format("%s %s", resources.getString(R.string.address), dataPosition.address.city));
        holder.zipcode.setText(String.format("%s %s", resources.getString(R.string.zipcode), dataPosition.address.zipcode));
        holder.geo.setText(String.format("%s %s", resources.getString(R.string.geo), dataPosition.address.geo.lng));
        holder.phone.setText(String.format("%s %s", resources.getString(R.string.phone), dataPosition.phone));
        holder.website.setText(String.format("%s %s", resources.getString(R.string.website), dataPosition.website));
        holder.company.setText(resources.getString(R.string.company) + " " + dataPosition.company.name + ", " + dataPosition.company.catchPhrase + ", " + dataPosition.company.bs);
    }

    @Override
    public int getItemCount() {
        return userData.size();
    }
}
