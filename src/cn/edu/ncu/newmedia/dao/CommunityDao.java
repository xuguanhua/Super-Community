package cn.edu.ncu.newmedia.dao;

import cn.edu.ncu.newmedia.bean.CommunityAccount;
import cn.edu.ncu.newmedia.util.jdbc.JdbcUtil;

import java.sql.*;
import java.util.List;
import java.util.ArrayList;

public class CommunityDao{

    public CommunityDao() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        }
        catch (ClassNotFoundException e){
            e.printStackTrace();
        }
    }

    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    
    /**----------显示社团信息------------ */
    public CommunityAccount get(int id) {
        CommunityAccount ct = null;
        
        try {
            con = JdbcUtil.getConnection();
            Statement s = con.createStatement();
            String sql = "select * from community_account where id = " + id;

            rs = s.executeQuery(sql);

            if (rs.next()) {
                ct  = new CommunityAccount();
                String communityPhone = rs.getString(2);
                String communityName = rs.getString(3);
                byte state = rs.getByte(8);
                ct.setCommunityPhone(communityPhone);
                ct.setCommunityName(communityName);
                ct.setState(state);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return ct;
    }

    /**------------修改社团信息------------ */
    public void updateCommunity(CommunityAccount ct) {
        try {
            con = JdbcUtil.getConnection();
            String sql = "update community_account set community_name = ?";
            ps = con.prepareStatement(sql);
            ps.setString(3, ct.getCommunityName());
            ps.executeUpdate();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**------------修改社团审核状态-------------- */
    public void updateState(CommunityAccount ct) {
        try {
            con = JdbcUtil.getConnection();
            String sql = "update community_account set state = ?";
            ps = con.prepareStatement(sql);
            ps.setByte(8, ct.getState());
            ps.executeUpdate();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

}
