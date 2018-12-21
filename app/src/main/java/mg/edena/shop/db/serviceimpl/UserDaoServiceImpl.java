package mg.edena.shop.db.serviceimpl;

import java.util.List;
import java.util.concurrent.Callable;

import io.reactivex.Single;
import mg.edena.shop.db.base.BaseDb;
import mg.edena.shop.db.service.UserDaoService;
import mg.edena.shop.model.db.UserShop;

public class UserDaoServiceImpl extends BaseDb implements UserDaoService {

	@Override
	public Single<List<UserShop>> getList() {
		return Single.fromCallable(new Callable<List<UserShop>>() {
			@Override
			public List<UserShop> call() throws Exception {
				return appDb.userDao().getAll();
			}
		});
	}

	@Override
	public Single<Long> save(final UserShop user) {
		return Single.fromCallable(new Callable<Long>() {
			@Override
			public Long call() throws Exception {
				return appDb.userDao().save(user);
			}
		});
	}

}
