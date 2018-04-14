package cn.edu.ncu.newmedia.servlet;



import cn.edu.ncu.newmedia.bean.CommunityAccount;
import cn.edu.ncu.newmedia.dao.CommunityDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CommunityUpdateServlet extends HttpServlet {
    protected void service(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
            String communityName = request.getParameter("communityName");

            CommunityAccount ct = new CommunityAccount();
            ct.setCommunityName(communityName);
            
            new CommunityDao().updateCommunity(ct);
            response.sendRedirect("");
    }
}