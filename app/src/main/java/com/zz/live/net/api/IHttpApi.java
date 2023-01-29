package com.zz.live.net.api;


import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

public interface IHttpApi {



    //请求腾讯直播sdk推流地址
    @POST("weapp/utils/get_test_pushurl")
    Observable<Response<ResponseBody>> getPusherUrl();

    //注册
    @POST("api/member/regUser")
    Observable<Response<ResponseBody>> register(@Body RequestBody requestBody);

    @Headers({
            "Authorization:Basic bW9iaWxlOm1vYmlsZQ==",
            "Accept-Language:zh-CN,zh",
    })
/*    @FormUrlEncoded
    @POST("auth/oauth/token?grant_type=password")
//    Observable<Response<ResponseBody>> login(@FieldMap HashMap<String,Object>data);
    Observable<Response<ResponseBody>> login(@FieldMap HashMap<String,Object>data);*/


    @FormUrlEncoded
    @POST("auth/oauth/tokenauth/oauth/token?grant_type=password")
//    @POST("auth/oauth/token")
//    @GET("auth/oauth/token")
/*    Observable<Response<ResponseBody>> login(
            @Field(value="username") String username,
           @Field(value="password",encoded =true) String password,
           @Field(value="grant_type") String grant_type
    );*/
    Observable<Response<ResponseBody>> login(@FieldMap HashMap<String,Object>data);


    //获取用户信息

    @POST("api/member/info")
    Observable<Response<ResponseBody>>  getUserInfo(@Body  RequestBody requestBody);

    //排行榜
    @FormUrlEncoded
    @POST("api/ranking/incomeRanking")
    Observable<Response<ResponseBody>> getRankList(@FieldMap HashMap<String,Object>data);
    //系统消息
    @FormUrlEncoded
    @POST("api/systemMessages/systemMessage")
    Observable<Response<ResponseBody>> getSystemMesssageList(@FieldMap HashMap<String,Object>data);
    //删除系统消息
    @POST("api/systemMessages/deleteMessage/{type}")
    Observable<Response<ResponseBody>> deleteSystemMessage(@Path("type") int type);
    @FormUrlEncoded
    @POST("api/systemMessages/page")
    Observable<Response<ResponseBody>>systemMessageist(@FieldMap HashMap<String,Object>data);


    //更改系统消息未读状态
    @POST("api/systemMessages/read/{id}")
    Observable<Response<ResponseBody>> modifySystemMessageReadStatus(@Path("id") String id);
    //关注消息列表
    @FormUrlEncoded
    @POST("api/followMessage/page")
    Observable<Response<ResponseBody>>followMessageLsit(@FieldMap HashMap<String,Object>data);

    //私信消息列表
    @FormUrlEncoded
    @POST("api/privateMessages/page")
    Observable<Response<ResponseBody>>privateMessageList(@FieldMap HashMap<String,Object>data);

    //删除私信消息
    @POST("api/privateMessages/delete/{id}")
    Observable<Response<ResponseBody>> deletePrivateMessage(@Path("id") String id);
    //更改私信消息未读状态
    @POST("api/privateMessages/read/{id}")
    Observable<Response<ResponseBody>> modifyPrivateMessageReadStatus(@Path("id") String id);
    @Multipart
    @POST("api/file/upload")
    Observable<Response<ResponseBody>> uploadFile(@Part("file")RequestBody key, @Part MultipartBody.Part file);
    //修改用户信息
    @POST("api/member/updateMemberInfo")
    Observable<Response<ResponseBody>> modifyUserInfo(@Body RequestBody requestBody);
    //修改登录密码
    @POST("api/member/updatePassword")
    Observable<Response<ResponseBody>> findPsd(@Body RequestBody requestBody);

    //收礼消息明细
    @FormUrlEncoded
    @POST("api/myDetail/receiveGifts")
    Observable<Response<ResponseBody>>giftDetailsList(@FieldMap HashMap<String,Object>data);

