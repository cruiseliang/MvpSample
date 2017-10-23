package com.touyuanren.mvpsample.presenter;

import android.content.Context;

import com.touyuanren.mvpsample.bean.Book;
import com.touyuanren.mvpsample.data.http.RetrofitHelper;
import com.touyuanren.mvpsample.data.model.BookInfoModel;
import com.touyuanren.mvpsample.presenter.contract.BookInfoContract;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Liang on 2017/10/23 0023.
 */

public class BookPresenter {

    private BookInfoContract.IView mView;
    private BookInfoModel bookInfoModel;
    private Book mBook;

    public BookPresenter(BookInfoContract.IView mView) {
        this.mView = mView;
        bookInfoModel = new BookInfoModel(RetrofitHelper.getInstance((Context) mView).getServer());
    }

    //获取数据
    public void getMsg(String name, String tag, int start, int count) {
        bookInfoModel.getBookMsg(name, tag, start, count)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Book>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Book value) {
                        mBook = value;
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        mView.showError("请求失败");
                    }

                    @Override
                    public void onComplete() {
                        if (mView != null) {
                            mView.showResult(mBook.toString());
                        }
                    }
                });

    }
}
