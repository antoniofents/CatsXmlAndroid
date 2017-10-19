package com.example.afentanes.customlists;

import android.os.Parcel;
import android.os.Parcelable;


public class CatInfo implements Parcelable {

    public String url;
    public String id;
    public String source;

    public CatInfo() {

    }

    public static final Parcelable.Creator<CatInfo> CREATOR = new Parcelable.Creator<CatInfo>() {
        public CatInfo createFromParcel(Parcel in) {
            return new CatInfo(in);
        }

        public CatInfo[] newArray(int size) {

            return new CatInfo[size];
        }

    };

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(url);
        dest.writeString(id);
        dest.writeString(source);
    }

    private CatInfo(Parcel in) {
        url = in.readString();
        id = in.readString();
        source = in.readString();
    }

    public int describeContents() {
        return 0;
    }


}
