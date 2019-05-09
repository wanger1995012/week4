package com.bawei.zdyview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bawei.week4.R;

/**
 * 作者:今夕何夕
 * 时间:${data}
 * Description:这个是注释
 */
public class JianJiaView extends LinearLayout {
    Context context;
    TextView text_num,text_jia,text_jian;
    int nums=0;
    MyCall myCall;
    public JianJiaView(Context context) {
        super(context);
    }

    public JianJiaView(final Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context=context;
        View view = View.inflate(context, R.layout.jianjian_item, this);
        text_num=view.findViewById(R.id.text_num);
        text_jia=view.findViewById(R.id.text_jia);
        text_jian=view.findViewById(R.id.text_jian);
        //设置加
        text_jia.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                nums++;
                text_num.setText(nums+"");
                myCall.jiajian(nums);
            }
        });
        //设置减
        text_jian.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (nums==1){
                    Toast.makeText(context,"不能再减了",Toast.LENGTH_SHORT).show();
                    nums=1;
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

    public JianJiaView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    //设置接口
    public interface MyCall{
        public void jiajian(int nums);
    }

    public void setMyCall(MyCall myCall) {
        this.myCall = myCall;
    }
    //设置数据
    public void setnum(int numss){
        this.nums=numss;
        text_num.setText(nums+"");
    }
}
