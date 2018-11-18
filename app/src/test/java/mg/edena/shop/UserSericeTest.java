package mg.edena.shop;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import io.reactivex.Observable;
import mg.edena.shop.bean.User;
import mg.edena.shop.ws.retrofit.BaseRetrofit;
import mg.edena.shop.ws.retrofit.RetrofitService;
import mg.edena.shop.ws.retrofit.impl.UserRetrofit;

import static org.mockito.Mockito.when;

public class UserSericeTest {
	@Mock
	UserRetrofit userRetrofit;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void getList(){
		UserRetrofit userRetrofit = RetrofitService.getRetrofitService(UserRetrofit.class,BaseRetrofit.BASE_URL);
		Observable<List<User>> observable = userRetrofit.getList();
		when(userRetrofit.getList())
				.thenReturn(Observable.just(Collections.<User>emptyList()));
	}




}
