package com.bawei.zdyview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bawei.week2.R;

/**
 * 作者:今夕何夕
 * 时间:${data}
 * Description:这个是注释
 */
public class JiaJianView extends LinearLayout {
    Context context;
    TextView text_num,text_jia,text_jian;
    int num=0;
    MyCall myCall;
    public JiaJianView(Context context) {
        super(context);
    }

    public JiaJianView(final Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context=context;
        View view = View.inflate(context, R.layout.jiajian_item, this);
        text_jian=view.findViewById(R.id.text_jian);
        text_num=view.findViewById(R.id.text_num);
        text_jia=view.findViewById(R.id.text_jia);
        //设置加
        text_jia.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                num++;
                myCall.jia(num);
                text_num.setText(num+"");
            }
        });
        //设置减
        text_jian.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (num==0){
                    Toast.makeText(context,"不能再减了",Toast.LENGTH_SHORT).show();
                    num=0;
                    myCall.jia(num);
                    text_num.setText(num+"");
                }else {
                    num--;
                    myCall.jia(num);
                    text_num.setText(num+"");
                }
            }
        });
    }

    public JiaJianView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    //设置方法拿到数量
    public void getData(int nums){
        this.num=nums;
        text_num.setText(num+"");
    }
    //设置接口返回
    public interface MyCall{
        public void jia(int nums);
    }

    public void setMyCall(MyCall myCall) {
        this.myCall = myCall;
    }
}
