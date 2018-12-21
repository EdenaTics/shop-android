package mg.edena.shop.db.base;

import android.arch.persistence.room.Room;

import static com.facebook.FacebookSdk.getApplicationContext;

public class BaseDb {
	private static final String DATABASE_NAME = "anilakoshop";

	protected AppDb appDb;

	public BaseDb(){
		this.appDb = Room.databaseBuilder(getApplicationContext(),
				AppDb.class, DATABASE_NAME).build();
	}

	public BaseDb(AppDb appDb){
		this.appDb = appDb;
	}
}