    //直播时长明细
    @FormUrlEncoded
    @POST("api/myDetail/liveTime")
    Observable<Response<ResponseBody>>durationDetailsList(@FieldMap HashMap<String,Object>data);

    //用户银行卡列表
    @FormUrlEncoded
    @POST("api/memberBankCard/queryBankCard")
    Observable<Response<ResponseBody>>userBankCardList(@FieldMap HashMap<String,Object>data);
    //银行列表
    @FormUrlEncoded
    @POST("api/bankCard/page")
    Observable<Response<ResponseBody>>bankList(@FieldMap HashMap<String,Object>data);

    //添加银行卡
    @POST("api/memberBankCard/saveBankCard")
    Observable<Response<ResponseBody>>addBankcard(@Body RequestBody requestBody);
    //用户余额
    @FormUrlEncoded
    @POST("api/money/getMoney")
    Observable<Response<ResponseBody>> getMoney(@FieldMap HashMap<String,Object>data);

    //提现申请
    @POST("api/memberWithdrawRecord/withdraw")
    Observable<Response<ResponseBody>>withDraw(@Body RequestBody requestBody);

    //提现记录分页
    @POST("api/memberWithdrawRecord/page/{type}")
    Observable<Response<ResponseBody>> withdrawList(@Path("type") String type,@QueryMap Map<String,Object> data);

    //系统参数
    @FormUrlEncoded
    @POST("api/systemParams/getParams")
    Observable<Response<ResponseBody>> getSystemParams(@FieldMap HashMap<String,Object>data);

    //主播认证
    @POST("api/member/authentication")
    Observable<Response<ResponseBody>> cerication(@Body RequestBody requestBody);

    //代理认证
    @POST("api/member/agent/certification")
    Observable<Response<ResponseBody>> agentCetification(@Body RequestBody requestBody);

    //代理信息
    @FormUrlEncoded
    @POST("api/agent/agentInfo")
    Observable<Response<ResponseBody>> agentInfo (@FieldMap HashMap<String,Object>data);
    //代理中心主播列表
    @FormUrlEncoded
    @POST("api/agent/anchorInfoList")
    Observable<Response<ResponseBody>> agnetAnchorList (@FieldMap HashMap<String,Object>data);

    //主播头部数据
    @POST("api/agent/anchorDataIndex/{type}")
    Observable<Response<ResponseBody>> anchorData(@Path("type") String id);
    //主播列表数据


    //提现记录分页
    @POST("api/agent/anchorDataPage/{type}")
    Observable<Response<ResponseBody>> anchorListData(@Path("type") String type,@QueryMap Map<String,Object> data);

    //直播分类
    @FormUrlEncoded
    @POST("api/live/category/getCategory")
    Observable<Response<ResponseBody>>liveClassfy(@FieldMap HashMap<String,Object>data);
    //直播列表
    @FormUrlEncoded
    @POST("api/live/Management/page")
    Observable<Response<ResponseBody>>liveList(@FieldMap HashMap<String,Object>data);
    //轮播图
    @FormUrlEncoded
    @POST("api/carousel/page")
    Observable<Response<ResponseBody>>banner(@FieldMap HashMap<String,Object>data);

    //开启直播
    @POST("api/live/Management/createLiveRoom")
    Observable<Response<ResponseBody>> startLive(@Body RequestBody requestBody);

    @FormUrlEncoded
    @POST("api/live/Management/liveOffline")
    Observable<Response<ResponseBody>>finishLive(@FieldMap HashMap<String,Object>data);
    //未读消息
    @FormUrlEncoded
    @POST("api/systemMessages/unReadMessage")
    Observable<Response<ResponseBody>>unReadMessage(@FieldMap HashMap<String,Object>data);

    //开启直播
    @POST("api/live/Management/createSuccess")
    Observable<Response<ResponseBody>> pushSuccess(@Body RequestBody requestBody);

