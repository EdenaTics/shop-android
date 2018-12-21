package mg.edena.shop.db.base;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import mg.edena.shop.db.dao.UserDao;
import mg.edena.shop.model.db.UserShop;

@Database(entities = {UserShop.class}, version = 1)
public abstract class AppDb extends RoomDatabase {
	public abstract UserDao userDao();
}
