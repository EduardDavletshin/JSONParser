package com.example.eddy.jsonparser;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
    }

    @Override
    public void onBackPressed() {
        finish();
        overridePendingTransition(R.anim.slideenter, R.anim.slideexit);
        super.onBackPressed();
    }
}