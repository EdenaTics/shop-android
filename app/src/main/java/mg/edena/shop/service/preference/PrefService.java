package mg.edena.shop.service.preference;

import mg.edena.shop.model.bean.User;

public interface PrefService {
	public boolean isFirstRun();
	public void setFirstRun(boolean status);
	public User getUserLogged();
	public void setSaveUserLogged(User userLogged);
}
