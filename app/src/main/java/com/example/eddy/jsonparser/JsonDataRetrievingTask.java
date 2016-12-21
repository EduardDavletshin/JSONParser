package com.example.eddy.jsonparser;

import android.os.AsyncTask;

import com.example.eddy.jsonparser.User.User;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;


/**
 * Created by eddy on 12/11/2016.
 */

class JsonDataRetrievingTask extends AsyncTask<String, String, String> {

    private Callback callback;

    JsonDataRetrievingTask(Callback callback) {
        this.callback = callback;
    }

    public String doInBackground(String... params) {

        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;

        try {
            URL url = new URL(params[0]);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.connect();

            InputStream in = urlConnection.getInputStream();

            reader = new BufferedReader(new InputStreamReader(in));

            StringBuilder result = new StringBuilder();

            String line;

            while ((line = reader.readLine()) != null) {
                result.append(line);
            }

            return result.toString();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    protected void onProgressUpdate(String... values) {
        super.onProgressUpdate(values);
    }

    @Override
    public void onPostExecute(String result) {
        super.onPostExecute(result);
        if (result != null) {
            Gson gson = new Gson();
            Type type = new TypeToken<ArrayList<User>>(){}.getType();
            ArrayList<User> data = gson.fromJson(result, type);
            callback.onFinish(data);
        }
    }
}