package com.hongka.hkcommonlibrary.retrofit.model.youtube;

import android.os.Parcel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jusung.kim@sk.com on 2017/05/23
 */

public class PlaylistsResponse extends BaseResponse {
    public PageInfo pageInfo;
    public List<Playlist> items;

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

    public PlaylistsResponse() {
    }

    protected PlaylistsResponse(Parcel in) {
        super(in);
        this.pageInfo = in.readParcelable(PageInfo.class.getClassLoader());
        this.items = new ArrayList<Playlist>();
        in.readList(this.items, Playlist.class.getClassLoader());
    }

    public static final Creator<PlaylistsResponse> CREATOR = new Creator<PlaylistsResponse>() {
        @Override
        public PlaylistsResponse createFromParcel(Parcel source) {
            return new PlaylistsResponse(source);
        }

        @Override
        public PlaylistsResponse[] newArray(int size) {
            return new PlaylistsResponse[size];
        }
    };
}
