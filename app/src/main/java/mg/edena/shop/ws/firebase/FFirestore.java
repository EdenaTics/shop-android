package mg.edena.shop.ws.firebase;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreSettings;

public class FFirestore {

	FirebaseFirestore mFireBaseStore = null;
	private static FFirestore mFFirestore = null;

	private FFirestore() {
		mFireBaseStore = FirebaseFirestore.getInstance();
		FirebaseFirestoreSettings settings = new FirebaseFirestoreSettings.Builder()
				.setTimestampsInSnapshotsEnabled(true)
				.build();
		mFireBaseStore.setFirestoreSettings(settings);
	}

	public static FFirestore getInstance(){
		if(mFFirestore == null) mFFirestore = new FFirestore();
		return mFFirestore;

	}

	public FirebaseFirestore getFireBaseStore(){
		return mFireBaseStore;
	}


}
