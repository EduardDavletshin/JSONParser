package com.example.eddy.jsonparser;

import com.example.eddy.jsonparser.User.User;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by eddy on 12/21/2016.
 */
public interface RequestInterface {

    @GET("/users")
    Call<ArrayList<User>> getJSON();
}
