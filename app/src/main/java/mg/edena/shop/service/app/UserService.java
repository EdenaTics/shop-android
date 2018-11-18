package mg.edena.shop.service.app;

import java.util.List;

import mg.edena.shop.bean.User;
import mg.edena.shop.service.ServiceCallback;

public interface UserService {
	public void getList(ServiceCallback<List<User>,Throwable> callback);
}
