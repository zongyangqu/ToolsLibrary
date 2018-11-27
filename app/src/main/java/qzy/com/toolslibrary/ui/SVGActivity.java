package qzy.com.toolslibrary.ui;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Toast;

import butterknife.Bind;
import qzy.com.toolslibrary.R;
import qzy.com.toolslibrary.utils.CheckUtil;
import qzy.com.toolslibrary.utils.base.BaseActivity;
import qzy.com.utilslib.svgText.VectorCompatTextView;

/**
 * 作者：quzongyang
 *
 * 创建时间：2018/2/1
 *
 * 类描述：
 */

public class SVGActivity extends BaseActivity {


    @Bind(R.id.vectorCompatTextView)
    VectorCompatTextView vectorCompatTextView;

    boolean isChecked = false;

    @Override
    protected void onViewCreated() {
        vectorCompatTextView.setDrawableTopCompat(R.drawable.ic_homepage);
        vectorCompatTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //boolean b = CheckUtil.checkAliPayInstalled(SVGActivity.this);
                //Toast.makeText(SVGActivity.this,b+"",Toast.LENGTH_SHORT).show();
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
