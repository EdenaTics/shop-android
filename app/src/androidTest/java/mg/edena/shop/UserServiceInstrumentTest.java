package mg.edena.shop;

import android.support.test.runner.AndroidJUnit4;

import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Collections;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.Single;
import io.reactivex.disposables.Disposable;
import mg.edena.shop.model.bean.User;
import mg.edena.shop.ws.base.retrofit.BaseRetrofit;
import mg.edena.shop.ws.base.retrofit.RetrofitService;
import mg.edena.shop.ws.user.UserService;

import static junit.framework.TestCase.assertTrue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;

@RunWith(AndroidJUnit4.class)
public class UserServiceInstrumentTest {

	List<User> list;

	@Test
	public void getList() throws InterruptedException {
		UserService userRetrofit = RetrofitService.getRetrofitService(UserService.class,BaseRetrofit.BASE_URL);
		Single<List<User>> observable = userRetrofit.getList();
		Observable obsTest = Observable.just(Collections.<User>emptyList());
		observable.subscribe();
		Thread.sleep(3000);
		assertThat(list,Matchers.notNullValue());
	}


}
