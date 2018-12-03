package mg.edena.shop.ui.base;


import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public abstract class BaseFragment<T extends ViewModel> extends Fragment {

	private View mRootView;
	private T mViewModel;

	public BaseFragment() {
	}

	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
							 @Nullable Bundle savedInstanceState) {
		mRootView = inflater.inflate(getIdLayoutToInflate(), container, false);
		return mRootView;
	}

	public abstract int getIdLayoutToInflate();
	public abstract Class<T> getClazzTmodel();
	public View getRootView() {
		return mRootView;
	}
	public T getViewModel() {
		return mViewModel;
	}



	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}


	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		mViewModel = ViewModelProviders.of(getActivity()).get(getClazzTmodel());

	}

	public void addChildFragment(Fragment fragment, int layoutIdRender) {
		FragmentManager childFragMan = getChildFragmentManager();

		FragmentTransaction childFragTrans = childFragMan.beginTransaction();
		childFragTrans.add(layoutIdRender, fragment);
		childFragTrans.addToBackStack("B");
		childFragTrans.commit();

	}


}




