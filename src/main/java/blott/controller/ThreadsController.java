package blott.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;

import blott.dao.ThreadDao;
import blott.object.Threads;

@WebServlet("/ThreadsController")
public class ThreadsController extends HttpServlet {
	@Override

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter out = resp.getWriter();

		List<Threads> threads = ThreadDao.displayAll();
		
		out.println("<form action=ThreadServlet method='get'>" + "<input type='text' name='newtopic' placeholder='Submit a new thread.'>"
				+ "<input type='submit' value='Submit'></form>");
		
		out.println("<ul>");
		for (Threads t : threads) {
			out.println("<li><button type='button' onclick='threadify(" + t.getThreadID() + ")'>" + t.getThreadName()
					+ "</a></li>");
		}
		out.println("</ul>");
		out.close();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter out = resp.getWriter();
		HttpSession session = req.getSession();
		int un = (int) session.getAttribute("id");

		List<Threads> threads = ThreadDao.displayMine(un);

		//Post a new Topic form
		out.println("<form action=ThreadServlet method='get'>" + "<input type='text' name='newtopic' placeholder='Submit a new thread.'>"
				+ "<input type='submit' value='Submit'>" + "</form>");

		//Displays users created topics
		out.println("<div> Your Threads:");
		if (threads != null) {
			for (Threads t : threads) {
				out.println("<li><button type='button' onclick='threadify(" + t.getThreadID() + ")'>"
						+ t.getThreadName() + "</a></li>");
			}
			out.println("</ul>");
		} else {
			out.println("None found!");
		}
		out.println("</div>");

		out.close();
	}
}
