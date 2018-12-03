package mg.edena.shop.ws.retrofit.impl;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import mg.edena.shop.model.bean.User;
import mg.edena.shop.model.interf.ServiceCallback;
import mg.edena.shop.service.app.UserService;
import mg.edena.shop.ws.retrofit.RetrofitService;

import static mg.edena.shop.ws.retrofit.BaseRetrofit.BASE_URL;

public class UserServiceImpl implements UserService {

	UserRetrofit retrofit = RetrofitService.getRetrofitService(UserRetrofit.class,BASE_URL);

	public UserServiceImpl() {

	}

	@Override
	public void getList(final ServiceCallback<List<User>, Throwable> callback) {

		retrofit.getList().subscribeOn(Schedulers.newThread())
				.observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<List<User>>() {
			@Override
			public void onSubscribe(Disposable d) {

			}

			@Override
			public void onNext(List<User> users) {
				if(callback != null) callback.onSucces(users);
			}

			@Override
			public void onError(Throwable e) {
				if(callback != null) callback.onFaillure(e);

			}

			@Override
			public void onComplete() {

			}
		});
	}

}
