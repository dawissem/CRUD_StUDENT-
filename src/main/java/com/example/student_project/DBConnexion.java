package com.example.student_project;

import java.sql.*;

public class DBConnexion {

    static String user ="root";
    static  String password = "1469.";
    static String url="jdbc:mysql://localhost:3306/CRUD?useSSL=false";

    static String driver = "com.mysql.cj.jdbc.Driver";

   public static Connection getCon(){
        Connection con= null;
        try {
            Class.forName(driver);
            try {
                con = DriverManager.getConnection(url, user, password);
             } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return  con;
    }


}
