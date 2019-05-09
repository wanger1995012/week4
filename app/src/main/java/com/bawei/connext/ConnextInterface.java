package com.bawei.connext;

import com.bawei.bean.Bean;
import com.bawei.bean.SouBean;

import java.util.List;

/**
 * 作者:今夕何夕
 * 时间:${data}
 * Description:这个是注释
 */
public class ConnextInterface {
    //V
    public interface VInterface{
        public void SouView(List<SouBean.ResultBean> lst);
        public void MyView(List<Bean.DataBean> lst);
    }
    //P
    public interface PInterface{
        public void SouMolder(String keyword,int page,int count);
        public void toMolde();
        public void onDestroy();
    }
}
