package qzy.com.toolslibrary.ui;

import android.widget.TextView;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

import qzy.com.toolslibrary.R;
import qzy.com.toolslibrary.utils.base.BaseActivity;

/**
 * author : quzongyang
 * e-mail : quzongyang@xiaohe.com
 * time   : 2018/09/19
 * desc   :  https://github.com/orhanobut/logger
 * version: 1.0
 */


public class LoggerActivity extends BaseActivity {

    String text = "android:name=\"com.baonahao.weixin.wxapi.WXEntryActivity\"";
    @Override
    protected void onViewCreated() {
        TextView tvText = findViewById(R.id.tvText);

        Logger.addLogAdapter(new AndroidLogAdapter());
        Logger.d("hello");
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_logger;
    }
}
