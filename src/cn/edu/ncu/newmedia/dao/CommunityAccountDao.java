package cn.edu.ncu.newmedia.dao;

import cn.edu.ncu.newmedia.bean.Recruit;
import cn.edu.ncu.newmedia.util.jdbc.JdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CommunityAccountDao {

    /**
     * 判断社团ID是否存在
     * @author xuguanhua
     * @throws Exception
     */
    public boolean isExit(String mobile) throws Exception {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            //连接数据库
            conn = JdbcUtil.getConnection();

            //查询数据库
            String select = "SELECT community_phone FROM community_account WHERE community_phone=? ";
            //返回执行句柄
            stmt = conn.prepareStatement(select);
            stmt.setString(1, mobile);

            rs = stmt.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            JdbcUtil.closeResource(conn, rs, stmt);
        }


        return false;
    }

    /**
     * 注册社团
     * @author xuguanhua
     * @throws Exception
     */
    public void register(String mobile, String name, String password) throws Exception {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {

            //连接数据库
            conn = JdbcUtil.getConnection();

            //添加一个社团到数据库中
            String insert = "INSERT INTO community_account(community_phone,community_name,password) VALUES(?,?,?)";

            //返回执行句柄
            stmt = conn.prepareStatement(insert);
            //数据添加
            stmt.setString(1, mobile);
            stmt.setString(2, name);
            stmt.setString(3, password);
            //提交修改
            stmt.executeUpdate();


        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            JdbcUtil.closeResource(conn, stmt);
        }

    }

    /**
     * 忘记密码
     * @author xuguanhua
     * @throws Exception
     */
    public void reset(String mobile, String password) throws Exception {

        Connection conn = null;
        PreparedStatement stmt = null;
        try {

            //连接数据库
            conn = JdbcUtil.getConnection();

            //修改密码
            String update = "UPDATE community_account SET password=? WHERE community_phone=?";
            //返回执行句柄
            stmt = conn.prepareStatement(update);
            //数据添加
            stmt.setString(2, mobile);
            stmt.setString(1, password);
            //提交修改
            stmt.executeUpdate();
            //JdbcUtil.closeResource(conn, stmt);

        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            JdbcUtil.closeResource(conn, stmt);
        }

    }
    
    /**
     * @author xuguanhua
     * @param state community_id
     * @return
     * @throws SQLException 
     * @throws  
     * @throws Exception 
     * 由报名者的状态和社团的id来确定要发送短信的报名者的姓名和电话号码
     */
    public ArrayList<Recruit> getNameAndPhone(String state,String community_id) throws SQLException {
    	Connection conn = null;
    	PreparedStatement stmt = null;
    	ResultSet rs = null;
    	ArrayList<Recruit> list = new ArrayList<Recruit>();
    	try{
    		//连接数据库
    		conn = JdbcUtil.getConnection();
    		
    		//查询数据库
    		String select = "SELECT * FROM recruit WHERE state=? && community_id=?";
    		
            //返回执行句柄
            stmt = conn.prepareStatement(select);
            stmt.setString(1, state);
            stmt.setString(2, community_id);
            rs = stmt.executeQuery();
            //遍历查询数据
            while(rs.next()) {
                Recruit rt = new Recruit();//创建一个recruit对象
                //从数据库获取需要的name和phone
                String name = rs.getString("name");
                String phone = rs.getString("phone");
                //将获取到的name和phone添加到recruit对象中
                rt.setName(name);
                rt.setPhone(phone);
                //将对象添加到ArrayList中
                list.add(rt);
            }
    	}catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
            JdbcUtil.closeResource(conn, rs, stmt);
        }
    	
		return list;
    }
}
