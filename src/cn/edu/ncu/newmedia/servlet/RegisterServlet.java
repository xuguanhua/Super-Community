package cn.edu.ncu.newmedia.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.edu.ncu.newmedia.dao.DBServlet;
import cn.edu.ncu.newmedia.util.message.ApiServlet;

public class RegisterServlet extends HttpServlet {

	public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		
		//设置编码格式
		request.setCharacterEncoding("utf-8");
		
		/*
		 * 获取前端的发送的数据
		 * 1.请求的步骤step
		 * 2.手机号码mobile
		 * 3.前端填写的验证码
		 */
		//请求步骤
		String step = request.getParameter("step");

		HttpSession session = request.getSession();
		if(step.equals("1")){
			
			//要发送到的手机号码
			String mobile = request.getParameter("telephone");
			
			//调用api
			ApiServlet api = new ApiServlet();
			
			//apikey
			String apikey = "0a85ec42a4c9da1f64f7f1e817255cab";
			
			//生成随机验证码
			String code = api.newverification();
			
			//
			session.setAttribute("code", code);
			session.setAttribute("mobile", mobile);
			
//			System.out.println("电话号码是"+session.getAttribute("mobile"));
//			System.out.println("yzm" + session.getAttribute("code"));
//			System.out.println("验证码是" + code);
			String text = "【NCU新媒体】您购买的" + code + "商品因库存不足，正在为您办理退款，3个工作日内退款将会原路返回到您的付款账户中。";
			
			String result = api.sendMessage(apikey,text,mobile);
			System.out.println(result);
			
			response.setContentType("json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().print(result);
			
		}else if(step.equals("2")){
			//获取前端填写的验证码
			String codeCheck = request.getParameter("code"); 
			
			//判断验证码是否相同
			if(codeCheck.equals(session.getAttribute("code"))){
				System.out.println("验证码相同");
				response.setCharacterEncoding("UTF-8");
				response.getWriter().print("true");
			}
		}else if(step.equals("3")){
			
			//获取前端填写的社团名称和登录密码
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
