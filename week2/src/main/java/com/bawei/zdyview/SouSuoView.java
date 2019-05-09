package com.bawei.zdyview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bawei.week2.R;

/**
 * 作者:今夕何夕
 * 时间:${data}
 * Description:这个是注释
 */
public class SouSuoView extends LinearLayout {
    Context context;
    TextView sou_fanhui,sou_queren;
    EditText sou_edit;
    MyCall myCall;
    public SouSuoView(Context context) {
        super(context);
    }

    public SouSuoView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context=context;
        View view = View.inflate(context, R.layout.sou_main, this);
        //初识化控件
        sou_edit=view.findViewById(R.id.sou_edit);
        sou_fanhui=view.findViewById(R.id.sou_fanhui);
        sou_queren=view.findViewById(R.id.sou_queren);
        //设置确认的点击事件
        sou_queren.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                //获取输入框的内容
                String s = sou_edit.getText().toString();
                myCall.sou(s);
            }
        });
        //设置返回的点击事件
        sou_fanhui.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                myCall.suo(51);
            }
        });
    }

    public SouSuoView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    //设置接口
    public interface MyCall{
        public void sou(String o);
        public void suo(int o);
    }

    public void setMyCall(MyCall myCall) {
        this.myCall = myCall;
    }
}
