package blott.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import blott.dao.PostsDao;
import blott.dao.UserDao;

@WebServlet("/UsersController")
public class UsersController extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();

		int uid = (int) session.getAttribute("id");
		String name = req.getParameter("name");
		String pass = req.getParameter("pass");
		String email = req.getParameter("email");

		UserDao.editUser(name, pass, email, uid);
		session.setAttribute("name", name);
		
		RequestDispatcher rd = req.getRequestDispatcher("main.html");
		rd.forward(req, resp);
		return;
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter out = resp.getWriter();
		
		String name = req.getParameter("name");
		String pass = req.getParameter("pass");
		String email = req.getParameter("email");

		UserDao.newUser(name, email, pass, 0);
		
		out.write("<div>User created!</div>");
		req.getRequestDispatcher("login.html").include(req, resp);
		return;
	}
}
