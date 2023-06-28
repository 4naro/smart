package com.example.smart.signin;

import java.io.IOException;

import javax.sql.DataSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.example.smart.javabean.Place;
import com.example.smart.javabean.User;
import com.example.smart.login.Identification;

import jakarta.annotation.Resource;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
@WebServlet("/signin")
public class SignIn extends HttpServlet {
	@Resource(name = "signin/smart")
	private DataSource ds;
	private static final Logger log = LogManager.getLogger(SignIn.class);

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String name = request.getParameter("name");
		String address = request.getParameter("address");
		String phone = request.getParameter("phone");
		String type = request.getParameter("type");
		Identification svc = new Identification(ds); //in questo modo evitiamo di passare il ds tra i vari metodi

		if (type.equals("user")) {
			User user = svc.saveUser(email, password);
			log.trace(user + "registration done");
			request.getRequestDispatcher("formBooking.html").forward(request, response);
		} else {
			Place place = svc.savePlace(email, password, name, address, phone);
			log.trace(place + "registration done");
			request.getRequestDispatcher("formSpaceAv.html").forward(request, response);
		}

		// SignIn fallisce
		request.setAttribute("message", "sorry, registration failed. Try Again");
		request.getRequestDispatcher("index.html").forward(request, response);
	}

}

