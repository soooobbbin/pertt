package kr.member.vo;

import java.sql.Date;

public class MemberVO {
	private int member_num;

	private String member_id;
	private int auth;
	private String name;
	private String passwd;
	private  String origin_passwd;
	
	private String phone;
	private String phone1;
	private String phone2;
	private String phone3;
	
	private String email;
	private String birth;
	private String birth1;
	private String birth2;
	private String birth3;
	
	private Date reg_date;
	private Date mod_date;

	//비밀번호 일치 여부 체크
	public boolean isCheckedPassword(String memberPasswd) {
		//회원등급(auth) : 0탈퇴,1일반,2관리자
		if(auth > 0 && passwd.equals(memberPasswd)) {
			return true;
		}
		return false;
	}



	public int getMember_num() {
		return member_num;
	}
	public void setMember_num(int member_num) {
		this.member_num = member_num;
	}
	public int getAuth() {
		return auth;
	}
	public void setAuth(int auth) {
		this.auth = auth;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	
	
	public String getOrigin_passwd() {
		return origin_passwd;
	}



	public void setOrigin_passwd(String origin_passwd) {
		this.origin_passwd = origin_passwd;
	}

	
	public String getPhone() {
		return phone;
	}
	
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public String getPhone1() {
		return phone1;
	}

	public void setPhone1(String phone1) {
		this.phone1 = phone1;
	}

	public String getPhone2() {
		return phone2;
	}

	public void setPhone2(String phone2) {
		this.phone2 = phone2;
	}

	public String getPhone3() {
		return phone3;
	}

	public void setPhone3(String phone3) {
		this.phone3 = phone3;
	}

	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getBirth() {
		return birth;
	}

	public void setBirth(String birth) {
		this.birth = birth;;
	}
	
	public String getBirth1() {
		return birth1;
	}

	public void setBirth1(String birth1) {
		this.birth1 = birth1;
	}

	public String getBirth2() {
		return birth2;
	}

	public void setBirth2(String birth2) {
		this.birth2 = birth2;
	}

	public String getBirth3() {
		return birth3;
	}

	public void setBirth3(String birth3) {
		this.birth3 = birth3;
	}

	public Date getReg_date() {
		return reg_date;
	}
	public void setReg_date(Date reg_date) {
		this.reg_date = reg_date;
	}
	public Date getMod_date() {
		return mod_date;
	}
	public void setMod_date(Date mod_date) {
		this.mod_date = mod_date;
	}

	public String getMember_id() {
		return member_id;
	}

	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}
	

	
	

	







}