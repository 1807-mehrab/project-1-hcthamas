package blott.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import blott.object.Post;
import blott.object.User;
import blott.util.ConnectionUtil;

public class UserDao {
	public static User profile(int id) {
		User u = null;
		try {
			Connection con = ConnectionUtil.getConnection();

			PreparedStatement ps = con.prepareStatement("select * from ACCOUNTS where ACCOUNT_ID = ?");
			ps.setInt(1, id);

			ResultSet rs = ps.executeQuery();
			rs.next();
			if (rs != null) {
				u = new User(rs.getInt("ACCOUNT_ID"), rs.getString("USERNAME"), rs.getString("EMAIL"),
						rs.getString("PASSWORD"), (rs.getInt("IS_ADMIN") == 1));
			}
			rs.close();
			ps.close();

		} catch (Exception e) {
			System.out.println(e);
		}
		return u;
	}

	public static String getUsername(int id) {
		String u = null;
		try {
			Connection con = ConnectionUtil.getConnection();

			PreparedStatement ps = con.prepareStatement("select USERNAME from ACCOUNTS where ACCOUNT_ID = ?");
			ps.setInt(1, id);

			ResultSet rs = ps.executeQuery();
			rs.next();

			u = rs.getString("USERNAME");

			rs.close();
			ps.close();

		} catch (Exception e) {
			System.out.println(e);
		}
		return u;
	}

	public static void editUser(String name, String pass, String email, int id) {
		try {
			Connection con = ConnectionUtil.getConnection();

			PreparedStatement ps = con
					.prepareStatement("update ACCOUNTS set USERNAME = ?, EMAIL = ?, PASSWORD = ? where ACCOUNT_ID = ?");
			ps.setString(1, name);
			ps.setString(2, email);
			ps.setString(3, pass);
			ps.setInt(4, id);

			ps.executeQuery();
			ps.close();

		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public static List<User> getAll() {
		List<User> users = new ArrayList<>();
		User u = null;

		try {
			Connection con = ConnectionUtil.getConnection();

			PreparedStatement ps = con.prepareStatement("select * from ACCOUNTS");

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				u = new User(rs.getInt("ACCOUNT_ID"), rs.getString("USERNAME"), rs.getString("EMAIL"),
						rs.getString("PASSWORD"), (1 == rs.getInt("IS_ADMIN")));
				users.add(u);
			}
			rs.close();
			ps.close();

		} catch (Exception e) {
			System.out.println(e);
		}

		return users;
	}
	
	public static void newUser(String name, String email, String pass, int isadm) {
		try {
			Connection con = ConnectionUtil.getConnection();

			PreparedStatement ps = con.prepareStatement("insert into ACCOUNTS values (0 , ?, ?, ?, ?)");
			ps.setString(1, name);
			ps.setString(2, email);
			ps.setString(3, pass);
			ps.setInt(4, isadm);
			
			ResultSet rs = ps.executeQuery();

			rs.close();
			ps.close();

		} catch (Exception e) {
			System.out.println(e);
		}
	
	}
}
