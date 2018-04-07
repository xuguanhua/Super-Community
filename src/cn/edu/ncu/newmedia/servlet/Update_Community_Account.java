package cn.edu.ncu.newmedia.servlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.ncu.newmedia.bean.Community_Account;
import cn.edu.ncu.newmedia.dao.Community_AccountDAO;

public class Update_Community_Account extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void service(HttpServletRequest request,HttpServletResponse response) {
		try {
			int state = Integer.parseInt(request.getParameter("state"));
			Community_Account community_Account = new Community_Account();
			community_Account.setState(state);
			new Community_AccountDAO().update(community_Account);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
