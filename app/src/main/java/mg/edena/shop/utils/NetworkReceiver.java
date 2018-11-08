package mg.edena.shop.utils;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;


public class NetworkReceiver extends BroadcastReceiver {

	public static final String NETWORK_ACTION = "network.action.NetworkReceiver";
	public static final String NETWORK_STATUS_EXTRA = "status";
	public static final String NETWORK_STATUS_LABEL_EXTRA = "status_label";
	//adb shell am broadcast -a android.net.wifi.STATE_CHANGE

	Activity act;
	NetworkListner networkListner;
	public boolean connected;

	public NetworkReceiver(Activity act) {
		super();
		this.act = act;
		networkListner = (NetworkListner)act;
	}

	@Override
	public void onReceive(final Context context, final Intent intent) {

		boolean isConnected = isConnected(context);
		connected = isConnected;
		//send(context, isConnected, isConnected?"Internet estabilished":"No internet");
		if(networkListner != null) networkListner.isOnline(isConnected,isConnected?"Internet estabilished":"No internet");

	}


	private void send(Context context, boolean isConnected, String statusLabel) {
		try {
			Intent intent = new Intent();
			intent.putExtra(NETWORK_STATUS_EXTRA, isConnected);
			intent.putExtra(NETWORK_STATUS_LABEL_EXTRA, statusLabel);
			intent.setAction(NETWORK_ACTION);
			context.sendBroadcast(intent);
		}
		catch (Exception ex){
			ex.printStackTrace();
		}
	}

	public void register(){
		try {
			IntentFilter intentFilter = new IntentFilter();
			intentFilter.addAction("android.net.wifi.WIFI_STATE_CHANGED");
			intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
			this.act.registerReceiver(this,intentFilter);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void unregister(){
		try {
			this.act.unregisterReceiver(this);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	public static boolean isConnected(Context context){
		boolean isConnected = false;
		try {
			ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
			NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
			isConnected = (networkInfo != null && networkInfo.isConnected());
		}
		catch (Exception ex){
			ex.printStackTrace();
		}
		return isConnected;
	}

	public interface NetworkListner{
		public void isOnline(boolean status, String labelStatus);
	}

	/*
	ConnectedNetworkReceiver connectedNetworkReceiver = new ConnectedNetworkReceiver();
	private class ConnectedNetworkReceiver extends BroadcastReceiver
	{

		private NetworkReceiver registerReceiver = new NetworkReceiver(BaseActivity.this);

		@Override
		public void onReceive(Context context, Intent intent) {
			toast(intent.getStringExtra(NetworkReceiver.NETWORK_STATUS_LABEL_EXTRA),GlideToast.INFOTOAST);

		}

		public void register()
		{
			try {
				registerReceiver.register();
			} catch (Exception e) {
				e.printStackTrace();
			}
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
			try {
				registerReceiver.unregister();
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				unregisterReceiver(this);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	 */
}
