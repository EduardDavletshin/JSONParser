package com.example.eddy.jsonparser;

import com.example.eddy.jsonparser.User.User;

import java.util.ArrayList;

/**
 * Created by eddy on 12/18/2016.
 */

interface Callback {
    void onFinish(ArrayList<User> data);
}
