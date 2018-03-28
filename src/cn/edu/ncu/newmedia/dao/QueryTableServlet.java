package cn.ncu.newmedia;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class QueryTableServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	//jdbc驱动名及数据库
	static final String DRIVER ="com.mysql.jdbc.Driver";
	static final String DB_URL ="jdbc:mysql://localhost:3306/newmedia";
	//数据库的用户名与密码
	static final String USER = "root";
	static final String PASS = "admin";
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn = null;
		Statement stmt = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			//打开一个连接
			conn = DriverManager.getConnection(DB_URL,USER,PASS);
			//执行sql查询
			stmt = conn.createStatement();
			
			//设置响应内容
			response.setContentType("text/html;charset=utf-8");
			PrintWriter printWriter=response.getWriter();
			
			//community_account
			String sql_community_account=" select * from community_account";
			ResultSet rs = stmt.executeQuery(sql_community_account);
			//展开结果集数据库
			while (rs.next()) {
				//通过字段检索
				int id = rs.getInt(1);
				String community_phone = rs.getString(2);
				String community_name = rs.getString(3);
				String password = rs.getString(4);
				int password_error = rs.getInt(5);
				
				printWriter.print("id:"+id+"	"+"community_phone:"+community_phone+"		"
								+"community_name:"+community_name+"		"+"password:"+password+"	"
								+"password_error:"+password_error);
				}
			
			//community_messages
			String sql_community_messages="select * from community_messages";
			ResultSet rs1 = stmt.executeQuery(sql_community_messages);
			while(rs1.next()) {
				int id = rs1.getInt(1);
				int cid = rs1.getInt(2);
				String message = rs1.getString(3);
				int readed = rs1.getInt(4);
				String create_at = rs1.getString(5);
				
			}
			
			//department
			String sql_department="select * from department";
			ResultSet rs2 = stmt.executeQuery(sql_department);
			while(rs2.next()) {
				int id = rs2.getInt(1);
				int community_id = rs2.getInt(2);
				String department_name = rs2.getString(3);
				String create_at = rs2.getString(4);
				
			}
			
			//recruit
			String sql_recruit="select * from recruit";
			ResultSet rs3 = stmt.executeQuery(sql_recruit);
			while(rs3.next()) {
				String id = rs3.getString(1);
				int community_id = rs3.getInt(2);
				String name = rs3.getString(3);
				String sex = rs3.getString(4);
				String nation = rs3.getString(5);
				String native_place = rs3.getString(6);
				String political_affiliation = rs3.getString(7);
				String college = rs3.getString(8);
				String major = rs3.getString(9);
				String phone = rs3.getString(10);
				String department1 = rs3.getString(11);
				String department2 = rs3.getString(12);
				String department3 = rs3.getString(13);
				String speciality = rs3.getString(14);
				String experience = rs3.getString(15);
				int times_id = rs3.getInt(16);
				int message_state = rs3.getInt(17);
				String create_at = rs3.getString(18);
				int state = rs3.getInt(19);
				
				printWriter.print("id:"+id+"  "+"community_id:"+community_id
						+"  "+"name:"+name+"  "+"sex:"+sex+"  "+"nation:"+
						nation+"  "+"native_place"+native_place);
			}
			
			//recruit_times
			String sql_recruit_times="select * from recruit_times";
			ResultSet rs4 = stmt.executeQuery(sql_recruit_times);
			while(rs4.next()) {
				int id = rs4.getInt(1);
				int community_id = rs4.getInt(2);
				int interviewers_ph = rs4.getInt(3);
				String interview_date = rs4.getString(4);
				String interview_address = rs4.getString(5);
				String create_at = rs4.getString(6);
				int state = rs4.getInt(7);
			}
			rs.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
            // 最后是用于关闭资源的块
            try{
                if(stmt!=null)
                stmt.close();
            }catch(SQLException se2){
            }
            try{
                if(conn!=null)
                conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }
        }
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
