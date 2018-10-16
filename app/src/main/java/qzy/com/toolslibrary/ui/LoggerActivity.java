package qzy.com.toolslibrary.ui;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

import qzy.com.toolslibrary.R;
import qzy.com.utilslib.base.BaseActivity;

/**
 * author : quzongyang
 * e-mail : quzongyang@xiaohe.com
 * time   : 2018/09/19
 * desc   :  https://github.com/orhanobut/logger
 * version: 1.0
 */


public class LoggerActivity extends BaseActivity {
    @Override
    protected void onViewCreated() {
        Logger.addLogAdapter(new AndroidLogAdapter());
        Logger.d("hello");
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_logger;
    }
}
