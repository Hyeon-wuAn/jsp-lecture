package com.Zero.model;

public class MemberDto {
	private String id;
	private String name;
	private String password;
	private String email;
	private int zonecode;
	private String address;
	private String detailAddress;
	private String extraAddress;
	private String profile;
	private String realprofile;
	
	
	
	
	public MemberDto(String id, String name, String password, String email, int zonecode, String address,
			String detailAddress, String extraAddress, String profile, String realprofile) {
		super();
		this.id = id;
		this.name = name;
		this.password = password;
		this.email = email;
		this.zonecode = zonecode;
		this.address = address;
		this.detailAddress = detailAddress;
		this.extraAddress = extraAddress;
		this.profile = profile;
		this.realprofile = realprofile;
	}

	public MemberDto() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getZonecode() {
		return zonecode;
	}

	public void setZonecode(int zonecode) {
		this.zonecode = zonecode;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDetailAddress() {
		return detailAddress;
	}

	public void setDetailAddress(String detailAddress) {
		this.detailAddress = detailAddress;
	}

	public String getExtraAddress() {
		return extraAddress;
	}

	public void setExtraAddress(String extraAddress) {
		this.extraAddress = extraAddress;
	}

	public String getProfile() {
		return profile;
	}

	public void setProfile(String profile) {
		this.profile = profile;
	}

	public String getRealProfile() {
		return realprofile;
	}

	public void setRealProfile(String realprofile) {
		this.realprofile = realprofile;
	}

	@Override
	public String toString() {
		return "MemberDto [id=" + id + ", name=" + name + ", password=" + password + ", email=" + email + ", zonecode="
				+ zonecode + ", address=" + address + ", detailAddress=" + detailAddress + ", extraAddress="
				+ extraAddress + ", profile=" + profile + ", realprofile=" + realprofile + "]";
	}
	
	
}
