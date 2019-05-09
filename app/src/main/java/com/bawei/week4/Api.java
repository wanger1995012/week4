package com.bawei.week4;

import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;
import rx.Observable;

/**
 * 作者:今夕何夕
 * 时间:${data}
 * Description:这个是注释
 */
public interface Api {
    //搜索
    @GET
    public Observable<ResponseBody> gets(@Url String url, @QueryMap Map<String,String> map);
    //展示
    @GET
    public Observable<ResponseBody> get(@Url String url);
}
