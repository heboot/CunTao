package com.example.http;


import com.waw.hr.mutils.base.BaseBean;
import com.waw.hr.mutils.bean.ArticleIndexBean;
import com.waw.hr.mutils.bean.BankListBean;
import com.waw.hr.mutils.bean.CashLogModel;
import com.waw.hr.mutils.bean.CurlgetBean;
import com.waw.hr.mutils.bean.IndexBean;
import com.waw.hr.mutils.bean.LoginBean;
import com.waw.hr.mutils.bean.RankListBean;
import com.waw.hr.mutils.bean.UserInfoBean;
import com.waw.hr.mutils.model.OrderModel;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

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
    @POST("Register")
    Observable<BaseBean<Object>> Register(@FieldMap Map<String, Object> params);


    @FormUrlEncoded
    @POST("sendCode")
    Observable<BaseBean<Object>> sendVerify(@FieldMap Map<String, Object> params);

    @FormUrlEncoded
    @POST("login")
    Observable<BaseBean<LoginBean>> login(@FieldMap Map<String, Object> params);

    @FormUrlEncoded
    @POST("changePwd")
    Observable<BaseBean<Object>> changePwd(@FieldMap Map<String, Object> params);


    @POST("profileCenter")
    Observable<BaseBean<UserInfoBean>> profileCenter(@Header("token") String token);

    @GET("changeName")
    Observable<BaseBean<Object>> changeName(@Header("token") String token, @QueryMap Map<String, Object> params);

    @Multipart
    @POST("changeAvatar")
    Observable<BaseBean<Object>> changeAvatar(@Header("token") String token, @Part MultipartBody.Part img);

    @GET("changeGender")
    Observable<BaseBean<Object>> changeGender(@Header("token") String token, @QueryMap Map<String, Object> params);

    @GET("getHelp")
    Observable<BaseBean<Map>> getHelp();

    @POST("alipayInfo")
    Observable<BaseBean<List<Map>>> alipayInfo(@Header("token") String token);

    @FormUrlEncoded
    @POST("changePayAccount")
    Observable<BaseBean<Object>> changePayAccount(@Header("token") String token, @FieldMap Map<String, Object> params);

    @FormUrlEncoded
    @POST("changeMobile")
    Observable<BaseBean<Object>> changeMobile(@Header("token") String token, @FieldMap Map<String, Object> params);

    @FormUrlEncoded
    @POST("accountDel")
    Observable<BaseBean<Object>> accountDel(@Header("token") String token, @FieldMap Map<String, Object> params);

    @FormUrlEncoded
    @POST("accountList")
    Observable<BaseBean<List<BankListBean>>> accountList(@Header("token") String token, @FieldMap Map<String, Object> params);

    @FormUrlEncoded
    @POST("accountAdd")
    Observable<BaseBean<Object>> accountAdd(@Header("token") String token, @FieldMap Map<String, Object> params);


    @GET("articleIndex")
    Observable<BaseBean<ArticleIndexBean>> articleIndex(@Header("token") String token, @QueryMap Map<String, Object> params);


    @FormUrlEncoded
    @POST("withdrawIndex")
    Observable<BaseBean<List<CashLogModel>>> withdrawIndex(@Header("token") String token, @FieldMap Map<String, Object> params);

    @GET("articleMy")
    Observable<BaseBean<ArticleIndexBean>> articleMy(@Header("token") String token, @QueryMap Map<String, Object> params);

    @Multipart
    @POST("articleAdd")
    Observable<BaseBean<Object>> articleAdd(@Header("token") String token, @Part MultipartBody.Part params, @Part List<MultipartBody.Part> part);

    @GET("articleDel")
    Observable<BaseBean<Object>> articleDel(@Header("token") String token, @QueryMap Map<String, Object> params);


    @FormUrlEncoded
    @POST("curlget")
    Observable<BaseBean<CurlgetBean>> curlget(@Header("token") String token, @FieldMap Map<String, Object> params);

    @GET("getExplain")
    Observable<BaseBean<Map>> getExplain(@QueryMap Map<String, Object> params);

    @FormUrlEncoded
    @POST("moneyWithdrawDeposit")
    Observable<BaseBean<Object>> moneyWithdrawDeposit(@Header("token") String token, @FieldMap Map<String, Object> params);



    @FormUrlEncoded
    @POST("orderIncrease")
    Observable<BaseBean<Object>> orderIncrease(@Header("token") String token, @FieldMap Map<String, Object> params);

    @FormUrlEncoded
    @POST("orderList")
    Observable<BaseBean<List<OrderModel>>> orderList(@Header("token") String token, @FieldMap Map<String, Object> params);



    @GET("home")
    Observable<BaseBean<IndexBean>> homea(@Header("token") String token, @Query("data_type") String type);


    @GET("commission")
    Observable<BaseBean<List<RankListBean>>> commission(@Header("token") String token, @Query("data_type") int type);
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