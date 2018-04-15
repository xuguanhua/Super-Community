package cn.edu.ncu.newmedia.servlet;

import cn.edu.ncu.newmedia.bean.Department;
import cn.edu.ncu.newmedia.dao.DepartmentDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class DepartmentShowAllServlet extends HttpServlet {
    protected void service(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
            List<Department> list = new DepartmentDao().showAll();
            request.setAttribute("", list);
            request.getRequestDispatcher("").forward(request, response);

    }
}