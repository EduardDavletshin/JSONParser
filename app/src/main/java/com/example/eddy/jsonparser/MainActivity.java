package com.example.eddy.jsonparser;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static junit.runner.Version.id;

public class MainActivity extends AppCompatActivity {

    private static final String USER_LIST = "userArrayList";
    static List<User> userData;
    private static String TAG = "MainActivity";
    ArrayList<User> userList;
    Parcelable userListState;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    RecyclerView.Adapter recyclerViewAdapter;
    RecyclerView.LayoutManager recyclerViewLayoutManager;

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
        new JsonDataRetrievingTask(new Callback() {
            @Override
            public void onFinish(ArrayList data) {
                userList = data;
                createRecyclerView(userList);
                Log.e(TAG, "AAA");
            }
        }).execute("https://jsonplaceholder.typicode.com/users");
        Log.e(TAG, "BBBB");
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
        if (userList != null) {
            createRecyclerView(userList);
        }
    }
}
