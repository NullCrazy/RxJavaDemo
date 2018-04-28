package lucky.com.rxjavademo.data.http.server;


import io.reactivex.Observable;
import lucky.com.rxjavademo.data.http.DataResponse;
import retrofit2.http.GET;

public interface ApiServer {

    @GET("/")
    Observable<DataResponse<String>> getUser();
}
