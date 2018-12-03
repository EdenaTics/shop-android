package mg.edena.shop.ui.authent;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.jeevandeshmukh.glidetoastlib.GlideToast;

import mg.edena.shop.App;
import mg.edena.shop.R;
import mg.edena.shop.ui.authent.utils.AuthUtils;
import mg.edena.shop.ui.base.BaseActivity;

public class MainActivity extends BaseActivity<MainViewModel> implements MainInterface {
	private final static String TAG = MainActivity.class.getName();

	LoginButton mFBLoginBtn;
	CallbackManager mFBCallbackManager;




	@Override
	protected void onCreate(Bundle savedInstanceState)

	{
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.activity_main);

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

	@Override
	public MainViewModelFactory getFactory() {
		return new MainViewModelFactory(mAuthFireBase,this);
	}

	@Override
	public Class<MainViewModel> getClassViewModel() {
		return MainViewModel.class;
	}

	@Override
	public int getIdLayoutToInflate() {
		return R.layout.activity_main;
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		mFBCallbackManager.onActivityResult(requestCode, resultCode, data);
		super.onActivityResult(requestCode, resultCode, data);
	}

	private void handleAccessToken(AccessToken fbAccesToken) {
		getViewModel().handleFbGetInfoAccessToken(fbAccesToken);
	}


	@Override
	public void authentFinished() {
		gotoHomeMainPage();
	}

	@Override
	public void authentError(Throwable e) {
		Log.e("Authentification: ","Failed authen "+ e.getMessage());
		toast(e.getMessage(),GlideToast.FAILTOAST);
	}
}
