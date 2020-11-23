package ru.springcourse.lessons;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;

@WebServlet("/TestDBServlet")
public class JDBCTestServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String user = "springstudent";
        String password = "springstudent";
        String jdbcUrl = "jdbc:mysql://localhost:3306/web_customer_tracker?useSSL=false&serverTimezone=UTC";
        String driver = "com.mysql.jdbc.Driver";
        try {
            PrintWriter out = response.getWriter();
            out.println("Connecting to database: " + jdbcUrl);
            Class.forName(driver);
            Connection connection = DriverManager.getConnection(jdbcUrl, user, password);
            out.println("Connection successfull");

        } catch (Exception e) {
            e.printStackTrace();
            throw new ServletException();
        }
    }
}
