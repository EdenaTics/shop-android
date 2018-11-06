package mg.edena.shop.utils.edena.retrofit;

import android.content.Context;

import java.io.Serializable;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
/**
 * Created by edena on 18/01/2017.
 */

public class EDRetrofit {

    private static  final String TAG = "EDRetrofit";
    private   String _urlBase = null;

    static EDRetrofit _instance;
    Context _context;
    public EDRetrofit(Context context,String urlBase) {
        _context = context;
        _urlBase = urlBase;
    }

    public static EDRetrofit getIntance(Context context,String urlBase){
        if(_instance == null) _instance = new EDRetrofit(context,urlBase);
        return _instance;
    }

    public void setUrlBase(String urlBase) {
        this._urlBase = urlBase;
    }

    public Retrofit build(){
        //Here a logging interceptor is created
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(logging);
        Retrofit retrofit = new Retrofit.Builder()
                .client(httpClient.build())
                .addConverterFactory(GsonConverterFactory.create())
                //.addConverterFactory(new StringConverterFactory())
                .baseUrl(_urlBase).build();
        return retrofit;
    }


}
