package com.vera.sample.wanandroid.ui.activity.main;



import com.vera.sample.wanandroid.bean.publicaccount_bean.PublicAcccountBean;
import com.vera.sample.wanandroid.mvp.BaseModel;
import com.vera.sample.wanandroid.mvp.BaseView;

import java.util.List;

/**
 * @author Vera
 * @descripition:
 * @date 2019/1/30 15:23
 */

public interface MainView extends BaseView {
//    void onMainSuccess(BaseModel<List<HomeBeans.DatasBean>> list);
    void onMainSuccess(BaseModel<List<PublicAcccountBean>> list);
}
