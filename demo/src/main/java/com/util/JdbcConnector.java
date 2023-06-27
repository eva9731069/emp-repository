package com.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbcConnector {


    //ctbc
//    static final String DB_URL = "jdbc:oracle:thin:@192.168.31.9:1803/wmfpsuat.ctbcbank.com";
//
//    static final String USER = "wmfpssusr";
//    static final String PASS = "wmfps#1234";


    //pixel
    static final String DB_URL = "jdbc:postgresql://localhost:5432/postgres";

    static final String USER = "postgres";
    static final String PASS = "mypassword";

    public static Connection getConnection() {
        Connection conn = null;

        try {
//			Class.forName("oracle.jdbc.driver.OracleDriver");
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }
}
