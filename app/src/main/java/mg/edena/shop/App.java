package mg.edena.shop;

import android.app.Application;
import android.content.Intent;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;

import com.facebook.AccessToken;
import com.facebook.login.LoginManager;
import com.google.firebase.auth.FirebaseAuth;

import mg.edena.shop.utils.CommonUtils;

public class App extends Application {


	private static App mApp;
	SharedPreferences mPref;

	public static App getInstance(){
		return mApp;
	}

	@Override
	public void onCreate() {
		super.onCreate();
		mApp = this;
		mPref = this.getApplicationContext().getSharedPreferences("shop_key", MODE_PRIVATE);
		CommonUtils.getKeyHash(this);

	}

	public boolean isFirstRun() {
		return mPref.getBoolean("is_first_run", true);
	}

	public void setFirstRunned() {
		SharedPreferences.Editor edit = mPref.edit();
		edit.putBoolean("is_first_run", false);
		edit.commit();
	}

	public void verifyConnect(){
		if(getTokenUser() == null)  startLoginView();
	}

	public AccessToken getTokenUser(){
		AccessToken accessToken = AccessToken.getCurrentAccessToken();
		if(accessToken != null && !accessToken.isExpired()) return accessToken;
		else return null;
	}

	protected void deconnect(){
		LoginManager.getInstance().logOut();
		FirebaseAuth.getInstance().signOut();
		startLoginView();
	}

	public void startLoginView(){
		startActivity(new Intent(this,MainActivity.class));
	}


}
