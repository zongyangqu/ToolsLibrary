package qzy.com.toolslibrary.ui;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

import qzy.com.toolslibrary.R;
import qzy.com.toolslibrary.utils.base.BaseActivity;
import qzy.com.utilslib.tabbar.CustomizeTabLayout;
import qzy.com.utilslib.tabbar.TabBean;

/**
 * 作者：quzongyang
 *
 * 创建时间：2018/5/24
 *
 * 类描述：支持tabbar网络图
 *
 * github：https://github.com/LuckSiege/PictureSelector
 */

public class TabBarActivity extends BaseActivity implements View.OnClickListener {

    private ArrayList<TabBean> mTabbeans = new ArrayList<>();

    private ImageView vp_container;

    private String[] mTitles = {"首页", "关于", "消息", "我的"};

    private int[] mUnSelectIcons = {
            R.drawable.tab_home_unselect,R.drawable.tab_more_unselect, R.drawable.tab_speech_unselect, R.drawable.tab_more_unselect};
    private int[] mSelectIcons = {
            R.drawable.tab_home_select,R.drawable.tab_more_select,  R.drawable.tab_speech_select,R.drawable.tab_more_select};

    private int mSelectColor = Color.BLUE;
    private int mUnSelectColor = Color.BLACK;

    private String[] mSelectUrls={"http://pic38.nipic.com/20140307/2531170_074502124000_2.jpg",
            "http://pic72.nipic.com/file/20150719/9583477_022559838000_2.jpg",
            "http://img1.imgtn.bdimg.com/it/u=1283566983,3267885599&fm=21&gp=0.jpg",
            "http://pic38.nipic.com/20140307/2531170_074502124000_2.jpg"};
    private String[] mUnSelectUrls={"http://img.taopic.com/uploads/allimg/110419/2376-11041Z15S685.jpg",
            "http://pic.taopic.com/uploads/allimg/140627/240424-14062G2544388-lp.jpg",
            "http://pic42.nipic.com/20140628/19074191_112631798000_2.jpg",
            "http://pic74.nipic.com/file/20150807/21290976_162220169617_2.jpg"};
    private CustomizeTabLayout mTabLayout;


    @Override
    protected void onViewCreated() {
        mTabLayout = findViewById(R.id.tabLayout);
        vp_container = findViewById(R.id.vp_container);
        for (int i = 0; i < mTitles.length; i++) {
            mTabbeans.add(new TabBean(mTitles[i], mSelectUrls[i],mUnSelectUrls[i], mSelectColor, mUnSelectColor,mSelectIcons[i], mUnSelectIcons[i]));
            //mTabbeans.add(new TabBean(mTitles[i], null,null, mSelectColor, mUnSelectColor,mSelectIcons[i], mUnSelectIcons[i]));
        }
        RequestOptions options = new RequestOptions();
        options.placeholder(R.drawable.tab_home_select).diskCacheStrategy(DiskCacheStrategy.ALL);
        Glide.with(TabBarActivity.this).load("http://pic.taopic.com/uploads/allimg/140627/240424-14062G2544388-lp.jpg").apply(options).into(vp_container);
        initFragmentViewpager();
//        mFragments.add(new HomeFragment());
//        mFragments.add(new TalkaboutFragment());
//        mFragments.add(new MessageFragment());
//        mFragments.add(new MineFragment());
    }

    private void initFragmentViewpager() {
        mTabLayout.setTabDate(mTabbeans);
        mTabLayout.setmListener(new CustomizeTabLayout.OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                Toast.makeText(TabBarActivity.this,position+"",Toast.LENGTH_SHORT).show();
            }
            /**
             * 连续点击调用此方法
             */
            @Override
            public void onTabReselect(int position) {

            }
        });

    }


    @Override
    protected int getLayoutId() {
        return R.layout.activity_tabbar;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {

        }
    }
}
