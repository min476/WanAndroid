package com.vera.sample.wanandroid.mvp;

import android.app.Activity;

/**
 * @descripition:
 *
 * @author Vera
 * @date 2019/1/30 10:40
 */

public interface BaseView {
    /**
     * 显示dialog
     */
    void showLoading();
    /**
     * 隐藏 dialog
     */

    void hideLoading();

    /**
     * 显示错误信息
     */
    void showError();

    /**
     * 显示错误信息
     * @param msg
     */
    void showErrorMsg(String msg);
    /**
     * 错误码
     */
    void onErrorCode(BaseModel model);


}