    //获取腾讯云配置
    @FormUrlEncoded
    @POST("api/apiLive/appSecret/getSecret")
    Observable<Response<ResponseBody>>getTXparams(@FieldMap HashMap<String,Object>data);

    //首页金额
    @FormUrlEncoded
    @POST("api/memberMoney/record/getAnchorIndex")
    Observable<Response<ResponseBody>>homeAmount(@FieldMap HashMap<String,Object>data);

    //我的钱包
    @FormUrlEncoded
    @POST("api/money/getMyWallet")
    Observable<Response<ResponseBody>>mineWallet(@FieldMap HashMap<String,Object>data);
    //收入详情列表
    @FormUrlEncoded
    @POST("api/memberMoney/record/incomeDetailPage")
    Observable<Response<ResponseBody>>incomeList(@FieldMap HashMap<String,Object>data);
    //收入详情头部
    @FormUrlEncoded
    @POST("api/memberMoney/record/incomeDetailTotal")
    Observable<Response<ResponseBody>>incomeHead(@FieldMap HashMap<String,Object>data);

    //家族首页
    @FormUrlEncoded
    @POST("api/money/patriarchIndex")
    Observable<Response<ResponseBody>> houseHome(@FieldMap HashMap<String,Object>data);

    //家族主播列表
    @FormUrlEncoded
    @POST("api/member/familyAnchorVo")
    Observable<Response<ResponseBody>> houseAnchorList(@FieldMap HashMap<String,Object>data);


    //族长修改主播密码
    @POST("api/member/updateAnchorPassword")
    Observable<Response<ResponseBody>> modifyChildPsd(@Body RequestBody requestBody);

    //族长注册主播
    @POST("api/member/patriarchRegistered")
    Observable<Response<ResponseBody>> addAnchor(@Body RequestBody requestBody);

    //家族主播资金列表
    @FormUrlEncoded
    @POST("api/money/familyAnchorMoney")
    Observable<Response<ResponseBody>> anchorAmountList(@FieldMap HashMap<String,Object> data);

    //族长提取主播资金
    @FormUrlEncoded
    @POST("api/money/extractMoney")
    Observable<Response<ResponseBody>> extractAmount(@FieldMap HashMap<String,Object> data);

    //拉黑主播
    @POST("api/member/pullBlack/{userId}")
    Observable<Response<ResponseBody>>banAnchor(@Path("userId")String streamName);

    @GET("api/appVersion/info")
    Observable<Response<ResponseBody>> appUpdate(@QueryMap Map<String,Object> data);

    //禁言
    @POST("api/gag/add")
    Observable<Response<ResponseBody>> forbidden(@Body RequestBody requestBody);

    //禁言列表
    @FormUrlEncoded
    @POST("api/gag/list")
    Observable<Response<ResponseBody>> forbiddenList(@FieldMap HashMap<String,Object>data);

    //解除禁言
    @POST("api/gag/del")
    Observable<Response<ResponseBody>> unForbidden(@Body RequestBody requestBody);
    //域名管理
    @FormUrlEncoded
    @POST("api/domainName/page")
    Observable<Response<ResponseBody>> baseUrlList(@FieldMap HashMap<String,Object>data);

    @FormUrlEncoded
    @POST("api/live/Management/changeRoomType")
    Observable<Response<ResponseBody>> changeRoomType(@FieldMap HashMap<String,Object>data);

    @POST("api/administrator/add")
    Observable<Response<ResponseBody>> setManager(@Body RequestBody requestBody);

    @FormUrlEncoded
    @POST("api/administrator/list")
    Observable<Response<ResponseBody>> manageList(@FieldMap HashMap<String,Object> data);


    @POST("api/bulletScreen/add")
    Observable<Response<ResponseBody>> commitMessage(@Body RequestBody requestBody);

