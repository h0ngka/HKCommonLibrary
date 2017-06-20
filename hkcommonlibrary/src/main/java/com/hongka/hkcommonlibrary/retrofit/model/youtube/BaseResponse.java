package com.hongka.hkcommonlibrary.retrofit.model.youtube;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by jusung.kim@sk.com on 2017/06/20
 */

public class BaseResponse implements Parcelable {
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }

    public BaseResponse() {
    }

    protected BaseResponse(Parcel in) {
    }

}
