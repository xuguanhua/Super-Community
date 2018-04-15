package cn.edu.ncu.newmedia.servlet;



import cn.edu.ncu.newmedia.bean.CommunityAccount;
import cn.edu.ncu.newmedia.dao.CommunityDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CommunityEditServlet extends HttpServlet {
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        long id = Long.parseLong(request.getParameter("id"));
        CommunityAccount ct =new CommunityDao().get(id);
        request.setAttribute("", ct);
        request.getRequestDispatcher("").forward(request, response);
    }
}