package com.hongka.hkcommonlibrary.retrofit.model.youtube;

import android.os.Parcel;
import android.os.Parcelable;

public class Statistics implements Parcelable {
	public String viewCount;
	public String likeCount;
	public String dislikeCount;
	public String favoriteCount;
	public String commentCount;
	// channels api 사용
	public String subscriberCount;
	public String videoCount;

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(this.viewCount);
		dest.writeString(this.likeCount);
		dest.writeString(this.dislikeCount);
		dest.writeString(this.favoriteCount);
		dest.writeString(this.commentCount);
		dest.writeString(this.subscriberCount);
		dest.writeString(this.videoCount);
	}

	public Statistics() {
	}

	protected Statistics(Parcel in) {
		this.viewCount = in.readString();
		this.likeCount = in.readString();
		this.dislikeCount = in.readString();
		this.favoriteCount = in.readString();
		this.commentCount = in.readString();
		this.subscriberCount = in.readString();
		this.videoCount = in.readString();
	}

	public static final Parcelable.Creator<Statistics> CREATOR = new Parcelable.Creator<Statistics>() {
		@Override
		public Statistics createFromParcel(Parcel source) {
			return new Statistics(source);
		}

		@Override
		public Statistics[] newArray(int size) {
			return new Statistics[size];
		}
	};
}
