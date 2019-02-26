package com.vera.sample.wanandroid.ui.fragment.public_account;



import com.vera.sample.wanandroid.bean.publicaccount_bean.PublicAcccountBean;
import com.vera.sample.wanandroid.mvp.BaseView;

import java.util.List;

/**
 * File descripition: 公众号
 *
 * @author: Vera
 * @date: 2019/2/14
 */

public interface PublicAccountsView extends BaseView {
//    void onMainSuccess(BaseModel<List<HomeBeans.DatasBean>> list);

    void setPublicAccountTab(List<PublicAcccountBean> publicAcccountBeans );
}
