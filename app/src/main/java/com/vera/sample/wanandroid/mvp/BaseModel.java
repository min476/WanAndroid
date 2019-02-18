package com.vera.sample.wanandroid.mvp;

import java.io.Serializable;

/**
 * @descripition: Model基类
 *
 * @author Vera
 * @date 2019/1/30 10:53
 */

public class BaseModel<T> implements Serializable {
    public static final int SUCCESS = 0;
    private String errorMsg;
    private int errorCode;
    private T data;

    public BaseModel(String message, int code) {
        this.errorMsg = message;
        this.errorCode = code;
    }

    public int getErrcode() {
        return errorCode;
    }

    public void setErrcode(int code) {
        this.errorCode = code;
    }

    public String getErrmsg() {
        return errorMsg;
    }

    public void setErrmsg(String message) {
        this.errorMsg = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T result) {
        this.data = result;
    }

    @Override
    public String toString() {
        return "BaseModel{" +
                "code=" + errorCode +
                ", msg='" + errorMsg + '\'' +
                ", result=" + data +
                '}';
    }
}
