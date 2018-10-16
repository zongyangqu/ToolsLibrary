package qzy.com.utilslib.base;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.noober.background.BackgroundLibrary;

import butterknife.ButterKnife;

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
        setContentView(getLayoutId());
        ButterKnife.bind(this);
        onViewCreated();
    }

    protected abstract void onViewCreated();

    @LayoutRes
    protected abstract int getLayoutId();

    public AppCompatActivity getActivity(){
        return this;
    }
}
