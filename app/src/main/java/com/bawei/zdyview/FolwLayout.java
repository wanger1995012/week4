package com.bawei.zdyview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者:今夕何夕
 * 时间:${data}
 * Description:这个是注释
 */
public class FolwLayout extends ViewGroup {
    Context context;
    List<Integer> list=new ArrayList<>();
    public FolwLayout(Context context) {
        super(context);
    }

    public FolwLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context=context;
    }

    public FolwLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    int meLeft=20;//定义左边距
    int meRaght=20;//定义右边距
    int tmTop=20;//定义高
    int view_W=0;//标识本view宽
    int view_H=0;//标识本view的高
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //设置测量模式
        measureChildren(widthMeasureSpec,heightMeasureSpec);

        //先获取到父布局传过来的宽的测量模式
        int mod_W=MeasureSpec.getMode(widthMeasureSpec);
        //先获取到父布局传过来的高的测量宽度
        int mod_gao=MeasureSpec.getMode(heightMeasureSpec);
        //获取到父布局传过来的测量宽度
        int Measure_W=MeasureSpec.getSize(widthMeasureSpec);
        //获取父布局传过来的测量高度
        int Measure_h=MeasureSpec.getSize(heightMeasureSpec);

        //判断测量模式
        if (mod_W==MeasureSpec.AT_MOST){
            int chid_h=0;
            //循环设置宽高
            for (int i = 0; i < getChildCount(); i++) {
                //先拿到每个孩子的宽高
                int chid_w = getChildAt(i).getWidth();
                chid_h = getChildAt(i).getHeight();

                //判断
                if (view_W>getMeasuredWidth()){
                    //一旦换行先记录行宽
                    list.add(view_W);
                    //换行
                    view_W=0;
                    //行高要累加 并且只累加上边距，不累加下边距
                    view_H+=tmTop+chid_h;
                }

                //行宽要累加
                view_W+=meLeft+chid_w;

            }
            //在累加最后一行的高度
            view_H+=tmTop+chid_h;

        }

        setMeasuredDimension(view_W,view_H);

    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

        //行宽

        int view_W=0;
        int chid_h=0;
        //循环设置宽高
        for (int i = 0; i < getChildCount(); i++) {
            //先拿到每个孩子的宽高
            int chid_w = getChildAt(i).getWidth();
            chid_h = getChildAt(i).getHeight();

            //判断
            if (view_W>getWidth()){
                //换行
                view_W=0;
                //行高要累加 并且只累加上边距，不累加下边距
                view_H+=tmTop+chid_h;
                //开始布局,左上右下
                getChildAt(i).layout(view_W,view_H,view_W+chid_w,view_H+chid_h);
            }

            getChildAt(i).layout(view_W,view_H,view_W+chid_w,view_H+chid_h);
            //行宽要累加
            view_W+=meLeft+chid_w;

        }

    }
}
