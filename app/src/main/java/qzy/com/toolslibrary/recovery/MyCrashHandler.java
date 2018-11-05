package qzy.com.toolslibrary.recovery;

import android.util.Log;

/**
 * author : quzongyang
 * e-mail : quzongyang@xiaohe.com
 * time   : 2018/11/05
 * desc   :
 * version: 1.0
 */

public class MyCrashHandler implements Thread.UncaughtExceptionHandler {

    private Thread.UncaughtExceptionHandler mUncaughtExceptionHandler;

    public MyCrashHandler() {
        mUncaughtExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
    }

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        Log.e("zxy", "myCrashHandler...");
        mUncaughtExceptionHandler.uncaughtException(t, e);
    }


    public static void register() {
        Thread.setDefaultUncaughtExceptionHandler(new MyCrashHandler());
    }
}
