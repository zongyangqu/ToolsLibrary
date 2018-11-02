package qzy.com.toolslibrary.ui;

import android.Manifest;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import com.tbruyelle.rxpermissions.RxPermissions;

import java.util.ArrayList;
import java.util.List;
import java.util.Observer;

import qzy.com.toolslibrary.R;
import qzy.com.toolslibrary.adapter.EaseMessageAdapter;
import qzy.com.toolslibrary.bean.MessageBean;
import qzy.com.toolslibrary.service.PlayService;
import qzy.com.toolslibrary.utils.AppCache;
import qzy.com.toolslibrary.utils.TimeUtils;
import qzy.com.toolslibrary.utils.base.BaseActivity;
import qzy.com.utilslib.voicerecorder.widget.VoiceRecorderView;

/**
 * 作者：quzongyang
 *
 * 创建时间：2018/2/5
 *
 * 类描述：仿微信录制语音
 *
 * github：https://github.com/wangshanhai/VoiceRecorder
 */

public class RecordAudioActivity extends BaseActivity {


    protected VoiceRecorderView voiceRecorderView;
    protected ListView message_list;
    protected TextView tvRecorder;

    private static final int MY_PERMISSIONS_RECORD = 100;
//    private static final int MY_PERMISSIONS_RECORD_AUDIO = 101;
//    private static final int MY_PERMISSIONS_READ_EXTERNAL_STORAGE = 102;
//    private static final int MY_PERMISSIONS_WRITE_EXTERNAL_STORAGE = 103;
//    private static final int MY_PERMISSIONS_READ_PHONE_STATE = 104;
//    private static final int MY_PERMISSIONS_MOUNT_UNMOUNT_FILESYSTEMS = 105;
    private List<MessageBean> voices;
    EaseMessageAdapter adapter;
    PlayServiceConnection mPlayServiceConnection;

    @Override
    protected void onViewCreated() {
        voices = new ArrayList<MessageBean>();
        initView();
        getPermissions();
        /*ActivityCompat.requestPermissions(RecordAudioActivity.this,
                new String[]{Manifest.permission.RECORD_AUDIO,Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE
                        ,Manifest.permission.READ_PHONE_STATE,Manifest.permission.MOUNT_UNMOUNT_FILESYSTEMS},
                MY_PERMISSIONS_RECORD);*/
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_record_audio;
    }



    private void initView() {
        Intent intent = new Intent();
        intent.setClass(this, PlayService.class);
        mPlayServiceConnection = new PlayServiceConnection();
        bindService(intent, mPlayServiceConnection, Context.BIND_AUTO_CREATE);
        message_list = (ListView) findViewById(R.id.message_list);
        // hold to record voice
        //noinspection ConstantConditions
        voiceRecorderView = (VoiceRecorderView) findViewById(R.id.voice_recorder);
/*        voiceRecorderView.setDrawableAnimation(new Drawable[]{
                getResources().getDrawable(com.ilike.voicerecorder.R.drawable.ease_record_animate_01),
                getResources().getDrawable(com.ilike.voicerecorder.R.drawable.ease_record_animate_02),
                getResources().getDrawable(com.ilike.voicerecorder.R.drawable.ease_record_animate_03),
                getResources().getDrawable(com.ilike.voicerecorder.R.drawable.ease_record_animate_04),
                getResources().getDrawable(com.ilike.voicerecorder.R.drawable.ease_record_animate_05),
                getResources().getDrawable(com.ilike.voicerecorder.R.drawable.ease_record_animate_06),
                getResources().getDrawable(com.ilike.voicerecorder.R.drawable.ease_record_animate_07),
                getResources().getDrawable(com.ilike.voicerecorder.R.drawable.ease_record_animate_08),
                getResources().getDrawable(com.ilike.voicerecorder.R.drawable.ease_record_animate_09),
                getResources().getDrawable(com.ilike.voicerecorder.R.drawable.ease_record_animate_10),
                getResources().getDrawable(com.ilike.voicerecorder.R.drawable.ease_record_animate_11),
                getResources().getDrawable(com.ilike.voicerecorder.R.drawable.ease_record_animate_12),
                getResources().getDrawable(com.ilike.voicerecorder.R.drawable.ease_record_animate_13),
                getResources().getDrawable(com.ilike.voicerecorder.R.drawable.ease_record_animate_14),});*/
        //voiceRecorderView.setCustomNamingFile(true, "语音命名");
        //PathUtil.getInstance().initDirs("voice", this);

        voiceRecorderView.setShowMoveUpToCancelHint("松开手指，取消发送");
        voiceRecorderView.setShowReleaseToCancelHint("手指上滑，取消发送");
        tvRecorder = (TextView) findViewById(R.id.tv_touch_recorder);
        tvRecorder.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    if (AppCache.getPlayService().isPlaying) {
                        AppCache.getPlayService().stopPlayVoiceAnimation2();
                    }
                }

                return voiceRecorderView.onPressToSpeakBtnTouch(v, event, new VoiceRecorderView.EaseVoiceRecorderCallback() {
                    @Override
                    public void onVoiceRecordComplete(String voiceFilePath, int voiceTimeLength) {
                        Log.e("voiceFilePath=", voiceFilePath + "  time = " + voiceTimeLength);
                        //   sendVoiceMessage(voiceFilePath, voiceTimeLength);
                        MessageBean bean = new MessageBean();
                        bean.path = voiceFilePath;
                        bean.msg = "image";
                        bean.second = voiceTimeLength;
                        bean.time = TimeUtils.getCurrentTimeInLong();
                        voices.add(bean);
                        adapter.notifyDataSetChanged();
                    }
                });
            }
        });


        adapter = new EaseMessageAdapter(this, voices);
        message_list.setAdapter(adapter);
        adapter.setOnItemClickLister(new EaseMessageAdapter.onItemClickLister() {
            @Override
            public void onItemClick(ImageView imageView, String path, int position) {
                //播放语音
                //  VoicePlayClickListener voicePlayClickListener = new VoicePlayClickListener(imageView, TestVoiceActivity.this);
               /* voicePlayClickListener.setStopPlayIcon(R.drawable.ease_chatto_voice_playing);
                voicePlayClickListener.setPlayingIconDrawableResoure(R.drawable.voice_to_icon);*/
                //   voicePlayClickListener.playVoice(path);

                // new VoicePlayClickListener(imageView, TestVoiceActivity.this).playUrlVoice("http://img.layuva.com//b96c4bde124a328d9c6edb5b7d51afb2.amr");


                if (AppCache.getPlayService() != null) {
                    AppCache.getPlayService().setImageView(imageView);
                    AppCache.getPlayService().stopPlayVoiceAnimation();
                    //  AppCache.getPlayService().play("http://img.layuva.com//b96c4bde124a328d9c6edb5b7d51afb2.amr");
                    AppCache.getPlayService().play(path);
                }
            }
        });

    }


    private class PlayServiceConnection implements ServiceConnection {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            final PlayService playService = ((PlayService.PlayBinder) service).getService();
            Log.e("onServiceConnected----", "onServiceConnected");
            AppCache.setPlayService(playService);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
        }
    }

    //运行授权，6.0以上系统需要
    private void getPermissions() {
        RxPermissions rxPermissions = new RxPermissions(RecordAudioActivity.this);
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
                    Toast.makeText(RecordAudioActivity.this, "同意权限", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(RecordAudioActivity.this, "拒绝权限", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}
