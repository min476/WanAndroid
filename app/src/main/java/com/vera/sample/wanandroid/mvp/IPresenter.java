package com.vera.sample.wanandroid.mvp;

/**
 * File descripition:
 *
 * @author: Vera
 * @date: 2019/2/14
 */

public interface IPresenter<T extends BaseView> {
    void attachView(T model);
    void detachView();
}
