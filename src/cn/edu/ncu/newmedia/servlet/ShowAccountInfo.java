package cn.edu.ncu.newmedia.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.ncu.newmedia.bean.CommunityAccount;
import cn.edu.ncu.newmedia.dao.CommunityAccountDao;
import cn.edu.ncu.newmedia.util.json.ToJSON;

public class ShowAccountInfo extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String mobile = request.getParameter("mobile");
		CommunityAccountDao cad = new CommunityAccountDao();
		CommunityAccount ca  = cad.getCommunityAccountInfo(mobile);
		ArrayList<CommunityAccount> list = new ArrayList<>();
		list.add(ca);
		String json = null;
		json = ToJSON.communityAccountListToJSON(list);
		
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().write(json);
	}
}