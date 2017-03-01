package com.example.administrator.wit_live;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.administrator.wit_live.Adapter.MainAdapter;
import com.example.administrator.wit_live.activitys.ZhiBoMineActivity;
import com.example.administrator.wit_live.main_fragment.main_main_activity;
import com.example.administrator.wit_live.main_fragment.main_my_Activity;
import com.tencent.rtmp.TXLivePusher;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private List<Fragment> mList;
    private ViewPager mPager;
    private MainAdapter madapter;
    private RadioGroup mRadioGroup;
    private RadioButton tab1, tab2;

    private main_main_activity mMain_main_activity;
    private main_my_Activity mMain_my_activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initview();

        LoadTXZBoVrsion();


        Intent intent = new Intent(this, ZhiBoMineActivity.class);
        startActivity(intent);
    }

    private void LoadTXZBoVrsion() {

        int[] sdkver = TXLivePusher.getSDKVersion();
        if (sdkver != null && sdkver.length >= 3) {
            Log.d(TAG,"rtmp sdk version is:" + sdkver[0] + "." + sdkver[1] + "." + sdkver[2]);
        }

    }

    private void initview() {
        mPager = (ViewPager) findViewById(R.id.main_viewpager);
        mPager.setOffscreenPageLimit(2);
        mList = getData();
        madapter = new MainAdapter(getSupportFragmentManager(), mList);
        mPager.setAdapter(madapter);
        mPager.addOnPageChangeListener(new TabOnPageChangeListener());

        mRadioGroup = (RadioGroup) findViewById(R.id.radio2);

        tab1 = (RadioButton) findViewById(R.id.main_radio_btn_1);
        tab2 = (RadioButton) findViewById(R.id.main_radio_btn_2);

        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.main_radio_btn_1:
                        mPager.setCurrentItem(0);
                        break;
                    case R.id.main_radio_btn_2:
                        mPager.setCurrentItem(1);
                        break;
                }
            }
        });


    }

    public List<Fragment> getData() {
        mList = new ArrayList<Fragment>();
        mMain_main_activity = new main_main_activity();
        mList.add(mMain_main_activity);
        mMain_my_activity = new main_my_Activity();
        mList.add(mMain_my_activity);

        return mList;
    }

    public class TabOnPageChangeListener implements ViewPager.OnPageChangeListener {

        //当滑动状态改变时调用
        public void onPageScrollStateChanged(int state) {

        }

        //当前页面被滑动时调用
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        //当新的页面被选中时调用
        public void onPageSelected(int position) {
            switch (position) {
                case 0:
                    tab1.setChecked(true);

                    break;
                case 1:
                    tab2.setChecked(true);

                    break;

            }
        }
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exitBy2Click(); //调用双击退出函数
        }
        return false;
    }

    /**
     * 双击退出函数
     */
    private static Boolean isExit = false;

    private void exitBy2Click() {
        Timer tExit = null;
        if (isExit == false) {
            isExit = true; // 准备退出
            Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
            tExit = new Timer();
            tExit.schedule(new TimerTask() {
                @Override
                public void run() {
                    isExit = false; // 取消退出
                }
            }, 2000); // 如果2秒钟内没有按下返回键，则启动定时器取消掉刚才执行的任务

        } else {
            finish();
            System.exit(0);
        }
    }
}
