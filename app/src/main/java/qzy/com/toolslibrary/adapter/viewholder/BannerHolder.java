package qzy.com.toolslibrary.adapter.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import qzy.com.toolslibrary.R;
import qzy.com.toolslibrary.bean.ZhaoBean;


/**
 * Created by ruzhan on 2017/5/1.
 */

public class BannerHolder extends RecyclerView.ViewHolder {

  private ImageView iv;

  public BannerHolder(View itemView) {
    super(itemView);
    iv = (ImageView) itemView.findViewById(R.id.banner_iv);
  }

  public void bind(int position, ZhaoBean bean) {
    iv.setImageResource(bean.getIcon());
  }
}
