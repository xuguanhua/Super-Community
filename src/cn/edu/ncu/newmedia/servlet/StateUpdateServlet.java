package cn.edu.ncu.newmedia.servlet;



import cn.edu.ncu.newmedia.bean.CommunityAccount;
import cn.edu.ncu.newmedia.dao.CommunityDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class StateUpdateServlet extends HttpServlet {
    protected void service(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
            byte state = Byte.parseByte(request.getParameter("state"));

            CommunityAccount ct = new CommunityAccount();
            ct.setState(state);
            
            new CommunityDao().updateCommunity(ct);
            response.sendRedirect("");
    }
}