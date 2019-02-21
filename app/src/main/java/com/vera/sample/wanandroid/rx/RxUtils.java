package com.vera.sample.wanandroid.rx;

import com.vera.sample.wanandroid.app.MyApplication;
import com.vera.sample.wanandroid.mvp.BaseModel;
import com.vera.sample.wanandroid.rx.exception.OtherException;
import com.vera.sample.wanandroid.utils.NetUtils;

import io.reactivex.FlowableTransformer;
import io.reactivex.Observable;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by chao.qu at 2017/10/20
 * @author quchao
 */

public class RxUtils {

    /**
     * 统一线程处理
     * @param <T> 指定的泛型类型
     * @return FlowableTransformer
     */
    public static <T> FlowableTransformer<T, T> rxFlSchedulerHelper() {
        return flowable -> flowable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 统一线程处理
     * @param <T> 指定的泛型类型
     * @return ObservableTransformer
     */
    public static <T> ObservableTransformer<T, T> rxSchedulerHelper() {
        return observable -> observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 统一返回结果处理
     * @param <T> 指定的泛型类型
     * @return ObservableTransformer
     */
    public static <T> ObservableTransformer<BaseModel<T>, T> handleResult() {
        return httpResponseObservable ->
                httpResponseObservable.flatMap((Function<BaseModel<T>, Observable<T>>) baseResponse -> {
            if(baseResponse.getErrcode() == BaseModel.SUCCESS
                    && baseResponse.getData() != null
                    && NetUtils.isNetworkAvailable(MyApplication.getContext())) {
                return createData(baseResponse.getData());
            } else {
                return Observable.error(new OtherException());
            }
        });
    }

    /**
     * 退出登录返回结果处理
     * @param <T> 指定的泛型类型
     * @return ObservableTransformer
     */
//    public static <T> ObservableTransformer<BaseModel<T>, T> handleLogoutResult() {
//        return httpResponseObservable ->
//                httpResponseObservable.flatMap((Function<BaseModel<T>, Observable<T>>) baseResponse -> {
//                    if(baseResponse.getErrcode() == BaseModel.SUCCESS
//                            && NetUtils.isNetworkAvailable()) {
//                        //创建一个非空数据源，避免onNext()传入null
//                        return createData(CommonUtils.cast(new LoginData()));
//                    } else {
//                        return Observable.error(new OtherException());
//                    }
//                });
//    }

//    /**
//     * 收藏返回结果处理
//     * @param <T> 指定的泛型类型
//     * @return ObservableTransformer
//     */
//    public static <T> ObservableTransformer<BaseResponse<T>, T> handleCollectResult() {
//        return httpResponseObservable ->
//                httpResponseObservable.flatMap((Function<BaseResponse<T>, Observable<T>>) baseResponse -> {
//                    if(baseResponse.getErrorCode() == BaseResponse.SUCCESS
//                            && CommonUtils.isNetworkConnected()) {
//                        //创建一个非空数据源，避免onNext()传入null
//                        return createData(CommonUtils.cast(new FeedArticleListData()));
//                    } else {
//                        return Observable.error(new OtherException());
//                    }
//                });
//    }

    /**
     * 得到 Observable
     * @param <T> 指定的泛型类型
     * @return Observable
     */
    private static <T> Observable<T> createData(final T t) {
        return Observable.create(emitter -> {
            try {
                emitter.onNext(t);
                emitter.onComplete();
            } catch (Exception e) {
                emitter.onError(e);
            }
        });
    }


}
