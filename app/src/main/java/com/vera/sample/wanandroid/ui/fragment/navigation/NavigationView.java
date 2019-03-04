package com.vera.sample.wanandroid.ui.fragment.navigation;



import com.vera.sample.wanandroid.bean.navigation.NavigationListBean;
import com.vera.sample.wanandroid.bean.project.ProjectClassifyBean;
import com.vera.sample.wanandroid.mvp.BaseView;

import java.util.List;

/**
 * File descripition: 导航逻辑
 *
 * @author: Vera
 * @date: 2019/3/1
 */

public interface NavigationView extends BaseView {
//    void onMainSuccess(BaseModel<List<HomeBeans.DatasBean>> list);

    void setNavigationTab(List<NavigationListBean> projectTab);
}
