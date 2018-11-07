package mg.edena.shop;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.jeevandeshmukh.glidetoastlib.GlideToast;

import mg.edena.shop.utils.NetworkReceiver;

public class BaseActivity extends AppCompatActivity implements NetworkReceiver.NetworkListner {



	NetworkReceiver networkReceiver = new NetworkReceiver(this);
	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		registerNetworkReceiver();

	}

	public void toast(String mess, String type){
		new GlideToast.makeToast(this,mess, GlideToast.LENGTHLONG, type).show();
	}

	@Override
	protected void onDestroy()
	{
		unregisterNetworkReceiver();
		super.onDestroy();
	}

	@Override
	public void isOnline(boolean status, String labelStatus) {
		toast(labelStatus,GlideToast.INFOTOAST);
	}


	private void registerNetworkReceiver(){
		//connectedNetworkReceiver.register();
		networkReceiver.register();
	}

	private void unregisterNetworkReceiver(){
		//connectedNetworkReceiver.unregister();
		networkReceiver.unregister();
	}




}
