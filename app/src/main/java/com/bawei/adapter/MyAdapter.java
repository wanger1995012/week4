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
import com.bawei.week4.R;

import java.util.List;

/**
 * 作者:今夕何夕
 * 时间:${data}
 * Description:这个是注释
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.holder>{
    List<Bean.DataBean> list;
    Context context;
    MyCallBe myCallBe;
    public MyAdapter(List<Bean.DataBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_item, null);
        return new holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final holder holder, final int i) {
        holder.text_shangjia.setText(list.get(i).getSellerName());
        holder.checkBox_shangjia.setChecked(list.get(i).isChecked);

        //设置管理器
        LinearLayoutManager layoutManager=new LinearLayoutManager(context);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        holder.recyclerView_zitiaomu.setLayoutManager(layoutManager);
        //设置适配器
        MyZiAdapter myZiAdapter=new MyZiAdapter(list.get(i).getList(),context);
        holder.recyclerView_zitiaomu.setAdapter(myZiAdapter);
        //设置全选
        holder.checkBox_shangjia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean b = holder.checkBox_shangjia.isChecked();
                list.get(i).isChecked=b;
                myCallBe.adaplis(list);
            }
        });
        //设置接口
        myZiAdapter.setMyCallbe(new MyZiAdapter.MyCallbe() {
            @Override
            public void myziad(List<Bean.DataBean.ListBean> ziada) {
                int ss=0;
                for (int j = 0; j < ziada.size(); j++) {
                    if (ziada.get(j).isSelected){
                        ss++;
                    }
                }
                if (ss==ziada.size()){
                    holder.checkBox_shangjia.setChecked(true);
                    list.get(i).isChecked=true;
                }else {
                    holder.checkBox_shangjia.setChecked(false);
                    list.get(i).isChecked=false;
                }
                myCallBe.adaplis(list);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class holder extends RecyclerView.ViewHolder{
        CheckBox checkBox_shangjia;
        TextView text_shangjia;
        RecyclerView recyclerView_zitiaomu;
        public holder(@NonNull View itemView) {
            super(itemView);
            checkBox_shangjia=itemView.findViewById(R.id.checkbox_shangjia);
            text_shangjia=itemView.findViewById(R.id.text_shangjia);
            recyclerView_zitiaomu=itemView.findViewById(R.id.recyclerview_zitiaomu);
        }
    }
    //设置接口
    public interface MyCallBe{
        public void adaplis(List<Bean.DataBean> adapl);
    }

    public void setMyCallBe(MyCallBe myCallBe) {
        this.myCallBe = myCallBe;
    }
}
