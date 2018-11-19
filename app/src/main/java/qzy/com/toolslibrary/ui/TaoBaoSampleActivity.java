package qzy.com.toolslibrary.ui;

import android.graphics.Color;
import android.os.CountDownTimer;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationViewPager;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.Bind;
import qzy.com.toolslibrary.R;
import qzy.com.toolslibrary.adapter.ViewPagerAdapter;
import qzy.com.toolslibrary.utils.base.BaseActivity;

/**
 * author : quzongyang
 * e-mail : quzongyang@xiaohe.com
 * time   : 2018/11/19
 * desc   :
 * version: 1.0
 */


public class TaoBaoSampleActivity extends BaseActivity{

    @Bind(R.id.view_pager)
    AHBottomNavigationViewPager viewPager;
    @Bind(R.id.bottom_navigation)
    AHBottomNavigation bottomNavigation;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.tv_search_tips)
    TextView tvSearchTips;
    private CountDownTimer timer;

    private ArrayList<AHBottomNavigationItem> bottomNavigationItems = new ArrayList<>();
    @Override
    protected void onViewCreated() {
        initView();
    }

    public void initView() {
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        AHBottomNavigationItem item1 = new AHBottomNavigationItem(R.string.bottom_navigation_item_homepage, R.mipmap.navtab_home, R.color.colorBottomNavigationActiveColored);
        AHBottomNavigationItem item2 = new AHBottomNavigationItem(R.string.bottom_navigation_item_we, R.mipmap.navtab_we, R.color.colorBottomNavigationActiveColored);
        AHBottomNavigationItem item3 = new AHBottomNavigationItem(R.string.bottom_navigation_item_question, R.mipmap.navtab_question, R.color.colorBottomNavigationActiveColored);
        AHBottomNavigationItem item4 = new AHBottomNavigationItem(R.string.bottom_navigation_item_cart, R.mipmap.navtab_cart, R.color.colorBottomNavigationActiveColored);
        AHBottomNavigationItem item5 = new AHBottomNavigationItem(R.string.bottom_navigation_item_personal, R.mipmap.navtab_user, R.color.colorBottomNavigationActiveColored);

        bottomNavigationItems.add(item1);
        bottomNavigationItems.add(item2);
        bottomNavigationItems.add(item3);
        bottomNavigationItems.add(item4);
        bottomNavigationItems.add(item5);

        bottomNavigation.addItems(bottomNavigationItems);
        bottomNavigation.setForceTitlesDisplay(true);
        bottomNavigation.setDefaultBackgroundColor(Color.parseColor("#FEFEFE"));
        bottomNavigation.setAccentColor(Color.parseColor("#1DA1F2"));
        bottomNavigation.setInactiveColor(Color.parseColor("#949494"));

        bottomNavigation.setOnTabSelectedListener(new AHBottomNavigation.OnTabSelectedListener() {
            @Override
            public boolean onTabSelected(int position, boolean wasSelected) {
                viewPager.setCurrentItem(position, false);
                return true;
            }
        });
        viewPager.setOffscreenPageLimit(5);
        viewPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager()));

        /**
         * 顶部搜索框内容定时更新，简单的采用倒计时功能
         */
        final List<String> tips = new ArrayList<>();
        tips.add("AndroidNexus");
        tips.add("EasyToForget");
        tips.add("zhiye.wei@gmail.com");

        timer = new CountDownTimer(3000000, 5000) {
            @Override
            public void onTick(long millisUntilFinished) {
                tvSearchTips.setText(tips.get(new Random().nextInt(2)));
            }

            @Override
            public void onFinish() {

            }
        };
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_taobao_sample;
    }
}
