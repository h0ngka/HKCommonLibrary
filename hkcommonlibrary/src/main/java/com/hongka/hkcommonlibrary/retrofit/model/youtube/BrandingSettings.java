package com.hongka.hkcommonlibrary.retrofit.model.youtube;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class BrandingSettings implements Parcelable {
	public Channel channel;
	public Image image;

	public static class Channel implements Parcelable {
		public String title;
		public String description;
		public String featuredChannelsTitle;
		public List<String> featuredChannelsUrls;

		@Override
		public int describeContents() {
			return 0;
		}

		@Override
		public void writeToParcel(Parcel dest, int flags) {
			dest.writeString(this.title);
			dest.writeString(this.description);
			dest.writeString(this.featuredChannelsTitle);
			dest.writeStringList(this.featuredChannelsUrls);
		}

		public Channel() {
		}

		protected Channel(Parcel in) {
			this.title = in.readString();
			this.description = in.readString();
			this.featuredChannelsTitle = in.readString();
			this.featuredChannelsUrls = in.createStringArrayList();
		}

		public static final Creator<Channel> CREATOR = new Creator<Channel>() {
			@Override
			public Channel createFromParcel(Parcel source) {
				return new Channel(source);
			}

			@Override
			public Channel[] newArray(int size) {
				return new Channel[size];
			}
		};
	}

	public static class Image implements Parcelable {
		public String bannerMobileImageUrl;
		public String bannerMobileExtraHdImageUrl;

		@Override
		public int describeContents() {
			return 0;
		}

		@Override
		public void writeToParcel(Parcel dest, int flags) {
			dest.writeString(this.bannerMobileImageUrl);
			dest.writeString(this.bannerMobileExtraHdImageUrl);
		}

		public Image() {
		}

		protected Image(Parcel in) {
			this.bannerMobileImageUrl = in.readString();
			this.bannerMobileExtraHdImageUrl = in.readString();
		}

		public static final Creator<Image> CREATOR = new Creator<Image>() {
			@Override
			public Image createFromParcel(Parcel source) {
				return new Image(source);
			}

			@Override
			public Image[] newArray(int size) {
				return new Image[size];
			}
		};
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeParcelable(this.channel, flags);
		dest.writeParcelable(this.image, flags);
	}

	public BrandingSettings() {
	}

	protected BrandingSettings(Parcel in) {
		this.channel = in.readParcelable(Channel.class.getClassLoader());
		this.image = in.readParcelable(Image.class.getClassLoader());
	}

	public static final Parcelable.Creator<BrandingSettings> CREATOR = new Parcelable.Creator<BrandingSettings>() {
		@Override
		public BrandingSettings createFromParcel(Parcel source) {
			return new BrandingSettings(source);
		}

		@Override
		public BrandingSettings[] newArray(int size) {
			return new BrandingSettings[size];
		}
	};
}