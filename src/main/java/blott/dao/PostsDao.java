package blott.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import blott.object.Post;
import blott.object.Threads;
import blott.util.ConnectionUtil;

public class PostsDao {
	public static List<Post> displayAll(String id) {
		Post p = null;
		List<Post> posts = new ArrayList<>();

		try {
			Connection con = ConnectionUtil.getConnection();

			PreparedStatement ps = con.prepareStatement(
					"select * from POSTS inner join THREADS on POSTS.T_ID = THREADS.T_ID where THREADS.T_ID = ?");
			ps.setString(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				p = new Post(rs.getInt("P_ID"), rs.getString("MESSAGE"), rs.getInt("T_ID"), rs.getInt("USER_ID"),
						(rs.getInt("FLAG") == 1), rs.getString("CREATED"));
				posts.add(p);
			}
			rs.close();
			ps.close();

		} catch (Exception e) {
			System.out.println(e);
		}
		return posts;
	}

	public static List<Post> getFlagged() {
		Post p = null;
		List<Post> posts = new ArrayList<>();

		try {
			Connection con = ConnectionUtil.getConnection();

			PreparedStatement ps = con.prepareStatement(
					"select * from POSTS where FLAG > 0");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				p = new Post(rs.getInt("P_ID"), rs.getString("MESSAGE"), rs.getInt("T_ID"), rs.getInt("USER_ID"),
						(rs.getInt("FLAG") == 1), rs.getString("CREATED"));
				posts.add(p);
			}
			rs.close();
			ps.close();

		} catch (Exception e) {
			System.out.println(e);
		}
		return posts;
	}
	
	public static void postReply(String msg, String tid, int uid) {
		try {
			Connection con = ConnectionUtil.getConnection();

			PreparedStatement ps = con.prepareStatement("insert into POSTS values(0, ?, ?, ?, 0, current_timestamp(0))");
			ps.setString(1, msg);
			ps.setInt(2, Integer.parseInt(tid));
			ps.setInt(3, uid);

			ps.executeQuery();
			ps.close();

		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public static void flagPost(int pid) {
		try {
			Connection con = ConnectionUtil.getConnection();

			PreparedStatement ps = con.prepareStatement("update POSTS set FLAG = 1 where P_ID = ?");
			ps.setInt(1, pid);

			ps.executeQuery();
			ps.close();

		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	public static void editPost(int pid, String msg) {
		try {
			Connection con = ConnectionUtil.getConnection();

			PreparedStatement ps = con.prepareStatement("update POSTS set MESSAGE = ? where P_ID = ?");
			ps.setInt(2, pid);
			ps.setString(1, msg);

			ps.executeQuery();
			ps.close();

		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	public static void delete(int pid) {
		try {
			Connection con = ConnectionUtil.getConnection();

			PreparedStatement ps = con.prepareStatement("delete POSTS where P_ID = ?");
			ps.setInt(1, pid);

			ps.executeQuery();
			ps.close();

		} catch (Exception e) {
			System.out.println(e);
		}
	}
}