package com.example.eddy.jsonparser;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by eddy on 12/20/2016.
 */

public class ViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.txt_name) TextView name;
    @BindView(R.id.txt_username) TextView username;
    @BindView(R.id.txt_email) TextView email;
    @BindView(R.id.txt_address) TextView address;
    @BindView(R.id.txt_zipcode) TextView zipcode;
    @BindView(R.id.txt_geo) TextView geo;
    @BindView(R.id.txt_phone) TextView phone;
    @BindView(R.id.txt_website) TextView website;
    @BindView(R.id.txt_company) TextView company;

    public ViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
