package com.touyuanren.mvpsample.data.http;

import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by win764-1 on 2016/12/12.
 */

public class RetrofitHelper {


    OkHttpClient client = new OkHttpClient();
    GsonConverterFactory factory = GsonConverterFactory.create(new GsonBuilder().create());
    private static RetrofitHelper instance = null;
    private Retrofit mRetrofit = null;
    public static RetrofitHelper getInstance( ){
        if (instance == null){
            instance = new RetrofitHelper();
        }
        return instance;
    }
    private RetrofitHelper( ){
        init();
    }

    private void init() {
        resetApp();
    }

    private void resetApp() {
        mRetrofit = new Retrofit.Builder()
                .baseUrl(ApiService.BASE_URL)
                .client(client)
                .addConverterFactory(factory)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }
    public ApiService getServer(){
        return mRetrofit.create(ApiService.class);
    }
}
