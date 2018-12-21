package mg.edena.shop.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Base64;
import android.util.Log;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class CommonUtils {

	public static void getKeyHash(Context cxt) {
		try {
			if(Build.VERSION.SDK_INT >= 28) {
				final PackageInfo packageInfo = cxt.getPackageManager().getPackageInfo(cxt.getPackageName(), PackageManager.GET_SIGNING_CERTIFICATES);
				final Signature[] signatures = packageInfo.signingInfo.getApkContentsSigners();
				final MessageDigest md = MessageDigest.getInstance("SHA");
				for (Signature signature : signatures) {
					md.update(signature.toByteArray());
					final String signatureBase64 = new String(Base64.encode(md.digest(), Base64.DEFAULT));
					Log.d("Signature Base64", signatureBase64);
				}
			}
			/*
			else{
				PackageInfo info = cxt.getPackageManager().getPackageInfo(cxt.getPackageName(), PackageInfo.GET_SIGNING_CERTIFICATES);
				for (Signature signature : info.signatures) {
					MessageDigest md = MessageDigest.getInstance("SHA");
					md.update(signature.toByteArray());
					Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
				}
			}
			*/
		} catch (PackageManager.NameNotFoundException | NoSuchAlgorithmException e) {
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


}
