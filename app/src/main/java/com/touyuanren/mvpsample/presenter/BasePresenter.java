package com.touyuanren.mvpsample.presenter;

import android.content.Context;
import android.support.v4.app.Fragment;

import com.touyuanren.mvpsample.data.http.ApiService;
import com.touyuanren.mvpsample.data.http.RetrofitHelper;
import com.touyuanren.mvpsample.ui.BaseView;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;


/**
 * Created by Liang on 2017/10/9 0009.
 */

public class BasePresenter<V extends BaseView> {
    protected V mView;

    protected Context context;
    protected ApiService mApiService;
    private CompositeDisposable mCompositeDisposable;

    //使用弱引用
    private Reference<V> mReference = null;

    public BasePresenter(V v) {
        this.mView = v;
        initContext();
    }

    private void initContext() {
        if (mView instanceof Fragment) {
            context = ((Fragment) mView).getActivity();
        } else {
            context = (Context) mView;
        }
    }

    //绑定view
    public void attachView(V mvpView) {
        mReference = new WeakReference<V>(mvpView);
        mApiService = RetrofitHelper.getInstance().getServer();
    }

    //判断是否绑定
    public boolean isAttach() {
        return null != mReference && null != mReference.get();
    }

    //接触绑定
    public void detachView() {
        if (null != mReference) {
            mReference.clear();
            mReference = null;
        }
        onUnsubscribe();
    }


    //RXjava取消注册，以避免内存泄露
    public void onUnsubscribe() {
        if (mCompositeDisposable != null && mCompositeDisposable.isDisposed()) {
            mCompositeDisposable.clear();
        }
    }


    public void addSubscription(Observable observable, DisposableObserver subscriber) {
        if (mCompositeDisposable == null) {
            mCompositeDisposable = new CompositeDisposable();
        }
        observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(subscriber);
        mCompositeDisposable.add(subscriber);
    }

}
