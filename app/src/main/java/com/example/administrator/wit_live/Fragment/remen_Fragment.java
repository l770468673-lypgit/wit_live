package com.example.administrator.wit_live.Fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.administrator.wit_live.R;
import com.example.administrator.wit_live.viewpager.NewViewPager;
import com.example.administrator.wit_live.viewpager.imgBean;
import com.example.administrator.wit_live.viewpager.lunboAdapter;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;


public class remen_Fragment extends Fragment {
    public static Fragment newInstance() {

        Bundle args = new Bundle();
        remen_Fragment fragment = new remen_Fragment();
        fragment.setArguments(args);
        return fragment;
    }
    private NewViewPager mViewPager;
    private lunboAdapter mAdapter;
    public boolean isRun = false;
    public boolean isDown = false;
    private static final int SLEEPTIME = 3000;
    private LinearLayout mBottomLayout;
    private ImageView imgCur;
    private imgBean mImgBean;
    private Handler mHandler;
    private List<imgBean.imgs> mList = new ArrayList<>();
    private RelativeLayout hei;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_remen_, container, false);

        initLunbo(view);

        return view;
    }


    private void initLunbo(final View view) {

        hei = (RelativeLayout)view.findViewById(R.id.lunbo_r);

        DisplayMetrics dm2 = getResources().getDisplayMetrics();

        System.out.println("heigth2 : " + dm2.heightPixels);

        System.out.println("width2 : " + dm2.widthPixels);

        Log.d("hei", String.valueOf(dm2.heightPixels));

        LinearLayout.LayoutParams linearParams = (LinearLayout.LayoutParams) hei.getLayoutParams(); // 取控件mGrid当前的布局参数
        linearParams.height = dm2.heightPixels/3;// 当控件的高强制设成75象素
        hei.setLayoutParams(linearParams); // 使设置好的布局参数应用到控件mGrid2


        OkHttpUtils.get().url("http://bobtrip.com/tripcal/api/advert/list.action").build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e) {

            }

            @Override
            public void onResponse(String response) {
                if (!TextUtils.isEmpty(response)) {
                    Gson gson = new Gson();
                    mImgBean = gson.fromJson(response, imgBean.class);
                    mList = mImgBean.getlist();
                }
                setViewPager(view);
            }
        });

    }


    private void setViewPager(View view) {
        mHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);

                switch (msg.what) {
                    case 0:
                        mViewPager.setCurrentItem(mViewPager.getCurrentItem() + 1, true);
                        if (isRun && !isDown) {
                            this.sendEmptyMessageDelayed(0, SLEEPTIME);
                        }
                        break;

                    case 1:
                        if (isRun && !isDown) {
                            this.sendEmptyMessageDelayed(0, SLEEPTIME);
                        }
                }
            }
        };
        isRun = true;
        mHandler.sendEmptyMessageDelayed(0, SLEEPTIME);

        mViewPager = (NewViewPager) view.findViewById(R.id.main_lunbo);
        mBottomLayout = (LinearLayout) view.findViewById(R.id.ll_points);
        mAdapter = new lunboAdapter(new remen_Fragment.MyLoopViewPagerAdatper());
        mViewPager.setOnPageChangeListener(new remen_Fragment.MyOnPageChangeListener());
        mViewPager.setInfinateAdapter(this, mHandler, mAdapter);
        setFaceCurPage(0);


    }

    private class MyLoopViewPagerAdatper extends PagerAdapter {

        @Override
        public int getCount() {
            return mList.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == (View) object;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @SuppressWarnings("unused")
        @Override
        public Object instantiateItem(ViewGroup container, final int position) {
            ImageView imageView = new ImageView(getContext());
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            String url = mList.get(position).getAdImg();
            if (!TextUtils.isEmpty(url)) {
                Glide.with(remen_Fragment.this).load(url).centerCrop().diskCacheStrategy(DiskCacheStrategy.ALL).placeholder(R.mipmap.lunbo).crossFade().into(imageView);
            }
            container.addView(imageView);

//            imageView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Intent mIntent = new Intent(getContext(), Viewxiangqing.class);
//                    Bundle bundle = new Bundle();
//                    bundle.putString("id", mList.get(position).getLinkUrl());
//                    mIntent.putExtras(bundle);
//                    startActivity(mIntent);
//                }
//            });
            return imageView;
        }


    }

    public void setFaceCurPage(int page) {
        mBottomLayout.removeAllViews();
        page = page % mList.size();
        for (int i = 0, m = mList.size(); i < m; i++) {
            imgCur = new ImageView(getContext());
            imgCur.setBackgroundResource(R.mipmap.dain);
            if (page != i) {
                imgCur.setBackgroundResource(R.mipmap.dian2);
            }
//            Looper looper = Looper.getMainLooper();
//            looper.loop();
            mBottomLayout.addView(imgCur);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(imgCur.getLayoutParams());
            lp.setMargins(10, 5, 5, 10);
            lp.width = 20;
            lp.height = 20;
            imgCur.setLayoutParams(lp);
        }
    }

    private class MyOnPageChangeListener implements ViewPager.OnPageChangeListener {
        /**
         * Indicates that the pager is in an idle, settled state. The current
         * page is fully in view and no animation is in progress.
         */
        public static final int SCROLL_STATE_IDLE = 0;

        /**
         * Indicates that the pager is currently being dragged by the user.
         */

        public static final int SCROLL_STATE_DRAGGING = 1;

        /**
         * Indicates that the pager is in the process of settling to a final
         * position.
         */
        public static final int SCROLL_STATE_SETTLING = 2;

        @Override
        public void onPageScrollStateChanged(int state) {
            switch (state) {
                case SCROLL_STATE_IDLE:
                    break;
                case SCROLL_STATE_DRAGGING:
                    break;
                case SCROLL_STATE_SETTLING:
                    break;
            }

        }

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        }

        @Override
        public void onPageSelected(int position) {
            Log.e("", "" + position);
            setFaceCurPage(position);
        }
    }



}
