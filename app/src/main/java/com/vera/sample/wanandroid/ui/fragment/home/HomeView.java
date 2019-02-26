package com.vera.sample.wanandroid.ui.fragment.home;

import com.vera.sample.wanandroid.bean.BannerBean;
import com.vera.sample.wanandroid.mvp.BaseView;

import java.util.List;

/**
 * File descripition: 契约类
 *
 * @author: Vera
 * @date: 2019/2/20
 */

public interface HomeView extends BaseView {
//   void setBannerList(List<String> bannerList);
   void setBannerList(List<String> bannerList,List<BannerBean> bannerBeans);

}
