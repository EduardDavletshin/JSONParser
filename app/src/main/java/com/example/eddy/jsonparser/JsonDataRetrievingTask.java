package com.example.eddy.jsonparser;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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
        ArrayList<User> data = new ArrayList<>();

        try {
            JSONArray jsonArray = new JSONArray(result);

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObjectUser = jsonArray.getJSONObject(i);
                User user = new User();
                user.name = jsonObjectUser.getString("name");
                user.username = jsonObjectUser.getString("username");
                user.email = jsonObjectUser.getString("email");

                JSONObject jsonObjectAddress = jsonObjectUser.getJSONObject("address");
                user.street = jsonObjectAddress.getString("street");
                user.suite = jsonObjectAddress.getString("suite");
                user.city = jsonObjectAddress.getString("city");
                user.zipcode = jsonObjectAddress.getString("zipcode");

                JSONObject jsonObjectGeo = jsonObjectAddress.getJSONObject("geo");
                user.lat = jsonObjectGeo.getDouble("lat");
                user.lng = jsonObjectGeo.getDouble("lng");

                user.phone = jsonObjectUser.getString("phone");
                user.website = jsonObjectUser.getString("website");

                JSONObject jsonObjeсtCompany = jsonObjectUser.getJSONObject("company");
                user.companyName = jsonObjeсtCompany.getString("name");
                user.catchPhrase = jsonObjeсtCompany.getString("catchPhrase");
                user.bs = jsonObjeсtCompany.getString("bs");
                data.add(user);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        callback.onFinish(data);
    }
}