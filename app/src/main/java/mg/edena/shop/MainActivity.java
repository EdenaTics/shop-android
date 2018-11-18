package mg.edena.shop;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.gson.Gson;

import java.util.Arrays;

import mg.edena.shop.authent.AuthBean;
import mg.edena.shop.authent.AuthUtils;
import mg.edena.shop.bean.User;

public class MainActivity extends BaseActivity  implements AuthBean.AuthUtilsCallback {
	private final static String TAG = MainActivity.class.getName();

	LoginButton mFBLoginBtn;
	CallbackManager mFBCallbackManager;
	AuthUtils mAuthUtils;



	@Override
	protected void onCreate(Bundle savedInstanceState)

	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mFBLoginBtn = (LoginButton) findViewById(R.id.login_button);
		mFBLoginBtn.setReadPermissions(AuthUtils.fbPermission());
		// If you are using in a fragment, call loginButton.setFragment(this);
		mFBCallbackManager = CallbackManager.Factory.create();
		//LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("public_profile"));

		mFBLoginBtn.registerCallback(mFBCallbackManager, new FacebookCallback<LoginResult>() {
			@Override
			public void onSuccess(LoginResult loginResult) {
				AccessToken accessToken = loginResult.getAccessToken();
				handleAccessToken(accessToken);

			}

			@Override
			public void onCancel() {

			}

			@Override
			public void onError(FacebookException exception) {
				//toast(getString(R.string.error),GlideToast.FAILTOAST);
			}
		});

		if(App.getInstance().getFbAccesToken() != null){
			handleAccessToken(App.getInstance().getFbAccesToken());
		}


	}

	private void handleAccessToken(AccessToken fbAccesToken) {
		if(mAuthUtils == null) mAuthUtils = new AuthUtils(mAuthFireBase,this);
		mAuthUtils.getDataUtils(fbAccesToken,this);
	}


	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		mFBCallbackManager.onActivityResult(requestCode, resultCode, data);
		super.onActivityResult(requestCode, resultCode, data);
	}


	@Override
	public void onComplete(AuthBean authBean) {

		boolean isDeconnect = false;
		if(authBean != null) {
			Task<AuthResult> authResultTask = authBean.authResultTask;
			GraphResponse graphResponse = authBean.graphResponse;

			if (authResultTask == null
					|| (authResultTask != null && !authResultTask.isSuccessful())
					|| graphResponse == null) {
					isDeconnect = true;
					Log.e(TAG, "FirebaseAuth authResultTask = null");
			}else{
				User user = new Gson().fromJson(graphResponse.getRawResponse(),User.class);
				App.getInstance().setUserLogged(user);
			}
		}else{
			isDeconnect = true;
			Log.e(TAG, "AuthBean = null");
		}

		if (isDeconnect) {
			App.getInstance().deconnect();
		} else {
			gotoHomeMainPage();
		}
	}
}
