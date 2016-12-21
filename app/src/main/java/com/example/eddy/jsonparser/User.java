package com.example.eddy.jsonparser;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by eddy on 12/19/2016.
 */

class User implements Parcelable{

    public int id;
    public String name;
    String username;
    String email;
    String street;
    String suite;
    String city;
    String zipcode;
    double lat;
    double lng;
    String phone;
    String website;
    String companyName;
    String catchPhrase;
    String bs;

    public User() {

    }

    protected User(Parcel in) {
        id = in.readInt();
        name = in.readString();
        username = in.readString();
        email = in.readString();
        street = in.readString();
        suite = in.readString();
        city = in.readString();
        zipcode = in.readString();
        lat = in.readDouble();
        lng = in.readDouble();
        phone = in.readString();
        website = in.readString();
        companyName = in.readString();
        catchPhrase = in.readString();
        bs = in.readString();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeString(username);
        dest.writeString(email);
        dest.writeString(street);
        dest.writeString(suite);
        dest.writeString(city);
        dest.writeString(zipcode);
        dest.writeDouble(lat);
        dest.writeDouble(lng);
        dest.writeString(phone);
        dest.writeString(website);
        dest.writeString(companyName);
        dest.writeString(catchPhrase);
        dest.writeString(bs);
    }
}
