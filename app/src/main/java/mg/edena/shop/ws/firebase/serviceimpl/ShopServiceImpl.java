package mg.edena.shop.ws.firebase.serviceimpl;

import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.QuerySnapshot;

import mg.edena.shop.bean.ShopBean;
import mg.edena.shop.service.ServiceCallbackBean;
import mg.edena.shop.service.ServiceDelegate;
import mg.edena.shop.service.app.ShopService;

public class ShopServiceImpl extends FireStoreBase implements ShopService {

	@Override
	public void getList(final ServiceDelegate delegate) {
		getFirebaseFirestore().collection("shop").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
			@Override
			public void onComplete(@NonNull Task<QuerySnapshot> task) {
				ServiceCallbackBean value = new ServiceCallbackBean();
				if(task == null){
					value.setStatus(-1);
					if(delegate != null) delegate.onFailure(value);
				}else{
					value.setStatus(0);
					if(delegate != null) delegate.onSuccess(value);
				}
			}
		});
	}

	@Override
	public void add(ShopBean shopBean, ServiceDelegate delegate) {

	}
}
