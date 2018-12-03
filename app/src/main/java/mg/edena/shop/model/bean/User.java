package mg.edena.shop.model.bean;

import com.google.gson.annotations.SerializedName;

public  class User {
	@SerializedName("birthday")
	private String birthday;
	@SerializedName("picture")
	private Picture picture;
	@SerializedName("gender")
	private String gender;
	@SerializedName("email")
	private String email;
	@SerializedName("last_name")
	private String last_name;
	@SerializedName("first_name")
	private String first_name;
	@SerializedName("name")
	private String name;
	@SerializedName("id")
	private String id;
	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public Picture getPicture() {
		return picture;
	}

	public void setPicture(Picture picture) {
		this.picture = picture;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLastName() {
		return last_name;
	}

	public void setLastName(String last_name) {
		this.last_name = last_name;
	}

	public String getFirstName() {
		return first_name;
	}

	public void setFirstName(String first_name) {
		this.first_name = first_name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}



	public static class Picture {
		@SerializedName("data")
		private Data data;

		public Data getData() {
			return data;
		}

		public void setData(Data data) {
			this.data = data;
		}
	}

	public static class Data {
		@SerializedName("width")
		private int width;
		@SerializedName("url")
		private String url;
		@SerializedName("is_silhouette")
		private boolean is_silhouette;
		@SerializedName("height")
		private int height;

		public int getWidth() {
			return width;
		}

		public void setWidth(int width) {
			this.width = width;
		}

		public String getUrl() {
			return url;
		}

		public void setUrl(String url) {
			this.url = url;
		}

		public boolean isSilhouette() {
			return is_silhouette;
		}

		public void setIslhouette(boolean is_silhouette) {
			this.is_silhouette = is_silhouette;
		}

		public int getHeight() {
			return height;
		}

		public void setHeight(int height) {
			this.height = height;
		}


	}
}
