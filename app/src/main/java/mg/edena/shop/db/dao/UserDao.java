package mg.edena.shop.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import mg.edena.shop.model.db.UserShop;


@Dao
public interface UserDao {

	@Insert(onConflict = OnConflictStrategy.REPLACE)
	Long save(UserShop user);

	@Query("SELECT * FROM user_shop")
	List<UserShop> getAll();

	@Query("SELECT * FROM user_shop WHERE id = :userId")
	UserShop getUserById(Long userId);
}
