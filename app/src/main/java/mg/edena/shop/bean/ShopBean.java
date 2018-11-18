package mg.edena.shop.bean;

import android.os.Parcel;
import android.os.Parcelable;

public class ShopBean implements Parcelable {
	private String title;
	private String desc;


	public ShopBean() {
	}

	public ShopBean(String title, String desc) {
		this.title = title;
		this.desc = desc;
	}

	protected ShopBean(Parcel in) {
		title = in.readString();
		desc = in.readString();
	}

	public static final Creator<ShopBean> CREATOR = new Creator<ShopBean>() {
		@Override
		public ShopBean createFromParcel(Parcel in) {
			return new ShopBean(in);
		}

		@Override
		public ShopBean[] newArray(int size) {
			return new ShopBean[size];
		}
	};



	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel parcel, int i) {
		parcel.writeString(title);
		parcel.writeString(desc);
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
}
