package com.bawei.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.bawei.bean.Bean;
import com.bawei.week1.R;

import java.util.List;

/**
 * 作者:今夕何夕
 * 时间:${data}
 * Description:这个是注释
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.holder>{
    List<Bean.DataBean> list;
    Context context;
    MyCallB myCallB;
    public MyAdapter(List<Bean.DataBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.shangjia_item, null);
        return new holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final holder holder, final int i) {
        holder.checkBox.setChecked(list.get(i).isSelected);
        holder.text_shangjia.setText(list.get(i).getSellerName());
        //设置管理器
        LinearLayoutManager layoutManager=new LinearLayoutManager(context);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        holder.recyclerView_shangjia.setLayoutManager(layoutManager);
        //设置适配器
        MyZiAdapter myZiAdapter=new MyZiAdapter(list.get(i).getList(),context);
        holder.recyclerView_shangjia.setAdapter(myZiAdapter);
        //复选框
        holder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean b = holder.checkBox.isChecked();
                list.get(i).isSelected=b;
                myCallB.myadap(list);
            }
        });
        myZiAdapter.setMyCallB(new MyZiAdapter.MyCallB() {
            @Override
            public void ziadap(List<Bean.DataBean.ListBean> zilist) {
                int ss=0;
                for (int j = 0; j < zilist.size(); j++) {
                    if (zilist.get(j).isChecked){
                        ss++;
                    }
                }
                if (ss==zilist.size()){
                    holder.checkBox.setChecked(true);
                    list.get(i).isSelected=true;
                }else {
                    holder.checkBox.setChecked(false);
                    list.get(i).isSelected=false;
                }
                myCallB.myadap(list);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class holder extends RecyclerView.ViewHolder{
        CheckBox checkBox;
        TextView text_shangjia;
        RecyclerView recyclerView_shangjia;
        public holder(@NonNull View itemView) {
            super(itemView);
            checkBox=itemView.findViewById(R.id.checkbox_shangjia);
            text_shangjia=itemView.findViewById(R.id.text_shangjia);
            recyclerView_shangjia=itemView.findViewById(R.id.recyclerview_shangjia);
        }
    }
    //设置接口
    public interface MyCallB{
        public void myadap(List<Bean.DataBean> mylst);
    }

    public void setMyCallB(MyCallB myCallB) {
        this.myCallB = myCallB;
    }
}
