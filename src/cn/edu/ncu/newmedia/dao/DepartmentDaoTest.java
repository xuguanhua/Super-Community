package cn.edu.ncu.newmedia.dao;

import cn.edu.ncu.newmedia.bean.Department;

import java.util.Calendar;

    public class DepartmentDaoTest {
        public static void main(String[] args) throws Exception{
            CommunityDao cd = new CommunityDao();
            DepartmentDao dd = new DepartmentDao();
            Department dt;
            dt = new Department(7,"aaa", Calendar.getInstance().getTime());

            dd.addDepartment(dt);
            dd.deleteDepartment(1);
            dd.updateDepartment(dt);
            dd.showAll();
        }
    }

