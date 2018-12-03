package mg.edena.shop.model.interf;

import java.util.List;

import mg.edena.shop.model.bean.User;
import mg.edena.shop.model.interf.ServiceCallback;

public interface UserCallback {
	public void getList(ServiceCallback<List<User>,Throwable> callback);
}
