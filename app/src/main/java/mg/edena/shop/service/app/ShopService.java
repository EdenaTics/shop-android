package mg.edena.shop.service.app;

import java.util.List;

import mg.edena.shop.bean.ShopBean;
import mg.edena.shop.service.ServiceCallback;

public interface ShopService {
	public void getList(ServiceCallback<List<ShopBean>,Throwable> callback);
	public void add(ShopBean shopBean, ServiceCallback<ShopBean,Throwable> callback);
}
