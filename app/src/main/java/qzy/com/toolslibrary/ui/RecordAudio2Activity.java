package qzy.com.toolslibrary.ui;

import android.Manifest;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.tbruyelle.rxpermissions.RxPermissions;

import java.util.ArrayList;
import java.util.List;

import qzy.com.toolslibrary.R;
import qzy.com.toolslibrary.adapter.EaseMessageAdapter;
import qzy.com.toolslibrary.adapter.RecorderAdapter;
import qzy.com.toolslibrary.bean.MessageBean;
import qzy.com.toolslibrary.bean.Recorder;
import qzy.com.toolslibrary.service.PlayService;
import qzy.com.toolslibrary.utils.AppCache;
import qzy.com.toolslibrary.utils.TimeUtils;
import qzy.com.utilslib.base.BaseActivity;
import qzy.com.utilslib.recorder.AudioRecorderButton;
import qzy.com.utilslib.recorder.MediaManager;
import qzy.com.utilslib.voicerecorder.widget.VoiceRecorderView;

/**
 * 作者：quzongyang
 *
 * 创建时间：2018/2/5
 *
 * 类描述：仿微信录制语音
 *
 * csdn：https://blog.csdn.net/lhk147852369/article/details/78658055
 */

public class RecordAudio2Activity extends BaseActivity{


    private ListView mListView;
    private ArrayAdapter<Recorder> mAdapter;
    private List<Recorder> mDatas =new ArrayList<>();
    private static final int MY_PERMISSIONS_RECORD_AUDIO = 101;

    private AudioRecorderButton mAudioRecorderButton;
    private View mAnimView;

    @Override
    protected void onViewCreated() {
        initView();
        setListViewAdapter();
        ActivityCompat.requestPermissions(RecordAudio2Activity.this,
                new String[]{Manifest.permission.RECORD_AUDIO,Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE},
                MY_PERMISSIONS_RECORD_AUDIO);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_record_audio2;
    }



    private void initView(){
        mListView = (ListView) findViewById(R.id.id_listview);
        mAudioRecorderButton = (AudioRecorderButton) findViewById(R.id.id_recorder_button);

        mAudioRecorderButton.setAudioFinishRecorderListener(new AudioRecorderButton.AudioFinishRecorderListener() {
            @Override
            public void onFinish(float seconds, String filePath) {
                //每完成一次录音
                Recorder recorder = new Recorder(seconds,filePath);
                mDatas.add(recorder);
                //更新adapter
                mAdapter.notifyDataSetChanged();
                //设置listview 位置
                mListView.setSelection(mDatas.size()-1);
            }
        });
    }

    private void setListViewAdapter(){
        mAdapter = new RecorderAdapter(this, mDatas);
        mListView.setAdapter(mAdapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //如果第一个动画正在运行， 停止第一个播放其他的
                if (mAnimView != null) {
                    mAnimView.setBackgroundResource(R.drawable.adj);
                    mAnimView = null;
                }
                //播放动画
                mAnimView = view.findViewById(R.id.id_recorder_anim);
                mAnimView.setBackgroundResource(R.drawable.play_anim);
                AnimationDrawable animation = (AnimationDrawable) mAnimView.getBackground();
                animation.start();

                //播放音频  完成后改回原来的background
                MediaManager.playSound(mDatas.get(position).filePath, new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        mAnimView.setBackgroundResource(R.drawable.adj);
                    }
                });
            }
        });
    }

    /**
     * 根据生命周期 管理播放录音
     */
    @Override
    protected void onPause() {
        super.onPause();
        MediaManager.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        MediaManager.resume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        MediaManager.release();
    }


    //运行授权，6.0以上系统需要
    private void getPermissions() {
        RxPermissions rxPermissions = new RxPermissions(RecordAudio2Activity.this);
        // rxPermissions.request(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.RECORD_AUDIO)
        rxPermissions.request(Manifest.permission.RECORD_AUDIO,Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE
        ).subscribe(new rx.Observer<Boolean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Boolean aBoolean) {
                if (aBoolean) {
                    Toast.makeText(RecordAudio2Activity.this, "同意权限", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(RecordAudio2Activity.this, "拒绝权限", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}
