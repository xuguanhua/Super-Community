package cn.edu.ncu.newmedia.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.ncu.newmedia.bean.Department;
import cn.edu.ncu.newmedia.dao.CommunityAccountDao;
import cn.edu.ncu.newmedia.util.json.ToJSON;

public class ShowDepartmentInfo extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int communityId = Integer.parseInt(request.getParameter("communityId"));
		CommunityAccountDao cad = new CommunityAccountDao();
		ArrayList<Department> departments = cad.getDepartmentInfo(communityId);
		String json = null;
		json = ToJSON.departmentsListToJSON(departments);
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().write(json);
	}
}