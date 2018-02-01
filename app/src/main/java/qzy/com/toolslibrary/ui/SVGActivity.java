package qzy.com.toolslibrary.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import butterknife.Bind;
import qzy.com.toolslibrary.R;
import qzy.com.utilslib.base.BaseActivity;
import qzy.com.utilslib.svgText.VectorCompatTextView;

/**
 * 作者：quzongyang
 *
 * 创建时间：2018/2/1
 *
 * 类描述：
 */

public class SVGActivity extends BaseActivity{


    @Bind(R.id.vectorCompatTextView)
    VectorCompatTextView vectorCompatTextView;

    boolean isChecked = false;

    @Override
    protected void onViewCreated() {
        vectorCompatTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isChecked = !isChecked;
                if(isChecked){
                    vectorCompatTextView.setTextColor(ContextCompat.getColor(SVGActivity.this,R.color.colorf84848));
                }else{
                    vectorCompatTextView.setTextColor(ContextCompat.getColor(SVGActivity.this,R.color.color333333));
                }
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_svg;
    }
}
