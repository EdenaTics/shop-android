package mg.edena.shop.ws.firebase.serviceimpl;

import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;

import mg.edena.shop.bean.ShopBean;
import mg.edena.shop.service.ServiceCallback;
import mg.edena.shop.service.ServiceCallbackBean;
import mg.edena.shop.service.app.ShopService;

public class ShopServiceImpl extends FireStoreBase implements ShopService {


	@Override
	public void getList(ServiceCallback<List<ShopBean>, Throwable> callback) {
		getFirebaseFirestore().collection("shop").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
			@Override
			public void onComplete(@NonNull Task<QuerySnapshot> task) {
				ServiceCallbackBean value = new ServiceCallbackBean();
				if(task == null){
					value.setStatus(-1);
					if(callback != null) callback.onFaillure(new Throwable());
				}else{
					value.setStatus(0);
					if(callback != null) callback.onSucces(null);
				}
			}
		});
	}

	@Override
	public void add(ShopBean shopBean, ServiceCallback<ShopBean, Throwable> callback) {

	}
}
