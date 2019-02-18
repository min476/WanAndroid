package com.vera.sample.wanandroid.mvp;

import android.app.Activity;


import com.vera.sample.wanandroid.api.ApiRetrofit;
import com.vera.sample.wanandroid.api.ApiServer;
import com.vera.sample.wanandroid.app.DataManager;
import com.vera.sample.wanandroid.bean.PublicAcccountBean;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * @descripition: Presenter基类
 *
 * @author Vera
 * @date 2019/1/30 10:41
 */

public class BasePresenter<T extends BaseView> implements IPresenter<T>  {
    private CompositeDisposable compositeDisposable;
    public T baseView;
    protected Activity mActivity;
    protected ApiServer apiServer = ApiRetrofit.getInstance().getApiService();
    private DataManager mDataManager ;

    public BasePresenter(T baseView ,Activity mActivity) {//,DataManager dataManager
        this.baseView = baseView;
        this.mActivity = mActivity;
//        this.mDataManager = dataManager;
    }

    public BasePresenter(){}
    @Override
    public void attachView(T model) {
        this.baseView = model;
    }

    /**
     * 解除绑定
     */
    public void detachView() {
        baseView = null;
        removeDisposable();
    }


    public int getCurrentPage() {
        return mDataManager.getCurrentPage();
    }
    /**
     * 返回 view
     *
     * @return
     */
    public T getBaseView() {
        return baseView;
    }

    public void addDisposable(Observable<BaseModel<List<PublicAcccountBean>>> observable, BaseObserver observer) {
        if (compositeDisposable == null) {
            compositeDisposable = new CompositeDisposable();
        }
        compositeDisposable.add(observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(observer));
    }

    protected void addSubscribe(Disposable disposable) {
        if (compositeDisposable == null) {
            compositeDisposable = new CompositeDisposable();
        }
        compositeDisposable.add(disposable);
    }

    public void removeDisposable() {
        if (compositeDisposable != null) {
            compositeDisposable.dispose();
        }
    }
}
