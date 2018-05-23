package qzy.com.toolslibrary.adapter.viewholder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import butterknife.Bind;
import butterknife.ButterKnife;
import qzy.com.toolslibrary.R;
import qzy.com.toolslibrary.base.BaseApplication;
import qzy.com.toolslibrary.bean.Anim;
import qzy.com.utilslib.recyclerAdapter.holder.SimpleRViewHolder;

/**
 * 作者：quzongyang
 *
 * 创建时间：2018/2/5
 *
 * 类描述：
 */

public class AnimViewHolder extends SimpleRViewHolder<Anim> {

    @Bind(R.id.animRes)
    ImageView animRes;
    @Bind(R.id.animName)
    TextView animName;

    public AnimViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void bind(Anim obj, int position) {
        animName.setText(obj.name);
        Glide.with(BaseApplication.getContext()).load(obj.resourceId).into(animRes);
    }
}
