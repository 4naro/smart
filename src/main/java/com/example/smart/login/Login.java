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
	@Resource(name = "login/smart")
	private DataSource ds;
	private static final Logger log = LogManager.getLogger(Login.class);

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String type = request.getParameter("type");
		Identification svc = new Identification(ds);

		if (type.equals("user")) {
			User user = svc.getUser(email, password);
			log.trace(user + "logged");
			request.getRequestDispatcher("formBooking.html").forward(request, response);
		} else {
			Place place = svc.getPlace(email, password); //INCOMPRENSIBILE
			log.trace(place + "logged");
			request.getRequestDispatcher("formSpaceAv.html").forward(request, response);
		}

		// login fallisce
		request.setAttribute("message", "unrecognized user");
		request.getRequestDispatcher("index.html").forward(request, response);
	}

}
