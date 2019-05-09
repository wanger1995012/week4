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
import com.bawei.week2.R;

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
        View view = LayoutInflater.from(context).inflate(R.layout.myadapter_item, null);
        return new holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final holder holder, final int i) {
        holder.text_shangjia.setText(list.get(i).getSellerName());
        holder.checkBox_shang.setChecked(list.get(i).isSelected);
        //设置管理器
        LinearLayoutManager layoutManager=new LinearLayoutManager(context);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        holder.recyclerView_zitiaomu.setLayoutManager(layoutManager);
        //设置适配器
        MyZiAdapter myZiAdapter=new MyZiAdapter(list.get(i).getList(),context);
        holder.recyclerView_zitiaomu.setAdapter(myZiAdapter);
        //设置复选框
        holder.checkBox_shang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean b = holder.checkBox_shang.isChecked();
                list.get(i).isSelected=b;
                myCallB.MyAdap(list);
            }
        });
        //设置实现
        myZiAdapter.setMyCallB(new MyZiAdapter.MyCallB() {
            @Override
            public void ZiAdap(List<Bean.DataBean.ListBean> zilist) {
                int ss=0;
                for (int j = 0; j < zilist.size(); j++) {
                    if (zilist.get(j).isChecked){
                        ss++;
                    }
                }
                if (ss==zilist.size()){
                    holder.checkBox_shang.setChecked(true);
                    list.get(i).isSelected=true;
                }else {
                    holder.checkBox_shang.setChecked(false);
                    list.get(i).isSelected=false;
                }
                myCallB.MyAdap(list);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class holder extends RecyclerView.ViewHolder{
        CheckBox checkBox_shang;
        TextView text_shangjia;
        RecyclerView recyclerView_zitiaomu;
        public holder(@NonNull View itemView) {
            super(itemView);
            checkBox_shang=itemView.findViewById(R.id.checkbox_shangjia);
            text_shangjia=itemView.findViewById(R.id.text_shangjia);
            recyclerView_zitiaomu=itemView.findViewById(R.id.recyclerview_shangjia);
        }
    }
    //设置接口
    public interface MyCallB{
        public void MyAdap(List<Bean.DataBean> mylist);
    }

    public void setMyCallB(MyCallB myCallB) {
        this.myCallB = myCallB;
    }
}
