package blott.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import blott.object.Post;
import blott.object.Threads;
import blott.util.ConnectionUtil;

public class ThreadDao {
	public static List<Threads> displayAll() {
		Threads t = null;
		List<Threads> threads = new ArrayList<>();

		try {
			Connection con = ConnectionUtil.getConnection();

			PreparedStatement ps = con.prepareStatement("select * from THREADS");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				t = new Threads(rs.getInt("T_ID"), rs.getString("T_NAME"), rs.getInt("USER_ID"),
						rs.getString("CREATED"));
				threads.add(t);
			}
			rs.close();
			ps.close();

		} catch (Exception e) {
			System.out.println(e);
		}
		return threads;
	}

	public static List<Threads> displayMine(int id) {
		Threads t = null;
		List<Threads> threads = new ArrayList<>();

		try {
			Connection con = ConnectionUtil.getConnection();

			PreparedStatement ps = con.prepareStatement("select * from THREADS where USER_ID = ?");
			ps.setString(1, Integer.toString(id));

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				t = new Threads(rs.getInt("T_ID"), rs.getString("T_NAME"), rs.getInt("USER_ID"),
						rs.getString("CREATED"));
				threads.add(t);
			}
			rs.close();
			ps.close();

		} catch (Exception e) {
			System.out.println(e);
		}
		return threads;
	}

	public static Threads displayTopic(String id) {
		Threads t = null;

		try {
			Connection con = ConnectionUtil.getConnection();

			PreparedStatement ps = con.prepareStatement("select * from THREADS where THREADS.T_ID = ?");
			ps.setString(1, id);

			ResultSet rs = ps.executeQuery();
			rs.next();
			t = new Threads(rs.getInt("T_ID"), rs.getString("T_NAME"), rs.getInt("USER_ID"), rs.getString("CREATED"));

			rs.close();
			ps.close();

		} catch (Exception e) {
			System.out.println(e);
		}

		return t;
	}

	public static void postThreads(String name, int un) {
		try {
			Connection con = ConnectionUtil.getConnection();

			PreparedStatement ps = con.prepareStatement("insert into THREADS values(0, ?, ?, current_timestamp(0))");
			ps.setString(1, name);
			ps.setInt(2, un);

			ps.executeQuery();
			ps.close();

		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
