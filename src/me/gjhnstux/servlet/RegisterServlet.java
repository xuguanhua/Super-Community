package me.gjhnstux.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import api.ApiServlet;
import me.gjhnstxu.db.DBServlet;

public class RegisterServlet extends HttpServlet {

	public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		
		//���ñ����ʽ
		request.setCharacterEncoding("utf-8");
		
		/*
		 * ��ȡǰ�˵ķ��͵�����
		 * 1.����Ĳ���step
		 * 2.�ֻ�����mobile
		 * 3.ǰ����д����֤��
		 */
		//������
		String step = request.getParameter("step");

		HttpSession session = request.getSession();
		if(step.equals("1")){
			
			//Ҫ���͵����ֻ�����
			String mobile = request.getParameter("telephone");
			
			//����api
			ApiServlet api = new ApiServlet();
			
			//apikey
			String apikey = "0a85ec42a4c9da1f64f7f1e817255cab";
			
			//���������֤��
			String code = api.newverification();
			
			//
			session.setAttribute("code", code);
			session.setAttribute("mobile", mobile);
			
//			System.out.println("�绰������"+session.getAttribute("mobile"));
//			System.out.println("yzm" + session.getAttribute("code"));
//			System.out.println("��֤����" + code);
			String text = "��NCU��ý�塿�������" + code + "��Ʒ���治�㣬����Ϊ�������˿3�����������˿��ԭ·���ص����ĸ����˻��С�";
			
			String result = api.sendMessage(apikey,text,mobile);
			System.out.println(result);
			
			response.setContentType("json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().print(result);
			
		}else if(step.equals("2")){
			//��ȡǰ����д����֤��
			String codeCheck = request.getParameter("code"); 
			
			//�ж���֤���Ƿ���ͬ
			if(codeCheck.equals(session.getAttribute("code"))){
				System.out.println("��֤����ͬ");
				response.setCharacterEncoding("UTF-8");
				response.getWriter().print("true");
			}
		}else if(step.equals("3")){
			
			//��ȡǰ����д���������ƺ͵�¼����
			String community = request.getParameter("community");
			String password = request.getParameter("password");
			
			String mobile = (String) session.getAttribute("mobile");
			
			DBServlet db = new DBServlet();
			
			db.register(mobile, community, password);
		}else if(step.equals("4")){
			
			String password = request.getParameter("password");
			String mobile = (String) session.getAttribute("mobile");
			
			DBServlet db = new DBServlet();
			db.reset(mobile, password);
			
		}else if(step.equals("5")){
			String mobile = request.getParameter("telephone");
			
			DBServlet db = new DBServlet();
			
			if(db.isExit(mobile)){
				response.setCharacterEncoding("UTF-8");
				response.getWriter().print("true");
			}
		}

	}
	
	public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		doGet(request, response);
	}
}
