package lucky.com.rxjavademo.data.http;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class Connector {
    private String baseUrl = "http://127.0.0.1/";
    private Retrofit mRetrofit;

    private Connector() {
        mRetrofit = init();
    }

    public Retrofit getRetrofit() {
        return mRetrofit;
    }

    public static Connector getInstance() {
        return SingleHolder.mConnector;
    }

    private Retrofit init() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .build();
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    private static class SingleHolder {
        private static Connector mConnector = new Connector();
    }
}
