package com.touyuanren.mvpsample.presenter;

import com.touyuanren.mvpsample.bean.Book;
import com.touyuanren.mvpsample.presenter.contract.BookInfoContract;

import io.reactivex.observers.DisposableObserver;

/**
 * Created by Liang on 2017/10/23 0023.
 */

public class BookPresenter extends BasePresenter<BookInfoContract.IView> {

    private Book mBook;

    public BookPresenter(BookInfoContract.IView iView) {
        super(iView);
    }

    //获取数据
    public void getMsg(String name, String tag, int start, int count) {
        addSubscription(mApiService.getSearchBooks(name, tag, start, count), new DisposableObserver<Book>() {
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
//        bookInfoModel.getBookMsg(name, tag, start, count)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Observer<Book>() {
//                    @Override
//                    public void onSubscribe(Disposable d) {
//
//                    }
//
//                    @Override
//                    public void onNext(Book value) {
//
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        e.printStackTrace();
//                        mView.showError("请求失败");
//                    }
//
//                    @Override
//                    public void onComplete() {
//
//                    }
//                });
    }
}
