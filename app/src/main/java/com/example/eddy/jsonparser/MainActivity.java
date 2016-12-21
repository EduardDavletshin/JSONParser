package com.example.eddy.jsonparser;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.eddy.jsonparser.User.User;

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
    @BindView(R.id.button2)
    Button nextButton;
    RecyclerView.Adapter recyclerViewAdapter;
    RecyclerView.LayoutManager recyclerViewLayoutManager;
    JsonDataRetrievingTask jsonDataRetrievingTask;

    private View.OnClickListener myOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.slidein, R.anim.slideout);
                }
            }, 1);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(MainActivity.this);
        nextButton.setOnClickListener(myOnClickListener);
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
