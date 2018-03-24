package me.gjhnstxu.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBServlet {

	/**
	 * 判断社团ID是否存在
	 *
	 */
	public boolean isExit(String mobile){
		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			//连接数据库
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/community_recruit?useSSL=false","root","password");
			
			//查询数据库
			String select = "select community_phone from community_account where community_phone=? ";
			//返回执行句柄
			PreparedStatement stmt = conn.prepareStatement(select);
			stmt.setString(1, mobile);
			
			ResultSet rs = stmt.executeQuery();
			if(rs.next()){
				return true;
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		return false;
	}
	/**
	 * 注册社团
	 */
	public void register(String mobile,String name,String password){
		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			//连接数据库
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/community_recruit?useSSL=false","root","password");
			
			//添加一个社团到数据库中
			String insert = "insert into community_account(community_phone,community_name,password) values(?,?,?)";
			
			//返回执行句柄
			PreparedStatement stmt = conn.prepareStatement(insert);
			//数据添加
			stmt.setString(1, mobile);
			stmt.setString(2, name);
			stmt.setString(3, password);
			//提交修改
			stmt.executeUpdate();
			stmt.close();
			conn.close();
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 忘记密码
	 */
	public void reset(String mobile,String password){
		
		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			//连接数据库
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/community_recruit?useSSL=false","root","password");
			
			//修改密码
			String update = "update community_account set password=? where community_phone=?";
			//返回执行句柄
			PreparedStatement stmt = conn.prepareStatement(update);
			//数据添加
			stmt.setString(2, mobile);
			stmt.setString(1, password);
			//提交修改
			stmt.executeUpdate();
			stmt.close();
			conn.close();
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
