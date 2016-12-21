package com.example.eddy.jsonparser.User;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by eddy on 12/21/2016.
 */

public class Geo implements Parcelable{

    public double lat;

    public Geo() {
    }

    public double lng;

    protected Geo(Parcel in) {
        lat = in.readDouble();
        lng = in.readDouble();
    }

    public static final Creator<Geo> CREATOR = new Creator<Geo>() {
        @Override
        public Geo createFromParcel(Parcel in) {
            return new Geo(in);
        }

        @Override
        public Geo[] newArray(int size) {
            return new Geo[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeDouble(lat);
        dest.writeDouble(lng);
    }
}
