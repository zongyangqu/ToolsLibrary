package qzy.com.toolslibrary.presenter;

import qzy.com.toolslibrary.contract.HomeContract;
import qzy.com.toolslibrary.model.HomeLoadModel;
import qzy.com.toolslibrary.model.entity.HomeBottom;
import qzy.com.toolslibrary.model.entity.HomeTop;
import qzy.com.toolslibrary.model.impl.HomeModelImpl;

/**
 * @author Smile Wei
 * @since 2017/03/01.
 */

public class HomePresenter implements HomeContract.Presenter {
    private HomeContract.View view;
    private HomeLoadModel loadModel;

    public void init(HomeContract.View view) {
        loadModel = new HomeModelImpl();
        this.view = view;
        this.view.initView();
    }

    @Override
    public void start(int type) {
        view.loading();
        loadModel.load(new OnLoadListener<HomeTop>() {
            @Override
            public void onSuccess(HomeTop bean) {
                view.show(bean);
            }

            @Override
            public void onError(String state, String msg) {
                view.error(msg);
            }

            @Override
            public void networkError() {
                view.networkError();
            }
        }, type);

    }

    @Override
    public void start(int type, int page, int pageSize) {
        view.loading();
        loadModel.load(new OnLoadListener<HomeBottom>() {
            @Override
            public void onSuccess(HomeBottom success) {
                view.show(success);
            }

            @Override
            public void onError(String state, String msg) {
                view.error(msg);
            }

            @Override
            public void networkError() {
                view.networkError();
            }
        }, type, page, pageSize);
    }

    @Override
    public void start() {

    }
}
