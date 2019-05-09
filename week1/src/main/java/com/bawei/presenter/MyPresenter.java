package com.bawei.presenter;

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
        tt=t;
    }

    @Override
    public void SouPer(String kayword, int page, int count) {
        Map<String,String> map=new HashMap<>();
        map.put("keyword",kayword);
        map.put("page",page+"");
        map.put("count",count+"");
        myMolder.getSou(map, new MyMolder.MyCallBreak() {
            @Override
            public void sressco(Object o) {
                ConnextInterface.MyView myView= (ConnextInterface.MyView) tt;
                SouBean souBean= (SouBean) o;
                myView.SouView(souBean.getResult());
            }
        });
    }

    @Override
    public void ShowPer() {
        myMolder.getShow(new MyMolder.MyCallBreak() {
            @Override
            public void sressco(Object o) {
                ConnextInterface.MyView myView= (ConnextInterface.MyView) tt;
                Bean beans= (Bean) o;
                myView.MView(beans.getData());
            }
        });
    }

    @Override
    public void onDestry() {
        tt=null;
    }
}
