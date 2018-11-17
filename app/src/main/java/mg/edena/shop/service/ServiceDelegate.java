package mg.edena.shop.service;

public interface ServiceDelegate {
	public void onSuccess(ServiceCallbackBean delegateValue);
	public void onFailure(ServiceCallbackBean delegateValue);

}
