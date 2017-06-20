package com.hongka.hkcommonlibrary.retrofit.model.youtube;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jusung.kim@sk.com on 2017/05/24
 */

public class SearchResponse extends BaseResponse implements Parcelable {
    public PageInfo pageInfo;
    public List<Search> items;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.pageInfo, flags);
        dest.writeList(this.items);
    }

    public SearchResponse() {
    }

    protected SearchResponse(Parcel in) {
        this.pageInfo = in.readParcelable(PageInfo.class.getClassLoader());
        this.items = new ArrayList<Search>();
        in.readList(this.items, Search.class.getClassLoader());
    }

    public static final Parcelable.Creator<SearchResponse> CREATOR = new Parcelable.Creator<SearchResponse>() {
        @Override
        public SearchResponse createFromParcel(Parcel source) {
            return new SearchResponse(source);
        }

        @Override
        public SearchResponse[] newArray(int size) {
            return new SearchResponse[size];
        }
    };
}
