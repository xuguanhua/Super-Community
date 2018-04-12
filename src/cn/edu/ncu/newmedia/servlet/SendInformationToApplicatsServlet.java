package cn.edu.ncu.newmedia.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.edu.ncu.newmedia.bean.Recruit;
import cn.edu.ncu.newmedia.dao.CommunityAccountDao;
import cn.edu.ncu.newmedia.util.message.ApiServlet;

public class SendInformationToApplicatsServlet extends HttpServlet {

	 public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		 //设置编码格式
		 request.setCharacterEncoding("utf-8");
		 
		 /**
		  * @author xuguanhua
		  * 获取前端发送的数据
		  * 1.获取要发送短信的报名者的状态
		  * 2.获取要发送短信的内容：时间地点
		  *	2
		  * 
		  */
		 
		 String applicateState = request.getParameter("state");
		 String time = request.getParameter("time");
		 String place= request.getParameter("place");
		 
		 //获取到社团id的session值
		 HttpSession session = request.getSession();
		 String cId = (String) session.getAttribute("community_id");
		 
		 
         //apikey
         String apikey = "0a85ec42a4c9da1f64f7f1e817255cab";
		 
		 //第一轮面试通知
		 if(applicateState.endsWith("1")&&!time.equals("")&&!place.equals("")){
			 
			 CommunityAccountDao dao = new CommunityAccountDao();
			 try {
				ArrayList<Recruit> list = dao.getNameAndPhone("1",cId);
				
				//遍历
				for(Recruit recruit:list){
					
		            //要发送到的手机号码
		            String mobile = recruit.getPhone();

		            //调用api
		            ApiServlet api = new ApiServlet();
		            
		            String text = "【NCU新媒体】    "+recruit.getName()+"同学，你好！感谢你能够报名加入学生会！我们将在"+time+"于"+place+"进行此次招新的初试。希望你能够准时到场，我们期待你的加入！收到请回复，谢谢";
		            //发送短信
		            String result = api.sendMessage(apikey, text, mobile);
		            System.out.println(result);
					
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		 }
		 
		 //第一轮面试失败通知
		 if(applicateState.endsWith("1")&&time.equals("")&&place.equals("")){
			 
			 CommunityAccountDao dao = new CommunityAccountDao();
			 try {
				ArrayList<Recruit> list = dao.getNameAndPhone("1",cId);
				
				//遍历
				for(Recruit recruit:list){
					
		            //要发送到的手机号码
		            String mobile = recruit.getPhone();

		            //调用api
		            ApiServlet api = new ApiServlet();
		            
		            String text = "【NCU新媒体】"+recruit.getName()+"同学，你好！这里是信息工程学院学生会。感谢你参加了"+time+"的学生会招新初试。鉴于你的信息与部门的要求上匹配还不足，所以很遗憾没有选择录取你。";
		            //发送短信
		            String result = api.sendMessage(apikey, text, mobile);
		            System.out.println(result);
					
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		 }
		 
		 //第一轮面试成功通知
		 if(applicateState.endsWith("2")&&!time.equals("")&&!place.equals("")){
			 
			 CommunityAccountDao dao = new CommunityAccountDao();
			 try {
				ArrayList<Recruit> list = dao.getNameAndPhone("2",cId);
				
				//遍历
				for(Recruit recruit:list){
					
		            //要发送到的手机号码
		            String mobile = recruit.getPhone();

		            //调用api
		            ApiServlet api = new ApiServlet();
		            
		            String text = "【NCU新媒体】    "+recruit.getName()+"同学，你好！感谢你能够报名加入学生会！我们将在"+time+"于"+place+"进行此次招新的初试。希望你能够准时到场，我们期待你的加入！收到请回复，谢谢";
		            //发送短信
		            String result = api.sendMessage(apikey, text, mobile);
		            System.out.println(result);
					
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		 }
		 
		 //第二轮面试失败通知
		 if(applicateState.endsWith("2")&&time.equals("")&&place.equals("")){
			 
			 CommunityAccountDao dao = new CommunityAccountDao();
			 try {
				ArrayList<Recruit> list = dao.getNameAndPhone("1",cId);
				
				//遍历
				for(Recruit recruit:list){
					
		            //要发送到的手机号码
		            String mobile = recruit.getPhone();

		            //调用api
		            ApiServlet api = new ApiServlet();
		            
		            String text = "【NCU新媒体】"+recruit.getName()+"同学，你好！这里是信息工程学院学生会。感谢你参加了"+time+"的学生会招新初试。鉴于你的信息与部门的要求上匹配还不足，所以很遗憾没有选择录取你。";
		            //发送短信
		            String result = api.sendMessage(apikey, text, mobile);
		            System.out.println(result);
					
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		 }
		 
		 //第三轮面试成功通知（录取通知）
		 if(applicateState.endsWith("3")&&!time.equals("")&&!place.equals("")){
			 
			 CommunityAccountDao dao = new CommunityAccountDao();
			 try {
				ArrayList<Recruit> list = dao.getNameAndPhone("3",cId);
				
				//遍历
				for(Recruit recruit:list){
					
		            //要发送到的手机号码
		            String mobile = recruit.getPhone();

		            //调用api
		            ApiServlet api = new ApiServlet();
		            
		            String text = "【NCU新媒体】    "+recruit.getName()+"同学，你好！感谢你能够报名加入学生会！我们将在"+time+"于"+place+"进行此次招新的初试。希望你能够准时到场，我们期待你的加入！收到请回复，谢谢";
		            //发送短信
		            String result = api.sendMessage(apikey, text, mobile);
		            System.out.println(result);
					
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		 }
		 
	 }
	 
	 public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response); 
	 }

}
