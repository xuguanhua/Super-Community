package cn.edu.ncu.newmedia.bean;

import java.util.Date;

public class Department {
    /**
     * id               部门id
     * communityId      社团id
     * departmentName   部门名称
     * createAt         部门创建时间
     */

    private int id;
    private int departmentId;
    private int communityId;
    private String departmentName;
    private Date createAt;

    public Department() {
    }

    public Department(int departmentId, int communityId, String departmentName, Date createAt) {
        this.departmentId = departmentId;
    	this.communityId = communityId;
        this.departmentName = departmentName;
        this.createAt = createAt;
    }

    public Department(int departmentId, int id, int communityId, String departmentName, Date createAt) {
        this.id = id;
        this.departmentId = departmentId;
        this.communityId = communityId;
        this.departmentName = departmentName;
        this.createAt = createAt;
    }

    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public int getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
	}

	public int getCommunityId() {
        return this.communityId;
    }
    
    public void setCommunityId(int communityId) {
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