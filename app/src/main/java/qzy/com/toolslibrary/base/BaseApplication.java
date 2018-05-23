package qzy.com.toolslibrary.base;

import android.app.Application;
import android.content.Context;

/**
 * 作者：quzongyang
 *
 * 创建时间：2018/2/5
 *
 * 类描述：
 */

public class BaseApplication extends Application{

    private static Application appContext;

    @Override
    public void onCreate() {
        super.onCreate();
        appContext = this;
    }

    public static Context getContext() {
        return appContext;
    }
}