    @POST("api/member/starApp/{memberId}")
    Observable<Response<ResponseBody>> appStatistics(@Path("memberId")String memberId);


    @POST("api/memberFeedback/submitFeedback")
    Observable<Response<ResponseBody>> feedBack(@Body RequestBody requestBody);


    @POST("api/memberFeedback/opinionTypeList")
    Observable<Response<ResponseBody>> feedBackType(@Body RequestBody requestBody);
    //


    @POST("api/memberFeedback/page")
    Observable<Response<ResponseBody>> feedBackList(@Body RequestBody requestBody);


    @POST("api/websiteNotice/page")
    Observable<Response<ResponseBody>> homeNotice(@Body RequestBody requestBody);

    @POST("api/live/Management/realUserCount")
    Observable<Response<ResponseBody>> watchCount(@Body RequestBody requestBody);

    @POST("api/giftRecord/userGiftRecordByAnchor")
    Observable<Response<ResponseBody>> liveRankList(@Body RequestBody requestBody);




    @POST("api/member/familyAnchorPlayTime")
    Observable<Response<ResponseBody>> anchorReport(@Body RequestBody requestBody);

/*   --------------------------------------------------------------cp接口-  全都加上统一的header 用于动态切换baseUrl 在拦截器中如果是有"urlname:cp这个header则不替换baseUrl-----------------------------------------------*/
    //彩票列表
    @Headers({"urlname:cp"})
    @GET("api/live/category/getGameClass")
    Observable<Response<ResponseBody>>lotteryList(@QueryMap Map<String,Object>data);

    //彩种列表
    @Headers({"urlname:cp"})
    @POST("game/navigate.json")
    Observable<Response<ResponseBody>> cpLptteryList(@Body RequestBody requestBody);

    //上期开奖结果
    @Headers({"urlname:cp"})
    @POST("{url}")
    Observable<Response<ResponseBody>>  getLastLottery(@Path(value = "url",encoded = true)String url,@QueryMap Map<String,Object> data);

    @GET("auth/oauth/token/test")
    Observable<Response<ResponseBody>> pingTest(@QueryMap Map<String, Object> data);

    //倒计时
    @Headers({"urlname:cp"})
    @POST("{url}")
    Observable<Response<ResponseBody>>  getCountDown(@Path(value = "url",encoded = true)String url,@QueryMap Map<String,Object> data);

    @Headers({"urlname:cp"})
    @POST("happy/lastLottery.json")
    Observable<Response<ResponseBody>> happyLast(@Body RequestBody requestBody);
    @Headers({"urlname:cp"})
    @POST("game/lastLottery")
    Observable<Response<ResponseBody>> kuaisanLast(@Body RequestBody requestBody);
    @Headers({"urlname:cp"})
    @POST("farm/lastLottery.json")
    Observable<Response<ResponseBody>> farmLast(@Body RequestBody requestBody);
    @Headers({"urlname:cp"})
    @POST("happyten/lastLottery.json")
    Observable<Response<ResponseBody>> tenLast(@Body RequestBody requestBody);
    @Headers({"urlname:cp"})
    @POST("dan/lastLottery.json")
    Observable<Response<ResponseBody>> danLast(@Body RequestBody requestBody);
    @Headers({"urlname:cp"})
    @POST("race/lastLottery")
    Observable<Response<ResponseBody>> raceLast(@Body RequestBody requestBody);
    @Headers({"urlname:cp"})
    @POST("marksix/lastLottery.json")
    Observable<Response<ResponseBody>> sixLast(@Body RequestBody requestBody);
    @Headers({"urlname:cp"})
    @POST("xuanwu/lastLottery.json")
    Observable<Response<ResponseBody>> xuanLast(@Body RequestBody requestBody);
    @Headers({"urlname:cp"})
    @POST("sscai/lastLottery")
    Observable<Response<ResponseBody>> sscLst(@Body RequestBody requestBody);


;
}