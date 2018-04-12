package cn.edu.ncu.newmedia.dao;

import cn.edu.ncu.newmedia.bean.Recruit;
import cn.edu.ncu.newmedia.util.jdbc.JdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class RecruitDao {


    
    public Recruit get(int uid,long ucommunityId) throws Exception {
        Connection conn = null;
        PreparedStatement ps = null;
        Recruit recruit = null;
        
        //连接数据库
        conn = JdbcUtil.getConnection();
        
        //执行SQL查询
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

    //更新审核状态
    public void update(String studentId,int communityId) throws SQLException {
    	Connection conn = null;
        PreparedStatement ps = null;
        Recruit recruit = null;
        
        String sql_state = "update recruit set state=2 where student_id="+studentId+" and community_id="+communityId;
        ps = conn.prepareStatement(sql_state);
        String sql_message = "update recruit set message_state=1 where id="+studentId+" and community_id="+communityId;
        ps = conn.prepareStatement(sql_message);
    }
    
    //返回该社团总的报名人数
    public int gettotal () throws SQLException {
    	Connection conn = null;
        PreparedStatement ps = null;
        Recruit recruit = null;
        
        String sql = "select count(community_id) from recruit";
        ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        int total = rs.getInt(1);
        return total;
    }

//    public ArrayList<Recruit> list() throws Exception {
//    	
//        ArrayList<Recruit> recruits = new ArrayList<Recruit>();
//        String sql = "select * from recruit";
//        ResultSet rs = ps.executeQuery(sql);
//        while(rs.next()){
//            Recruit recruit = new Recruit();
//            int id = rs.getInt(1);
//            String studentId = rs.getString(2);
//            long communityId = rs.getLong(3);
//            String name = rs.getString(4);
//            String sex = rs.getString(5);
//            String nation = rs.getString(6);
//            Date birthday = rs.getDate(7);
//            String nativePlace = rs.getString(8);
//            String politicalAffiliation = rs.getString(9);
//            String college = rs.getString(10);
//            String major = rs.getString(11);
//            String phone = rs.getString(12);
//            String department1 = rs.getString(13);
//            String department2 = rs.getString(14);
//            String department3 = rs.getString(15);
//            String speciality = rs.getString(16);
//            String experience = rs.getString(17);
//            long timesId = rs.getLong(18);
//            byte messageState =rs.getByte(19);
//            Date createAt = rs.getDate(20);
//            byte state = rs.getByte(21);
//
//            recruits.add(recruit);
//        }
//        return recruits;
//    }
}
