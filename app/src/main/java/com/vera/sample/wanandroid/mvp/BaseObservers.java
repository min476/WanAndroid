package com.vera.sample.wanandroid.mvp;

import android.text.TextUtils;
import android.util.Log;

import com.vera.sample.wanandroid.R;
import com.vera.sample.wanandroid.app.MyApplication;
import com.vera.sample.wanandroid.rx.exception.ServerException;
import com.vera.sample.wanandroid.utils.CommonUtils;
import com.vera.sample.wanandroid.utils.NetUtils;

import io.reactivex.observers.ResourceObserver;
import retrofit2.HttpException;

/**
 * @param <T>
 * @author quchao
 * @date 2017/11/27
 */

public abstract class BaseObservers<T> extends ResourceObserver<T> {

    private BaseView mView;
    private String mErrorMsg;
    private boolean isShowError = true;

    protected BaseObservers(BaseView view) {
        this.mView = view;
    }

    protected BaseObservers(BaseView view, String errorMsg) {
        this.mView = view;
        this.mErrorMsg = errorMsg;
    }

    protected BaseObservers(BaseView view, boolean isShowError) {
        this.mView = view;
        this.isShowError = isShowError;
    }

    protected BaseObservers(BaseView view, String errorMsg, boolean isShowError) {
        this.mView = view;
        this.mErrorMsg = errorMsg;
        this.isShowError = isShowError;
    }

    @Override
    public void onComplete() {

    }

    @Override
    public void onError(Throwable e) {
        if (mView == null) {
            return;
        }
        if (mErrorMsg != null && !TextUtils.isEmpty(mErrorMsg)) {
            mView.showErrorMsg(mErrorMsg);
        } else if (e instanceof ServerException) {
            mView.showErrorMsg(e.toString());
        } else if (e instanceof HttpException) {
            mView.showErrorMsg(MyApplication.getInstance().getString(R.string.http_error));
        } else {
            // 检查网络是否连接
            if (NetUtils.isNetworkAvailable(MyApplication.getContext())) {
                mView.showErrorMsg(MyApplication.getInstance().getString(R.string.unKnown_error));
                Log.e("onError:", e.toString());
            } else {
                CommonUtils.showMessage(MyApplication.getContext(), "报告小主，网络可能被外星人偷走啦~");
            }

        }
        if (isShowError) {
            mView.showError();
        }
    }
}
