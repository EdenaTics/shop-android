package mg.edena.shop.fragment;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import mg.edena.shop.R;
import mg.edena.shop.bean.ShopBean;
import mg.edena.shop.viewmodel.ShopListFragemntViewModel;

public class ShopDetailFragment extends BaseFragment {

	private ShopListFragemntViewModel mViewModel;
	private View rootView;

	public static ShopDetailFragment newInstance() {
		return new ShopDetailFragment();
	}

	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
							 @Nullable Bundle savedInstanceState) {
		rootView = inflater.inflate(R.layout.shop_detail_fragment, container, false);

		return rootView;
	}

	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		mViewModel = ViewModelProviders.of(getActivity()).get(ShopListFragemntViewModel.class);
		mViewModel.getItemSelected().observe(getActivity(),new Observer<ShopBean>() {
			@Override
			public void onChanged(@Nullable ShopBean item) {
				updateUiDetail(item);
			}
		});

	}

	private void updateUiDetail(ShopBean item){
		TextView title = rootView.findViewById(R.id.title);
		title.setText(item.getTitle()!=null?item.getTitle():"");

		TextView desc = rootView.findViewById(R.id.desc);
		desc.setText(item.getDesc()!=null?item.getDesc():"");

	}

}
