package qzy.com.toolslibrary.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

import qzy.com.toolslibrary.ui.fragment.taobao.NavCartFragment;
import qzy.com.toolslibrary.ui.fragment.taobao.NavHelpFragment;
import qzy.com.toolslibrary.ui.fragment.taobao.NavHomeFragment;
import qzy.com.toolslibrary.ui.fragment.taobao.NavMyFragment;
import qzy.com.toolslibrary.ui.fragment.taobao.NavWeFragment;

/**
 * author : quzongyang
 * e-mail : quzongyang@xiaohe.com
 * time   : 2018/11/19
 * desc   :
 * version: 1.0
 */


public class ViewPagerAdapter extends FragmentPagerAdapter {
    private List<Fragment> fragments = new ArrayList<>();

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);

        fragments.clear();
        fragments.add(NavHomeFragment.newInstance());
        fragments.add(NavWeFragment.newInstance());
        fragments.add(NavHelpFragment.newInstance());
        fragments.add(NavCartFragment.newInstance());
        fragments.add(NavMyFragment.newInstance());

    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

}
