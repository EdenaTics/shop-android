package mg.edena.shop.fragment;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import mg.edena.shop.R;
import mg.edena.shop.adapter.ShopListAdapter;
import mg.edena.shop.bean.ShopBean;
import mg.edena.shop.viewmodel.ShopListFragemntViewModel;

public class ShopListFragment extends BaseFragment {

	private ShopListFragemntViewModel mShopViewModel;
	private RecyclerView mShopRecyclerView;

	public static ShopListFragment newInstance() {
		return new ShopListFragment();
	}

	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
							 @Nullable Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.shop_list_fragment, container, false);
		setUpRecyclerView(v);
		return v;
	}

	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		mShopViewModel = ViewModelProviders.of(this).get(ShopListFragemntViewModel.class);
		mShopViewModel.getList().observe(this, new Observer<List<ShopBean>>() {
			@Override
			public void onChanged(@Nullable List<ShopBean> list) {
				ShopListAdapter adapterList = new ShopListAdapter(list);
				mShopRecyclerView.setAdapter(adapterList);
			}
		});

	}

	private void setUpRecyclerView(View v) {
		mShopRecyclerView = v.findViewById(R.id.list);
		mShopRecyclerView.setLayoutManager(new LinearLayoutManager(mShopRecyclerView.getContext()));
		//recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), VERTICAL));

	}

}
