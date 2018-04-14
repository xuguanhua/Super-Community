package cn.edu.ncu.newmedia.dao;

import cn.edu.ncu.newmedia.bean.Department;
import cn.edu.ncu.newmedia.util.jdbc.JdbcUtil;

import java.sql.*;
import java.util.List;
import java.util.ArrayList;

public class DepartmentDao{

    public DepartmentDao() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        }
        catch (ClassNotFoundException e){
            e.printStackTrace();
        }
    }

    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    /**----------添加部门并设置部门名称--------- */
    public void addDepartment(Department dt) {
        try {
            //链接数据库
            con = JdbcUtil.getConnection();
            String sql = "insert into department values(null,?,?,?)";
            ps = con.prepareStatement(sql);
            ps.setLong(1, dt.getId());
            ps.setLong(2, dt.getCommunityId());
            ps.setString(3, dt.getDepartmentName());
            //ps.setDate(5,dt.getCreateAt());

            ps.execute();

            rs = ps.getGeneratedKeys();
            if (rs.next()) {
                long tid = rs.getLong(1);
                dt.setId(tid);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        
    }


    /**------------删除部门-------------------- */
    public void deleteDepartment(long id) {
        try {
            con = JdbcUtil.getConnection();
            Statement s = con.createStatement();
            String sql = "delete from department where id = " + id;
            s.execute(sql);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    
    /**----------显示所有部门------------ */
    public List<Department> showAll() {
        return showAll(0, Short.MAX_VALUE);
    }

    public List<Department> showAll(int start, int count) {
        List<Department> list = new ArrayList<Department>();
        try {
            con = JdbcUtil.getConnection();
            String sql = "select * from department order by id desc limit ?, ?";
            ps = con.prepareStatement(sql);

            ps.setInt(1, start);
            ps.setInt(2, count);

            rs = ps.executeQuery();
            while(rs.next()) {
                Department dt = new Department();

                long id = rs.getLong(2);
                long cid = rs.getLong(3);
                String name = rs.getString(5);

                dt.setId(id);
                dt.setCommunityId(cid);
                dt.setDepartmentName(name);

                list.add(dt);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    /**------------修改部门信息------------ */
    public void updateDepartment(Department dt) {
        try {
            con = JdbcUtil.getConnection();
            String sql = "update department set department_id = ?, community_id = ?, department_name = ?";
            ps = con.prepareStatement(sql);
            ps.setLong(1, dt.getId());
            ps.setLong(2, dt.getCommunityId());
            ps.setString(3, dt.getDepartmentName());
            ps.executeUpdate();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

}