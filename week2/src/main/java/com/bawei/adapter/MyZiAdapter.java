package com.bawei.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.bawei.bean.Bean;
import com.bawei.week2.R;
import com.bawei.zdyview.JiaJianView;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

/**
 * 作者:今夕何夕
 * 时间:${data}
 * Description:这个是注释
 */
public class MyZiAdapter extends RecyclerView.Adapter<MyZiAdapter.holder>{
    List<Bean.DataBean.ListBean> list;
    Context context;
    MyCallB myCallB;
    public MyZiAdapter(List<Bean.DataBean.ListBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.myziadapter_item, null);
        return new holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final holder holder, final int i) {
        holder.text_name.setText(list.get(i).getTitle());
        holder.text_pice.setText(list.get(i).getPrice()+"");
        holder.checkBox_zi.setChecked(list.get(i).isChecked);
        holder.simpleDraweeView.setImageURI(list.get(i).getImages());
        holder.jiaJianView.getData(list.get(i).getNum());
        holder.jiaJianView.setMyCall(new JiaJianView.MyCall() {
            @Override
            public void jia(int nums) {
                list.get(i).setNum(nums);
                myCallB.ZiAdap(list);
            }
        });
        //设置复选框
        holder.checkBox_zi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean b = holder.checkBox_zi.isChecked();
                if (b){
                    list.get(i).isChecked=b;
                    myCallB.ZiAdap(list);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class holder extends RecyclerView.ViewHolder{
        CheckBox checkBox_zi;
        SimpleDraweeView simpleDraweeView;
        TextView text_name,text_pice;
        JiaJianView jiaJianView;
        public holder(@NonNull View itemView) {
            super(itemView);
            checkBox_zi=itemView.findViewById(R.id.checkbox_zi);
            simpleDraweeView=itemView.findViewById(R.id.simpledraweeview_zi);
            text_name=itemView.findViewById(R.id.zi_name);
            text_pice=itemView.findViewById(R.id.zi_pice);
            jiaJianView=itemView.findViewById(R.id.jianjian_id);
        }
    }
    //设置接口
    public interface MyCallB{
        public void ZiAdap(List<Bean.DataBean.ListBean> zilist);
    }

    public void setMyCallB(MyCallB myCallB) {
        this.myCallB = myCallB;
    }
}
