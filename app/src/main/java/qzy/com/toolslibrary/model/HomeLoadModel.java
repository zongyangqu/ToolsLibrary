package qzy.com.toolslibrary.model;


import qzy.com.toolslibrary.model.entity.HomeBottom;
import qzy.com.toolslibrary.model.entity.HomeTop;
import qzy.com.toolslibrary.presenter.OnLoadListener;

/**
 * @author Smile Wei
 * @since 2016/9/22.
 */

public interface HomeLoadModel extends LoadModel {

    void load(OnLoadListener<HomeTop> listener, int type);

    void load(OnLoadListener<HomeBottom> listener, int type, int page, int pageSize);
}
