package cn.edu.ncu.newmedia.servlet;

import cn.edu.ncu.newmedia.bean.Department;
import cn.edu.ncu.newmedia.dao.DepartmentDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DepartmentUpdateServlet extends HttpServlet {
    protected void service(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
            long id = Long.parseLong(request.getParameter("id"));
            long communityId = Long.parseLong(request.getParameter("communityId"));
            String departmentName = request.getParameter("departmentName");

            Department department = new Department();
            department.setId(id);
            department.setCommunityId(communityId);
            department.setDepartmentName(departmentName);

            new DepartmentDao().updateDepartment(department);
            response.sendRedirect("");
    }
}