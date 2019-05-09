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
    public interface MyView{
        public void SouView(List<SouBean.ResultBean> lst);
        public void MView(List<Bean.DataBean> lst);
    }
    //P
    public interface PInterface{
        public void SouPer(String kayword,int page,int count);
        public void ShowPer();
        public void onDestry();
    }
}
