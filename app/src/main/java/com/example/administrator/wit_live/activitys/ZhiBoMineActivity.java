package com.example.administrator.wit_live.activitys;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.administrator.wit_live.R;
import com.tencent.rtmp.TXLivePushConfig;
import com.tencent.rtmp.TXLivePusher;
import com.tencent.rtmp.ui.TXCloudVideoView;

/**
 * @
 * @类名称: ${}
 * @类描述: ${type_name}
 * @创建人： liuyanpeng07
 * @创建时间：${date} ${time}
 * @备注：
 */

public class ZhiBoMineActivity extends AppCompatActivity {
    private static final String TAG = "ZhiBoMineActivity";

    private TXCloudVideoView video_view;
    private TXLivePushConfig mLivePushConfig;

    public  String rtmpUrl = "rtmp://8076.livepush.myqcloud.com/live/8076_d1e19a5b90?bizid=8076&txSecret=3863862377a618f96da998824e82613e&txTime=58B6EFFF";
    private TXLivePusher mMLivePusher;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.zhibomineactivity);

        initview();

        initvideoview();


    }

    private void initvideoview() {
        mMLivePusher = new TXLivePusher(ZhiBoMineActivity.this);
        mLivePushConfig = new TXLivePushConfig();
        mMLivePusher.setConfig(mLivePushConfig);

        //  kaishi 推流
       mMLivePusher.startPusher(rtmpUrl);



    }

    private void initview() {
        video_view= (TXCloudVideoView) findViewById(R.id.video_view);
    }
}
