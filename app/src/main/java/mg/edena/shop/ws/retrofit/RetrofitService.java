package mg.edena.shop.ws.retrofit;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitService {

	public static <T> T getRetrofitService(final Class<T> cl, final String baseUrl)
	{

		final Retrofit retrofit = new Retrofit.Builder()
				.addConverterFactory(GsonConverterFactory.create())
				.addCallAdapterFactory(RxJava2CallAdapterFactory.create())
				.baseUrl(baseUrl)
				.build();

		T service = retrofit.create(cl);
		return service;
	}
}
