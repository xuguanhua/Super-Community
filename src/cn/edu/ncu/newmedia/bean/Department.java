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
    private int communityId;
    private String departmentName;
    private Date createAt;
    private String description;

    public Department() {
    }

    public Department(int communityId, String departmentName, Date createAt, String description) {
        this.communityId = communityId;
        this.departmentName = departmentName;
        this.createAt = createAt;
        this.description = description;
    }

    public Department(int id, int communityId, String departmentName, Date createAt, String description) {
        this.id = id;
        this.communityId = communityId;
        this.departmentName = departmentName;
        this.createAt = createAt;
        this.description = description;
    }

    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}