package com.example.mraz.fragtabhostdemo;

import android.annotation.SuppressLint;
import android.support.annotation.DrawableRes;
import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    // 图片
    @DrawableRes
    private int mImages[] = {
            R.drawable.tab_daily,
            R.drawable.tab_sort,
            R.drawable.tab_bonus,
            R.drawable.tab_about
    };

    // 标题
    private String mFragmentTags[] = {
            "每日干货",
            "分类阅读",
            "福利社区",
            "关于作者"
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        FragmentTabHost fragmentTabHost = (FragmentTabHost) this.findViewById(android.R.id.tabhost);
        fragmentTabHost.setup(this,getSupportFragmentManager(), android.R.id.tabcontent);

        for (int i = 0; i < mImages.length; i++) {
            TabHost.TabSpec tabSpec = fragmentTabHost.newTabSpec(mFragmentTags[i]).setIndicator(getImageView(i));
            fragmentTabHost.addTab(tabSpec, YFragment.class, null);
            fragmentTabHost.getTabWidget().getChildAt(i).setBackgroundResource(R.color.colorWhite);
        }

        fragmentTabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String s) {
                Toast.makeText(MainActivity.this, "OnTabChanged To : " + s, Toast.LENGTH_SHORT).show();
            }
        });

    }

    // 获得图片资源
    private View getImageView(int index) {
        @SuppressLint("InflateParams")
        View view = getLayoutInflater().inflate(R.layout.indicator, null);
        ImageView imageView = view.findViewById(R.id.iv_indicator);
        TextView textView = view.findViewById(R.id.tv_indicator);
        imageView.setImageResource(mImages[index]);
        textView.setText(mFragmentTags[index]);
        return view;
    }
}
