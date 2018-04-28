package lucky.com.rxjavademo.moudel.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Toast;

public abstract class BaseMvpActivity<P extends Presenter> extends BaseActivity implements MvpView {
    protected P presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        presenter = createPresent();
        presenter.attachView(this);
        init();
    }

    protected abstract int getLayoutId();

    //执行的某些初始化操作
    protected void init() {
    }

    protected abstract P createPresent();

    @Override
    protected void onStart() {
        super.onStart();
        presenter.start();
    }

    @Override
    public void showTip(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.resume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        presenter.pause();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {
    }

    @Override
    public Context context() {
        return this;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.destroyView();
    }
}
