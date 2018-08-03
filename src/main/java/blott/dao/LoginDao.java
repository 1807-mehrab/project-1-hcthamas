package blott.dao;

import java.sql.*;

import blott.util.ConnectionUtil;

public class LoginDao {
	public static boolean validate(String name, String pass) {
		boolean status = false;
		try {
			Connection con = ConnectionUtil.getConnection();

			PreparedStatement ps = con
					.prepareStatement("select * from ACCOUNTS where (USERNAME = ? or EMAIL = ?) and PASSWORD = ?");
			ps.setString(1, name);
			ps.setString(2, name);
			ps.setString(3, pass);

			ResultSet rs = ps.executeQuery();
			status = rs.next();

		} catch (Exception e) {
			System.out.println(e);
		}
		return status;
	}

	public static int getID(String name) {
		int id = -1;
		try {
			Connection con = ConnectionUtil.getConnection();

			PreparedStatement ps = con
					.prepareStatement("select ACCOUNT_ID from ACCOUNTS where (USERNAME = ? or EMAIL = ?)");
			ps.setString(1, name);
			ps.setString(2, name);

			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				id = rs.getInt("ACCOUNT_ID");
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return id;
	}

	public static boolean isAdmin(String name) {
		boolean is = false;
		try {
			Connection con = ConnectionUtil.getConnection();

			PreparedStatement ps = con
					.prepareStatement("select IS_ADMIN from ACCOUNTS where (USERNAME = ? or EMAIL = ?)");
			ps.setString(1, name);
			ps.setString(2, name);

			ResultSet rs = ps.executeQuery();
			rs.next();

			is = (rs.getInt("IS_ADMIN") == 1);
		} catch (Exception e) {
			System.out.println(e);
		}
		return is;
	}
}
