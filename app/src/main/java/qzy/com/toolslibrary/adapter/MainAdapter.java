package qzy.com.toolslibrary.adapter;

import android.view.LayoutInflater;

import java.util.List;

import qzy.com.toolslibrary.R;
import qzy.com.toolslibrary.adapter.viewholder.MainViewHolder;
import qzy.com.utilslib.listViewAdapter.SimpleAdapter;

/**
 * 作者：quzongyang
 *
 * 创建时间：2018/2/1
 *
 * 类描述：首页数据源
 */

public class MainAdapter extends SimpleAdapter<String, MainViewHolder> {

    public MainAdapter(List<String> campuses) {
        super(campuses);
    }

    @Override
    protected MainViewHolder createViewHolder(LayoutInflater layoutInflater, int i) {
        return new MainViewHolder(layoutInflater.inflate(R.layout.widget_main_item, null));
    }

    @Override
    protected void onBindViewHolder(MainViewHolder vh, int i) {
        vh.bind(getItem(i), i);
    }
}
