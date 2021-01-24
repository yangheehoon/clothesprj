package web.model;

public class Member {
	String id;
	String pw;
	String nickname;
	String name;
	String email;
	String gender;
	String phon_num;
	
	public Member() {
		// TODO Auto-generated constructor stub
	}

	public Member(String id, String pw, String nickname, String name, String email, String gender, String phon_num) {
		super();
		this.id = id;
		this.pw = pw;
		this.nickname = nickname;
		this.name = name;
		this.email = email;
		this.gender = gender;
		this.phon_num = phon_num;
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

	public String getPhon_num() {
		return phon_num;
	}

	public void setPhon_num(String phon_num) {
		this.phon_num = phon_num;
	}
	
}
