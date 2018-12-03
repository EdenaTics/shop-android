package mg.edena.shop.service.preference;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

import mg.edena.shop.model.bean.User;

import static android.content.Context.MODE_PRIVATE;

public class PrefServiceImpl implements PrefService {

	private static final String PREF_NAME = "shop_key";
	private static final String PREF_FIRST_RUN = "first_run";
	private static final String PREF_USER_LOGGED = "user_logged";

	private Context mContext;
	private SharedPreferences mPref;
	private static PrefServiceImpl instance;

	public static PrefServiceImpl getInstance(Context context) {
		if(instance == null) instance = new PrefServiceImpl(context);
		return instance;
	}

	private PrefServiceImpl(Context context) {
		this.mContext = context;
		mPref = context.getApplicationContext().getSharedPreferences(PREF_NAME, MODE_PRIVATE);
	}


	public boolean isFirstRun() {
		return mPref.getBoolean(PREF_FIRST_RUN, true);
	}

	public void setFirstRun(boolean status) {
		SharedPreferences.Editor edit = mPref.edit();
		edit.putBoolean(PREF_FIRST_RUN, status);
		edit.commit();
	}

	@Override
	public User getUserLogged() {
		User user = null;
		try {
			user = new Gson().fromJson(mPref.getString(PREF_USER_LOGGED, null), User.class);
		}catch (Exception e){
			e.printStackTrace();
		}
		return user;
	}

	@Override
	public void setSaveUserLogged(User userLogged) {
		try {
			SharedPreferences.Editor edit = mPref.edit();
			edit.putString(PREF_USER_LOGGED, new Gson().toJson(userLogged));
			edit.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


}
