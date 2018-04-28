package lucky.com.rxjavademo.data.http;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.annotations.NonNull;
import lucky.com.rxjavademo.data.http.exception.BusinessException;

public final class ResponseFlatResult {


    /**
     * 对服务器响应的数据，进行重新包装处理。
     * <p>
     * 例如：服务器返回201代码时，说明此次请求存在问题，需要进行处理。
     * 这时候可以通过调用onError抛向外层进行逻辑处理
     *
     * @param result
     * @param <T>
     * @return
     */
    public static <T> Observable<T> flatResult(final DataResponse<T> result) {
        return Observable.create(new ObservableOnSubscribe<T>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<T> subscriber) throws Exception {
                switch (result.getCode()) {
                    case 200:
                        subscriber.onNext(result.getData());
                        break;
                    case 201:
                        subscriber.onError(new BusinessException(result.getMessage()));
                        break;
                    default:
                        subscriber.onError(new Exception(result.getMessage()));
                        break;
                }
                subscriber.onComplete();
            }
        });
    }

}
