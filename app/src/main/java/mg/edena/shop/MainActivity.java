package mg.edena.shop;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.jeevandeshmukh.glidetoastlib.GlideToast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;

public class MainActivity extends BaseActivity {
	LoginButton mFBLoginBtn;
	CallbackManager mFBCallbackManager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mFBLoginBtn = (LoginButton) findViewById(R.id.login_button);
		mFBLoginBtn.setReadPermissions(Arrays.asList("email","public_profile","user_birthday", "user_gender"));
		// If you are using in a fragment, call loginButton.setFragment(this);
		mFBCallbackManager = CallbackManager.Factory.create();
		//LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("public_profile"));

		mFBLoginBtn.registerCallback(mFBCallbackManager, new FacebookCallback<LoginResult>() {
			@Override
			public void onSuccess(LoginResult loginResult) {
				AccessToken accessToken = loginResult.getAccessToken();
				getFbInformation(accessToken);

			}

			@Override
			public void onCancel() {

			}

			@Override
			public void onError(FacebookException exception) {
				toast(getString(R.string.error),GlideToast.FAILTOAST);
			}
		});
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		mFBCallbackManager.onActivityResult(requestCode, resultCode, data);
		super.onActivityResult(requestCode, resultCode, data);
	}

	private void getFbInformation(AccessToken accessToken){
		GraphRequest request = GraphRequest.newMeRequest(
				accessToken,
				new GraphRequest.GraphJSONObjectCallback() {
					@Override
					public void onCompleted(
							JSONObject object,
							GraphResponse response) {
						try {
							toast("Bienvenue "+object.getString("name"),GlideToast.SUCCESSTOAST);
						} catch (JSONException e) {
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
