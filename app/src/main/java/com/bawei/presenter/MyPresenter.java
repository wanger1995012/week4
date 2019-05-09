package com.bawei.presenter;

import com.bawei.bean.Bean;
import com.bawei.bean.SouBean;
import com.bawei.connext.ConnextInterface;
import com.bawei.fragment.ShowFragment;
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
    public void SouMolder(String keyword, int page, int count) {
        Map<String,String> map=new HashMap<>();
        map.put("keyword",keyword);
        map.put("page",page+"");
        map.put("count",count+"");
        myMolder.Sou(map, new MyMolder.MyCallBreak() {
            @Override
            public void sressco(Object o) {
                ConnextInterface.VInterface vInterface= (ConnextInterface.VInterface) tt;
                SouBean beans= (SouBean) o;
                vInterface.SouView(beans.getResult());
            }
        });
    }

    @Override
    public void toMolde() {
        myMolder.show(new MyMolder.MyCallBreak() {
            @Override
            public void sressco(Object o) {
                ConnextInterface.VInterface vInterface= (ConnextInterface.VInterface) tt;
                Bean beans= (Bean) o;
                vInterface.MyView(beans.getData());
            }
        });
    }

    @Override
    public void onDestroy() {
        tt=null;
    }
}
