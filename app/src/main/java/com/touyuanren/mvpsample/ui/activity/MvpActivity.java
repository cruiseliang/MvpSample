package com.touyuanren.mvpsample.ui.activity;

import android.os.Bundle;

import com.touyuanren.mvpsample.presenter.BasePresenter;
import com.touyuanren.mvpsample.ui.BaseView;


/**
 * Created by Liang on 2017/10/24 0024.
 */
public abstract class MvpActivity<P extends BasePresenter> extends BaseActivity implements BaseView{
    protected P mvpPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mvpPresenter = createPresenter();
        super.onCreate(savedInstanceState);
        mvpPresenter.attachView(this);
    }

    protected abstract P createPresenter();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mvpPresenter != null) {
            mvpPresenter.detachView();
        }
    }

    public void showLoading() {
        showProgressDialog();
    }

    public void hideLoading() {
        dismissProgressDialog();
    }
}
