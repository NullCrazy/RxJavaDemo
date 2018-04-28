package lucky.com.rxjavademo.moudel.base;

public interface Presenter<V extends MvpView> {
    void attachView(V v);

    void start();

    void resume();

    void pause();

    void destroyView();
}
