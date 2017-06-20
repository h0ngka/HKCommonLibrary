package com.hongka.hkcommonlibrary.retrofit.model.youtube;

import android.os.Parcel;
import android.os.Parcelable;

public class PageInfo implements Parcelable {
	public String nextPageToken;
	public String prevPageToken;
	public int totalResults;
	public int resultPerPage;

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(this.nextPageToken);
		dest.writeString(this.prevPageToken);
		dest.writeInt(this.totalResults);
		dest.writeInt(this.resultPerPage);
	}

	public PageInfo() {
	}

	protected PageInfo(Parcel in) {
		this.nextPageToken = in.readString();
		this.prevPageToken = in.readString();
		this.totalResults = in.readInt();
		this.resultPerPage = in.readInt();
	}

	public static final Parcelable.Creator<PageInfo> CREATOR = new Parcelable.Creator<PageInfo>() {
		@Override
		public PageInfo createFromParcel(Parcel source) {
			return new PageInfo(source);
		}

		@Override
		public PageInfo[] newArray(int size) {
			return new PageInfo[size];
		}
	};
}
