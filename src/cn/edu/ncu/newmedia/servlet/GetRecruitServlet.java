package cn.edu.ncu.newmedia.servlet;

import cn.edu.ncu.newmedia.bean.Recruit;
import cn.edu.ncu.newmedia.dao.RecruitDao;
import java.io.IOException;
import java.sql.SQLException;

@javax.servlet.annotation.WebServlet(name = "GetRecruitServlet")
public class GetRecruitServlet extends javax.servlet.http.HttpServlet {

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        RecruitDao recruitDao = new RecruitDao();
        Recruit recruit = new Recruit();
        //获得学生id，1,2,3...
        int uid = Integer.parseInt(request.getParameter("id"));
        //获得该社团总的报名人数，若到达最后一个则不允许再获取下一个
        try {
			if(recruitDao.gettotal()<=uid)
				System.out.println("审核已经到达最后一个");
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        //获得社团id
        long ucommunityId = Long.parseLong(request.getParameter("ucommunityId"));

        try {
            //下一个
            recruit = recruitDao.get(uid,ucommunityId);
            String recruitStr = "\"recruit" +  "\"" + ":" + "[" ;
            recruitStr += "{" + "\"id"+"\""+":"+"\""+ recruit.getId()+"\"," + "\"communityId"+"\""+":"+"\""+recruit.getCommunityId()+"\"," + "\"name"+"\""+":"+"\""+recruit.getName()+"\"," + "\"sex"+"\""+":"+"\""+recruit.getSex()+"\"," + "\"nation"+"\""+":"+"\""+recruit.getNation()+"\"," + "\"birthday"+"\""+":"+"\""+ recruit.getBirthday() +"\"," + "\"nativePlace"+"\""+":"+"\""+ recruit.getNativePlace() +"\"," + "\"politicalAffiliation"+"\""+":"+"\""+ recruit.getPoliticalAffiliation() +"\","  + "\"college"+"\""+":"+"\""+ recruit.getCollege() +"\"," + "\"major"+"\""+":"+"\""+ recruit.getMajor() +"\"," + "\"phone"+"\""+":"+"\""+ recruit.getPhone() +"\"," + "\"department1"+"\""+":"+"\""+ recruit.getDepartment1() +"\"," + "\"department2"+"\""+":"+"\""+ recruit.getDepartment2() +"\"," + "\"department3"+"\""+":"+"\""+ recruit.getDepartment3() +"\"," + "\"speciality"+"\""+":"+"\""+ recruit.getSpeciality() +"\"," + "\"experience"+"\""+":"+"\""+ recruit.getExperience() +"\"," + "\"timesId"+"\""+":"+"\""+ recruit.getTimesId() +"\"," + "\"messageState"+"\""+":"+"\""+ recruit.getMessageState() +"\"," + "\"createAt"+"\""+":"+"\""+ recruit.getCreateAt() +"\"," + "\"state"+"\""+":"+"\""+ recruit.getState() +"\"," + "}," ;
            recruitStr = "{"+recruitStr.substring(0, recruitStr.length()-1)+"]}" ;
            System.out.println(recruitStr);
            //若通过审核
            recruitDao.update(recruit.getStudentId(),recruit.getCommunityId());
            //若没通过审核，则不做操作
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
