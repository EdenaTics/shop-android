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

	private List<ShopBean> shopList = new ArrayList<>();
	private IAdapterDelegate delegate;

	public ShopListAdapter(List<ShopBean> shopList,IAdapterDelegate delegate) {
		this.shopList = shopList;
		this.delegate = delegate;
	}

	@Override
	public ShopListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
													 int viewType) {
		View view =  LayoutInflater.from(parent.getContext())
				.inflate(R.layout.shop_list_item, parent, false);
		ViewHolder viewHolder = new ViewHolder(view,delegate);
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



	public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

		TextView title;
		TextView desc;
		IAdapterDelegate delegate;

		public ViewHolder(View v,IAdapterDelegate delegate) {
			super(v);
			title = v.findViewById(R.id.title);
			desc = v.findViewById(R.id.desc);
			this.delegate = delegate;
			v.setOnClickListener(this);
		}

		@Override
		public void onClick(View view) {
			if(this.delegate != null) this.delegate.onItemClick(view,getAdapterPosition());
		}
	}

	public IAdapterDelegate getDelegate() {
		return delegate;
	}

	public void setDelegate(IAdapterDelegate delegate) {
		this.delegate = delegate;
	}


	public interface IAdapterDelegate{
		public void onItemClick(View view, int postion);
	}

}
