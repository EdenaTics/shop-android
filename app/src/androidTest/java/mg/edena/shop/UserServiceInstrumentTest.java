package mg.edena.shop;

import android.support.test.runner.AndroidJUnit4;

import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Collections;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import mg.edena.shop.model.bean.User;
import mg.edena.shop.ws.retrofit.BaseRetrofit;
import mg.edena.shop.ws.retrofit.RetrofitService;
import mg.edena.shop.ws.retrofit.impl.UserRetrofit;

import static junit.framework.TestCase.assertTrue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;

@RunWith(AndroidJUnit4.class)
public class UserServiceInstrumentTest {

	List<User> list;

	@Test
	public void getList() throws InterruptedException {
		UserRetrofit userRetrofit = RetrofitService.getRetrofitService(UserRetrofit.class,BaseRetrofit.BASE_URL);
		Observable<List<User>> observable = userRetrofit.getList();
		Observable obsTest = Observable.just(Collections.<User>emptyList());
		observable.subscribe(new Observer<List<User>>() {
			@Override
			public void onSubscribe(Disposable d) {

			}

			@Override
			public void onNext(List<User> users) {
					list = users;
			}

			@Override
			public void onError(Throwable e) {
				System.out.println(e.getMessage());

			}

			@Override
			public void onComplete() {
				System.out.println("onComplete");
			}
		});
		Thread.sleep(3000);
		assertThat(list,Matchers.notNullValue());
	}


}
