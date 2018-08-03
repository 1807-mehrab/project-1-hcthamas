package blott.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import blott.dao.PostsDao;
import blott.dao.UserDao;
import blott.object.Post;

@WebServlet("/AdminServlet")
public class AdminServlet extends HttpServlet {

	@Override

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter out = resp.getWriter();
		HttpSession session = req.getSession();

		boolean adm = (boolean) session.getAttribute("admin");
		if (adm) {
			out.println("<form action='AdminController' method='post'>"
					+ "<div>Username: <input type='text' name='name' placeholder='Username'></div>"
					+ "<div>Email   : <input type='text' name='email' placeholder='Email'></div>"
					+ "<div>Password: <input type='password' name='pass' placeholder='Password'></div>"
					+ "<legend>Access Level</legend>"
					+ "<div><input type='radio' id='adminC' name='acccess' value='admin'>"
					+ "<label for='adminC'>Admin</label></div>"
					+ "<div><input type='radio' id='plebC' name='acccess' value='basic' checked>"
					+ "<label for='plebC'>Basic</label></div>" 
					+ "<input type='submit' value='Add'></form>");
		} else {
			out.println("<p style='color:red'>You must be an admin to view this page.</p>");
		}
	}

	@Override

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter out = resp.getWriter();
		HttpSession session = req.getSession();

		boolean adm = (boolean) session.getAttribute("admin");
		if (adm) {
			out.println("<button type='button' onclick='viewFlags()'>Reported Posts");
			out.println("<button type='button' onclick='viewUsers()'>Users List");
			out.println("<button type='button' onclick='addUser()'>Add User");

		} else {
			out.println("<p style='color:red'>You must be an admin to view this page.</p>");
		}
	}
}
