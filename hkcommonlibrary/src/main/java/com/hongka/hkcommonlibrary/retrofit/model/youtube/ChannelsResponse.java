package com.hongka.hkcommonlibrary.retrofit.model.youtube;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by jusung.kim@sk.com on 2017/05/23
 */

public class ChannelsResponse extends BaseResponse implements Parcelable {
    public PageInfo pageInfo;
    public List<Channel> items;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.pageInfo, flags);
        dest.writeTypedList(this.items);
    }

    public ChannelsResponse() {
    }

    protected ChannelsResponse(Parcel in) {
        this.pageInfo = in.readParcelable(PageInfo.class.getClassLoader());
        this.items = in.createTypedArrayList(Channel.CREATOR);
    }

    protected void onTest() {

    }

    public static final Parcelable.Creator<ChannelsResponse> CREATOR = new Parcelable.Creator<ChannelsResponse>() {
        @Override
        public ChannelsResponse createFromParcel(Parcel source) {
            return new ChannelsResponse(source);
        }

        @Override
        public ChannelsResponse[] newArray(int size) {
            return new ChannelsResponse[size];
        }
    };
}
