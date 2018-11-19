package qzy.com.toolslibrary.ui.fragment.taobao;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import qzy.com.toolslibrary.R;
import qzy.com.toolslibrary.adapter.HomeAdapter;
import qzy.com.toolslibrary.base.BaseApplication;
import qzy.com.toolslibrary.base.Type;
import qzy.com.toolslibrary.contract.HomeContract;
import qzy.com.toolslibrary.model.entity.HomeBase;
import qzy.com.toolslibrary.model.entity.HomeBottom;
import qzy.com.toolslibrary.model.entity.HomeTop;
import qzy.com.toolslibrary.presenter.HomePresenter;

/**
 * author : quzongyang
 * e-mail : quzongyang@xiaohe.com
 * time   : 2018/11/19
 * desc   :
 * version: 1.0
 */


public class NavHomeFragment extends Fragment implements  HomeContract.View{

    private final static int HOME_TOP = 1;
    private final static int HOME_BOTTOM = 2;
    @Bind(R.id.recycler_view)
    RecyclerView recyclerView;
    private Context context;

    private HomeAdapter adapter;
    private HomePresenter presenter;
    private int page = 1;
    private int pageSize = 10;
    private HomeBase footerItem = new HomeBase();

    private List<HomeBase> list = new ArrayList<>();


    public static NavHomeFragment newInstance() {
        NavHomeFragment fragment = new NavHomeFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_navigation_home, container, false);
        ButterKnife.bind(this, view);
        presenter = new HomePresenter();
        presenter.init(this);
        return view;
    }

    public void initView() {
        Activity activity = getActivity();
        context = BaseApplication.getContext();

        int spanCount = getResources().getInteger(R.integer.grid_span_count);

        GridLayoutManager layoutManager = new GridLayoutManager(activity, spanCount);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new HomeAdapter(context, activity, list);
        layoutManager.setSpanSizeLookup(adapter.getSpanSizeLookup());
        recyclerView.setAdapter(adapter);
        //recyclerView.setOnLoadMoreListener(this);

        footerItem.setType(Type.TYPE_FOOTER_LOAD);
        footerItem.setSpanCount(spanCount);
        presenter.start(HOME_TOP);
    }

    //轮播、分类、头条、直播回调
    @Override
    public void show(HomeTop bean) {
        list.clear();
        adapter.setLoopList(bean.getCarousel());
        adapter.setHeadlineList(bean.getHeadlines());
        list.addAll(bean.getList());
        list.add(footerItem);
        adapter.notifyDataSetChanged();
        presenter.start(HOME_BOTTOM, page, pageSize);
    }

    //猜你喜欢模块回调
    @Override
    public void show(HomeBottom bean) {
        //recyclerView.setPage(page, bean.getTotalPage());
        footerItem.setType(page < bean.getTotalPage() ? Type.TYPE_FOOTER_LOAD : Type.TYPE_FOOTER_FULL);
        list.addAll(list.size() - 1, bean.getData());
        adapter.notifyDataSetChanged();
        //setRefreshLoading(false);
    }

    @Override
    public void loading() {

    }

    @Override
    public void networkError() {

    }

    @Override
    public void error(String msg) {

    }

    @Override
    public void showFailed(String msg) {

    }
}
