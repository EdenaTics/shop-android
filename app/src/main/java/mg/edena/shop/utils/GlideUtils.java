package mg.edena.shop.utils;


import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.RequestManager;

public class GlideUtils {
	private final String TAG = GlideUtils.class.getCanonicalName();

	private Application context;
	private static  GlideUtils instance;
	private RequestManager requestManager;

	private GlideUtils(Application context) {
		this.context = context;
		this.requestManager = Glide.with(context);

	}

	public static GlideUtils getInstance(Application context){
		if(instance == null) instance = new GlideUtils(context);
		return instance;
	}

	public RequestManager getRequestManager() {
		return requestManager;
	}

	public void load(ImageView imageView, String url){
		RequestBuilder requestBuilder = requestManager.load( url );

		requestBuilder.into(imageView);
	}


}
