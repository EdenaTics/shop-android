package mg.edena.shop.ui.authent.utils;

import android.content.Context;
import android.support.annotation.NonNull;

import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class AuthUtils {
	private final String TAG = AuthUtils.class.getCanonicalName();

	public AuthUtils() {
	}

	public static List fbPermission(){
		return Arrays.asList("email","public_profile","user_birthday", "user_gender");
	}

	public Observable<GraphResponse> fbGetInfoObservable(AccessToken accessToken){
		String graphPath ="/me?fields=id,name,first_name,last_name,email,link,gender,locale,picture,birthday";

		return Observable.fromCallable(new Callable<GraphResponse>() {
			@Override
			public GraphResponse call() throws Exception {
				GraphRequest fbRequest = new GraphRequest(accessToken, graphPath);
				return fbRequest.executeAndWait();
			}
		}).subscribeOn(Schedulers.io())
				.observeOn(AndroidSchedulers.mainThread());
	};

	public Observable<Task<AuthResult>> firebaseSignObservable(AccessToken accessToken, FirebaseAuth firebaseAuth){
		return Observable.fromCallable(new Callable<Task<AuthResult>>() {
			@Override
			public Task<AuthResult> call() throws Exception {
				AuthCredential credential = FacebookAuthProvider.getCredential(accessToken.getToken());
				return firebaseAuth.signInWithCredential(credential);
			}
		}).subscribeOn(Schedulers.io())
				.observeOn(AndroidSchedulers.mainThread());
	}

	public Observable<AuthBean> fbFirebaseCombineObservable(AccessToken accessToken, FirebaseAuth firebaseAuth){


		Observable firebaseObservable = firebaseSignObservable(accessToken, firebaseAuth);
		Observable fbObservable = fbGetInfoObservable(accessToken);

		Observable<AuthBean> combinedObservable = Observable.zip(fbObservable,
				firebaseObservable, new BiFunction<GraphResponse, Task<AuthResult>, AuthBean>()  {
					@Override
					public AuthBean apply(GraphResponse graphResponse, Task<AuthResult> authResultTask) throws Exception {
						return new AuthBean(graphResponse,authResultTask);
					}
				});
		return combinedObservable;
	}
}
