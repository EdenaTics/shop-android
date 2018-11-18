package mg.edena.shop;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v4.content.LocalBroadcastManager;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Collections;
import java.util.List;

import io.reactivex.Observable;
import mg.edena.shop.bean.User;
import mg.edena.shop.utils.NetworkReceiver;
import mg.edena.shop.ws.retrofit.BaseRetrofit;
import mg.edena.shop.ws.retrofit.RetrofitService;
import mg.edena.shop.ws.retrofit.impl.UserRetrofit;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class ReceiverInstrumentedTest {
	@Rule public ActivityTestRule<HomeActivity> homeActivityActivityTestRule = new ActivityTestRule<>(HomeActivity.class);

	@Test
	public void useAppContext() {
		Context appContext = InstrumentationRegistry.getTargetContext();
		assertEquals("mg.edena.shop", appContext.getPackageName());
	}

	@Test
	public void testNetworkReceiver() throws InterruptedException {
		Context appContext = InstrumentationRegistry.getTargetContext();
		Context vontext = InstrumentationRegistry.getContext();
		NetworkReceiver networkReceiver = new NetworkReceiver(homeActivityActivityTestRule.getActivity());
		String actionName = "android.net.conn.CONNECTIVITY_CHANGE";
		LocalBroadcastManager.getInstance(vontext).registerReceiver(networkReceiver, new IntentFilter(actionName));

		Intent intent = new Intent(actionName);
		//intent.putExtra()
		LocalBroadcastManager.getInstance(vontext).sendBroadcast(intent);
		Thread.sleep(2000);
		assertTrue(networkReceiver.connected);
		LocalBroadcastManager.getInstance(vontext).unregisterReceiver(networkReceiver);


	}



}
