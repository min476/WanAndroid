package com.vera.sample.wanandroid.api;

import com.google.gson.Gson;
import com.orhanobut.logger.Logger;
import com.vera.sample.wanandroid.app.Constants;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static okhttp3.internal.Util.UTF_8;

/**
 * File descripition:  创建Retrofit
 *
 * @author lp
 * @date 2018/6/19
 */

public class ApiRetrofit {
    public final String BASE_SERVER_URL = Constants.baseUrl;
    private String TAG = "ApiRetrofit %s";
    private static ApiRetrofit apiRetrofit;
    private Retrofit retrofit;
    private ApiServer apiServer;
    private static Gson gson;
    private static final int DEFAULT_TIMEOUT = 15;


    public ApiRetrofit() {
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();
        httpClientBuilder
//                .cookieJar(new CookieManger(App.getContext())) //这块是添加的管理cookie方法
                .addInterceptor(interceptor)//日志拦截
                .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true);//错误重联

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_SERVER_URL)
                .addConverterFactory(GsonConverterFactory.create())//添加json转换框架
                //支持RxJava2
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(httpClientBuilder.build())
                .build();
        apiServer = retrofit.create(ApiServer.class);
    }

    public static ApiRetrofit getInstance() {
        if (apiRetrofit == null) {
            synchronized (Object.class) {
                if (apiRetrofit == null) {
                    apiRetrofit = new ApiRetrofit();
                }
            }
        }
        return apiRetrofit;
    }

    public ApiServer getApiService() {
        return apiServer;
    }

    /**
     * 请求访问quest
     * response拦截器
     */
    private Interceptor interceptor = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            long startTime = System.currentTimeMillis();
            Response response = chain.proceed(chain.request());
            long endTime = System.currentTimeMillis();
            long duration = endTime - startTime;
            MediaType mediaType = response.body().contentType();
            String content = response.body().string();
//            analyzeJson("data", "", content);
            Logger.e(TAG, "----------Request Start----------------");
            printParams(request.body());
            Logger.e(TAG, "| " + request.toString() + "===========" + request.headers().toString());
            Logger.json(content);
            Logger.e(content);
            Logger.e(TAG, "----------Request End:" + duration + "毫秒----------");

            return response.newBuilder()
                    .body(ResponseBody.create(mediaType, content))
                    .build();
        }
    };

    /**
     * 请求参数日志打印
     *
     * @param body
     */
    private void printParams(RequestBody body) {
        if (body != null) {
            Buffer buffer = new Buffer();
            try {
                body.writeTo(buffer);
                Charset charset = Charset.forName("UTF-8");
                MediaType contentType = body.contentType();
                if (contentType != null) {
                    charset = contentType.charset(UTF_8);
                }
                String params = buffer.readString(charset);
                Logger.e(TAG, "请求参数： | " + params);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}