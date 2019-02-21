package com.vera.sample.wanandroid.mvp;

import com.google.gson.JsonParseException;
import com.vera.sample.wanandroid.app.Constants;
import com.vera.sample.wanandroid.app.MyApplication;
import com.vera.sample.wanandroid.utils.NetUtils;

import org.json.JSONException;

import java.io.InterruptedIOException;
import java.net.ConnectException;
import java.net.UnknownHostException;
import java.text.ParseException;

import io.reactivex.observers.DisposableObserver;
import retrofit2.HttpException;

/**
 * @descripition:
 *
 * @author Vera
 * @date 2019/1/30 11:11
 */

public abstract  class BaseObserver<T> extends DisposableObserver<T> {
    /**
     * 于服务器约定  返回code为几是正常请求
     */
    public static final int CODE = Constants.basecode;
    protected BaseView view;
    /**
     * 网络连接失败  无网
     */
    public static final int NETWORK_ERROR = 100000;
    /**
     * 解析数据失败
     */
    public static final int PARSE_ERROR = 1008;
    /**
     * 网络问题
     */
    public static final int BAD_NETWORK = 1007;
    /**
     * 连接错误
     */
    public static final int CONNECT_ERROR = 1006;
    /**
     * 连接超时
     */
    public static final int CONNECT_TIMEOUT = 1005;

    public BaseObserver(BaseView view) {
        this.view = view;
    }
    @Override
    protected void onStart() {
        if (view != null) {
            view.showLoading();
        }
    }
    @Override
    public void onNext(T o) {
        try {
            // loading写到这里没有延迟效果
            if (view != null) {
                view.hideLoading();
            }
            BaseModel model = (BaseModel) o;
            if (model.getErrcode() == CODE) {
                onSuccess(o);
            } else {
                if (view != null) {
                    view.onErrorCode(model);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            onError(e.toString());
        }
    }
    @Override
    public void onError(Throwable e) {
        if (view != null) {
            view.hideLoading();
        }
        if (e instanceof HttpException) {
            //   HTTP错误
            onException(BAD_NETWORK, "");
        } else if (e instanceof ConnectException
                || e instanceof UnknownHostException) {
            //   连接错误
            onException(CONNECT_ERROR, "");
        } else if (e instanceof InterruptedIOException) {
            //  连接超时
            onException(CONNECT_TIMEOUT, "");
        } else if (e instanceof JsonParseException
                || e instanceof JSONException
                || e instanceof ParseException) {
            //  解析错误
            onException(PARSE_ERROR, "");
            e.printStackTrace();
        }  else {
            if (e != null) {
                onError(e.toString());
            } else {
                onError("未知错误");
            }
        }
    }
    /**
     * 中间拦截一步  判断是否有网络  为确保准确  此步去除也可以
     *
     * @param unknownError
     * @param message
     */
    private void onException(int unknownError, String message) {
        BaseModel model = new BaseModel(message, unknownError);
        if (!NetUtils.isNetworkAvailable(MyApplication.getContext())) {
            model.setErrcode(NETWORK_ERROR);
            model.setErrmsg("网络不可用，请检查网络连接！");
        }
        onExceptions(model.getErrcode(), model.getErrmsg());
        if (view != null) {
            view.onErrorCode(model);
        }
    }
    private void onExceptions(int unknownError, String message) {
        switch (unknownError) {
            case CONNECT_ERROR:
                onError("连接错误");
                break;
            case CONNECT_TIMEOUT:
                onError("连接超时");
                break;
            case BAD_NETWORK:
                onError("网络超时");
                break;
            case PARSE_ERROR:
                onError("数据解析失败");
                break;
            //网络不可用
            case NETWORK_ERROR:
                onError("网络不可用，请检查网络连接！");
                break;
            default:
                break;
        }
    }
    //loading消失写到这 有一定的延迟  对dialog显示有影响
    @Override
    public void onComplete() {
       /* if (view != null) {
            view.hideLoading();
        }*/
    }
    public abstract void onSuccess(T o);
    public abstract void onError(String msg);
}
