package com.example.eddy.jsonparser;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Button;

import com.example.eddy.jsonparser.User.User;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private static final String USER_LIST = "userArrayList";

    ArrayList<User> userList;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.button2)
    Button nextButton;
    RecyclerView.Adapter recyclerViewAdapter;
    RecyclerView.LayoutManager recyclerViewLayoutManager;
    String URL = "https://jsonplaceholder.typicode.com/";

    @OnClick(R.id.button)
    public void loadJSON() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RequestInterface requestInterface = retrofit.create(RequestInterface.class);
        Call<ArrayList<User>> call = requestInterface.getJSON();
        call.enqueue(new retrofit2.Callback<ArrayList<User>>() {
            @Override
            public void onResponse(Call<ArrayList<User>> call, Response<ArrayList<User>> response) {
                userList = response.body();
                initRecyclerView(userList);
            }

            @Override
            public void onFailure(Call<ArrayList<User>> call, Throwable t) {
                Log.e("Error", t.getMessage());
            }
        });
    }


    @OnClick(R.id.button2)
    public void nextActivity(Button button) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slidein, R.anim.slideout);
            }
        }, 1);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(MainActivity.this);
    }

    public void initRecyclerView(ArrayList arrayList) {
        if (arrayList != null) {
            recyclerView.setHasFixedSize(true);
            recyclerViewLayoutManager = new LinearLayoutManager(MainActivity.this);
            recyclerView.setLayoutManager(recyclerViewLayoutManager);
            recyclerViewAdapter = new RecyclerViewAdapter(userList);
            recyclerView.setAdapter(recyclerViewAdapter);
        }
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
        initRecyclerView(userList);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
