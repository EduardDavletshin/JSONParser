package com.example.eddy.jsonparser;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static String TAG = "MainActivity";
    int i;
    TextView users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        users = (TextView) findViewById(R.id.json);
        Log.e(TAG, "insert " + i);
    }

    public void onClickButton(View view) {
        new JsonDataRetrievingTask(new Callback() {
            @Override
            public void onFinish(String result) {
                Log.e(TAG, "AAA");
                users.setText(result);
            }
        }).execute("https://jsonplaceholder.typicode.com/users");
        Log.e(TAG, "BBBB");
    }
}
