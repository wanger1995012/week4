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
import com.bawei.week1.R;
import com.bawei.zdyview.JiaJianView;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

/**
 * 作者:今夕何夕
 * 时间:${data}
 * Description:这个是注释
 */
public class MyZiAdapter extends RecyclerView.Adapter<MyZiAdapter.holder>{
    List<Bean.DataBean.ListBean> zi_list;
    Context context;
    MyCallB myCallB;

    public MyZiAdapter(List<Bean.DataBean.ListBean> zi_list, Context context) {
        this.zi_list = zi_list;
        this.context = context;
    }

    @NonNull
    @Override
    public holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.ziadapter_item, null);
        return new holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final holder holder, final int i) {
        holder.simpleDraweeView_zi.setImageURI(zi_list.get(i).getImages());
        holder.zi_name.setText(zi_list.get(i).getTitle());
        holder.zi_pice.setText(zi_list.get(i).getPrice()+"");
        holder.checkBox.setChecked(zi_list.get(i).isChecked);
        //设置加减及数据传递
        holder.jiaJianView.getnums(zi_list.get(i).getNum());
        holder.jiaJianView.setMyCall(new JiaJianView.MyCall() {
            @Override
            public void jiajian(int num) {
                zi_list.get(i).setNum(num);
                myCallB.ziadap(zi_list);
            }
        });
        //设置复选框
        holder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean b = holder.checkBox.isChecked();
                if (b){
                    zi_list.get(i).isChecked=b;
                    myCallB.ziadap(zi_list);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return zi_list.size();
    }

    public class holder extends RecyclerView.ViewHolder{
        SimpleDraweeView simpleDraweeView_zi;
        TextView zi_name,zi_pice;
        JiaJianView jiaJianView;
        CheckBox checkBox;
        public holder(@NonNull View itemView) {
            super(itemView);
            simpleDraweeView_zi=itemView.findViewById(R.id.simpledraweeview_zi);
            zi_name=itemView.findViewById(R.id.text_name);
            zi_pice=itemView.findViewById(R.id.text_pice);
            jiaJianView=itemView.findViewById(R.id.jianjianview_id);
            checkBox=itemView.findViewById(R.id.checkbox_zi);
        }
    }
    //设置接口
    public interface MyCallB{
        public void ziadap(List<Bean.DataBean.ListBean> zilist);
    }

    public void setMyCallB(MyCallB myCallB) {
        this.myCallB = myCallB;
    }
}
