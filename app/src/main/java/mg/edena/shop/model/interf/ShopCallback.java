package mg.edena.shop.model.interf;

import java.util.List;

import mg.edena.shop.model.bean.ShopBean;
import mg.edena.shop.model.interf.ServiceCallback;

public interface ShopCallback {
	public void getList(ServiceCallback<List<ShopBean>,Throwable> callback);
	public void add(ShopBean shopBean, ServiceCallback<ShopBean,Throwable> callback);
}
