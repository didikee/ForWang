package com.inno.wang.fragment;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.inno.wang.R;
import com.inno.wang.utils.BitmapUtil;
import com.inno.wang.view.smartlayout.v4.ViewPagerItemAdapter;
import com.inno.wang.view.smartlayout.v4.ViewPagerItems;
import com.ogaclejapan.smarttablayout.SmartTabLayout;

public class WallPagerFragment extends Fragment {

    private ViewPagerItemAdapter mAdapter;
    private Button mButton_singleScreen;
    private Button mButton_multiScreen;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View rooView=inflater.inflate(R.layout.fragment_wallpager, container, false);
//        StatusBarUtil.setTransparent(getActivity());
//        StatusBarCompat.translucentStatusBar(getActivity());
        init(rooView);
        return rooView;
    }

    //init this fragment
    private void init(View rootView) {
        initView(rootView);
        initClick2ChangeWallPager(rootView);
    }

    private void initClick2ChangeWallPager(View rootView) {
        mButton_singleScreen = ((Button) rootView.findViewById(R.id.wallpager_one));
        mButton_multiScreen = ((Button) rootView.findViewById(R.id.wallpager_two));

        mButton_singleScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(),"这年头还有谁用单屏壁纸啊喂~~o(￣ヘ￣o＃)",Toast.LENGTH_SHORT).show();
            }
        });

        mButton_multiScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setupMultiScreenWallPager();
            }
        });


    }

    //设置壁纸
    private void setupMultiScreenWallPager() {

    }

    private void initView(View rootView) {
        ViewPager mViewpager=((ViewPager) rootView.findViewById(R.id.viewpager));
        SmartTabLayout mVPLayout=((SmartTabLayout) rootView.findViewById(R.id.viewpagertab));

        mAdapter = new ViewPagerItemAdapter(
                ViewPagerItems.with(getActivity())
                        .add("星期一", R.layout.mg)
                        .add("星期二",R.layout.mg)
                        .add("星期三",R.layout.mg)
                        .add("星期四",R.layout.mg)
                        .add("星期五",R.layout.mg)
                        .add("星期六",R.layout.mg)
                        .add("星期日",R.layout.mg).create()
        ){
            //动态的改变壁纸
            @Override
            public void setBitmap(View view, int position) {
                switch (position){
                    case 0:
                        Drawable temp=getResources().getDrawable(R.drawable.w);
                        Bitmap bitmap_1=convertDrawable2BitmapByCanvas(temp);

                        Bitmap bitmap=BitmapUtil.scaleImage(bitmap_1,0.33f,0.33f);
//                        ((ImageView)view).setBackgroundResource(R.drawable.w);
                        ((ImageView)view).setImageBitmap(bitmap);
                        break;
                }
            }
        };



        mViewpager.setAdapter(mAdapter);
        mVPLayout.setViewPager(mViewpager);

        mViewpager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    public static Bitmap convertDrawable2BitmapByCanvas(Drawable drawable) {
        Bitmap bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(),drawable.getIntrinsicHeight(),
                drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888: Bitmap.Config.RGB_565);
        Canvas canvas = new Canvas(bitmap);
        // canvas.setBitmap(bitmap);
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(),
                drawable.getIntrinsicHeight());
        drawable.draw(canvas);
        return bitmap;
    }


}
