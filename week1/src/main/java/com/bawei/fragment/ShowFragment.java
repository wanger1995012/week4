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
import com.bawei.week1.R;
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
public class ShowFragment extends Fragment implements ConnextInterface.MyView {
    @BindView(R.id.sousuoview_id)
    SouSuoView sousuoview;
    @BindView(R.id.recyclerview_id)
    RecyclerView recyclerview;
    @BindView(R.id.check_qx)
    CheckBox checkQx;
    Unbinder unbinder;
    //P
    ConnextInterface.PInterface pInterface;
    //搜索框
    String sou;
    MySouAdapter mySouAdapter;
    List<SouBean.ResultBean> sou_list = new ArrayList<>();
    //展示数据
    List<Bean.DataBean> list = new ArrayList<>();
    MyAdapter myAdapter;
    @BindView(R.id.text_qx)
    TextView textQx;

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
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerview.setLayoutManager(layoutManager);
        //P
        pInterface = new MyPresenter(this);
        //设置搜索框
        sousuoview.setMyCall(new SouSuoView.MyCall() {
            @Override
            public void soued(String sousuo) {
                sou = sousuo;
                //设置适配器
                mySouAdapter = new MySouAdapter(sou_list, getContext());
                recyclerview.setAdapter(mySouAdapter);
                //去P层请求数据
                pInterface.SouPer(sou, 1, 10);
            }
        });
        //设置适配器
        myAdapter = new MyAdapter(list, getActivity());
        recyclerview.setAdapter(myAdapter);
        //去P层请求数据
        pInterface.ShowPer();
        //设置复选框
        checkQx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean b = checkQx.isChecked();
                int sum = 0;
                if (b) {
                    for (int i = 0; i < list.size(); i++) {
                        list.get(i).isSelected = b;
                        for (int j = 0; j < list.get(i).getList().size(); j++) {
                            list.get(i).getList().get(j).isChecked = b;
                            int pice = (int) list.get(i).getList().get(j).getPrice();
                            int num = list.get(i).getList().get(j).getNum();
                            sum += pice * num;
                        }
                    }
                } else {
                    for (int i = 0; i < list.size(); i++) {
                        list.get(i).isSelected = b;
                        for (int j = 0; j < list.get(i).getList().size(); j++) {
                            list.get(i).getList().get(j).isChecked = b;
                            sum = 0;
                        }
                    }
                }
                textQx.setText(sum+"");
                myAdapter.notifyDataSetChanged();
            }
        });
        //实现接口
        myAdapter.setMyCallB(new MyAdapter.MyCallB() {
            @Override
            public void myadap(List<Bean.DataBean> mylst) {
                int ss=0;
                int sum=0;
                for (int i = 0; i < mylst.size(); i++) {
                   boolean b=mylst.get(i).isSelected;
                    if (b){
                        ss++;
                        for (int j = 0; j < mylst.get(i).getList().size(); j++) {
                            list.get(i).getList().get(j).isChecked = b;
                            int pice = (int) mylst.get(i).getList().get(j).getPrice();
                            int num = mylst.get(i).getList().get(j).getNum();
                            sum += pice * num;
                        }
                    }else {
                        for (int j = 0; j < mylst.get(i).getList().size(); j++) {
                            if (list.get(i).getList().get(j).isChecked){
                                int pice = (int) mylst.get(i).getList().get(j).getPrice();
                                int num = mylst.get(i).getList().get(j).getNum();
                                sum += pice * num;
                            }
                        }
                    }
                }
                if (ss==mylst.size()){
                    checkQx.setChecked(true);
                }else {
                    checkQx.setChecked(false);
                }
                textQx.setText(sum+"");
                myAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void SouView(List<SouBean.ResultBean> lst) {
        Log.e("aaa", "SouView: " + lst.get(1).getCommodityName());
        this.sou_list.addAll(lst);
        mySouAdapter.notifyDataSetChanged();
    }

    @Override
    public void MView(List<Bean.DataBean> lst) {
        Log.e("aaa", "MView: " + lst.get(1).getSellerName());
        this.list.addAll(lst);
        myAdapter.notifyDataSetChanged();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        pInterface.onDestry();
        pInterface = null;
    }
}
