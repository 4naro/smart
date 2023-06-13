package com.example.smart;

import jakarta.annotation.Resource;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

@SuppressWarnings("serial")
@WebServlet("/hello")
public class Hello extends HttpServlet {
    @Resource(name = "jdbc/smart")
    private DataSource ds;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try (Connection connection = ds.getConnection(); //
                Statement stmt = connection.createStatement(); //
                ResultSet rs = stmt.executeQuery("select count(*) from person")) {
            rs.next();
            response.getWriter().append(rs.getInt(1) + " records in person table");
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }
}
