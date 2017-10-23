package com.touyuanren.mvpsample.data.model;


import com.touyuanren.mvpsample.bean.Book;
import com.touyuanren.mvpsample.data.http.ApiService;
import com.touyuanren.mvpsample.presenter.contract.BookInfoContract;

import io.reactivex.Observable;

/**
 * Created by Liang on 2017/10/9 0009.
 */

public class BookInfoModel implements BookInfoContract.IBookModel {

    private ApiService mApiService;

    public BookInfoModel(ApiService mApiService) {
        this.mApiService = mApiService;
    }

    @Override
    public Observable<Book> getBookMsg(String name, String tag, int start, int count) {
        return mApiService.getSearchBooks(name, tag, start, count);
    }
}
