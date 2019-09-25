package cn.edu.ncu.newmedia.bean;

public class Community_Account {
	public int id;
	public String community_phone;
	public String community_name;
	public String password;
	public int password_error;
	public String login_time;
	public int state;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCommunity_phone() {
		return community_phone;
	}
	public void setCommunity_phone(String community_phone) {
		this.community_phone = community_phone;
	}
	public String getCommunity_name() {
		return community_name;
	}
	public void setCommunity_name(String community_name) {
		this.community_name = community_name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getPassword_error() {
		return password_error;
	}
	public void setPassword_error(int password_error) {
		this.password_error = password_error;
	}
	public String getLogin_time() {
		return login_time;
	}
	public void setLogin_time(String login_time) {
		this.login_time = login_time;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
}
