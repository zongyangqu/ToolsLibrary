package qzy.com.toolslibrary.adapter.viewholder;

import android.view.View;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import qzy.com.toolslibrary.R;
import qzy.com.utilslib.listViewAdapter.SimpleViewHolder;

/**
 * 作者：quzongyang
 *
 * 创建时间：2018/2/1
 *
 * 类描述：
 */

public class MainViewHolder extends SimpleViewHolder<String> {

    @Bind(R.id.position)
    TextView tvPosition;
    @Bind(R.id.instruction)
    TextView instruction;

    public MainViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void bind(String obj, int position) {
        tvPosition.setText(String.valueOf(position+1));
        instruction.setText(obj);
    }
}
