package com.bawei.zdyview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bawei.week1.R;

/**
 * 作者:今夕何夕
 * 时间:${data}
 * Description:这个是注释
 */
public class JiaJianView extends LinearLayout {
    Context context;
    TextView text_jia,text_jian,text_num;
    int nums=0;
    MyCall myCall;
    public JiaJianView(Context context) {
        super(context);
    }

    public JiaJianView(final Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context=context;
        View view = View.inflate(context, R.layout.jiajian_item, this);
        text_jia=view.findViewById(R.id.text_jia);
        text_jian=view.findViewById(R.id.text_jian);
        text_num=view.findViewById(R.id.text_num);
        //设置加的点击事件
        text_jia.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                nums++;
                text_num.setText(nums+"");
                myCall.jiajian(nums);
            }
        });
        //设置减的点击事件
        text_jian.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (nums==0){
                    Toast.makeText(context,"不能再减了",Toast.LENGTH_SHORT).show();
                    nums=0;
                    text_num.setText(nums+"");
                    myCall.jiajian(nums);
                }else {
                    nums--;
                    text_num.setText(nums+"");
                    myCall.jiajian(nums);
                }
            }
        });
    }

    public JiaJianView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    //写入方法获取数量
    public void getnums(int nums){
        this.nums=nums;
        text_num.setText(nums+"");
    }
    //设置接口
    public interface MyCall{
        public void jiajian(int num);
    }

    public void setMyCall(MyCall myCall) {
        this.myCall = myCall;
    }
}
