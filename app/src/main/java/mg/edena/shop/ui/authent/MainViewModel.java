package mg.edena.shop.ui.authent;

import android.util.Log;

import com.facebook.AccessToken;
import com.facebook.GraphResponse;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.gson.Gson;

import io.reactivex.functions.Consumer;
import mg.edena.shop.App;
import mg.edena.shop.model.bean.User;
import mg.edena.shop.ui.authent.utils.AuthBean;
import mg.edena.shop.ui.authent.utils.AuthUtils;
import mg.edena.shop.ui.base.BaseViewModel;

public class MainViewModel extends BaseViewModel {
	private static final String TAG = MainViewModel.class.getCanonicalName();

	private AuthUtils mAuthUtils;
	private FirebaseAuth mAuthFireBase;
	private MainInterface mMainInterface;

	public MainViewModel(FirebaseAuth firebaseAuth, MainInterface mainInterface) {
		super();
		this.mAuthFireBase = firebaseAuth;
		this.mMainInterface = mainInterface;
		mAuthUtils = new AuthUtils();
	}


	public void handleFbGetInfoAccessToken(AccessToken fbAccesToken) {
		mAuthUtils.fbGetInfoObservable(fbAccesToken).subscribe(new Consumer<GraphResponse>() {
			@Override
			public void accept(GraphResponse graphResponse){
				if(graphResponse != null){
					writeToPreferenceFbUserLogged(graphResponse);
					if(mMainInterface != null) mMainInterface.authentFinished();
				}else {
					if(mMainInterface != null) mMainInterface.authentError(new Throwable("Error to get information."));
				}
			}
		});
	}

	public void handleFBFireBaseAuthToken(AccessToken fbAccesToken) {
		mAuthUtils.fbFirebaseCombineObservable(fbAccesToken, mAuthFireBase).subscribe(new Consumer<AuthBean>() {
			@Override
			public void accept(AuthBean authBean){
				if(authBean != null){
					if(onFbFirebaseSuccess(authBean)) {
						if(mMainInterface != null) mMainInterface.authentFinished();
					}else{
						if(mMainInterface != null) mMainInterface.authentError(new Throwable("Error to log."));
					}

				}else {
					if(mMainInterface != null) mMainInterface.authentError(new Throwable("Error to log."));
				}
			}
		});
	}

	private void writeToPreferenceFbUserLogged(GraphResponse graphResponse){
		User user = new Gson().fromJson(graphResponse.getRawResponse(), User.class);
		App.getInstance().setUserLogged(user);
	}

	private boolean onFbFirebaseSuccess(AuthBean authBean) {
		boolean isOk = false;
		if (authBean != null) {
			Task<AuthResult> authResultTask = authBean.authResultTask;
			GraphResponse graphResponse = authBean.graphResponse;
			if (authResultTask == null
					|| (authResultTask != null && !authResultTask.isSuccessful())
					|| graphResponse == null) {
				isOk = true;
				Log.e(TAG, "FirebaseAuth authResultTask = null");
				return true;
			} else {
				writeToPreferenceFbUserLogged(graphResponse);
			}

		}
		return isOk;
	}

}
