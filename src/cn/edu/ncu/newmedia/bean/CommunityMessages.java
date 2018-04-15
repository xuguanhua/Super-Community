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

    private int id;
    private int cid;
    private String message;
    private byte readed;
    private Date createAat;

    public CommunityMessages() {
    }

    public CommunityMessages(int cid, String message, byte readed, Date createAat) {
        this.cid = cid;
        this.message = message;
        this.readed = readed;
        this.createAat = createAat;
    }

    public CommunityMessages(int id, int cid, String message, byte readed, Date createAat) {
        this.id = id;
        this.cid = cid;
        this.message = message;
        this.readed = readed;
        this.createAat = createAat;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCid() {
        return this.cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public byte getReaded() {
        return this.readed;
    }

    public void setReaded(byte readed) {
        this.readed = readed;
    }

    public Date getCreateAat() {
        return this.createAat;
    }

    public void setCreateAat(Date createAat) {
        this.createAat = createAat;
    }
}