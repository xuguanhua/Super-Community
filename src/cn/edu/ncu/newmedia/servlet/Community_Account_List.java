package cn.edu.ncu.newmedia.servlet;

import java.util.List;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import org.apache.http.HttpResponse;
import cn.edu.ncu.newmedia.bean.Community_Account;
import cn.edu.ncu.newmedia.dao.Community_AccountDAO;


public class Community_Account_List extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    protected void service(HttpServletRequest request,HttpResponse response) throws Exception {
    	List<Community_Account> community_Accounts = new Community_AccountDAO().list();
    	request.setAttribute("community_Accounts", community_Accounts);
    }

}
