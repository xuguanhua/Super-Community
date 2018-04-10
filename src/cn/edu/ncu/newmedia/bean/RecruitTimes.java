package cn.edu.ncu.newmedia.bean;

import java.util.Date;

public class RecruitTimes {
    /**
     * id                   招新场次id
     * communityId          社团id
     * interviewersPh       每小时面试的人
     * interviewDate        面试时间
     * interviewAddress     面试地点
     * createAt             招新创建时间
     * endtime              招新结束时间
     * state                招新状态
     */

    private int id;
    private int communityId;
    private int interviewersPh;
    private Date interviewDate;
    private String interviewAddress;
    private Date createAt;
    private Date endtime;
    private byte state;

    public RecruitTimes(){
    }

    public RecruitTimes(int communityId, int interviewersPh, Date interviewDate, String interviewAddress,
        Date createAt, Date endtime, byte state){
            this.communityId = communityId;
            this.interviewersPh = interviewersPh;
            this.interviewDate = interviewDate;
            this.interviewAddress = interviewAddress;
            this.createAt = createAt;
            this.endtime = endtime;
            this.state = state;
    }

    public RecruitTimes(int id, int communityId, int interviewersPh, Date interviewDate, String interviewAddress,
        Date createAt, Date endtime, byte state){
            this.id = id;
            this.communityId = communityId;
            this.interviewersPh = interviewersPh;
            this.interviewDate = interviewDate;
            this.interviewAddress = interviewAddress;
            this.createAt = createAt;
            this.endtime = endtime;
            this.state = state;
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCommunityId() {
		return communityId;
	}

	public void setCommunityId(int communityId) {
		this.communityId = communityId;
	}

	public int getInterviewersPh() {
		return interviewersPh;
	}

	public void setInterviewersPh(int interviewersPh) {
		this.interviewersPh = interviewersPh;
	}

	public Date getInterviewDate() {
		return interviewDate;
	}

	public void setInterviewDate(Date interviewDate) {
		this.interviewDate = interviewDate;
	}

	public String getInterviewAddress() {
		return interviewAddress;
	}

	public void setInterviewAddress(String interviewAddress) {
		this.interviewAddress = interviewAddress;
	}

	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

	public Date getEndtime() {
		return endtime;
	}

	public void setEndtime(Date endtime) {
		this.endtime = endtime;
	}

	public byte getState() {
		return state;
	}

	public void setState(byte state) {
		this.state = state;
	}
}