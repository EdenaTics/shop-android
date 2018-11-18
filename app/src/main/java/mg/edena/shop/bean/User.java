package mg.edena.shop.bean;

public class User {
	/**
	 * id : 1
	 * provider : fb
	 * name : test
	 * nameFirst : first name test
	 * providerId : 456
	 * email : test@test.test
	 * gender : null
	 * birth : 2018-12-10
	 */

	private Long id;
	private String provider;
	private String name;
	private String nameFirst;
	private String providerId;
	private String email;
	private String gender;
	private String birth;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getProvider() {
		return provider;
	}

	public void setProvider(String provider) {
		this.provider = provider;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNameFirst() {
		return nameFirst;
	}

	public void setNameFirst(String nameFirst) {
		this.nameFirst = nameFirst;
	}

	public String getProviderId() {
		return providerId;
	}

	public void setProviderId(String providerId) {
		this.providerId = providerId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getBirth() {
		return birth;
	}

	public void setBirth(String birth) {
		this.birth = birth;
	}
}
