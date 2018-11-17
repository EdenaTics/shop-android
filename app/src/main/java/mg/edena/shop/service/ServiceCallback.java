package mg.edena.shop.service;

public interface ServiceCallback<T,E> {
	public void onFaillure(E ex);
	public void onSucces(T success);
}
