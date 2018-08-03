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
import blott.dao.ThreadDao;
import blott.dao.UserDao;
import blott.object.Post;
import blott.object.Threads;

@WebServlet("/ThreadServlet")
public class ThreadServlet extends HttpServlet {
	@Override

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter out = resp.getWriter();
		HttpSession session = req.getSession();

		String tid = req.getHeader("topics");
		session.setAttribute("thread", tid);
		int uid = (int) session.getAttribute("id");
		boolean adm = (boolean) session.getAttribute("admin");

		Threads thread = ThreadDao.displayTopic(tid);
		// out.println(thread.toString());
		out.print("<div>");
		out.println("<h2><bold>" + thread.getThreadName() + "</bold></h2>");
		out.println("<h4>Created by: " + UserDao.getUsername(thread.getCreatorID()) + "</h4>");
		out.println("<h6>on - " + thread.getTimestamp() + "</h6>");
		out.println("</div></br>");

		List<Post> posts = PostsDao.displayAll(tid);
		if (!posts.isEmpty()) {
			for (Post p : posts) {
				String addEdit = "";
				String addDelete = "";
				if(uid == p.getUserId() || adm) {
					addEdit = "<button type='button' onclick='editPost(" + p.getPostId() + ")'>Edit";
				};
				if(adm) {
					addDelete = "<button type='button' onclick='deletePost(" + p.getPostId() + ")'>Delete";
				};
				out.print("<div>" + p.getMessage() + "</br>Posted by: " + UserDao.getUsername(p.getUserId())
						+ "</br>on - " + p.getCreated() + "</br>"
						+ "<button type='button' onclick='flag(" + p.getPostId() + ")'>Report"
						+ addEdit + addDelete + "</div></br></br>");
			}
		}
		out.println("<form action='PostsController' method='get'>"
				+ "<div><textarea rows=10 cols=25 name='comment' placeholder='Enter reply....'></textarea><div>"
				+ "<div><input type='submit' value='Reply' onclick='threadify(" + tid + ")'></div></form>");

		out.close();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();

		String tn = req.getParameter("newtopic");
		int un = (int) session.getAttribute("id");

		ThreadDao.postThreads(tn, un);

		doPost(req, resp);
	}

}
