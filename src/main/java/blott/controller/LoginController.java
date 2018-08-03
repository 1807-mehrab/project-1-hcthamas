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

import blott.dao.LoginDao;
import blott.dao.PostsDao;
import blott.dao.ThreadDao;
import blott.dao.UserDao;
import blott.object.Post;
import blott.object.Threads;

@WebServlet("/LoginController")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String un = req.getParameter("name");
		String pw = req.getParameter("pass");
		PrintWriter out = resp.getWriter();
		
		if (LoginDao.validate(un, pw)) {
//			//User profile
//			out.println(UserDao.profile(un));
//			//Admin Portal
//
//			//Threads
//			List<Threads> threads = ThreadDao.displayAll();
//			for (Threads t : threads) {
//				out.println(t.toString());
//			}
//			//Posts
//			List<Post> posts = PostsDao.displayAll();
//			for (Post p : posts) {
//				out.println(p.toString());
//			}
	        HttpSession session=req.getSession();  
	        session.setAttribute("name",un);
	        session.setAttribute("id", LoginDao.getID(un));
	        session.setAttribute("admin", LoginDao.isAdmin(un));
	        RequestDispatcher rd = req.getRequestDispatcher("main.html");
			rd.forward(req,resp);
			return;
		} else {
			out.write("<div style='color:red'>Username or password invalid</div>");
			req.getRequestDispatcher("login.html").include(req, resp);
			return;
		}
	}
}
