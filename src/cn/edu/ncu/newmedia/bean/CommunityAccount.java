package cn.edu.ncu.newmedia.bean;

import java.util.Date;

public class CommunityAccount {
    /**
     * id               社团id
     * communityPhone   社团负责人电话
     * communityName    社团名称
     * password         社团登录密码
     * passwordError    密码出错次数
     * loginTime        上次登录时间
     * state            社团审核状态
     */

    private int id;
    private String communityPhone;
    private String communityName;
    private String password;
    private byte passwordError;
    private Date loginTime;
    private byte state;

    public CommunityAccount(){

    }

    public CommunityAccount(String communityPhone, String communityName, String password, byte passwordError, Date loginTime, byte state) {
        this.communityPhone = communityPhone;
        this.communityName = communityName;
        this.password = password;
        this.passwordError = passwordError;
        this.loginTime = loginTime;
        this.state = state;
    }

    public CommunityAccount(int id, String communityPhone, String communityName, String password, byte passwordError, Date loginTime, byte state) {
        this.id = id;
        this.communityPhone = communityPhone;
        this.communityName = communityName;
        this.password = password;
        this.passwordError = passwordError;
        this.loginTime = loginTime;
        this.state = state;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCommunityPhone() {
        return this.communityPhone;
    }

    public void setCommunityPhone(String communityPhone) {
        this.communityPhone = communityPhone;
    }

    public String getCommunityName() {
        return this.communityName;
    }

    public void setCommunityName(String communityName) {
        this.communityName = communityName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public byte getPasswordError() {
        return passwordError;
    }

    public void setPasswordError(byte passwordError) {
        this.passwordError = passwordError;
    }
    
    public Date getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    public byte getState() {
        return state;
    }

    public void setState(byte state) {
        this.state = state;
    }
}