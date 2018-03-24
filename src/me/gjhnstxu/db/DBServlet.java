package me.gjhnstxu.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBServlet {

	/**
	 * �ж�����ID�Ƿ����
	 *
	 */
	public boolean isExit(String mobile){
		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			//�������ݿ�
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/community_recruit?useSSL=false","root","password");
			
			//��ѯ���ݿ�
			String select = "select community_phone from community_account where community_phone=? ";
			//����ִ�о��
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
	 * ע������
	 */
	public void register(String mobile,String name,String password){
		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			//�������ݿ�
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/community_recruit?useSSL=false","root","password");
			
			//���һ�����ŵ����ݿ���
			String insert = "insert into community_account(community_phone,community_name,password) values(?,?,?)";
			
			//����ִ�о��
			PreparedStatement stmt = conn.prepareStatement(insert);
			//�������
			stmt.setString(1, mobile);
			stmt.setString(2, name);
			stmt.setString(3, password);
			//�ύ�޸�
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
	 * ��������
	 */
	public void reset(String mobile,String password){
		
		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			//�������ݿ�
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/community_recruit?useSSL=false","root","password");
			
			//�޸�����
			String update = "update community_account set password=? where community_phone=?";
			//����ִ�о��
			PreparedStatement stmt = conn.prepareStatement(update);
			//�������
			stmt.setString(2, mobile);
			stmt.setString(1, password);
			//�ύ�޸�
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
