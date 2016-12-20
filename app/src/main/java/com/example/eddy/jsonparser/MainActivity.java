package com.example.eddy.jsonparser;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    static List<User> userData;
    private static String TAG = "MainActivity";
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    RecyclerView.Adapter recyclerViewAdapter;
    RecyclerView.LayoutManager recyclerViewLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickButton(View view) {
        new JsonDataRetrievingTask(new Callback() {
            @Override
            public void onFinish(ArrayList data) {
                ButterKnife.bind(MainActivity.this);
                recyclerView.setHasFixedSize(true);
                recyclerViewLayoutManager = new LinearLayoutManager(MainActivity.this);
                recyclerView.setLayoutManager(recyclerViewLayoutManager);
                recyclerViewAdapter = new RecyclerViewAdapter(data);
                recyclerView.setAdapter(recyclerViewAdapter);
                Log.e(TAG, "AAA");
            }
        }).execute("https://jsonplaceholder.typicode.com/users");
        Log.e(TAG, "BBBB");
    }
}
