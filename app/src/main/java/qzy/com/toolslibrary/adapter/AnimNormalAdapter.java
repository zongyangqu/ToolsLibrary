package qzy.com.toolslibrary.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import qzy.com.toolslibrary.R;
import qzy.com.toolslibrary.adapter.viewholder.AnimViewHolder;
import qzy.com.toolslibrary.base.BaseApplication;
import qzy.com.toolslibrary.bean.Anim;
import qzy.com.utilslib.recyclerAdapter.SimpleRVAdapter;

/**
 * 作者：quzongyang
 *
 * 创建时间：2018/2/5
 *
 * 类描述：
 */

public class AnimNormalAdapter extends SimpleRVAdapter<Anim,AnimViewHolder> {


    public AnimNormalAdapter(List dataList) {
        super(dataList);
    }

    @Override
    protected AnimViewHolder onCreateViewHolder(LayoutInflater layoutInflater, int viewTye) {
        LayoutInflater inflater = LayoutInflater.from(BaseApplication.getContext());
        View itemView = inflater.inflate(R.layout.widget_anim,null,true);
        /*RecyclerView.LayoutParams lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        itemView.setLayoutParams(lp);*/
        return new AnimViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(AnimViewHolder holder, int position) {
        holder.bind(getItem(position),position);
    }
}
