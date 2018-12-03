package mg.edena.shop.ws.user;

import java.util.List;

import io.reactivex.Observable;
import mg.edena.shop.model.bean.User;
import mg.edena.shop.ws.base.retrofit.BaseRetrofit;
import retrofit2.http.GET;

public interface UserService {

	public static String BASE_URL = BaseRetrofit.BASE_URL;

	@GET("/users/")
	Observable<List<User>> getList();
}
