package cn.edu.ncu.newmedia.bean;

public class Recruit_Times {
	public int id;
	public int community_id;
	public int interviewers_ph;
	public String interview_date;
	public String interview_address;
	public String create_at;
	public int state;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCommunity_id() {
		return community_id;
	}
	public void setCommunity_id(int community_id) {
		this.community_id = community_id;
	}
	public int getInterviewers_ph() {
		return interviewers_ph;
	}
	public void setInterviewers_ph(int interviewers_ph) {
		this.interviewers_ph = interviewers_ph;
	}
	public String getInterview_date() {
		return interview_date;
	}
	public void setInterview_date(String interview_date) {
		this.interview_date = interview_date;
	}
	public String getInterview_address() {
		return interview_address;
	}
	public void setInterview_address(String interview_address) {
		this.interview_address = interview_address;
	}
	public String getCreate_at() {
		return create_at;
	}
	public void setCreate_at(String create_at) {
		this.create_at = create_at;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
}
