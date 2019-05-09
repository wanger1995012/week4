package com.bawei.zdyview;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bawei.fragment.ShowFragment;
import com.bawei.week1.MainActivity;
import com.bawei.week1.R;

/**
 * 作者:今夕何夕
 * 时间:${data}
 * Description:这个是注释
 */
public class SouSuoView extends LinearLayout {
    Context context;
    TextView text_fanhui,text_quere;
    EditText editText;
    MyCall myCall;
    public SouSuoView(Context context) {
        super(context);
    }

    public SouSuoView(final Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context=context;
        //加载布局
        View view = View.inflate(context, R.layout.sousuo_item, this);
        //初识化控件
        text_fanhui=view.findViewById(R.id.text_fanhui);
        text_quere=view.findViewById(R.id.text_queren);
        editText=view.findViewById(R.id.edit_id);
        //设置点击事件
        text_quere.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                String string = editText.getText().toString();
                //将数据传到接口中
                myCall.soued(string);
            }
        });
        //设置返回点击事件
        text_fanhui.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"返回",Toast.LENGTH_SHORT).show();
            }
        });

    }

    public SouSuoView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    //定义一个接口
    public interface MyCall{
        public void soued(String sousuo);
    }

    public void setMyCall(MyCall myCall) {
        this.myCall = myCall;
    }
}
