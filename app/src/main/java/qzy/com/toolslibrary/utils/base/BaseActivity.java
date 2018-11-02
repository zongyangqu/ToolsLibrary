package qzy.com.toolslibrary.utils.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.noober.background.BackgroundLibrary;

import butterknife.ButterKnife;
import qzy.com.toolslibrary.MainActivity;
import qzy.com.utilslib.appreset.AppStatusConstant;
import qzy.com.utilslib.appreset.AppStatusManager;

/**
 * 作者：quzongyang
 *
 * 创建时间：2018/2/1
 *
 * 类描述：基类Activity
 */

public abstract class BaseActivity extends AppCompatActivity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        BackgroundLibrary.inject(this);
        super.onCreate(savedInstanceState);

        switch (AppStatusManager.getInstance().getAppStatus()) {
            /**
             * 应用被强杀
             */
            case AppStatusConstant.STATUS_FORCE_KILLED:
                //跳到主页,主页lauchmode SINGLETASK
                protectApp();
                break;
            /**
             * 用户被踢或者TOKEN失效
             */
            case AppStatusConstant.STATUS_KICK_OUT:
                //弹出对话框,点击之后跳到主页,清除用户信息,运行退出登录逻辑
//                Intent intent=new Intent(this,MainActivity.class);
//                startActivity(intent);
                break;
            case AppStatusConstant.STATUS_NORMAL:
                setContentView(getLayoutId());
                ButterKnife.bind(this);
                onViewCreated();
                break;
        }
    }


    protected void protectApp() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra(AppStatusConstant.KEY_HOME_ACTION, AppStatusConstant.ACTION_RESTART_APP);
        startActivity(intent);
    }

    protected abstract void onViewCreated();

    @LayoutRes
    protected abstract int getLayoutId();

    public AppCompatActivity getActivity(){
        return this;
    }
}
