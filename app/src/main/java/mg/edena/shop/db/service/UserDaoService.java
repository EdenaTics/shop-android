package mg.edena.shop.db.service;

import java.util.List;

import io.reactivex.Single;
import mg.edena.shop.model.db.UserShop;

public interface UserDaoService {
	Single<List<UserShop>> getList();
	Single<Long> save(UserShop user);
}
