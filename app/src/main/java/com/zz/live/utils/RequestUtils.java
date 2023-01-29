package com.zz.live.utils;

public class RequestUtils {
    /**
     * 注册
     */
    public static String REGISTER = "api/member/regUser";
    /**
     * 登录
     */
    public static String LOGIN="auth/oauth/token?grant_type=password";

    /**
     * 获取用户信息
     */
    public static String USER_INFO="api/member/info";

    /**
     * 排行榜
     */
    public static String RANK_LIST="api/ranking/incomeRanking";

    /**
     * 系统消息 (列表)
     */
    public static String SYSTEM_MESSAGE="api/systemMessages/systemMessage";
    /**
     * 删除系统消息 (列表)
     */
    public static String DELETE_SYSTEM_MESSAGE="api/systemMessages/deleteMessage/{type}";

    /**
     * 系统消息列表(分页查询)
     * current
     * size
     */
    public static String SYSTEM_MESSAGE_LIST="api/systemMessages/page";

    /**
     * 修改系统消息已读状态
     */
    public static String MODIFY_SYSTEM_MESSAGE_READ_STATUS="api/systemMessages/read/1";

    /**
     * 关注消息列表
     */
    public static String FOLLOW_MESSAGE_LIST="api/followMessage/page";

    /**
     * 私信消息列表
     * current
     * size
     */
    public static String PRIVATE_MESSAGE_LIST="api/privateMessages/page";

    /**
     * 删除私信消息
     */
    public static String DELETE_PRIVATE_MESSAGE="api/privateMessages/delete/{id}";

    /**
     * 文件上传
     */
    public static String UOLOAD_FILE="api/file/upload";


    /**
     * 修改用户信息
     * image 选填
     * nickname 选填
     * phone 选填
     * sex 选填
     */
    public static String MODIFY_USERINFO="api/member/updateMemberInfo";

    /**
     * 修改登录密码
     * newPassword
     * oldPassword
     * type 类型1登录密码2支付密码
     */
    public static String FIND_PASSWORD="api/member/updatePassword";

    /**
     * 收礼明细
     */
    public static String GIFT_DETATILS_LIST="api/myDetail/receiveGifts";

    /**
     * 直播时长
     */
    public static String LIVE_DURATION_LIST="api/myDetail/liveTime";

    /**
     * 查询用户银行卡列表
     */
    public static String USER_BANK_CARD_LIST ="api/memberBankCard/queryBankCard";

    /**
     * 获取可绑定的银行列表
     */
    public static  String BANK_LIST ="api/bankCard/page";

    /**
     * 添加银行卡
     * bankName
     * branchName
     * cardNumber
     * realName
     * */
    public static String ADD_BANKCORD="api/memberBankCard/saveBankCard";

    /**
     * 用户余额
     */
    public static  String USER_MONEY="api/money/getMoney";

    /**
     * 提现申请
     * amount
     * bankCardId
     * remark  备注(可不传)
     */
    public static String WITHDRAW_APPLICATION="api/memberWithdrawRecord/withdraw";

    /**
     * 提现记录列表分页查询
     */
    public static String WITHDRAW_LIST="api/memberWithdrawRecord/page/{type}";

    /**
     * 系统参数
     */
    public static String SYSTEM_PATAMS="api/systemParams/getParams";

    /**
     * 主播认证
     * identityCard 身份证
     * phone
     * realName
     * verifyPictures 认证图片(逗号分割)
     */
    public static String CERTIFICATION="api/member/authentication";

    /**
     * 代理认证
     */
    public static String AGENT_CERTIFICATON="api/member/agent/certification";

    /**
     * 代理信息
     */
    public static String AGENT_INFO="api/agent/agentInfo";

    /**
     * 代理中心下级列表
     */
    public static String AGENT_ANCHOR_LIST = "api/agent/anchorInfoList";

    /**
     * 主播数据头部
     */
    public static String ANCHOR_DATA="api/agent/anchorDataIndex/{type}";

    /**
     * 主播列表数据
     */
    public static String ANCHOR_LIST_DATA="api/agent/anchorDataPage/{type}";

    /**
     * 直播分类
     */
    public static String LIVE_CLASSFY="api/live/category/getCategory";

    /**
     * 直播列表
     */
    public static String LIVE_LIST="api/live/Management/page";

    /**
     * 轮播图管理
     */
    public static String BANNER="api/carousel/page";

    /**
     * 创建直播
     */
    public static String START_LIVE="api/live/Management/createLiveRoom";

    /**
     * 彩票列表
     */
    public static String LOTTERY_LIST="api/live/category/getGameClass";

    /**
     * 结束直播
     */
    public static String FINISH_LIVE="api/live/Management/liveOffline";

    /**
     * 未读消息
     */
    public static String UNREAD_MESSAGE="api/systemMessages/unReadMessage";

    /**
     * 推流成功
     * id
     * pushUrl
     * streamName
     */
    public static String PUSH_SUCCESS="api/live/Management/createSuccess";

    /**
     * 获取腾讯云配置数据
     */
    public static String TX_LIVE_PARAMS="api/apiLive/appSecret/getSecret";

