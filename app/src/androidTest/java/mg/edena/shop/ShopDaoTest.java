package mg.edena.shop;

/**
 * crea
 */

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import mg.edena.shop.bean.ShopBean;
import mg.edena.shop.dao.DBResultListner;
import mg.edena.shop.dao.ShopServiceDao;
import mg.edena.shop.dao.impl.ShopServiceDaoImpl;

import static org.junit.Assert.assertTrue;

@RunWith(AndroidJUnit4.class)
public class ShopDaoTest {

	@Test
	public void insert(){
		Context cxt = InstrumentationRegistry.getContext();
		ShopBean sb = new ShopBean();
		sb.setName("edena");
		ShopServiceDao sd = new ShopServiceDaoImpl();
		sd.create(sb, new DBResultListner() {
			@Override
			public void onSucces(Object object) {
				//org.hamcrest.MatcherAssert.assertThat(object, );
				assertTrue (false);
			}

			@Override
			public void onError(Object object) {
				assertTrue (false);
			}
		});

	}



}
