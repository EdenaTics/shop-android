package mg.edena.shop.ws.retrofit.impl;

import java.util.List;

import io.reactivex.Observable;
import mg.edena.shop.bean.User;
import mg.edena.shop.ws.retrofit.BaseRetrofit;
import retrofit2.http.GET;

public interface UserRetrofit {

	public static String BASE_URL = BaseRetrofit.BASE_URL;

	@GET("/users/")
	Observable<List<User>> getList();
}
