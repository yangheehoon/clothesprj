package web.model;

import java.util.Date;

public class Member {
	String id;
	String pw;
	String nickname;
	String name;
	int birth;
	String email;
	String gender;
	String phone_num;
	Date regdate;
	String pro_file;
	
	public Member() {
		// TODO Auto-generated constructor stub
	}

	public Member(String id, String pw, String nickname, String name, int birth, String email, String gender, String phone_num, Date regdate, String pro_file) {
		super();
		this.id = id;
		this.pw = pw;
		this.nickname = nickname;
		this.name = name;
		this.birth = birth;
		this.email = email;
		this.gender = gender;
		this.phone_num = phone_num;
		this.regdate = regdate;
		this.pro_file = pro_file;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getPhone_num() {
		return phone_num;
	}

	public void setPhone_num(String phone_num) {
		this.phone_num = phone_num;
	}

	public int getBirth() {
		return birth;
	}

	public void setBirth(int birth) {
		this.birth = birth;
	}

	public Date getRegdate() {
		return regdate;
	}

	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}

	public String getPro_file() {
		return pro_file;
	}

	public void setPro_file(String pro_file) {
		this.pro_file = pro_file;
	}

	@Override
	public String toString() {
		return "Member [id=" + id + ", pw=" + pw + ", nickname=" + nickname + ", name=" + name + ", birth=" + birth
				+ ", email=" + email + ", gender=" + gender + ", phone_num=" + phone_num + ", regdate=" + regdate
				+ ", pro_file=" + pro_file + "]";
	}
	
	
}
