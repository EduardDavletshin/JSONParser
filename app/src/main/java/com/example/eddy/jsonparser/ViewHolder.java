package com.example.eddy.jsonparser;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by eddy on 12/20/2016.
 */

class ViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.txt_name) TextView name;
    @BindView(R.id.txt_username) TextView username;
    @BindView(R.id.txt_email) TextView email;
    @BindView(R.id.txt_address) TextView address;
    @BindView(R.id.txt_zipcode) TextView zipcode;
    @BindView(R.id.txt_geo) TextView geo;
    @BindView(R.id.txt_phone) TextView phone;
    @BindView(R.id.txt_website) TextView website;
    @BindView(R.id.txt_company) TextView company;


    ViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), Main2Activity.class);
                v.getContext().startActivity(intent);
            }
        });
    }
}
