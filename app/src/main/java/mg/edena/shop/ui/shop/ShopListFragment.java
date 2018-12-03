package mg.edena.shop.ui.shop;

import android.arch.lifecycle.Observer;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.transition.ChangeBounds;
import android.support.transition.Transition;
import android.support.transition.TransitionManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;

import java.util.List;

import mg.edena.shop.R;
import mg.edena.shop.ui.shop.adapter.ShopListAdapter;
import mg.edena.shop.model.bean.ShopBean;
import mg.edena.shop.ui.base.BaseFragment;

public class ShopListFragment extends BaseFragment<ShopListFragemntViewModel> implements ShopListAdapter.IAdapterDelegate {

	private ShopListFragemntViewModel mShopViewModel;
	private RecyclerView mShopRecyclerView;
	private ConstraintLayout mRootLayout;
	private ConstraintSet mContrConstraintSet = new ConstraintSet();
	private ConstraintSet mContrConstraintSetDetail = new ConstraintSet();
	private boolean isDetailShow = false;

	public static ShopListFragment newInstance() {
		return new ShopListFragment();
	}

	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
							 @Nullable Bundle savedInstanceState) {
		View v = super.onCreateView(inflater,container,savedInstanceState);
		setUpAnimationLayoutContraint(v);
		setUpRecyclerView(v);
		setUpDetailFragment();
		return v;
	}

	@Override
	public int getIdLayoutToInflate() {
		return R.layout.shop_list_fragment;
	}

	@Override
	public Class getClazzTmodel() {
		return ShopListFragemntViewModel.class;
	}

	private void setUpAnimationLayoutContraint(View v) {
		mRootLayout = v.findViewById(R.id.rootLayout);
		mContrConstraintSet.clone(mRootLayout);
		mContrConstraintSetDetail.clone(getActivity(),R.layout.shop_list_fragment_detail_clone);
	}


	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		//mShopViewModel = ViewModelProviders.of(getActivity()).get(ShopListFragemntViewModel.class);
		getViewModel().getList().observe(getActivity(), new Observer<List<ShopBean>>() {
			@Override
			public void onChanged(@Nullable List<ShopBean> list) {
				ShopListAdapter adapterList = new ShopListAdapter(list,ShopListFragment.this);
				mShopRecyclerView.setAdapter(adapterList);
			}
		});

	}

	private void setUpRecyclerView(View v) {
		mShopRecyclerView = v.findViewById(R.id.list);
		mShopRecyclerView.setLayoutManager(new LinearLayoutManager(mShopRecyclerView.getContext()));
		//recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), VERTICAL));
	}

	private void setUpDetailFragment(){
		ShopDetailFragment fragment = ShopDetailFragment.newInstance();
		addChildFragment(fragment,R.id.detailLayout);
	}

	@Override
	public void onItemClick(View view, int postion) {
		showDetail(getViewModel().getList().getValue().get(postion));
	}

	private void showDetail(ShopBean item){
		if(animateLayout()){
			setUiDetail(item);
		}

	}

	private void setUiDetail(ShopBean item){
		getViewModel().setItemSelected(item);
	}

	private boolean animateLayout(){
		Transition transition = new ChangeBounds();
		transition.setInterpolator(new AccelerateInterpolator());
		TransitionManager.beginDelayedTransition(mRootLayout,transition);
		if(!isDetailShow){
			mContrConstraintSetDetail.applyTo(mRootLayout);
			isDetailShow = true;
		}else{
			mContrConstraintSet.applyTo(mRootLayout);
			isDetailShow = false;

		}
		return isDetailShow;

	}
}
