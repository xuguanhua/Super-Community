package cn.edu.ncu.newmedia.bean;

import java.util.Date;

public class CommunityMessages {
    /**
     * id           消息id
     * cid          社团id
     * message      消息内容
     * readed       是否已经查看
     * createAt     消息创建时间
     */

    private long id;
    private long cid;
    private String message;
    private int readed;
    private Date createAat;

    public CommunityMessages() {
    }

    public CommunityMessages(long cid, String message, int readed, Date createAat) {
        this.cid = cid;
        this.message = message;
        this.readed = readed;
        this.createAat = createAat;
    }

    public CommunityMessages(long id, long cid, String message, int readed, Date createAat) {
        this.id = id;
        this.cid = cid;
        this.message = message;
        this.readed = readed;
        this.createAat = createAat;
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getCid() {
        return this.cid;
    }

    public void setCid(long cid) {
        this.cid = cid;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getReaded() {
        return this.readed;
    }

    public void setReaded(int readed) {
        this.readed = readed;
    }

    public Date getCreateAat() {
        return this.createAat;
    }

    public void setCreateAat(Date createAat) {
        this.createAat = createAat;
    }
}