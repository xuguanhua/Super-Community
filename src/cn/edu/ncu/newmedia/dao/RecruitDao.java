package cn.edu.ncu.newmedia.dao;

import cn.edu.ncu.newmedia.bean.Recruit;
import cn.edu.ncu.newmedia.util.jdbc.JdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;

public class RecruitDao {
	/*
     * 先获得招新场次，若招新场次为1，则调用一轮审核get(),update()方法
     * 若招新场次为2，则调用二轮审核get(),update()方法
     * 一轮获取每个学生的信息
     * 想了半天，一轮二轮还是分开来写比较好
     */
    public Recruit get_one(int uid,long ucommunityId) throws Exception {
        Connection conn = null;
        PreparedStatement ps = null;
        Recruit recruit = null;
        conn = JdbcUtil.getConnection();
        String sql = "select * from recruit where community_id="+ucommunityId+" and id>+"+uid+" order by id limit 1";
        ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        if(rs.next()){
            int id = rs.getInt(1);
            String studentId = rs.getString(2);
            int communityId = rs.getInt(3);
            String name = rs.getString(4);
            String sex = rs.getString(5);
            String nation = rs.getString(6);
            Date birthday = rs.getDate(7);
            String nativePlace = rs.getString(8);
            String politicalAffiliation = rs.getString(9);
            String college = rs.getString(10);
            String major = rs.getString(11);
            String phone = rs.getString(12);
            String department1 = rs.getString(13);
            String department2 = rs.getString(14);
            String department3 = rs.getString(15);
            String speciality = rs.getString(16);
            String experience = rs.getString(17);
            long timesId = rs.getLong(18);
            byte messageState =rs.getByte(19);
            Date createAt = rs.getDate(20);
            byte state = rs.getByte(21);
            recruit = new Recruit(studentId,communityId,name,sex,nation,birthday,nativePlace,politicalAffiliation,college,major,
                    phone,department1,department2,department3,speciality,experience,timesId,messageState,createAt,state);
        }
        return recruit;
    }

    /*
     * 更新第一轮审核状态
     */
    public void passone(String studentId,int communityId) throws Exception {
    	Connection conn = null;
        PreparedStatement ps = null;
        Recruit recruit = null;
        conn = JdbcUtil.getConnection();
        String sql_state = "update recruit set state=2 where student_id="+studentId+" and community_id="+communityId;
        ps = conn.prepareStatement(sql_state);
        //只要通过审核，无论是一轮还是二轮，都要将发送短信状态置为1
        String sql_message = "update recruit set message_state=1 where id="+studentId+" and community_id="+communityId;
        ps = conn.prepareStatement(sql_message);
    }
    
    /*
     * 返回该社团总的报名人数
     */
    public int gettotal (int communityId) throws Exception {
    	Connection conn = null;
        PreparedStatement ps = null;
        Recruit recruit = null;
        conn = JdbcUtil.getConnection();
        String sql = "select count(community_id) from recruit where community_id = "+communityId;
        ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        int total;
		if(rs.next()&&rs!=null)
        	total = rs.getInt(1);
        else
        	total=0;
        return total;
    }
    /*
     * 一轮招新发送完短信之后，将发送短信状态重置为0
     * 第二轮审核是否通过以是否发送短信为参照
     */
    public void reset() throws Exception {
    	boolean result;
    	Connection conn = null;
        PreparedStatement ps = null;
        Recruit recruit = null;
        conn = JdbcUtil.getConnection();
        String sql = "update recruit set message_state=0 where message_state=1";
        ps = conn.prepareStatement(sql);
    }
    /*
     * 一轮招新结束，将挑选出审核状态为1的学生，一个一个进行返回
     * servlet对id进行遍历，并同时传id值，servlet判断，若为空，则继续遍历
     */
    public Recruit get_two(int uid) throws Exception {
    	 Connection conn = null;
         PreparedStatement ps = null;
         Recruit recruit = null;
         conn = JdbcUtil.getConnection();
         String sql = "select * from recruit where state=1 and id= "+uid;
         ps = conn.prepareStatement(sql);
         ResultSet rs = ps.executeQuery();
         if(rs.next()){
             int id = rs.getInt(1);
             String studentId = rs.getString(2);
             int communityId = rs.getInt(3);
             String name = rs.getString(4);
             String sex = rs.getString(5);
             String nation = rs.getString(6);
             Date birthday = rs.getDate(7);
             String nativePlace = rs.getString(8);
             String politicalAffiliation = rs.getString(9);
             String college = rs.getString(10);
             String major = rs.getString(11);
             String phone = rs.getString(12);
             String department1 = rs.getString(13);
             String department2 = rs.getString(14);
             String department3 = rs.getString(15);
             String speciality = rs.getString(16);
             String experience = rs.getString(17);
             long timesId = rs.getLong(18);
             byte messageState =rs.getByte(19);
             Date createAt = rs.getDate(20);
             byte state = rs.getByte(21);
             recruit = new Recruit(studentId,communityId,name,sex,nation,birthday,nativePlace,politicalAffiliation,college,major,
                     phone,department1,department2,department3,speciality,experience,timesId,messageState,createAt,state);
         }
         return recruit;
    }
    
    /*
     * 更新第二轮审核状态
     */
    public void passtwo(String studentId,int communityId) throws Exception {
    	Connection conn = null;
        PreparedStatement ps = null;
        Recruit recruit = null;
        conn = JdbcUtil.getConnection();
        //若通过审核，将发送短信状态置为1
        String sql_message = "update recruit set message_state=1 where id="+studentId+" and community_id="+communityId;
        ps = conn.prepareStatement(sql_message);
    }
}
