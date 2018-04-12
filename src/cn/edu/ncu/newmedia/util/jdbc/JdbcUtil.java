package cn.edu.ncu.newmedia.util.jdbc;

import java.io.InputStream;
import java.sql.*;
import java.util.Properties;


public class JdbcUtil {

    private static String DRIVERNAME = null;
    private static String URL = null;

    //数据库用户名和密码
    private static String USER = null;
    private static String PASSWORD = null;

    //连接
    private static Connection conn = null;

    //获取数据库连接信息
    static {
        try {

            //获得参数文件db.properties的参数
            Properties props = new Properties();
            InputStream input = JdbcUtil.class.getClassLoader().getResourceAsStream("db.properties");
            props.load(input);

            DRIVERNAME = props.getProperty("drivername");
            URL = props.getProperty("url");
            USER = props.getProperty("user");
            PASSWORD = props.getProperty("password");

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    /*
     * 	返回值：数据库的连接
     *	功能： 获取数据库连接
     */
    public static Connection getConnection() throws Exception {

        if (conn != null) {
            return conn;
        }

        Class.forName(DRIVERNAME);
        conn = DriverManager.getConnection(URL, USER, PASSWORD);
        return conn;
    }

    /*
     * 参数：连接，结果集，执行句柄
     * 功能：关闭数据库资源
     */
    public static void closeResource(Connection conn, ResultSet rs, PreparedStatement st) throws SQLException {
        st.close();
        rs.close();
        conn.close();
        st=null;
        rs=null;
        conn=null;
    }

    /*
     * 参数：连接，执行句柄
     * 功能：关闭数据库资源
     */
    public static void closeResource(Connection conn, PreparedStatement st) throws SQLException {
        st.close();
        conn.close();
        st=null;
        conn=null;
    }


}
