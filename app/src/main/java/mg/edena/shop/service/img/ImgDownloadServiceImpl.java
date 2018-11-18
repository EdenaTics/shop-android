package mg.edena.shop.service.img;

import android.app.Application;
import android.widget.ImageView;

import mg.edena.shop.App;
import mg.edena.shop.utils.GlideUtils;

public class ImgDownloadServiceImpl implements ImgDownloadService {


	private static ImgDownloadServiceImpl intance;
	private Application context;

	private ImgDownloadServiceImpl(){
		this.context = App.getInstance();
	}

	public static ImgDownloadServiceImpl getIntance(){
		if(intance == null) intance = new ImgDownloadServiceImpl();
		return intance;
	}

	@Override
	public void showUrlimgInImageview(String url, ImageView imageView) {
		GlideUtils.getInstance(context).load(imageView,url);
	}
}
