package mg.edena.shop.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

import mg.edena.shop.bean.ShopBean;

public class ShopListFragemntViewModel extends ViewModel {
	private static final String TAG = ShopListFragemntViewModel.class.getCanonicalName();

	private MutableLiveData<List<ShopBean>> list;

	private final MutableLiveData<ShopBean> itemSelected = new MutableLiveData<>();

	public LiveData<List<ShopBean>> getList() {
		if (list == null) {
			list = new MutableLiveData<List<ShopBean>>();
			getData();
		}
		return list;
	}

	public LiveData<ShopBean> getItemSelected() {
		return itemSelected;
	}

	public void setItemSelected(ShopBean itemSelected) {
		this.itemSelected.setValue(itemSelected);
	}

	private void getData() {
		List<ShopBean> list = new ArrayList<>();
		list.add(new ShopBean("Emily","Veste rouge, pantalon noir"));
		list.add(new ShopBean("Julien","Veste bleu, pantalon blanc"));
		list.add(new ShopBean("Eden","Veste noir, pantalon noir"));
		this.list.setValue(list);
	}


}
