package com.inno.wang.activity;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;

import com.inno.wang.R;
import com.inno.wang.fragment.MeFragment;
import com.inno.wang.fragment.WallPagerFragment;
import com.inno.wang.fragment.WeFragment;
import com.inno.wang.utils.StatusBarUtil;
import com.inno.wang.view.smartlayout.v4.v4.FragmentPagerItemAdapter;
import com.inno.wang.view.smartlayout.v4.v4.FragmentPagerItems;
import com.ogaclejapan.smarttablayout.SmartTabLayout;

public class MainActivity extends AppCompatActivity {

    private ViewPager mViewPager;
    private SmartTabLayout mVP_tab;
    private LinearLayout ll_root;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        StatusBarUtil.setTransparent(MainActivity.this);
        init();
    }

    private void init() {
        initView();
    }

    private void initView() {
        initCustomViewPager();
        initStatusBarCampot();
        //重新
        mViewPager.setCurrentItem(0);
    }

    //设置全透渐变状态栏
    private void initStatusBarCampot() {
        ll_root = ((LinearLayout) findViewById(R.id.main_root));
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position==0){
                    ll_root.setBackgroundResource(R.drawable.bg_main);
                    return;
                }else if (position==1){
                    ll_root.setBackgroundResource(R.drawable.bg_2);
                    return;
                }else if (position==2){
                    ll_root.setBackgroundResource(R.drawable.bg_3);
                    return;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void initCustomViewPager() {
        mVP_tab = (SmartTabLayout) findViewById(R.id.viewpagertab);

        mViewPager = (ViewPager) findViewById(R.id.viewpager);

        FragmentPagerItemAdapter mAdapter = new FragmentPagerItemAdapter(
                getSupportFragmentManager(), FragmentPagerItems.with(this)
                .add("WallPager", WallPagerFragment.class)
                .add("WE", WeFragment.class)
                .add("ME", MeFragment.class)
                .create());

        mViewPager.setAdapter(mAdapter);

        mVP_tab.setViewPager(mViewPager);
    }

}
