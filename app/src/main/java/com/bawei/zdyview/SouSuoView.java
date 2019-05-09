package com.bawei.zdyview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bawei.week4.R;

/**
 * 作者:今夕何夕
 * 时间:${data}
 * Description:这个是注释
 */
public class SouSuoView extends LinearLayout {
    Context context;
    TextView text_fanhui,text_queren;
    EditText edit_text;
    MyCall myCall;
    public SouSuoView(Context context) {
        super(context);
    }

    public SouSuoView(final Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context=context;
        View view = View.inflate(context, R.layout.sousuo_item, this);
        text_fanhui=view.findViewById(R.id.text_fanhui);
        text_queren=view.findViewById(R.id.text_queren);
        edit_text=view.findViewById(R.id.edit_text);
        //设置返回的点击事件
        text_fanhui.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"返回",Toast.LENGTH_SHORT).show();
            }
        });
        //设置确认的点击事件
        text_queren.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = edit_text.getText().toString();
                myCall.sousuo(s);
            }
        });
    }

    public SouSuoView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    //设置接口将数据传输
    public interface MyCall{
        public void sousuo(String tex);
    }

    public void setMyCall(MyCall myCall) {
        this.myCall = myCall;
    }
}
