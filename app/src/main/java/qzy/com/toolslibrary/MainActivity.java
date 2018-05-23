package qzy.com.toolslibrary;

import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import qzy.com.toolslibrary.adapter.MainAdapter;
import qzy.com.toolslibrary.ui.HeadViewRecyclerViewActivity;
import qzy.com.toolslibrary.ui.NormalRecyclerViewActivity;
import qzy.com.toolslibrary.ui.RecordAudio2Activity;
import qzy.com.toolslibrary.ui.RecordAudioActivity;
import qzy.com.toolslibrary.ui.SVGActivity;
import qzy.com.utilslib.Person;
import qzy.com.utilslib.base.BaseActivity;
import qzy.com.utilslib.svgText.VectorCompatTextView;

public class MainActivity extends BaseActivity {

    @Bind(R.id.swipe_target)
    ListView swipe_target;
    MainAdapter mainAdapter;

    @Override
    protected void onViewCreated() {
        mainAdapter = new MainAdapter(initData());
        swipe_target.setAdapter(mainAdapter);
        swipe_target.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i){
                    case 0:
                        startActivity(new Intent(getActivity(), SVGActivity.class));
                        break;
                    case 1:
                        startActivity(new Intent(getActivity(), HeadViewRecyclerViewActivity.class));
                        break;
                    case 2:
                        startActivity(new Intent(getActivity(), NormalRecyclerViewActivity.class));
                        break;
                    case 3:
                        startActivity(new Intent(getActivity(), RecordAudioActivity.class));
                        break;
                    case 4:
                        startActivity(new Intent(getActivity(), RecordAudio2Activity.class));
                        break;
                }
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }


    public List<String> initData(){
        List<String> data = new ArrayList<String>();
        data.add("svg图形TextView");
        data.add("封装有headView的RecyclerView");
        data.add("封装普通的RecyclerView");
        data.add("仿微信录制语音（1）");
        data.add("仿微信录制语音（2）");
        return data;
    }
}
