package cn.edu.ncu.newmedia.bean;

import java.util.Date;

public class Department {
    /**
     * id               部门id
     * communityId      社团id
     * departmentName   部门名称
     * createAt         部门创建时间
     */

    private long id;
    private int departmentId;
    private long communityId;
    private String departmentName;
    private Date createAt;

    public Department() {
    }

    public Department(int departmentId, long communityId, String departmentName, Date createAt) {
        this.departmentId = departmentId;
    	this.communityId = communityId;
        this.departmentName = departmentName;
        this.createAt = createAt;
    }

    public Department(int departmentId, long id, long communityId, String departmentName, Date createAt) {
        this.id = id;
        this.departmentId = departmentId;
        this.communityId = communityId;
        this.departmentName = departmentName;
        this.createAt = createAt;
    }

    public long getId() {
        return this.id;
    }
    
    public void setId(long id) {
        this.id = id;
    }
    
    public int getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
	}

	public long getCommunityId() {
        return this.communityId;
    }
    
    public void setCommunityId(long communityId) {
        this.communityId = communityId;
    }
    
    public String getDepartmentName() {
        return this.departmentName;
    }
    
    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }
    
    public Date getCreateAt() {
        return this.createAt;
    }
    
    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }
}