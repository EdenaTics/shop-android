package mg.edena.shop;

import android.app.Application;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.util.Base64;
import android.util.Log;

import com.facebook.AccessToken;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import mg.edena.shop.utils.edena.EDAcrReportCrash;


public class App extends Application {


	private static App mApp;
	SharedPreferences mPref;

	public static App getInstance(){
		return mApp;
	}

	@Override
	public void onCreate() {
		super.onCreate();
		EDAcrReportCrash.getInstance(this).init();
		//FacebookSdk.sdkInitialize(getApplicationContext());
		AppEventsLogger.activateApp(this);
		mApp = this;
		mPref = this.getApplicationContext().getSharedPreferences("shop_key", MODE_PRIVATE);
		getKeyHash();
	}

	public boolean isFirstRun() {
		return mPref.getBoolean("is_first_run", true);
	}

	public void setFirstRunned() {
		SharedPreferences.Editor edit = mPref.edit();
		edit.putBoolean("is_first_run", false);
		edit.commit();
	}

	public boolean isLoggedInFB(){

		AccessToken accessToken = AccessToken.getCurrentAccessToken();
		boolean isLoggedIn = accessToken != null && !accessToken.isExpired();
		return  isLoggedIn;

	}

	private void getKeyHash() {
		try {
			PackageInfo info = getPackageManager().getPackageInfo("facebooklocation.facebooklocation", PackageManager.GET_SIGNATURES);
			for (Signature signature : info.signatures) {
				MessageDigest md = MessageDigest.getInstance("SHA");
				md.update(signature.toByteArray());
				Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
			}
		} catch (PackageManager.NameNotFoundException e) {

		} catch (NoSuchAlgorithmException e) {

		}
	}
}
