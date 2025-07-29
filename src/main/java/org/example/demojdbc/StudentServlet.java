package org.example.demojdbc;


import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.demojdbc.DataBase.DBconect;
import org.example.demojdbc.entites.Student;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "StudenServlet", urlPatterns = {"/student/*"})


public class StudentServlet extends HttpServlet {
    Connection conn = null;

    @Override
    public void init() throws ServletException {
        DBconect dbconect = new DBconect();
        conn = dbconect.getConnection();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uri = req.getPathInfo();
        if (uri == null) {
            uri = "";
        }switch (uri) {
            case "/":
            case "":
                showListUserPage(req,resp);
                break;
                case "/delete":
                    deleteStudent(req,resp);
                    break;
        }
    }
    private void showListUserPage(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        try {
            String sql = "select * from student";
            PreparedStatement ps = conn.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();
            List<Student> List = new ArrayList<>();

            while (rs.next()) {
                int id = rs.getInt("StudentId");
                String name = rs.getString("StudentName");
                String address = rs.getString("Address");
                String phone = rs.getString("Phone");

                Student student = new Student(id, name, address, phone);
                List.add(student);
            }

            req.setAttribute("list", List);
            RequestDispatcher rd = req.getRequestDispatcher("/View/students/list.jsp");
            rd.forward(req,resp);
        }catch (SQLException | IOException e ) {
            throw new ServletException(e);
        }

    }
    private void deleteStudent(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String id = req.getParameter("id");
        try {
            String sql = "delete from student where studentId=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, Integer.parseInt(id));
            ps.execute();
            resp.sendRedirect("/student");
        }catch (SQLException e ) {
            throw new RuntimeException(e);
        }

    }
}
