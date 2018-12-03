package mg.edena.shop.ui.shop;

import android.arch.lifecycle.Observer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import mg.edena.shop.R;
import mg.edena.shop.model.bean.ShopBean;
import mg.edena.shop.ui.base.BaseFragment;

public class ShopDetailFragment extends BaseFragment<ShopListFragemntViewModel> {

	private ShopListFragemntViewModel mViewModel;
	private View rootView;

	public static ShopDetailFragment newInstance() {
		return new ShopDetailFragment();
	}


	@Override
	public int getIdLayoutToInflate() {
		return R.layout.shop_detail_fragment;
	}

	@Override
	public Class getClazzTmodel() {
		return ShopListFragemntViewModel.class;
	}

	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		getViewModel().getItemSelected().observe(getActivity(),new Observer<ShopBean>() {
			@Override
			public void onChanged(@Nullable ShopBean item) {
				updateUiDetail(item);
			}
		});

	}

	private void updateUiDetail(ShopBean item){
		TextView title = getRootView().findViewById(R.id.title);
		title.setText(item.getTitle()!=null?item.getTitle():"");

		TextView desc = getRootView().findViewById(R.id.desc);
		desc.setText(item.getDesc()!=null?item.getDesc():"");

	}

}
