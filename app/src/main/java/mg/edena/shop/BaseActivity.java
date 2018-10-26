package mg.edena.shop;

import android.support.v7.app.AppCompatActivity;

import com.jeevandeshmukh.glidetoastlib.GlideToast;

public class BaseActivity extends AppCompatActivity {

	public void toast(String mess,String type){
		new GlideToast.makeToast(this,"Some random text here", GlideToast.LENGTHLONG, type).show();
	}

}
