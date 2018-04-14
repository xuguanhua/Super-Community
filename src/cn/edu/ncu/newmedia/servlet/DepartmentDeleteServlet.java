package cn.edu.ncu.newmedia.servlet;

import cn.edu.ncu.newmedia.dao.DepartmentDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DepartmentDeleteServlet extends HttpServlet {
    protected void service(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
            long id = Integer.parseInt(request.getParameter("id"));
            new DepartmentDao().deleteDepartment(id);

            response.sendRedirect(""); 
        }
}