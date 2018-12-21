package mg.edena.shop.ws.shop;

import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;
import java.util.concurrent.Callable;

import io.reactivex.Observable;
import mg.edena.shop.model.bean.ShopBean;
import mg.edena.shop.model.interf.ServiceCallback;
import mg.edena.shop.model.api.ResultBean;
import mg.edena.shop.ws.base.firebase.FireStoreBase;

public class ShopServiceImpl extends FireStoreBase implements ShopService {

	public void getList(ServiceCallback<List<ShopBean>, Throwable> callback) {
		getFirebaseFirestore().collection("shop").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
			@Override
			public void onComplete(@NonNull Task<QuerySnapshot> task) {
				ResultBean value = new ResultBean();
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
	public Observable<List<ShopBean>> list() {
		return Observable.fromCallable(new Callable<List<ShopBean>>() {
			@Override
			public List<ShopBean> call() throws Exception {
				return null;
			}
		});
	}
}
