package cn.edu.ncu.newmedia.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.ncu.newmedia.bean.CommunityAccount;
import cn.edu.ncu.newmedia.dao.CommunityAccountDao;

import java.io.IOException;

public class LoginServlet extends HttpServlet {

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 设置编码格式
		request.setCharacterEncoding("utf-8");

		// 获取前台的账户和密码
		String userID = request.getParameter("userID");
		String password = request.getParameter("password");

		System.out.println("userID: " + userID);
		System.out.println("password" + password);

		CommunityAccountDao cad = new CommunityAccountDao();

		// 首先判断社团是否存在，如果社团存在就继续获取密码并进行匹配
		if((!"".equals("userID")) && (!"".equals(password))) {
			try {
				if(cad.isExit(userID)) {
					CommunityAccount ca = cad.getCommunityAccountInfo(userID);
					if(password.equals(ca.getPassword())) {
						response.getWriter().write("success");
						// 接下来到哪个页面呢？前端貌似还没写好
						// request.getRequestDispatcher("index.html").forward(request, response);
					}else {
						response.getWriter().write("fail");
						// 接下来怎么做呢？貌似还没说好
						//request.getRequestDispatcher("").forward(request, response);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}