package blott.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import blott.dao.PostsDao;
import blott.dao.ThreadDao;

@WebServlet("/PostsController")
public class PostsController extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();

		String msg = req.getParameter("comment");
		int uid = (int) session.getAttribute("id");
		String tid = (String) session.getAttribute("thread");

		PostsDao.postReply(msg, tid, uid);

		RequestDispatcher rd = req.getRequestDispatcher("main.html");
		rd.forward(req, resp);
		return;
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		
		int pid = Integer.parseInt(req.getHeader("post"));
		PostsDao.delete(pid);
		
		return;
	}
}
