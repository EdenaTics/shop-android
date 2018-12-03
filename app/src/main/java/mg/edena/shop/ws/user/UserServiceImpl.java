package mg.edena.shop.ws.user;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import mg.edena.shop.model.bean.User;
import mg.edena.shop.model.interf.ServiceCallback;
import mg.edena.shop.ws.base.retrofit.RetrofitService;

import static mg.edena.shop.ws.base.retrofit.BaseRetrofit.BASE_URL;

public class UserServiceImpl {

	UserService retrofit = RetrofitService.getRetrofitService(UserService.class,BASE_URL);

	public UserServiceImpl() {

	}

	public void getList(final ServiceCallback<List<User>, Throwable> callback) {

		retrofit.getList().subscribeOn(Schedulers.newThread())
				.observeOn(AndroidSchedulers.mainThread()).subscribe(new SingleObserver<List<User>>() {
			@Override
			public void onSubscribe(Disposable d) {

			}

			@Override
			public void onSuccess(List<User> users) {
				if(callback != null) callback.onSucces(users);
			}

			@Override
			public void onError(Throwable e) {
				if(callback != null) callback.onFaillure(e);
			}
		});
	}

}
