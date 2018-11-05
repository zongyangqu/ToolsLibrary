package qzy.com.toolslibrary.base;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDexApplication;
import android.util.Log;

import com.zxy.recovery.callback.RecoveryCallback;
import com.zxy.recovery.core.Recovery;

import qzy.com.toolslibrary.MainActivity;
import qzy.com.toolslibrary.recovery.MyCrashHandler;

/**
 * 作者：quzongyang
 *
 * 创建时间：2018/2/5
 *
 * 类描述：https://github.com/Sunzxyong/Recovery
 */

public class BaseApplication extends MultiDexApplication {

    private static Application appContext;

    @Override
    public void onCreate() {
        super.onCreate();
        appContext = this;
        initRecovery();
    }

    public static Context getContext() {
        return appContext;
    }

    public static void initRecovery(){
        Recovery.getInstance()
                .debug(true)
                .recoverInBackground(false)
                .recoverStack(true)
                .mainPage(MainActivity.class)
                .recoverEnabled(false)
                .callback(new MyCrashCallback())
                .silent(false, Recovery.SilentMode.RECOVER_ACTIVITY_STACK)
                //.skip(TestActivity.class)
                .init(appContext);
        MyCrashHandler.register();
    }


    static final class MyCrashCallback implements RecoveryCallback {
        @Override
        public void stackTrace(String exceptionMessage) {
            Log.e("zxy", "exceptionMessage:" + exceptionMessage);
        }
        @Override
        public void cause(String cause) {
            Log.e("zxy", "cause:" + cause);
        }
        @Override
        public void exception(String exceptionType, String throwClassName, String throwMethodName, int throwLineNumber) {
            Log.e("zxy", "exceptionClassName:" + exceptionType);
            Log.e("zxy", "throwClassName:" + throwClassName);
            Log.e("zxy", "throwMethodName:" + throwMethodName);
            Log.e("zxy", "throwLineNumber:" + throwLineNumber);
        }
        @Override
        public void throwable(Throwable throwable) {
        }
    }
}
