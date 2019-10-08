package com.example.http;


import com.waw.hr.mutils.base.BaseBean;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

/**
 * Created by jingbin on 16/11/21.
 * 网络请求类（一个接口一个方法）
 */
public interface HttpClient {

    class Builder {

        public static HttpClient getServer() {
            return HttpUtils.getInstance().getGuodongServer(HttpClient.class);
        }

        public static HttpClient getOtherServer() {
            return HttpUtils.getInstance().getGuodongServer(HttpClient.class);
        }
    }


    @FormUrlEncoded
    @POST("in/uCreate")
    Observable<BaseBean<Object>> uCreate(@FieldMap Map<String, Object> params);


    @FormUrlEncoded
    @POST("in/sendVerify")
    Observable<BaseBean<String>> sendVerify(@FieldMap Map<String, Object> params);




//    @GET("type/read")
//    Observable<BaseBean<List<WordTypeBean>>> read(@QueryMap Map<String, Object> params);
//
//    @GET("word/read")
//    Observable<BaseBean<WordListBaseBean>> word_read(@QueryMap Map<String, Object> params);
//
//
//    @GET("vocab/read")
//    Observable<BaseBean<WordDetailBean>> vocab_read(@QueryMap Map<String, Object> params);
//
//
//    @GET("test/get")
//    Observable<BaseBean<List<CheckWordBean>>> test_get(@QueryMap Map<String, Object> params);
//
//    @GET("res/grade")
//    Observable<BaseBean<CheckResBean>> grade(@QueryMap Map<String, Object> params);


}