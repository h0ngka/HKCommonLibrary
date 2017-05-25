package com.hongka.hkcommonlibrary.retrofit.model.youtube;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by jusung.kim@sk.com on 2017/05/23
 */

public class Channel implements Parcelable {
    public String id;
    public Snippet snippet;
    public Statistics statistics;
    public BrandingSettings brandingSettings;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeParcelable(this.snippet, flags);
        dest.writeParcelable(this.statistics, flags);
        dest.writeParcelable(this.brandingSettings, flags);
    }

    public Channel() {
    }

    protected Channel(Parcel in) {
        this.id = in.readString();
        this.snippet = in.readParcelable(Snippet.class.getClassLoader());
        this.statistics = in.readParcelable(Statistics.class.getClassLoader());
        this.brandingSettings = in.readParcelable(BrandingSettings.class.getClassLoader());
    }

    public static final Parcelable.Creator<Channel> CREATOR = new Parcelable.Creator<Channel>() {
        @Override
        public Channel createFromParcel(Parcel source) {
            return new Channel(source);
        }

        @Override
        public Channel[] newArray(int size) {
            return new Channel[size];
        }
    };
}
