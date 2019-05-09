package com.bawei.connext;

import com.bawei.bean.Bean;
import com.bawei.bean.SouBean;

import java.util.List;

/**
 * 作者:今夕何夕
 * 时间:${data}
 * Description:这个是注释
 */
public interface ConnextInterface {
    //V
    public interface VInterface{
        public void Sou(List<SouBean.ResultBean> lst);
        public void sho(List<Bean.DataBean> lst);
    }
    //P
    public interface PInterface{
        public void SouP(String keyword,int page,int count);
        public void sho(int shu);
        public void onDestroy();
    }

}
