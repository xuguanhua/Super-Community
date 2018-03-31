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
    private long communityId;
    private String departmentName;
    private Date createAt;

    public Department() {
    }

    public Department(long communityId, String departmentName, Date createAt) {
        this.communityId = communityId;
        this.departmentName = departmentName;
        this.createAt = createAt;
    }

    public Department(long id, long communityId, String departmentName, Date createAt) {
        this.id = id;
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