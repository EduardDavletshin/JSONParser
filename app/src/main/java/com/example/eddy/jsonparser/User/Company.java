package com.example.eddy.jsonparser.User;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by eddy on 12/21/2016.
 */

public class Company implements Parcelable{
    public String name;
    public String catchPhrase;
    public String bs;

    public Company() {
    }

    protected Company(Parcel in) {
        name = in.readString();
        catchPhrase = in.readString();
        bs = in.readString();
    }

    public static final Creator<Company> CREATOR = new Creator<Company>() {
        @Override
        public Company createFromParcel(Parcel in) {
            return new Company(in);
        }

        @Override
        public Company[] newArray(int size) {
            return new Company[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(catchPhrase);
        dest.writeString(bs);
    }
}
