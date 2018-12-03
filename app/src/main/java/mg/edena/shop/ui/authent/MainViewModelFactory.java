package mg.edena.shop.ui.authent;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.google.firebase.auth.FirebaseAuth;

public class MainViewModelFactory implements ViewModelProvider.Factory {

	private FirebaseAuth mAuthFireBase;
	private MainInterface mMainInterface;
	public MainViewModelFactory(FirebaseAuth firebaseAuth, MainInterface mainInterface){
		this.mAuthFireBase = firebaseAuth;
		this.mMainInterface = mainInterface;
	}

	@NonNull
	@Override
	public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
		return (T) new MainViewModel(mAuthFireBase, mMainInterface);
	}
}
