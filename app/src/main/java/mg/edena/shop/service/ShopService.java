package mg.edena.shop.service;

import mg.edena.shop.bean.ShopBean;

public interface ShopService {
	public void getList(ServiceDelegate delegate);
	public void add(ShopBean shopBean, ServiceDelegate delegate);
}
