package lucky.com.rxjavademo.moudel.exception;

import java.net.ConnectException;
import java.net.SocketTimeoutException;

import lucky.com.rxjavademo.data.http.exception.BusinessException;
import retrofit2.adapter.rxjava2.HttpException;

public final class ErrorMessageFactory {

    private ErrorMessageFactory() {
        //no-op
    }

    public static String create(Throwable exception) {
        String message;

        if (exception instanceof ConnectException) {
            message = "网络连接失败";
        } else if (exception instanceof SocketTimeoutException) {
            message = "连接超时";
        } else if (exception instanceof HttpException) {
            message = "IP 解析失败";
        } else if (exception instanceof BusinessException) {
            message = exception.getMessage();
        } else {
            message = exception.getMessage();
        }
        return message;
    }
}
