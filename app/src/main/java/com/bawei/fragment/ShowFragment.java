package com.bawei.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.bawei.adapter.MyAdapter;
import com.bawei.adapter.MySouAdapter;
import com.bawei.bean.Bean;
import com.bawei.bean.SouBean;
import com.bawei.connext.ConnextInterface;
import com.bawei.presenter.MyPresenter;
import com.bawei.week4.R;
import com.bawei.zdyview.SouSuoView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 作者:今夕何夕
 * 时间:${data}
 * Description:这个是注释
 */
public class ShowFragment extends Fragment implements ConnextInterface.VInterface {
    @BindView(R.id.sousuoview_id)
    SouSuoView sousuoview;
    @BindView(R.id.recyclerview_id)
    RecyclerView recyclerview;
    @BindView(R.id.check_qx)
    CheckBox checkQx;
    @BindView(R.id.text_zongjia)
    TextView textZongjia;
    Unbinder unbinder;
    //搜索框
    String sousu;
    MySouAdapter mySouAdapter;
    List<SouBean.ResultBean> souList=new ArrayList<>();
    //数据展示
    MyAdapter myAdapter;
    List<Bean.DataBean> list=new ArrayList<>();
    //P
    ConnextInterface.PInterface pInterface;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.show_fragment, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //设置管理器
        LinearLayoutManager layoutManager=new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerview.setLayoutManager(layoutManager);
        pInterface=new MyPresenter(this);
        //设置搜索框
        sousuoview.setMyCall(new SouSuoView.MyCall() {
            @Override
            public void sousuo(String tex) {
                sousu=tex;
                //设置搜索的适配器
                mySouAdapter=new MySouAdapter(souList,getActivity());
                recyclerview.setAdapter(mySouAdapter);
                //请求数据
                pInterface.SouMolder(sousu,1,5);
            }
        });
        //设置适配器加载
        myAdapter=new MyAdapter(list,getActivity());
        recyclerview.setAdapter(myAdapter);
        pInterface.toMolde();
        //设置全选
        checkQx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean b = checkQx.isChecked();
                int sums=0;
                if (b){
                    for (int i = 0; i < list.size(); i++) {
                        list.get(i).isChecked=b;
                        for (int j = 0; j < list.get(i).getList().size(); j++) {
                            list.get(i).getList().get(j).isSelected=b;
                            int pice= (int) list.get(i).getList().get(j).getPrice();
                            int num=list.get(i).getList().get(j).getNum();
                            sums+=pice*num;
                        }
                    }
                }else {
                    for (int i = 0; i < list.size(); i++) {
                        list.get(i).isChecked=b;
                        for (int j = 0; j < list.get(i).getList().size(); j++) {
                            list.get(i).getList().get(j).isSelected=b;
                            sums=0;
                        }
                    }
                }
                textZongjia.setText(sums+"");
                myAdapter.notifyDataSetChanged();
            }
        });
        //设置接口
        myAdapter.setMyCallBe(new MyAdapter.MyCallBe() {
            @Override
            public void adaplis(List<Bean.DataBean> adapl) {
                int ss=0;
                int sums=0;
                for (int i = 0; i < adapl.size(); i++) {
                   boolean b= adapl.get(i).isChecked;
                    if (b){
                        ss++;
                        for (int j = 0; j < list.get(i).getList().size(); j++) {
                            list.get(i).getList().get(j).isSelected=b;
                            int pice= (int) list.get(i).getList().get(j).getPrice();
                            int num=list.get(i).getList().get(j).getNum();
                            sums+=pice*num;
                        }
                    }else {
                        for (int j = 0; j < list.get(i).getList().size(); j++) {
                            if (list.get(i).getList().get(j).isSelected){
                                int pice= (int) list.get(i).getList().get(j).getPrice();
                                int num=list.get(i).getList().get(j).getNum();
                                sums+=pice*num;
                            }
                        }
                    }
                }
                if (ss==adapl.size()){
                    checkQx.setChecked(true);
                }else {
                    checkQx.setChecked(false);
                }
                textZongjia.setText(sums+"");
                myAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void SouView(List<SouBean.ResultBean> lst) {
        Log.e("aaa", "SouView: "+lst.get(0).getCommodityName() );
        this.souList.addAll(lst);
        mySouAdapter.notifyDataSetChanged();
    }

    @Override
    public void MyView(List<Bean.DataBean> lst) {
        this.list.addAll(lst);
        myAdapter.notifyDataSetChanged();
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        pInterface.onDestroy();
        pInterface=null;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
