package org.example.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    // реализуйте настройку соеденения с БД

    private static final String url = "jdbc:postgresql://localhost:5432/jdbc";
    private static final String userName = "postgres";
    private static final String password = "postgres213";

    public static Connection getConnection(){
        Connection conn =null;
        try {
            conn = DriverManager.getConnection(url,userName,password);

        }catch (SQLException e){
            e.getMessage();
        }
        return conn;
    }

}