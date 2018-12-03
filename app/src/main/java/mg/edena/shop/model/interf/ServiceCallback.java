package mg.edena.shop.model.interf;

public interface ServiceCallback<T,E> {
	public void onFaillure(E ex);
	public void onSucces(T success);
}
