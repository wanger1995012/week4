package com.bawei.Presenter;

import com.bawei.bean.Bean;
import com.bawei.bean.SouBean;
import com.bawei.connext.ConnextInterface;
import com.bawei.molder.MyMolder;

import java.util.HashMap;
import java.util.Map;

/**
 * 作者:今夕何夕
 * 时间:${data}
 * Description:这个是注释
 */
public class MyPresenter<T> implements ConnextInterface.PInterface {
    T tt;
    MyMolder myMolder;
    public MyPresenter(T t) {
        myMolder=new MyMolder();
        this.tt=t;
    }

    @Override
    public void SouP(String keyword, int page, int count) {
        Map<String,String> map=new HashMap<>();
        map.put("keyword",keyword);
        map.put("page",page+"");
        map.put("count",count+"");
        myMolder.SouGet(map, new MyMolder.MyCallBreak() {
            @Override
            public void sressco(Object o) {
                SouBean souBean= (SouBean) o;
                ConnextInterface.VInterface vInterface= (ConnextInterface.VInterface) tt;
                vInterface.Sou(souBean.getResult());
            }
        });
    }

    @Override
    public void sho(int shu) {
        Map<String,String> map=new HashMap<>();
        map.put("uid",shu+"");
        myMolder.ShowGet(map, new MyMolder.MyCallBreak() {
            @Override
            public void sressco(Object o) {
                Bean beans= (Bean) o;
                ConnextInterface.VInterface vInterface= (ConnextInterface.VInterface) tt;
                vInterface.sho(beans.getData());
            }
        });
    }

    @Override
    public void onDestroy() {
        tt=null;
    }
}
