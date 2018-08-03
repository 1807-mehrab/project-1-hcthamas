package blott.test;

import static org.junit.Assert.*;

import org.junit.Test;

import blott.dao.LoginDao;
import blott.dao.PostsDao;
import blott.dao.ThreadDao;
import blott.dao.UserDao;

public class BlottTest {

	//Login Test
	@Test
	public void loginGetID() {
		int id = LoginDao.getID("NotValidUsername");
		assertEquals(-1, id);
	}
	
	// Posts testing
	@Test
	public void postGetAllTest() {
		int len = PostsDao.displayAll("0").size();
		assertEquals(5, len);
	}

	// Threads testing
	@Test
	public void treadDisplayAllTest() {
		int len = ThreadDao.displayAll().size();
		assert (len >= 1);
	}

	// Users DAO testing
	@Test
	public void getUserTest() {
		assertEquals("Admin", UserDao.getUsername(0));
	}

	@Test
	public void getAllTest() {
		int len = UserDao.getAll().size();
		assert (len >= 1);
	}

	@Test
	public void profileTest() {
		String name = UserDao.profile(0).getUsername();
		assertEquals("Admin", name);
	}
}
