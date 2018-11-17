package mg.edena.shop.ws.firebase.serviceimpl;

import com.google.firebase.firestore.FirebaseFirestore;

import mg.edena.shop.ws.firebase.FFirestore;

public class FireStoreBase {
	private FirebaseFirestore firebaseFirestore;

	public FireStoreBase(){
		firebaseFirestore = FFirestore.getInstance().getFireBaseStore();
	}

	public FirebaseFirestore getFirebaseFirestore() {
		return firebaseFirestore;
	}

	public void setFirebaseFirestore(FirebaseFirestore firebaseFirestore) {
		this.firebaseFirestore = firebaseFirestore;
	}



}
