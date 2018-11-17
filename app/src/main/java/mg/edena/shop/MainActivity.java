package mg.edena.shop;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseUser;
import com.jeevandeshmukh.glidetoastlib.GlideToast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;

public class MainActivity extends BaseActivity {
	private final static String TAG = MainActivity.class.getName();

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
				//getFbInformation(accessToken);
				handleFacebookAccessToken(accessToken);

			}

			@Override
			public void onCancel() {

			}

			@Override
			public void onError(FacebookException exception) {
				//toast(getString(R.string.error),GlideToast.FAILTOAST);
			}
		});

		if(App.getInstance().getTokenUser() != null){
			handleFacebookAccessToken(App.getInstance().getTokenUser());
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		mFBCallbackManager.onActivityResult(requestCode, resultCode, data);
		super.onActivityResult(requestCode, resultCode, data);
	}


	private void handleFacebookAccessToken(AccessToken token) {
		Log.d(TAG, "handleFacebookAccessToken:" + token);

		AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
		mAuth.signInWithCredential(credential)
				.addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
					@Override
					public void onComplete(@NonNull Task<AuthResult> task) {
						if (task.isSuccessful()) {
							Log.d(TAG, "signInWithCredential:success");
							FirebaseUser user = mAuth.getCurrentUser();
							gotoHomeMainPage();
						} else {
							Log.w(TAG, "signInWithCredential:failure", task.getException());
							//toast(TAG, "Authentication failed.");

						}

					}
				});
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
							gotoHomePage();
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
