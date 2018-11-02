package qzy.com.toolslibrary.ui;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import qzy.com.toolslibrary.R;
import qzy.com.toolslibrary.utils.base.BaseActivity;

/**
 * 作者：quzongyang
 *
 * 创建时间：2018/5/24
 *
 * 类描述：图片(裁剪压缩)、视频(录制)、音频选择器
 *
 * github：https://github.com/LuckSiege/PictureSelector
 */

public class PicetureMainActivity extends BaseActivity implements View.OnClickListener {
    private Button btn_activity, btn_fragment;

    @Override
    protected void onViewCreated() {
        btn_activity = (Button) findViewById(R.id.btn_activity);
        btn_fragment = (Button) findViewById(R.id.btn_fragment);
        btn_activity.setOnClickListener(this);
        btn_fragment.setOnClickListener(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_other;
    }


    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.btn_activity:
                intent = new Intent(PicetureMainActivity.this, PhotoSelectActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_fragment:
                intent = new Intent(PicetureMainActivity.this, PhotoFragmentActivity.class);
                startActivity(intent);
                break;
        }
    }
}
