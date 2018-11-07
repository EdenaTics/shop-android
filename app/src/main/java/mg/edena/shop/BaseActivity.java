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

public class BaseActivity extends AppCompatActivity {

	private NetworkReceiver registerReceiver = new NetworkReceiver(this);

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

	private void registerNetworkReceiver(){
		registerReceiver.register();
		connectedNetworkReceiver.register();
	}

	private void unregisterNetworkReceiver(){
		registerReceiver.unregister();
		connectedNetworkReceiver.unregister();
	}

	ConnectedNetworkReceiver connectedNetworkReceiver = new ConnectedNetworkReceiver();
	private class ConnectedNetworkReceiver extends BroadcastReceiver
	{
		@Override
		public void onReceive(Context context, Intent intent) {
			toast(intent.getStringExtra(NetworkReceiver.NETWORK_STATUS_LABEL_EXTRA),GlideToast.INFOTOAST);

		}

		public void register()
		{
			try
			{

				IntentFilter intentFilter = new IntentFilter();
				intentFilter.addAction(NetworkReceiver.NETWORK_ACTION);
				registerReceiver(this, intentFilter);
			}
			catch (Exception ex)
			{
				ex.printStackTrace();
			}
		}

		public void unregister(){
			unregisterReceiver(this);
		}
	}

}
