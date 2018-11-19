package mg.edena.shop.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

public class BaseFragment extends Fragment {


	public BaseFragment() {
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	public void addChildFragment(Fragment fragment, int layoutIdRender) {
		FragmentManager childFragMan = getChildFragmentManager();

		FragmentTransaction childFragTrans = childFragMan.beginTransaction();
		childFragTrans.add(layoutIdRender, fragment);
		childFragTrans.addToBackStack("B");
		childFragTrans.commit();

	}



}
