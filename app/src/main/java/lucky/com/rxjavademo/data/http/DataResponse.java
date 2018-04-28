package lucky.com.rxjavademo.data.http;

import com.google.gson.annotations.SerializedName;

public class DataResponse<T> {
    //服务器定义的，业务code
    @SerializedName("code")
    private int code;
    //出错后需要告知的信息
    @SerializedName("message")
    private String message;
    //服务器响应的数据
    @SerializedName("data")
    private T data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
