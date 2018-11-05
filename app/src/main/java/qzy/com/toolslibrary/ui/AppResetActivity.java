package qzy.com.toolslibrary.ui;

import android.widget.TextView;

import qzy.com.toolslibrary.MainActivity;
import qzy.com.toolslibrary.R;
import qzy.com.toolslibrary.utils.base.BaseActivity;

/**
 * author : quzongyang
 * e-mail : quzongyang@xiaohe.com
 * time   : 2018/11/02
 * desc   : Android 内存被回收后直接重启APP 测试类    https://www.2cto.com/kf/201606/513992.html
 *
 * 新建了一个App状态常量类(AppStatusConstant)用于方便统一参数，同时新建了一个AppStatus单例类，这个单例类中定义了一个int类型的参数，
 * 参数默认值为-1，也就是默认为进程关闭的状态，而这个值是什么时候被改变的呢，很简单在SpalshActivity的onCreate方法被改变就可以啦！
 * 在SpalshActivity中改成正常态2即可。
 *
 * 当我们的停留在某个页面的时候应用被回收统一跳转到MainActivity即可，因为MainActivity为SINGLETASK启动模式，
 * 所以此时Activity栈中就只剩MainActivity一个Activity，在MainActivity中finish掉自己的同时在跳转SplashActivity即可完成，
 * 这样也就强制APP重新走了启动流程，为了统一管理，我们新建一个BaseActivity，其他Activty继承这个BaseActivity
 *
 * 我们在SpalshActivity中将状态改变为正常态，同时在BaseActivity中判断APP状态，只要进程被回收AppStatusManager则会被重新创建，
 * int Status因为没有走SplashActivity所以还是为默认值-1，在BaseActivity的oncreate方法中判断int status后则会走protectApp()这个方法(正常态不会走)，
 * 在MainActivity中重写这个方法跳转到SpalshActivity同时finish自己即可完成整个流程！我们用这个思路也可以处理诸如单点登录下线等问题！
 * version: 1.0
 */


public class AppResetActivity extends BaseActivity {

    @Override
    protected void onViewCreated() {
        TextView tv = findViewById(R.id.textview);
        //内存回收再回到此页面后会报空指针异常
        tv.setText(MainActivity.list.get(0));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_app_reset;
    }
}
