package mg.edena.shop.fragment;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import mg.edena.shop.R;
import mg.edena.shop.viewmodel.ShopListFragemntViewModel;

public class ShopListFragment extends BaseFragment {

	private ShopListFragemntViewModel mViewModel;

	public static ShopListFragment newInstance() {
		return new ShopListFragment();
	}

	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
							 @Nullable Bundle savedInstanceState) {
		return inflater.inflate(R.layout.shop_list_fragment, container, false);
	}

	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		mViewModel = ViewModelProviders.of(this).get(ShopListFragemntViewModel.class);
		// TODO: Use the ViewModel
	}

}
