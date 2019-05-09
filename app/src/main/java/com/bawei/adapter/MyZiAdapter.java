package com.bawei.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.bawei.bean.Bean;
import com.bawei.week4.R;
import com.bawei.zdyview.JianJiaView;
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
    MyCallbe myCallbe;
    public MyZiAdapter(List<Bean.DataBean.ListBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.myziadapter_id, null);
        return new holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final holder holder, final int i) {

        holder.text_name.setText(list.get(i).getTitle());
        holder.text_pice.setText(list.get(i).getPrice()+"");
        holder.simpleDraweeView_zi.setImageURI(list.get(i).getImages());
        holder.checkBox_zi.setChecked(list.get(i).isSelected);
        holder.jianJiaView.setnum(list.get(i).getNum());
        //加减的回调接口
        holder.jianJiaView.setMyCall(new JianJiaView.MyCall() {
            @Override
            public void jiajian(int nums) {
                list.get(i).setNum(nums);
                myCallbe.myziad(list);
            }
        });
        //设置全选
        holder.checkBox_zi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean b = holder.checkBox_zi.isChecked();
                list.get(i).isSelected=b;
                myCallbe.myziad(list);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class holder extends RecyclerView.ViewHolder{
        CheckBox checkBox_zi;
        TextView text_name,text_pice;
        SimpleDraweeView simpleDraweeView_zi;
        JianJiaView jianJiaView;
        public holder(@NonNull View itemView) {
            super(itemView);
            checkBox_zi=itemView.findViewById(R.id.checkbox_zi);
            text_name=itemView.findViewById(R.id.text_name);
            text_pice=itemView.findViewById(R.id.text_pice);
            simpleDraweeView_zi=itemView.findViewById(R.id.simpledraweeview_zi);
            jianJiaView=itemView.findViewById(R.id.jiajian_id);
        }
    }
    //设置接口
    public interface MyCallbe{
        public void myziad(List<Bean.DataBean.ListBean> ziada);
    }

    public void setMyCallbe(MyCallbe myCallbe) {
        this.myCallbe = myCallbe;
    }
}
