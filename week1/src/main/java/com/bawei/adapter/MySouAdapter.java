package com.bawei.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bawei.bean.SouBean;
import com.bawei.week1.R;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

/**
 * 作者:今夕何夕
 * 时间:${data}
 * Description:这个是注释
 */
public class MySouAdapter  extends RecyclerView.Adapter<MySouAdapter.holder>{
    List<SouBean.ResultBean> list;
    Context context;

    public MySouAdapter(List<SouBean.ResultBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.souadapter_item, null);
        return new holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull holder holder, int i) {
        holder.text_pice.setText(list.get(i).getPrice()+"");
        holder.text_name.setText(list.get(i).getCommodityName());
        holder.simpleDraweeView.setImageURI(list.get(i).getMasterPic());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class holder extends RecyclerView.ViewHolder{
        SimpleDraweeView simpleDraweeView;
        TextView text_name,text_pice;
        public holder(@NonNull View itemView) {
            super(itemView);
            simpleDraweeView=itemView.findViewById(R.id.simpledraweeview_sousuo);
            text_name=itemView.findViewById(R.id.sousuo_name);
            text_pice=itemView.findViewById(R.id.sousuo_pice);
        }
    }
}
