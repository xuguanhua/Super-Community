package cn.edu.ncu.newmedia.dao;

import cn.edu.ncu.newmedia.bean.CommunityAccount;
import cn.edu.ncu.newmedia.bean.Department;
import cn.edu.ncu.newmedia.util.jdbc.JdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class CommunityAccountDao {

    /**
     * <p>判断社团ID是否存在</p>
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
            String select = "SELECT community_phone FROM community_account WHERE community_phone=?";

            //返回执行句柄
            stmt = conn.prepareStatement(select);
            stmt.setString(1, mobile);

            rs = stmt.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.closeResource(conn, rs, stmt);
        }
        return false;
    }

    /**
     * <p>注册社团</p>
     * @author 徐莞华
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
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.closeResource(conn, stmt);
        }

    }

    /**
     * <p>忘记密码</p>
     * @author 徐莞华
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
     * <p>判断社团是否通过审核</p>
     * @author SwordPlayer
     */
    public static byte doesPassVerification(String mobile) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        byte state = -1;

        try{
            // 连接数据库
            conn = JdbcUtil.getConnection();

            // 查询数据库
            String select = "SELECT state FROM community_account WHERE community_phone=?";

            // 返回执行句柄
            stmt = conn.prepareStatement(select);
            stmt.setString(1, mobile);

            // 获得结果集并将结果返回┗|｀O′|┛ 嗷~~
            rs = stmt.executeQuery();
            if(rs.next()){
                state = rs.getByte("state");
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
        	// 关闭资源时可能会有异常发生哦QAQ
            try {
				JdbcUtil.closeResource(conn, rs, stmt);
			} catch (SQLException e) {
				e.printStackTrace();
			}
        }

        return state;
    }

    /**
     * <p>显示社团基本信息</p>
     * @author SwordPlayer
     */
    public static CommunityAccount getCommunityAccountInfo(String mobile){
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        CommunityAccount communityAccount = new CommunityAccount();

        try{
            // 连接数据库
            conn = JdbcUtil.getConnection();

            // 查询数据库
            String select = "SELECT * FROM community_account WHERE community_phone=?";

            // 返回执行句柄
            stmt = conn.prepareStatement(select);
            stmt.setString(1, mobile);

            rs = stmt.executeQuery();
            while(rs.next()){
                int id = rs.getInt("id");
                String communityPhone = rs.getString("community_phone");
                String communityName = rs.getString("community_name");
                Date loginTime = new Date(rs.getTimestamp("login_time").getTime());
                byte state = rs.getByte("state");
                
                communityAccount.setId(id);
                communityAccount.setCommunityPhone(communityPhone);
                communityAccount.setCommunityName(communityName);
                communityAccount.setLoginTime(loginTime);
                communityAccount.setState(state);
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
        	// 释放资源时可能会有异常发生
            try {
				JdbcUtil.closeResource(conn, rs, stmt);
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
        }
        return communityAccount;
    }

    /**
     * <p>显示部门信息</p>
     * @author SwordPlayer
     */
    public static ArrayList<Department> getDepartmentInfo(int community_id){
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<Department> departmentList = new ArrayList<>();

        try{
            // 连接数据库
            conn = JdbcUtil.getConnection();

            // 通过查询社团id来得到部门信息
            String select = "SELECT * FROM department WHERE community_id=?";

            // 返回执行句柄
            stmt = conn.prepareStatement(select);
            stmt.setInt(1, community_id);

            // 执行指令
            rs = stmt.executeQuery();
            while(rs.next()){
                int id = rs.getInt("id");
                int communityId = rs.getInt("community_id");
                String departmentName = rs.getString("department_name");
                Date createAt = new Date(rs.getTimestamp("create_at").getTime());
                String description = rs.getString("description");
                Department department = new Department(id, communityId, departmentName, createAt, description);
                departmentList.add(department);
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
        	// 释放资源时有可能出现异常哦( $ _ $ )
            try {
				JdbcUtil.closeResource(conn, rs, stmt);
			} catch (SQLException e) {
				e.printStackTrace();
			}
        }
        return departmentList;
    }

}