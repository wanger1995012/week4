package com.bawei.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bawei.bean.SouBean;
import com.bawei.week4.R;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

/**
 * 作者:今夕何夕
 * 时间:${data}
 * Description:这个是注释
 */
public class MySouAdapter extends RecyclerView.Adapter<MySouAdapter.holder>{
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
        holder.simpleDraweeView_sousuo.setImageURI(list.get(i).getMasterPic());
        holder.sou_name.setText(list.get(i).getCommodityName());
        holder.sou_pice.setText(list.get(i).getPrice()+"");
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class holder extends RecyclerView.ViewHolder{
        SimpleDraweeView simpleDraweeView_sousuo;
        TextView sou_name,sou_pice;
        public holder(@NonNull View itemView) {
            super(itemView);
            simpleDraweeView_sousuo=itemView.findViewById(R.id.simpledraweeview_sou);
            sou_name=itemView.findViewById(R.id.sou_name);
            sou_pice=itemView.findViewById(R.id.sou_pice);
        }
    }
}
