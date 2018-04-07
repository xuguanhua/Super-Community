package cn.edu.ncu.newmedia.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.mysql.jdbc.PreparedStatement;
import cn.edu.ncu.newmedia.bean.Community_Account;
import cn.edu.ncu.newmedia.util.jdbc.JdbcUtil;

public class Community_AccountDAO {

	Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    
	public List<Community_Account> list() throws Exception{
		List<Community_Account> community_account = new ArrayList<Community_Account>();
		
		try {
			//连接数据库
            conn = JdbcUtil.getConnection();
			
			//执行sql查询
			String sql_community_account=" select * from community_account";
			ResultSet rs = ps.executeQuery(sql_community_account);
			//展开结果集数据库
			while (rs.next()) {
				//通过字段检索
				Community_Account community_accounts = new Community_Account();
				int id = rs.getInt(1);
				String community_phone = rs.getString(2);
				String community_name = rs.getString(3);
				String password = rs.getString(4);
				int password_error = rs.getInt(5);
				String login_time = rs.getString(6);
				int state = rs.getInt(7);
				
				community_accounts.id = id;
				community_accounts.community_phone = community_phone;
				community_accounts.community_name = community_name;
				community_accounts.password = password;
				community_accounts.password_error = password_error;
				community_accounts.login_time = login_time;
				community_accounts.state = state;
				
				community_account.add(community_accounts);
				}
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
            // 最后是用于关闭资源的块
			JdbcUtil.closeResource(conn, rs, ps);
        }
		return community_account;
	}
	//更改审核状态
	public void update(Community_Account community_accounts) throws Exception {
		String sql_update_state = "update community_accounts set state=?where id=?";
		//连接数据库
        conn = JdbcUtil.getConnection();
        ps = (PreparedStatement) conn.prepareStatement(sql_update_state);
        ps.setInt(1,community_accounts.state );
        
        ps.execute();
	}
}
