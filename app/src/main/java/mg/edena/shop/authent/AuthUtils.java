package mg.edena.shop.authent;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;

import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.jeevandeshmukh.glidetoastlib.GlideToast;

import org.json.JSONException;
import org.json.JSONObject;
import org.reactivestreams.Subscriber;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;

import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class AuthUtils {
	private final String TAG = AuthUtils.class.getCanonicalName();

	FirebaseAuth firebaseAuth;
	Context context;

	public AuthUtils(FirebaseAuth firebaseAuth, Context context) {
		this.firebaseAuth = firebaseAuth;
		this.context = context;
	}

	public static List fbPermission(){
		return Arrays.asList("email","public_profile","user_birthday", "user_gender");
	}

	public void getDataUtils(AccessToken accessToken, @NonNull AuthBean.AuthUtilsCallback authUtilsCallback){
		String graphPath ="/me?fields=id,name,first_name,last_name,email,link,gender,locale,picture,birthday";
		Observable fbObservable = Observable.fromCallable(new Callable<GraphResponse>() {
			@Override
			public GraphResponse call() throws Exception {
				GraphRequest fbRequest = new GraphRequest(accessToken, graphPath);
				return fbRequest.executeAndWait();
			}
		}).subscribeOn(Schedulers.io())
				.observeOn(AndroidSchedulers.mainThread());

		Observable firebaseObservable = Observable.fromCallable(new Callable<Task<AuthResult>>() {
			@Override
			public Task<AuthResult> call() throws Exception {
				AuthCredential credential = FacebookAuthProvider.getCredential(accessToken.getToken());
				return firebaseAuth.signInWithCredential(credential);
			}
		}).subscribeOn(Schedulers.io())
				.observeOn(AndroidSchedulers.mainThread());

		Observable<AuthBean> combinedObservable = Observable.zip(fbObservable,
				firebaseObservable, new BiFunction<GraphResponse, Task<AuthResult>, AuthBean>()  {
					@Override
					public AuthBean apply(GraphResponse graphResponse, Task<AuthResult> authResultTask) throws Exception {
						return new AuthBean(graphResponse,authResultTask);
					}
				});

		combinedObservable.subscribe(new Consumer<AuthBean>() {
										 @Override
										 public void accept(AuthBean authBean) throws Exception {
											 authUtilsCallback.onComplete(authBean);
										 }
									 }
		);


	}

	public void handleFacebookAccessToken(AccessToken token,AuthBean.AuthUtilsCallback authUtilsCallback) {
		Log.d(TAG, "handleFacebookAccessToken:" + token);

		AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
		firebaseAuth.signInWithCredential(credential)
				.addOnCompleteListener((Activity) context, new OnCompleteListener<AuthResult>() {
					@Override
					public void onComplete(@NonNull Task<AuthResult> task) {
						if (task.isSuccessful()) {
							Log.d(TAG, "signInWithCredential:success");
							FirebaseUser user = firebaseAuth.getCurrentUser();
							//gotoHomeMainPage();
						} else {
							Log.w(TAG, "signInWithCredential:failure", task.getException());

						}

					}
				});
	}


	public void getFbDetails(AccessToken accessToken){
		GraphRequest request = GraphRequest.newMeRequest(
				accessToken,
				new GraphRequest.GraphJSONObjectCallback() {
					@Override
					public void onCompleted(
							JSONObject object,
							GraphResponse response) {
						try {
							System.out.println("Bienvenue "+object.getString("name"));
							//gotoHomePage();
						}
						catch (JSONException e) {
							e.printStackTrace();
						}

					}
				});
		Bundle parameters = new Bundle();
		parameters.putString("fields", "id,name,first_name,last_name,email,link,gender,locale,picture,birthday");
		request.setParameters(parameters);
		request.executeAsync();
	}
}
