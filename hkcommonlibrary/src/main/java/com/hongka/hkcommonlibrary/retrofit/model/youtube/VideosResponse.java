package com.hongka.hkcommonlibrary.retrofit.model.youtube;

import android.os.Parcel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jusung.kim@sk.com on 2017/05/22
 */

public class VideosResponse extends BaseResponse {
    public PageInfo pageInfo;
    public List<Video> items;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeParcelable(this.pageInfo, flags);
        dest.writeList(this.items);
    }

    public VideosResponse() {
    }

    protected VideosResponse(Parcel in) {
        super(in);
        this.pageInfo = in.readParcelable(PageInfo.class.getClassLoader());
        this.items = new ArrayList<Video>();
        in.readList(this.items, Video.class.getClassLoader());
    }

    public static final Creator<VideosResponse> CREATOR = new Creator<VideosResponse>() {
        @Override
        public VideosResponse createFromParcel(Parcel source) {
            return new VideosResponse(source);
        }

        @Override
        public VideosResponse[] newArray(int size) {
            return new VideosResponse[size];
        }
    };
}
