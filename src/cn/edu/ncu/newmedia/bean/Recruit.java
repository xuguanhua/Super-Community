package cn.edu.ncu.newmedia.bean;

import java.util.Date;

public class Recruit {

    /**.
     * id                       学生学号
     * communityId              社团账号
     * name                     学生姓名
     * sex                      学生性别
     * nation                   学生民族
     * birthday                 学生生日
     * nativePlace              籍贯
     * politicalAffiliation     学生政治面貌
     * college                  学生所属学院
     * major                    学生专业
     * phone                    学生电话
     * department1              第一志愿部门
     * department2              第二志愿部门
     * department3              第三志愿部门
     * speciality               学生特长
     * experience               学生成长经历
     * timesId                  招新场次id
     * messageState             是否需要发送信息
     * createAt                 学生申请时间
     * state                    招新状态
     */

    private int id;
    private String studentId;
    private int communityId;
    private String name;
    private String sex;
    private String nation;
    private Date birthday;
    private String nativePlace;
    private String politicalAffiliation;
    private String college;
    private String major;
    private String phone;
    private String department1;
    private String department2;
    private String department3;
    private String speciality;
    private String experience;
    private long timesId;
    private byte messageState;
    private Date createAt;
    private byte state;

    public Recruit() {
    }

    public Recruit(String studentId,int communityId, String name, String sex, String nation, Date birthday,
        String nativePlace, String politicalAffiliation, String college, String major, String phone,
        String department1, String department2, String department3, String speciality, String experience,
        long timesId, byte messageState, Date createAt, byte state) {
            this.studentId = studentId;
            this.communityId = communityId;
            this.name = name;
            this.sex = sex;
            this.nation = nation;
            this.birthday = birthday;
            this.nativePlace = nativePlace;
            this.politicalAffiliation = politicalAffiliation;
            this.college = college;
            this.major = major;
            this.phone = phone;
            this.department1 = department1;
            this.department2 = department2;
            this.department3 = department3;
            this.speciality = speciality;
            this.experience = experience;
            this.timesId = timesId;
            this.messageState = messageState;
            this.createAt = createAt;
            this.state = state;
            
    }

    public Recruit(int id, String studentId,int communityId, String name, String sex, String nation, Date birthday,
        String nativePlace, String politicalAffiliation, String college, String major, String phone,
        String department1, String department2, String department3, String speciality, String experience,
        long timesId, byte messageState, Date createAt, byte state) {
            this.id = id;
            this.studentId = studentId;
            this.communityId = communityId;
            this.name = name;
            this.sex = sex;
            this.nation = nation;
            this.birthday = birthday;
            this.nativePlace = nativePlace;
            this.politicalAffiliation = politicalAffiliation;
            this.college = college;
            this.major = major;
            this.phone = phone;
            this.department1 = department1;
            this.department2 = department2;
            this.department3 = department3;
            this.speciality = speciality;
            this.experience = experience;
            this.timesId = timesId;
            this.messageState = messageState;
            this.createAt = createAt;
            this.state = state;
            
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStudentId() {return this.studentId;}

    public void setStudentId(String studentId) {this.studentId = studentId;}

    public int getCommunityId() {
        return this.communityId;
    }

    public void setCommunityId(int communityId) {
        this.communityId = communityId;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return this.sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getNation() {
        return this.nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public Date getBirthday() {
        return this.birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getNativePlace() {
        return this.nativePlace;
    }

    public void setNativePlace(String nativePlace) {
        this.nativePlace = nativePlace;
    }

    public String getPoliticalAffiliation() {
        return this.politicalAffiliation;
    }

    public void setPoliticalAffiliation(String politicalAffiliation) {
        this.politicalAffiliation = politicalAffiliation;
    }

    public String getCollege() {
        return this.college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public String getMajor() {
        return this.major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDepartment1() {
        return this.department1;
    }

    public void setDepartment1(String department1) {
        this.department1 = department1;
    }

    public String getDepartment2() {
        return this.department2;
    }

    public void setDepartment2(String department2) {
        this.department2 = department2;
    }

    public String getDepartment3() {
        return this.department3;
    }

    public void setDepartment3(String department3) {
        this.department3 = department3;
    }

    public String getSpeciality() {
        return this.speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public String getExperience() {
        return this.experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public long getTimesId() {
        return this.timesId;
    }

    public void setTimesId(long timesId) {
        this.timesId = timesId;
    }

    public byte getMessageState() {
        return this.messageState;
    }

    public void setMessageState(byte messageState) {
        this.messageState = messageState;
    }

    public Date getCreateAt() {
        return this.createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public byte getState() {
        return this.state;
    }

    public void setState(byte state) {
        this.state = state;
    }
}