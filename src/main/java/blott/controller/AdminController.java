package blott.controller;

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
import blott.object.User;

@WebServlet("/AdminController")
public class AdminController extends HttpServlet {
	@Override

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// admin create account(basic or admin)
		PrintWriter out = resp.getWriter();
		HttpSession session = req.getSession();

		int uid = (int) session.getAttribute("id");
		String name = req.getParameter("name");
		String pass = req.getParameter("pass");
		String email = req.getParameter("email");
		String type = req.getParameter("access");
		int c; if(type == "admin") { c = 1;} else{c = 0;};
		boolean adm = (boolean) session.getAttribute("admin");
		if (adm) {
			UserDao.newUser(name, email, pass, c);
		} else {
			out.println("<p style='color:red'>You must be an admin to view this page.</p>");
		}
		
		RequestDispatcher rd = req.getRequestDispatcher("main.html");
		rd.forward(req, resp);
		return;
	}
	
	@Override

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter out = resp.getWriter();
		HttpSession session = req.getSession();

		boolean adm = (boolean) session.getAttribute("admin");
		if (adm) {
			List<User> users = UserDao.getAll();
			if (!users.isEmpty()) {
				for (User u : users) {
					out.println("<div><tr>" + u.getUsername() + "</tr></div>");
				}
			}
		} else {
			out.println("<p style='color:red'>You must be an admin to view this page.</p>");
		}
	}
}
