package com.bawei.week1;

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
    @GET
    public Observable<ResponseBody> GET(@Url String url, @QueryMap Map<String,String> map);
    @GET
    public Observable<ResponseBody> GETShow(@Url String url);
}
