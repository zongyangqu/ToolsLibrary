package qzy.com.toolslibrary.contract;


import qzy.com.toolslibrary.base.BasePresenter;
import qzy.com.toolslibrary.base.BaseView;
import qzy.com.toolslibrary.model.entity.HomeBottom;
import qzy.com.toolslibrary.model.entity.HomeTop;

/**
 * @author Smile Wei
 * @since 2017/3/1.
 */

public interface HomeContract {
    interface View extends BaseView {
        void show(HomeTop bean);

        void show(HomeBottom bean);

        void loading();

        void networkError();

        void error(String msg);

        void showFailed(String msg);
    }

    interface Presenter extends BasePresenter {
        void start(int type);

        void start(int type, int page, int pageSize);
    }
}
