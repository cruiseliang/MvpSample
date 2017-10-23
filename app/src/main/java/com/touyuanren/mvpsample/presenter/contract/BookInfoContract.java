package com.touyuanren.mvpsample.presenter.contract;


import com.touyuanren.mvpsample.bean.Book;
import com.touyuanren.mvpsample.ui.BaseView;

import io.reactivex.Observable;

/**
 * Created by Liang on 2017/10/9 0009.
 */

public interface BookInfoContract {
    interface IView extends BaseView {

        void showResult(String  msg);

//        void onRequestPermissionSuccess();
//
//        void onRequestPermissionSError();
    }

     interface IBookModel {

        Observable<Book> getBookMsg(String name,String tag,int start,int count);

    }

}
