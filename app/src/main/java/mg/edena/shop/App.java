package mg.edena.shop;

import android.app.Application;
import android.content.Intent;

import com.facebook.AccessToken;
import com.facebook.login.LoginManager;
import com.google.firebase.auth.FirebaseAuth;

import mg.edena.shop.model.bean.User;
import mg.edena.shop.service.preference.PrefServiceImpl;
import mg.edena.shop.ui.authent.MainActivity;
import mg.edena.shop.utils.CommonUtils;

public class App extends Application {


	private static App mApp;
	PrefServiceImpl mPrefService;
	private User mUserLogged;

	public static App getInstance(){
		return mApp;
	}

	@Override
	public void onCreate() {
		super.onCreate();
		mApp = this;
		mPrefService = PrefServiceImpl.getInstance(this);
		CommonUtils.getKeyHash(this);

	}

	public boolean isFirstRun() {
		return mPrefService.isFirstRun();
	}

	public void setFirstRun(boolean status) {
		mPrefService.setFirstRun(status);
	}

	public void verifyConnect(){
		if(getFbAccesToken() == null)  startLoginView();
	}

	public AccessToken getFbAccesToken(){
		AccessToken accessToken = AccessToken.getCurrentAccessToken();
		if(accessToken != null && !accessToken.isExpired()) return accessToken;
		else return null;
	}

	public void deconnect(){
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
		 return mPrefService.getUserLogged();
	}

	public void setUserLogged(User userLogged) {
		mPrefService.setSaveUserLogged(userLogged);
		this.mUserLogged = mUserLogged;
	}

	public void startLoginView(){
		startActivity(new Intent(this,MainActivity.class));
	}


}
