import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class JDBCUtils {
    // 成员变量用接口类型变量接收
    static DataSource dataSource;//null

    static {
        try {
            //ComboPooledDataSource pool = new ComboPooledDataSource();//pool有得到连接方法,成员变量用接口类型变量接收
            //ComboPooledDataSource pool = new ComboPooledDataSource("itheimac3p0");//pool有得到连接方法
            //dataSource = new ComboPooledDataSource();

            //工厂类,得到连接池对象.得到连接//能够使用DRUID连接池(必须掌握)
            Properties p = new Properties();
            InputStream is = JDBCUtils.class.getClassLoader().getResourceAsStream("druid.properties");
            p.load(is);//p

            //DataSource dataSource = DruidDataSourceFactory.createDataSource(p);//得到连接池对象dataSource.得到连接
            dataSource = DruidDataSourceFactory.createDataSource(p);//得到连接池对象dataSource.得到连接
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        try {
            //Class.forName("com.mysql.jdbc.Driver");//注册驱动一次
            //Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/d17", "root", "root");
            //Connection connection = DriverManager.getConnection(url, user, password);//mysql

            //能够编写C3P0连接池工具类(必须掌握)//静态代码块赋值对象,走一次
            //ComboPooledDataSource pool = new ComboPooledDataSource();//pool有得到连接方法,成员变量用接口类型变量接收
            //ComboPooledDataSource pool = new ComboPooledDataSource("itheimac3p0");//pool有得到连接方法
            Connection connection = dataSource.getConnection();
            return connection;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }


    public static void close(ResultSet resultSet,PreparedStatement preparedStatement,Connection connection) {
        if (resultSet!=null){
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (preparedStatement!=null){
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (connection!=null){
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
