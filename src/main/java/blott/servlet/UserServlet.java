package blott.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import blott.dao.UserDao;
import blott.object.User;

@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {
	@Override

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter out = resp.getWriter();
		HttpSession session = req.getSession();

		int id = (int) session.getAttribute("id");

		// print user stuff
		User user = UserDao.profile(id);
		out.println("<p>Username: " + user.getUsername() + "</p>");
		out.println("<p>Email: " + user.getEmail() + "</p>");
		// out.println("<p>Password: " + user.getPassword() + "</p>");

		// getMyTopics()
		out.println("<button type='button' onclick='getMyTopics()'>My Topics");
		out.println("<button type='button' onclick='editProfile()'>Edit Profile");
	}

	@Override

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter out = resp.getWriter();
		HttpSession session = req.getSession();

		int id = (int) session.getAttribute("id");
		User user = UserDao.profile(id);

		// edit form
		out.println("<form action='UsersController' method='post'><ul>"
				+ "<li>Username: <input type='text' name='name' value='" + user.getUsername() + "'></li>"
				+ "<li>Email: <input type='text' name='email' value='" + user.getEmail() + "'></li>"
				+ "<li>Password: <input type='password' name='pass' value='" + user.getPassword() + "'></li>"
				+ "</ul><input type='submit' value='Edit'></form>");

	}

}