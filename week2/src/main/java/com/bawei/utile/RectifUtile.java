package com.bawei.utile;

import android.util.Log;

import com.facebook.common.util.ByteConstants;

import java.io.File;
import java.io.IOException;

import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 作者:今夕何夕
 * 时间:${data}
 * Description:这个是注释
 */
public class RectifUtile {
    static RectifUtile utile;
    Retrofit retrofit;
    OkHttpClient okHttpClient;
    private RectifUtile(){
        OkHttpClient.Builder builder=new OkHttpClient.Builder();
        builder.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request=chain.request();
                Log.e("aaa", "request: "+request.toString() );
                Response response=chain.proceed(request);
                Log.e("aaa", "response: "+response.toString() );
                return response;
            }
        });
        builder.cache(new Cache(new File("com.bw.cache"),100*ByteConstants.MB));
        okHttpClient=builder.build();

        retrofit=new Retrofit.Builder()
                .baseUrl("http://172.17.8.100/")
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
    }
    public static synchronized RectifUtile getUtile(){
        if (utile==null){
            utile=new RectifUtile();
        }
        return utile;
    }
    public <T>T Gets(Class<T> tClass){
        return retrofit.create(tClass);
    }
}
