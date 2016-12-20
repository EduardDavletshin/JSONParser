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

    private static final String USER_LIST = "userArrayList";
    static List<User> userData;
    private static String TAG = "MainActivity";
    ArrayList<User> userList;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    RecyclerView.Adapter recyclerViewAdapter;
    RecyclerView.LayoutManager recyclerViewLayoutManager;
    JsonDataRetrievingTask jsonDataRetrievingTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(MainActivity.this);
    }

    public void createRecyclerView(ArrayList arrayList) {
        if (userList != null) {
            recyclerView.setHasFixedSize(true);
            recyclerViewLayoutManager = new LinearLayoutManager(MainActivity.this);
            recyclerView.setLayoutManager(recyclerViewLayoutManager);
            recyclerViewAdapter = new RecyclerViewAdapter(userList);
            recyclerView.setAdapter(recyclerViewAdapter);
        }
    }

    public void onClickButton(View view) {
        if (userList == null) {
            jsonDataRetrievingTask = (JsonDataRetrievingTask) new JsonDataRetrievingTask(new Callback() {
                @Override
                public void onFinish(ArrayList<User> data) {
                    userList = data;
                    createRecyclerView(userList);
                    Log.e(TAG, "AAA");
                }
            }).execute("https://jsonplaceholder.typicode.com/users");
            Log.e(TAG, "BBBB");
        } else if (recyclerView.getVisibility() == View.VISIBLE) {
            recyclerView.setVisibility(View.GONE);
        } else recyclerView.setVisibility(View.VISIBLE);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList(USER_LIST, userList);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        userList = savedInstanceState.getParcelableArrayList(USER_LIST);
    }

    @Override
    protected void onResume() {
        super.onResume();
        createRecyclerView(userList);
    }

    @Override
    protected void onDestroy() {
        if (jsonDataRetrievingTask != null) {
            jsonDataRetrievingTask.cancel(true);
        }
        super.onDestroy();
    }
}
