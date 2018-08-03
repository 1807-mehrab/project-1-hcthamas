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

@WebServlet ("/PostServlet")
public class PostServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		PrintWriter out = resp.getWriter();
		
		int pd = Integer.parseInt(req.getHeader("pid"));

		PostsDao.flagPost(pd);
		out.println("<p style='color:red'>Post has been reported</p>");
		return;
	}
	
	@Override

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter out = resp.getWriter();
		HttpSession session = req.getSession();

		boolean adm = (boolean) session.getAttribute("admin");
		if (adm) {
			List<Post> posts = PostsDao.getFlagged();
			if (!posts.isEmpty()) {
				for (Post p : posts) {
					out.println("<div>" + p.getMessage() + "</br>Posted by: " + UserDao.getUsername(p.getUserId())
							+ "</br>on - " + p.getCreated() + "</br>" + "<button type='button' onclick='editPost("
							+ p.getPostId() + ")'>Edit" + "<button type='button' onclick='deletePost(" + p.getPostId()
							+ ")'>Delete</div>");
				}
			}
		} else {
			out.println("<p style='color:red'>You must be an admin to view this page.</p>");
		}
	}
}
