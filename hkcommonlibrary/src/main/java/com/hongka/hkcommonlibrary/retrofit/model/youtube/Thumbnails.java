package com.hongka.hkcommonlibrary.retrofit.model.youtube;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by jusung.kim@sk.com on 2017/05/22
 */

public class Thumbnails implements Parcelable {
    public Thumbnail medium;
    public Thumbnail high;
    public Thumbnail standard;
    public Thumbnail maxres;

    public static class Thumbnail implements Parcelable {
        public String url;

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.url);
        }

        public Thumbnail() {
        }

        protected Thumbnail(Parcel in) {
            this.url = in.readString();
        }

        public static final Creator<Thumbnail> CREATOR = new Creator<Thumbnail>() {
            @Override
            public Thumbnail createFromParcel(Parcel source) {
                return new Thumbnail(source);
            }

            @Override
            public Thumbnail[] newArray(int size) {
                return new Thumbnail[size];
            }
        };
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.medium, flags);
        dest.writeParcelable(this.high, flags);
        dest.writeParcelable(this.standard, flags);
        dest.writeParcelable(this.maxres, flags);
    }

    public Thumbnails() {
    }

    protected Thumbnails(Parcel in) {
        this.medium = in.readParcelable(Thumbnail.class.getClassLoader());
        this.high = in.readParcelable(Thumbnail.class.getClassLoader());
        this.standard = in.readParcelable(Thumbnail.class.getClassLoader());
        this.maxres = in.readParcelable(Thumbnail.class.getClassLoader());
    }

    public static final Parcelable.Creator<Thumbnails> CREATOR = new Parcelable.Creator<Thumbnails>() {
        @Override
        public Thumbnails createFromParcel(Parcel source) {
            return new Thumbnails(source);
        }

        @Override
        public Thumbnails[] newArray(int size) {
            return new Thumbnails[size];
        }
    };
}
