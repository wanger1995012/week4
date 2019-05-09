package com.bawei.molder;

import android.util.Log;

import com.bawei.bean.Bean;
import com.bawei.bean.SouBean;
import com.bawei.utile.RectifUtile;
import com.bawei.week2.Api;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.Map;

import okhttp3.ResponseBody;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * 作者:今夕何夕
 * 时间:${data}
 * Description:这个是注释
 */
public class MyMolder {

    //设置搜索
    public void SouGet(Map<String,String> map, final MyCallBreak callBreak){
        RectifUtile rectifUtile=RectifUtile.getUtile();
        Api api=rectifUtile.Gets(Api.class);
        api.Sou("small/commodity/v1/findCommodityByKeyword?",map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<ResponseBody>() {
                    @Override
                    public void call(ResponseBody responseBody) {
                        try {
                            String json=responseBody.string();
                            Log.e("aaa", "sousuo: "+json );
                            Gson gson=new Gson();
                            SouBean souBean=gson.fromJson(json,SouBean.class);
                            callBreak.sressco(souBean);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }
    //设置展示
    public void ShowGet(Map<String,String> map, final MyCallBreak callBreak){
        RectifUtile rectifUtile=RectifUtile.getUtile();
        Api api=rectifUtile.Gets(Api.class);
        api.Sho("ks/product/getCarts?",map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<ResponseBody>() {
                    @Override
                    public void call(ResponseBody responseBody) {
                        try {
                            String json=responseBody.string();
                            Log.e("aaa", "bean: "+json );
                            Gson gson=new Gson();
                            Bean souBean=gson.fromJson(json,Bean.class);
                            callBreak.sressco(souBean);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

    //设置接口
    public interface MyCallBreak{
        public void sressco(Object o);
    }
}
