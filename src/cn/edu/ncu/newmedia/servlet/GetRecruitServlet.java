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
        /*
         * 从外界获得一个id，调用DAO层get()方法，获得比该id大的第一个学生的信息
         * 此后每调用完一个学生的信息，则id++
         */
        int uid = Integer.parseInt(request.getParameter("id"));
        /*
         * 获得想要查询某社团信息的communityId
         */
        long ucommunityId = Long.parseLong(request.getParameter("ucommunityId"));

        try {
        	/*
        	 * 获得需要查询的社团id
        	 */
        	String communityIdStr = request.getParameter("communityId");
        	int communityId = 0;
        	if(communityIdStr.equals("")||communityIdStr==null)
        		communityId = 0;
        	else
        		communityId = Integer.parseInt(communityIdStr);
        	/*
        	 * 当id等于该社团报名总人数-1时，即已经获取完所有学生信息
        	 * 拒绝调用get()方法
        	 */
        	if(recruitDao.gettotal(communityId)>uid)
        		recruit = recruitDao.get_one(uid,ucommunityId);
        	/*
        	 * 将获得的学生信息转成json格式
        	 */
            String recruitStr = "\"recruit" + "\"" + ":" + "[";
            recruitStr += "{" + "\"id"+"\""+":"+"\""+ recruit.getId()+"\"," + "\"communityId"+"\""+":"+"\""+recruit.getCommunityId()+"\"," + "\"name"+"\""+":"+"\""+recruit.getName()+"\"," + "\"sex"+"\""+":"+"\""+recruit.getSex()+"\"," + "\"nation"+"\""+":"+"\""+recruit.getNation()+"\"," + "\"birthday"+"\""+":"+"\""+ recruit.getBirthday() +"\"," + "\"nativePlace"+"\""+":"+"\""+ recruit.getNativePlace() +"\"," + "\"politicalAffiliation"+"\""+":"+"\""+ recruit.getPoliticalAffiliation() +"\","  + "\"college"+"\""+":"+"\""+ recruit.getCollege() +"\"," + "\"major"+"\""+":"+"\""+ recruit.getMajor() +"\"," + "\"phone"+"\""+":"+"\""+ recruit.getPhone() +"\"," + "\"department1"+"\""+":"+"\""+ recruit.getDepartment1() +"\"," + "\"department2"+"\""+":"+"\""+ recruit.getDepartment2() +"\"," + "\"department3"+"\""+":"+"\""+ recruit.getDepartment3() +"\"," + "\"speciality"+"\""+":"+"\""+ recruit.getSpeciality() +"\"," + "\"experience"+"\""+":"+"\""+ recruit.getExperience() +"\"," + "\"timesId"+"\""+":"+"\""+ recruit.getTimesId() +"\"," + "\"messageState"+"\""+":"+"\""+ recruit.getMessageState() +"\"," + "\"createAt"+"\""+":"+"\""+ recruit.getCreateAt() +"\"," + "\"state"+"\""+":"+"\""+ recruit.getState() +"\"" + "}," ;
            recruitStr = "{"+recruitStr.substring(0, recruitStr.length()-1)+"]}" ;
            System.out.println(recruitStr);
            /*
             * 若通过一轮审核，则调用passone()方法，将状态改为1，并将发送信息状态改为..
             * 若没通过审核，则不做操作
             */
            
            if(recruit.getTimesId()==1)		//还要加一个条件为前端返回为审核通过&&
            	recruitDao.passone(recruit.getStudentId(),recruit.getCommunityId());
            if(recruit.getTimesId()==2)
            	recruitDao.passtwo(recruit.getStudentId(),recruit.getCommunityId());
            /*
             * 当第一轮招新完全结束时(短信发送完成时)，要调用reset()方法，重置学生的发送短信状态
             */
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
