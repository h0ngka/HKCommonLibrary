package com.hongka.hkcommonlibrary.retrofit.model.youtube;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jusung.kim@sk.com on 2017/05/25
 */

public class PlaylistItemsResponse extends BaseResponse implements Parcelable {
    public PageInfo pageInfo;
    public List<PlaylistItem> items;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.pageInfo, flags);
        dest.writeList(this.items);
    }

    public PlaylistItemsResponse() {
    }

    protected PlaylistItemsResponse(Parcel in) {
        this.pageInfo = in.readParcelable(PageInfo.class.getClassLoader());
        this.items = new ArrayList<PlaylistItem>();
        in.readList(this.items, PlaylistItem.class.getClassLoader());
    }

    public static final Parcelable.Creator<PlaylistItemsResponse> CREATOR = new Parcelable.Creator<PlaylistItemsResponse>() {
        @Override
        public PlaylistItemsResponse createFromParcel(Parcel source) {
            return new PlaylistItemsResponse(source);
        }

        @Override
        public PlaylistItemsResponse[] newArray(int size) {
            return new PlaylistItemsResponse[size];
        }
    };
}
