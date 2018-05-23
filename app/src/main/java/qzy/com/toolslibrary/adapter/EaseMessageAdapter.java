package qzy.com.toolslibrary.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import qzy.com.toolslibrary.R;
import qzy.com.toolslibrary.bean.MessageBean;
import qzy.com.toolslibrary.utils.TimeUtils;
import qzy.com.utilslib.voicerecorder.utils.CommonUtils;

/**
 * 作者：quzongyang
 *
 * 创建时间：2018/5/22
 *
 * 类描述：消息适配器
 */

public class EaseMessageAdapter extends BaseAdapter {

    private Context context;
    private List<MessageBean> list;

    private onItemClickLister onItemClickLister;


    public EaseMessageAdapter(Context mContext, List<MessageBean> list) {
        this.context = mContext;
        this.list = list;
    }


    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return 0;
    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        final ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.ease_row_sent_voice, parent, false);

            viewHolder.iv_voice = (ImageView) convertView.findViewById(R.id.iv_voice);
            viewHolder.tv_length = (TextView) convertView.findViewById(R.id.tv_length);
            viewHolder.timestamp = (TextView) convertView.findViewById(R.id.timestamp);
            viewHolder.bubble = (RelativeLayout) convertView.findViewById(R.id.bubble);
            viewHolder.bubble_text = (RelativeLayout) convertView.findViewById(R.id.bubble_text);
            viewHolder.tv_chatcontent = (TextView) convertView.findViewById(R.id.tv_chatcontent);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }


        final MessageBean bean = list.get(position);

        viewHolder.bubble_text.setVisibility(View.GONE);
        viewHolder.bubble.setVisibility(View.VISIBLE);
        //更改并显示录音条长度
        RelativeLayout.LayoutParams ps = (RelativeLayout.LayoutParams) viewHolder.bubble.getLayoutParams();
        ps.width = CommonUtils.getVoiceLineWight2(context, bean.second);
        viewHolder.bubble.setLayoutParams(ps); //更改语音长条长度

        viewHolder.tv_length.setText(bean.second + "");


        viewHolder.timestamp.setText(TimeUtils.getTime(bean.time));

        viewHolder.iv_voice.setImageResource(R.drawable.ease_chatto_voice_playing);

        viewHolder.bubble.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickLister != null) {
                    onItemClickLister.onItemClick(viewHolder.iv_voice, bean.path, position);
                }
            }
        });
        return convertView;
    }

    private class ViewHolder {
        ImageView iv_voice;
        TextView tv_length;
        TextView timestamp;
        RelativeLayout bubble;
        RelativeLayout bubble_text;
        TextView tv_chatcontent;
    }

    public interface onItemClickLister {
        void onItemClick(ImageView imageView, String path, int position);
    }

    public void setOnItemClickLister(EaseMessageAdapter.onItemClickLister onItemClickLister) {
        this.onItemClickLister = onItemClickLister;
    }
}
