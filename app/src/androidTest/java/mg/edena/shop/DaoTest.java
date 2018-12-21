package mg.edena.shop;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.util.List;

import mg.edena.shop.db.base.AppDb;
import mg.edena.shop.db.dao.UserDao;
import mg.edena.shop.model.db.UserShop;

import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(AndroidJUnit4.class)
public class DaoTest {

	AppDb appDb;
	UserDao userDao;


	@Before
	public void createDb() {
		//Context context = ApplicationProvider.getApplicationContext();
		Context context = InstrumentationRegistry.getContext();
		appDb = Room.inMemoryDatabaseBuilder(context, AppDb.class).build();
		userDao = appDb.userDao();
	}

	@After
	public void closeDb() throws IOException {
		appDb.close();
	}

	@Test
	public void saveUser(){
		UserShop user = new UserShop();
		user.setName("test name");
		userDao.save(user);
		List<UserShop> list = userDao.getAll();
		assertThat(list,Matchers.notNullValue());
	}
}
