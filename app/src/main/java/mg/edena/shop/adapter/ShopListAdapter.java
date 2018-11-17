package mg.edena.shop.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import mg.edena.shop.R;
import mg.edena.shop.bean.ShopBean;

public class ShopListAdapter extends RecyclerView.Adapter<ShopListAdapter.ViewHolder> {

	List<ShopBean> shopList = new ArrayList<>();

	public ShopListAdapter(List<ShopBean> shopList) {
		this.shopList = shopList;
	}

	@Override
	public ShopListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
													 int viewType) {
		View view =  LayoutInflater.from(parent.getContext())
				.inflate(R.layout.shop_list_item, parent, false);
		ViewHolder viewHolder = new ViewHolder(view);
		return viewHolder;
	}

	@Override
	public void onBindViewHolder(@NonNull ShopListAdapter.ViewHolder viewHolder, int position) {
		ShopBean item = shopList.get(position);
		viewHolder.title.setText(item.getTitle());
		viewHolder.desc.setText(item.getDesc());
	}


	@Override
	public int getItemCount() {
		return shopList.size();
	}


	public static class ViewHolder extends RecyclerView.ViewHolder {

		TextView title;
		TextView desc;

		public ViewHolder(View v) {
			super(v);
			title = v.findViewById(R.id.title);
			desc = v.findViewById(R.id.desc);
		}
	}

}
