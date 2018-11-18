package mg.edena.shop.authent;

import com.facebook.GraphResponse;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;

public class AuthBean {

	public GraphResponse graphResponse;
	public Task<AuthResult> authResultTask;

	public AuthBean(GraphResponse graphResponse, Task<AuthResult> authResultTask) {
		this.graphResponse = graphResponse;
		this.authResultTask = authResultTask;
	}

	public interface AuthUtilsCallback{
		public void onComplete(AuthBean authBean);
	}


}
