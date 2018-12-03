package mg.edena.shop.ws.shop;

import java.util.List;

import io.reactivex.Observable;
import mg.edena.shop.model.bean.ShopBean;

public interface ShopService {
	public Observable<List<ShopBean>> list();
}
