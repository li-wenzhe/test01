package com.itheima.util;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * ����Դ�Ĺ�����
 */
public class DataSourceUtils {
    /**
     * ����˽�о�̬����Դ��Ա����
     */
    private static ComboPooledDataSource ds = new ComboPooledDataSource();

    /**
     * �������еĵõ�����Դ�ķ���
     *
     * @return
     */
    public static DataSource getDataSource() {
        return ds;
    }

    /**
     * �����õ����Ӷ���ķ���
     *
     * @return
     */
    public static Connection getConnection() {
        try {
            return ds.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * �����ͷ���Դ�ķ���
     * @param conn
     * @param stmt
     * @param rs
     */
    public static void close(Connection conn, Statement stmt, ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void close(Connection conn, Statement stmt) {
        close(conn, stmt, null);
    }

}
