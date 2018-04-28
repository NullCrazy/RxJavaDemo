package lucky.com.rxjavademo.data.http;


import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;
import lucky.com.rxjavademo.data.http.server.ApiServer;

public class TestApi {

    private ApiServer mApiServer;

    public TestApi() {
        mApiServer = Connector.getInstance().getRetrofit().create(ApiServer.class);
    }

    public Observable<String> getResponse() {
        return mApiServer.getUser()
                //将服务器返回的DataResponse<String> 转换成 ObservableSource<String>
                .flatMap(new Function<DataResponse<String>, ObservableSource<String>>() {
                    @Override
                    public ObservableSource<String> apply(DataResponse<String> stringDataResponse) throws Exception {
                        return ResponseFlatResult.flatResult(stringDataResponse);
                    }
                });
    }
}