    /**
     * 首页金额
     */
    public static String HOME_HEAD_DATA="api/memberMoney/record/getAnchorIndex";

    /**
     * 我的钱包
     */
    public static String MINE_WALLET="api/money/getMyWallet";

    /**
     * 收入详情;列表
     */
    public static String INCOME_LIST="api/memberMoney/record/incomeDetailPage";

    /**
     * 收入详情  头部
     *dateType
     * type
     * userId 族长查看下级时需要传(即ChildAnchorActivity中需要传)
     */
    public static String INCOME_HEAD="api/memberMoney/record/incomeDetailTotal";

    /**
     * 家族首页
     * dataType
     */
    public static String HOUSE_HOME="api/money/patriarchIndex";

    /**
     * keyWords 搜索关键词
     * 家族主播列表
     */
    public static String HOUSE_ANCHOR_LIST="api/member/familyAnchorVo";

    /**
     * 族长修改主播密码
     * memberId 主播id
     * newPassword 新密码
     * oldPassword 旧密码
     * patriarchPassword 族长面
     * type 类型1登录密码2支付密码
     */;
    public static String MODIFY_CHILD_ANCHOR_PASSWORD="api/member/updateAnchorPassword";

    /**
     * 族长注册主播
     *  password
     *  username
     */
    public static String ADD_ANCHOR="api/member/patriarchRegistered";
    /**
     * 家族主播资金表
     *
     */
    public static String ANCHOR_AMOUNT_LIST="api/money/familyAnchorMoney";

    /**
     * 族长提取主播资金
     * userId 为空提取全部
     */
    public static String EXTRACT_AMOUNT="api/money/extractMoney";

    /**
     * 拉黑主播
     */
    public static String BAN_ANCHOR="api/member/pullBlack/{userId}";

    /**
     * 彩种列表
     */
    public static String CP_LOTTERY_LIST="game/navigate.json";

    /**
     * 北京快乐8开奖结果
     */
    public static String REQUEST_04ha = "happy/lastLottery.json";

    /**
     * 快3骰宝游戏期数
     */
    public static String REQUEST_8r = "game/lastLottery";

    /**
     * 幸运农场开奖结果
     */
    public static String REQUEST_04farm = "farm/lastLottery.json";

    /**
     * 广东快乐十分开奖结果
     */
    public static String REQUEST_04happyten = "happyten/lastLottery.json";

    /**
     * PC蛋蛋近期开奖
     */
    public static String REQUEST_05dd = "dan/lastLottery.json";

    /**
     * 北京赛车近期开奖
     */
    public static String REQUEST_26r = "race/lastLottery";

    /**
     * 六合彩开奖结果
     */
    public static String REQUEST_05lhc = "marksix/lastLottery.json";

    /**
     * 广东11选5开奖结果
     */
    public static String REQUEST_04xuanwu = "xuanwu/lastLottery.json";
    /**
     * 近期开奖
     */
    public static String REQUEST_19r = "sscai/lastLottery";

    /**
     * 版本更新
     */
    public static String APP_UPDATE="api/appVersion/info";

    /**
     * 禁言
     */

    public static String FORBIDDEN="api/gag/add";

    /**
     * 禁言列表
     * chatRoomId
     */
    public static  String FORBIDDEN_LIST="api/gag/list";

    /**
     * 会员禁言
     * chatRoomId
     * rongMemberId[]
     */
    public static String UN_FORBIDDEN_LIST="api/gag/del";

    /**
     * 域名列表
     */
    public static String BASE_URL_LIST="api/domainName/page";

    /**
     * 收费/免费房间切换
     */
    public static String CHANGE_ROOM_TYPE="api/live/Management/changeRoomType";

    /**
     * 设置房管
     */
    public static String SET_MANAGE="api/administrator/add";

    /**
     * 房管列表
     */
    public static String MANAGE_LIST="api/administrator/list";
    /**
     *上传弹幕信息
     */
        public static String COMMIT_MESSAGE="api/bulletScreen/add";
    /**
     * 提交意见反馈
     */
    public static String FEED_BACK="api/memberFeedback/submitFeedback";

    /**
     * 意见反馈类型列表
     *
     */
    public static String FEEDBACK_TYPE="api/memberFeedback/opinionTypeList";

    /**
     * 我的反馈列表
     * current 分页
     * size
     */
    public static String MINE_FEEDBACK_LIST ="api/memberFeedback/page";
    /**
     * app启动统计
     */
    public static String APP_STATISTICS="api/member/starApp/{memberId}";


    /**
     * 首页弹窗
     */
    public static String HOME_NOTICE="api/websiteNotice/page";

    /**
     * 直播间真实在线人数
     */

    public static String ONLINE_WATCH_NUM="api/live/Management/realUserCount";

    /**
     *直播间排行榜
     */
    public static String LIVE_RANK_LIST="api/giftRecord/userGiftRecordByAnchor";

    /**
     * 主播报表
     */
    public static String ANCHOR_REPORT="api/member/familyAnchorPlayTime";

}
