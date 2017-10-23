package com.touyuanren.mvpsample.presenter;

import android.content.Context;
import android.support.v4.app.Fragment;

import com.touyuanren.mvpsample.ui.BaseView;


/**
 * Created by Liang on 2017/10/9 0009.
 */

public class BasePresenter<M, V extends BaseView> {
    protected V mView;
    protected M mModel;
    protected Context context;

    public BasePresenter(V v, M m) {
        this.mView = v;
        this.mModel = m;
        initContext();

    }

    private void initContext() {
        if (mView instanceof Fragment) {
            context = ((Fragment) mView).getActivity();
        } else {
            context = (Context) mView;
        }
    }
}
