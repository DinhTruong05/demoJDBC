package org.example.demojdbc.DataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBconect {
    private String jdbcUrl = "jdbc:mysql://localhost:3306/quanlysinhvien?useSSl=false";
    private String username = "root";
    private String password = "Dinhtruong95";


    public  DBconect() {

    }

    public Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(jdbcUrl,username,password);
            System.out.println("Connected to database successfully");
        }catch (ClassNotFoundException | SQLException e){
            throw new RuntimeException(e);
        }
        return conn;
    }
}
