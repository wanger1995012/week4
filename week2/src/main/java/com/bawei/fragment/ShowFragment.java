package com.bawei.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.bawei.Presenter.MyPresenter;
import com.bawei.adapter.MyAdapter;
import com.bawei.adapter.SouAdapter;
import com.bawei.bean.Bean;
import com.bawei.bean.SouBean;
import com.bawei.connext.ConnextInterface;
import com.bawei.week2.R;
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
    @BindView(R.id.checkbox_qx)
    CheckBox checkboxQx;
    @BindView(R.id.text_zongjia)
    TextView textZongjia;
    Unbinder unbinder;
    //P
    ConnextInterface.PInterface pInterface;
    //搜索框
    String sou;
    int su;
    List<SouBean.ResultBean> sou_list=new ArrayList<>();
    SouAdapter souAdapter;
    //展示数据
    MyAdapter myAdapter;
    List<Bean.DataBean> list=new ArrayList<>();
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
        //P
        pInterface=new MyPresenter(this);
        //设置搜索框的事件
        sousuoview.setMyCall(new SouSuoView.MyCall() {
            @Override
            public void sou(String o) {
                sou=o;
                //设置适配器
                souAdapter=new SouAdapter(sou_list,getContext());
                recyclerview.setAdapter(souAdapter);
                pInterface.SouP(sou,1,10);
            }

            @Override
            public void suo(int o) {
                su=o;
                //设置适配器
                myAdapter=new MyAdapter(list,getContext());
                recyclerview.setAdapter(myAdapter);
                pInterface.sho(51);
            }
        });
        //设置适配器
        myAdapter=new MyAdapter(list,getContext());
        recyclerview.setAdapter(myAdapter);
        pInterface.sho(51);
        //设置全选和复选
        checkboxQx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean b = checkboxQx.isChecked();
                int sum=0;
               if (b){
                   for (int i = 0; i < list.size(); i++) {
                       list.get(i).isSelected=b;
                       for (int j = 0; j < list.get(i).getList().size(); j++) {
                           list.get(i).getList().get(j).isChecked=b;
                           int pice= (int) list.get(i).getList().get(j).getPrice();
                           int num=list.get(i).getList().get(j).getNum();
                           sum+=pice*num;
                       }
                   }
               }else {
                   for (int i = 0; i < list.size(); i++) {
                       list.get(i).isSelected=b;
                       for (int j = 0; j < list.get(i).getList().size(); j++) {
                           list.get(i).getList().get(j).isChecked=b;
                           sum=0;
                       }
                   }
               }
               textZongjia.setText(sum+"");
               myAdapter.notifyDataSetChanged();
            }
        });
        //实现
        myAdapter.setMyCallB(new MyAdapter.MyCallB() {
            @Override
            public void MyAdap(List<Bean.DataBean> mylist) {
                int ss=0;
                int sum=0;
                for (int i = 0; i < mylist.size(); i++) {
                    boolean b = mylist.get(i).isSelected;
                    if (b){
                        ss++;
                        for (int j = 0; j < mylist.get(i).getList().size(); j++) {
                            list.get(i).getList().get(j).isChecked=b;
                            int pice= (int) mylist.get(i).getList().get(j).getPrice();
                            int num=mylist.get(i).getList().get(j).getNum();
                            sum+=pice*num;
                        }
                    }else {
                        for (int j = 0; j < mylist.get(i).getList().size(); j++) {
                            if (list.get(i).getList().get(j).isChecked){
                                int pice= (int) mylist.get(i).getList().get(j).getPrice();
                                int num=mylist.get(i).getList().get(j).getNum();
                                sum+=pice*num;
                            }
                        }
                    }
                }
                if (ss==mylist.size()){
                    checkboxQx.setChecked(true);
                }else {
                    checkboxQx.setChecked(false);
                }
                textZongjia.setText(sum+"");
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
    public void Sou(List<SouBean.ResultBean> lst) {
        this.sou_list.addAll(lst);
        souAdapter.notifyDataSetChanged();
    }

    @Override
    public void sho(List<Bean.DataBean> lst) {
        this.list.addAll(lst);
        myAdapter.notifyDataSetChanged();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        pInterface.onDestroy();
        pInterface=null;
    }
}
