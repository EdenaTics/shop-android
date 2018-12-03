package mg.edena.shop.service.app;

import java.util.List;

import mg.edena.shop.model.bean.User;
import mg.edena.shop.model.interf.ServiceCallback;

public interface UserService {
	public void getList(ServiceCallback<List<User>,Throwable> callback);
}
