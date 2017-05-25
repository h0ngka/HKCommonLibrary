package com.hongka.hkcommonlibrary.retrofit.model.youtube;

import android.os.Parcel;
import android.os.Parcelable;

public class Snippet implements Parcelable {
	public String publishedAt;
	public String title;
	public String description;
	public Thumbnails thumbnails;
	public String liveBroadcastContent; // live, none
	public String videoId; 				// Search api에서 video type에서는 사용하지 않음.
	public String channeId;
	public String channelTitle;
	public ResourceId resourceId;

	public static class ResourceId implements Parcelable {
		public String videoId; // playlistItems 에서 사용

		@Override
		public int describeContents() {
			return 0;
		}

		@Override
		public void writeToParcel(Parcel dest, int flags) {
			dest.writeString(this.videoId);
		}

		public ResourceId() {
		}

		protected ResourceId(Parcel in) {
			this.videoId = in.readString();
		}

		public static final Creator<ResourceId> CREATOR = new Creator<ResourceId>() {
			@Override
			public ResourceId createFromParcel(Parcel source) {
				return new ResourceId(source);
			}

			@Override
			public ResourceId[] newArray(int size) {
				return new ResourceId[size];
			}
		};
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(this.publishedAt);
		dest.writeString(this.title);
		dest.writeString(this.description);
		dest.writeParcelable(this.thumbnails, flags);
		dest.writeString(this.liveBroadcastContent);
		dest.writeString(this.videoId);
		dest.writeString(this.channeId);
		dest.writeString(this.channelTitle);
		dest.writeParcelable(this.resourceId, flags);
	}

	public Snippet() {
	}

	protected Snippet(Parcel in) {
		this.publishedAt = in.readString();
		this.title = in.readString();
		this.description = in.readString();
		this.thumbnails = in.readParcelable(Thumbnails.class.getClassLoader());
		this.liveBroadcastContent = in.readString();
		this.videoId = in.readString();
		this.channeId = in.readString();
		this.channelTitle = in.readString();
		this.resourceId = in.readParcelable(ResourceId.class.getClassLoader());
	}

	public static final Parcelable.Creator<Snippet> CREATOR = new Parcelable.Creator<Snippet>() {
		@Override
		public Snippet createFromParcel(Parcel source) {
			return new Snippet(source);
		}

		@Override
		public Snippet[] newArray(int size) {
			return new Snippet[size];
		}
	};
}
