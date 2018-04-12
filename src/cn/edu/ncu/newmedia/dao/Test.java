package cn.edu.ncu.newmedia.dao;

import cn.edu.ncu.newmedia.bean.Recruit;

public class Test {
    public static void main(String []args) throws Exception {
        RecruitDao recruitDao = new RecruitDao();
        Recruit recruit = new Recruit();
        System.out.println("ss");
        
        recruit = recruitDao.get(10,4);
        
        String recruitStr = "\"recruit" +  "\"" + ":" + "[" ;
        recruitStr += "{" + "\"id"+"\""+":"+"\""+ recruit.getId()+"\"," + "\"communityId"+"\""+":"+"\""+recruit.getCommunityId()+"\"," + "\"name"+"\""+":"+"\""+recruit.getName()+"\"," + "\"sex"+"\""+":"+"\""+recruit.getSex()+"\"," + "\"nation"+"\""+":"+"\""+recruit.getNation()+"\"," + "\"birthday"+"\","+":"+"\","+ recruit.getBirthday() +"\"," + "\"nativePlace"+"\""+":"+"\""+ recruit.getNativePlace() +"\"" + "\"politicalAffiliation"+"\","+":"+"\","+ recruit.getPoliticalAffiliation() +"\","  + "\"college"+"\""+":"+"\","+ recruit.getCollege() +"\"," + "\"major"+"\""+":"+"\""+ recruit.getMajor() +"\"," + "\"phone"+"\""+":"+"\""+ recruit.getPhone() +"\"," + "\"department1"+"\""+":"+"\""+ recruit.getDepartment1() +"\"," + "\"department2"+"\""+":"+"\""+ recruit.getDepartment2() +"\"," + "\"department3"+"\""+":"+"\""+ recruit.getDepartment3() +"\"," + "\"speciality"+"\""+":"+"\""+ recruit.getSpeciality() +"\"," + "\"experience"+"\""+":"+"\""+ recruit.getExperience() +"\"," + "\"timesId"+"\""+":"+"\""+ recruit.getTimesId() +"\"," + "\"messageState"+"\""+":"+"\""+ recruit.getMessageState() +"\"," + "\"createAt"+"\""+":"+"\""+ recruit.getCreateAt() +"\"," + "\"state"+"\""+":"+"\""+ recruit.getState() +"\"" + "}," ;

        recruitStr = "{"+recruitStr.substring(0, recruitStr.length()-1)+"]}" ;
        System.out.println(recruitStr);
    }
}
