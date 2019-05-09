package com.bawei.week1;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.bawei.adapter.MyPagerFragmentAdapter;
import com.bawei.fragment.MyFragment;
import com.bawei.fragment.ShowFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.viewpager_id)
    ViewPager viewpager;
    @BindView(R.id.tablayut_id)
    TabLayout tablayut;
    String[] titles={"首页","个人"};
    List<Fragment> list=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        list.add(new ShowFragment());
        list.add(new MyFragment());
        //设置适配器
        MyPagerFragmentAdapter myPagerFragmentAdapter=new MyPagerFragmentAdapter(getSupportFragmentManager(),titles,list);
        viewpager.setAdapter(myPagerFragmentAdapter);
        tablayut.setupWithViewPager(viewpager);
    }
}
