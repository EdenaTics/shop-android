package mg.edena.shop;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.jeevandeshmukh.glidetoastlib.GlideToast;

import mg.edena.shop.utils.NetworkReceiver;

public class BaseActivity extends AppCompatActivity implements NetworkReceiver.NetworkListner {

	public FirebaseAuth mAuthFireBase;
	protected FirebaseUser mCurrentUser;

	NetworkReceiver networkReceiver = new NetworkReceiver(this);
	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		registerNetworkReceiver();
		mAuthFireBase = FirebaseAuth.getInstance();
	}

	@Override
	protected void onStart() {
		super.onStart();
		mCurrentUser = mAuthFireBase.getCurrentUser();
	}



	@Override
	protected void onDestroy()
	{
		unregisterNetworkReceiver();
		super.onDestroy();
	}

	@Override
	public void isOnline(boolean status, String labelStatus) {
		//toast(labelStatus,GlideToast.INFOTOAST);
	}

	private void registerNetworkReceiver(){
		//connectedNetworkReceiver.register();
		networkReceiver.register();
	}

	private void unregisterNetworkReceiver(){
		//connectedNetworkReceiver.unregister();
		networkReceiver.unregister();
	}


	public void gotoHomeMainPage(){
		startActivity(new Intent(this,HomeMainActivity.class));
	}

	public void toast(String mess, String type){
		new GlideToast.makeToast(this,mess, GlideToast.LENGTHLONG, type).show();
	}

	public void replaceFragment(Fragment fragment, int idLayout){
		FragmentManager fragmentManager =  getSupportFragmentManager();
		FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
		fragmentTransaction.replace(idLayout, fragment);
		fragmentTransaction.commit();
	}

	public void addFragment(Fragment fragment, int idLayout){
		FragmentManager fragmentManager =  getSupportFragmentManager();
		FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
		fragmentTransaction.add(idLayout, fragment);
		fragmentTransaction.commit();
	}

}
