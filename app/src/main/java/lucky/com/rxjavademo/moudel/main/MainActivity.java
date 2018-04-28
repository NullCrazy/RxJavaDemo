package lucky.com.rxjavademo.moudel.main;

import android.annotation.SuppressLint;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import lucky.com.rxjavademo.R;
import lucky.com.rxjavademo.moudel.base.BaseMvpActivity;

@SuppressLint("CheckResult")
public class MainActivity extends BaseMvpActivity<MainPresenter> implements MainView {
    private Button btnJust;
    private Button btnFrom;
    private Button btnMap;
    private Button btnFlatMap;
    private Button btnFilter;
    private Button btnFirst;
    private Button btnMerge;
    private Button btnTimer;
    private Button btnInterval;
    private Button btnNet;

    private TextView mContent;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected MainPresenter createPresent() {
        return new MainPresenterImpl();
    }

    @Override
    protected void init() {
        super.init();
        btnJust = findViewById(R.id.btn_just);
        btnFrom = findViewById(R.id.btn_from);
        btnMap = findViewById(R.id.btn_map);
        btnFlatMap = findViewById(R.id.btn_flat_map);
        btnFilter = findViewById(R.id.btn_filter);
        btnFirst = findViewById(R.id.btn_first);
        btnMerge = findViewById(R.id.btn_merge);
        btnTimer = findViewById(R.id.btn_timer);
        btnInterval = findViewById(R.id.btn_interval);
        mContent = findViewById(R.id.tv_content);
        btnNet = findViewById(R.id.btn_net_test);

        btnJust.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mContent.setText("");
                testJust();
            }
        });

        btnFrom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mContent.setText("");
                testFrom();
            }
        });
        btnMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mContent.setText("");
                testMap();
            }
        });
        btnFlatMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mContent.setText("");
                testFlatMap();
            }
        });
        btnFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mContent.setText("");
                testFilter();
            }
        });
        btnFirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mContent.setText("");
                testFirst();
            }
        });
        btnMerge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mContent.setText("");
                testMerge();
            }
        });
        btnTimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mContent.setText("");
                testTimer();
            }
        });
        btnInterval.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mContent.setText("");
                testInterval();
            }
        });
        btnNet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.login();
            }
        });
    }

    private void testJust() {
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                emitter.onNext("1");
                emitter.onNext("12");
                emitter.onComplete();
            }
        });
        Observable.just(test())
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        mContent.setText(String.valueOf(integer));
                    }
                });
    }

    private int test() {
        return 10;
    }

    private void testFrom() {
        String[] strs = new String[]{"1", "3", "5", "7", "0"};

        Observable.fromArray(strs)
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        mContent.setText(mContent.getText() + s + " ");
                    }
                });
    }

    private void testMap() {
        Observable.just(100)
                .map(new Function<Integer, String>() {
                    @Override
                    public String apply(Integer integer) throws Exception {
                        return String.valueOf(integer + 100);
                    }
                })
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        mContent.setText(mContent.getText() + s + " ");
                    }
                });
    }

    private void testFlatMap() {
        Observable.just("007")
                .flatMap(new Function<String, ObservableSource<String>>() {
                    @Override
                    public ObservableSource<String> apply(String s) throws Exception {
                        return Observable.just("我是" + s);
                    }
                })
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        mContent.setText(mContent.getText() + s + " ");
                    }
                });
    }

    private void testFilter() {
        Integer[] strs = new Integer[]{1, 99, 36, 9, 2, 100};
        Observable.fromArray(strs)
                .filter(new Predicate<Integer>() {
                    @Override
                    public boolean test(Integer integer) throws Exception {
                        return integer > 10;
                    }
                })
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        mContent.setText(mContent.getText() + integer.toString() + " ");
                    }
                });
    }

    private void testFirst() {
        Integer[] strs = new Integer[]{1, 99, 36, 9, 2, 100};
        Observable.fromArray(strs)
                .first(0)
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        mContent.setText(mContent.getText() + integer.toString());
                    }
                });
    }

    private void testMerge() {
        Observable.merge(
                Observable.just(1, 2, 3, 4),
                Observable.just(5, 6, 7, 8))
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        mContent.setText(mContent.getText() + integer.toString());
                    }
                });

    }

    private void testTimer() {
        Observable.timer(2, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        mContent.setText(mContent.getText() + aLong.toString());
                    }
                });
    }

    Disposable disposable;

    private void testInterval() {
        Observable
                .interval(1, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        disposable = d;
                    }

                    @Override
                    public void onNext(Long aLong) {
                        if (aLong == 10) {
                            disposable.dispose();
                        }
                        mContent.setText(mContent.getText() + aLong.toString() + " ");
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
