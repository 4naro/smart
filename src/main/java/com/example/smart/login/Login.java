package com.example.smart.login;

import java.io.IOException;

import javax.sql.DataSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.example.smart.javabean.Place;
import com.example.smart.javabean.User;

import jakarta.annotation.Resource;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
@WebServlet("/login")

public class Login extends HttpServlet {
	@Resource(name = "jdbc/smart")
	private DataSource ds;
	private static final Logger log = LogManager.getLogger(Login.class);

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String type = request.getParameter("type");
		LoginSvc svc = new LoginSvc();

		if (type.equals("user")) {
			User user = svc.getUser(ds, email, password);
			request.getRequestDispatcher("formBooking.html").forward(request, response);
		} else {
			Place place = svc.getPlace(ds, email, password);
			request.getRequestDispatcher("formSpaceAv.html").forward(request, response);
		}

		// login fallisce
		request.setAttribute("message", "unrecognized user");
		request.getRequestDispatcher("home.html").forward(request, response);
	}

}
