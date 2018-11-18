package mg.edena.shop;

import android.app.Application;
import android.content.Intent;
import android.content.SharedPreferences;

import com.facebook.AccessToken;
import com.facebook.login.LoginManager;
import com.google.firebase.auth.FirebaseAuth;
import com.google.gson.Gson;

import mg.edena.shop.bean.User;
import mg.edena.shop.utils.CommonUtils;

public class App extends Application {


	private static App mApp;
	SharedPreferences mPref;
	private User mUserLogged;

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
		if(getFbAccesToken() == null)  startLoginView();
	}

	public AccessToken getFbAccesToken(){
		AccessToken accessToken = AccessToken.getCurrentAccessToken();
		if(accessToken != null && !accessToken.isExpired()) return accessToken;
		else return null;
	}

	protected void deconnect(){
		try {
			LoginManager.getInstance().logOut();
		}catch (Exception e){
			e.printStackTrace();
		}
		try {
			FirebaseAuth.getInstance().signOut();
		}catch (Exception e){
			e.printStackTrace();
		}

		startLoginView();
	}

	public User getUserLogged() {
			User user = null;
			try {
				user = new Gson().fromJson(mPref.getString("user_log", null), User.class);
			}catch (Exception e){
				e.printStackTrace();
			}
		 return user;
	}

	public void setUserLogged(User mUserLogged) {
		try {
			SharedPreferences.Editor edit = mPref.edit();
			edit.putString("user_log", new Gson().toJson(mUserLogged));
			edit.commit();
			this.mUserLogged = mUserLogged;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void startLoginView(){
		startActivity(new Intent(this,MainActivity.class));
	}


}
