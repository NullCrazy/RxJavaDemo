package lucky.com.rxjavademo.moudel.main;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import lucky.com.rxjavademo.data.http.TestApi;
import lucky.com.rxjavademo.moudel.base.BaseMvpPresenter;
import lucky.com.rxjavademo.moudel.exception.ErrorMessageFactory;

public class MainPresenterImpl extends BaseMvpPresenter<MainView> implements MainPresenter {
    private TestApi mTestApi;

    public MainPresenterImpl() {
        mTestApi = new TestApi();
    }

    @Override
    public void login() {
        mTestApi.getResponse()//发请网络请求
                .subscribeOn(Schedulers.io())//指定上游去io线程执行
                .observeOn(AndroidSchedulers.mainThread())//指定回调下游回到主线中运行
                .subscribe(new Observer<String>() {//发起订阅
                    @Override
                    public void onSubscribe(Disposable d) {
                        //收集此次订阅，避免发生内存泄漏
                        mCompositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(String s) {
                        //进行登录成功处理
                    }

                    @Override
                    public void onError(Throwable e) {
                        //讲错误信息交给view层处理
                        mView.showTip(ErrorMessageFactory.create(e));
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }
}
