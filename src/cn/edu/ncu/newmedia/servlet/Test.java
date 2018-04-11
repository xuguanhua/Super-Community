package cn.edu.ncu.newmedia.servlet;

import java.sql.SQLException;
import java.util.ArrayList;

import cn.edu.ncu.newmedia.bean.Recruit;
import cn.edu.ncu.newmedia.dao.CommunityAccountDao;
import cn.edu.ncu.newmedia.util.message.ApiServlet;

public class Test {

	public static void main(String[] args) {
        //apikey
        String apikey = "0a85ec42a4c9da1f64f7f1e817255cab";
        String time = "下周二";
        String place = "a1111";
		CommunityAccountDao dao = new CommunityAccountDao();
		 try {
			ArrayList<Recruit> list = dao.getNameAndPhone("1","6");
			//遍历
			for(Recruit recruit:list){
				
	            //要发送到的手机号码
	            String mobile = recruit.getPhone();

	            //调用api
	            ApiServlet api = new ApiServlet();
//	            
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
