package com.example.eddy.jsonparser.User;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by eddy on 12/21/2016.
 */

public class Address implements Parcelable {

    public String street;
    public String suite;
    public String city;
    public String zipcode;
    public Geo geo;

    public Address() {
    }

    protected Address(Parcel in) {
        street = in.readString();
        suite = in.readString();
        city = in.readString();
        zipcode = in.readString();
    }

    public static final Creator<Address> CREATOR = new Creator<Address>() {
        @Override
        public Address createFromParcel(Parcel in) {
            return new Address(in);
        }

        @Override
        public Address[] newArray(int size) {
            return new Address[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(street);
        dest.writeString(suite);
        dest.writeString(city);
        dest.writeString(zipcode);
    }
}
