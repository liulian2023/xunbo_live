package com.zz.live.ui.activity.start_live_activity;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.hardware.Camera;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.text.Editable;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Group;
import androidx.core.content.FileProvider;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.cambodia.zhanbang.rxhttp.net.common.BaseStringObserver;
import com.cambodia.zhanbang.rxhttp.net.utils.LogUtils;
import com.cambodia.zhanbang.rxhttp.net.utils.RxTransformerUtils;
import com.cambodia.zhanbang.rxhttp.sp.SharedPreferenceHelperImpl;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.faceunity.nama.FURenderer;
import com.faceunity.nama.IFURenderer;
import com.google.gson.Gson;
import com.lovense.sdklibrary.Lovense;
import com.lovense.sdklibrary.LovenseToy;
import com.zz.live.BuildConfig;
import com.zz.live.R;
import com.zz.live.bean.LiveGiftSVGAEntity;
import com.zz.live.bean.ProvinceJsonBean;
import com.zz.live.bean.SystemMessageModel;
import com.zz.live.bean.ToyConnectEvent;
import com.zz.live.myView.gift.AssetsSvgaManage;
import com.zz.live.ui.activity.main_tab_activity.ToyActivity;
import com.zz.live.ui.beauty.CameraPush;
import com.faceunity.nama.ui.BeautyControlView;
import com.faceunity.nama.ui.FaceUnityView;
import com.faceunity.nama.utils.CameraUtils;
import com.gyf.immersionbar.BarHide;
import com.gyf.immersionbar.ImmersionBar;

import com.opensource.svgaplayer.SVGAImageView;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.tencent.rtmp.ITXLivePushListener;
import com.tencent.rtmp.TXLiveBase;
import com.tencent.rtmp.TXLiveConstants;
import com.tencent.rtmp.TXLivePushConfig;
import com.tencent.rtmp.TXLivePusher;
import com.tencent.rtmp.ui.TXCloudVideoView;
import com.uber.autodispose.AutoDispose;
import com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider;
import com.zz.live.MyApplication;

import com.zz.live.base.BaseActivity;
import com.zz.live.base.BasePopupWindow;
import com.zz.live.base.Const;
import com.zz.live.bean.ChangeRoomTypeEntity;
import com.zz.live.bean.ChatUserEntity;
import com.zz.live.bean.GameTypeEnum;
import com.zz.live.bean.HomeClassfyEntity;
import com.zz.live.bean.LastLotteryEntity;
import com.zz.live.bean.LiveMessageModel;
import com.zz.live.bean.LiveParamEntity;
import com.zz.live.bean.LotteryEntitiy;
import com.zz.live.bean.NatureEntity;
import com.zz.live.bean.OpenNoMulBean;
import com.zz.live.bean.UserInfoEntity;
import com.zz.live.bean.UserMoneyEntity;
import com.zz.live.bean.ZjMessageModel;
import com.zz.live.bean.evenBus.CountDownEven;
import com.zz.live.bean.evenBus.leaveAppEvenModel;
import com.zz.live.bean.evenBus.StopPushEvenModel;
import com.zz.live.myView.AutoLineFeedLayoutManager;
import com.zz.live.myView.gift.GiftSendModel;
import com.zz.live.myView.gift.GiftView;
import com.zz.live.myView.gift.JoinSvgaMage;
import com.zz.live.myView.gift.GiftSvgaManage;
import com.zz.live.net.api.HttpApiImpl;
import com.zz.live.net.api.HttpApiUtils;
import com.zz.live.ui.activity.start_live_activity.bottom_fragment.BottomDialogFragment;
import com.zz.live.ui.activity.mine_fragment_activity.MineDetailsActivity;
import com.zz.live.ui.activity.play_live_activity.EditPanel;
import com.zz.live.ui.activity.play_live_activity.LiveChatFragment;
import com.zz.live.ui.activity.start_live_activity.rank_fragment.LiveRankDialogFragment;
import com.zz.live.ui.adapter.ChatUserAdapter;
import com.zz.live.ui.adapter.OpenNoMulAdapter;
import com.zz.live.ui.beauty.CameraPushImpl;
import com.zz.live.ui.beauty.PusherBeautyKit;
import com.zz.live.ui.pop.BeautyPop;
import com.zz.live.ui.pop.ChangeRoomTypePop;
import com.zz.live.ui.pop.ChooseNaturePop;
import com.zz.live.ui.pop.CommonChoosePop;
import com.zz.live.ui.pop.CommonTipPop;
import com.zz.live.ui.pop.LiveMorePop;
import com.zz.live.ui.pop.SetBusinessCardPop;
import com.zz.live.ui.pop.SetRoomTypePop;
import com.zz.live.ui.pop.TakeCameraPop;
import com.zz.live.ui.rongyun.RongLibUtils;
import com.zz.live.ui.rongyun.message.ForbiddenMessage;
import com.zz.live.ui.rongyun.message.LiveExitAndJoinMessage;
import com.zz.live.ui.rongyun.message.LiveFollowMessage;
import com.zz.live.ui.rongyun.message.LiveGiftMessage;
import com.zz.live.ui.rongyun.message.LiveNormalRedMessage;
import com.zz.live.ui.rongyun.message.LiveRedMessage;
import com.zz.live.ui.rongyun.message.LiveRewardMessage;
import com.zz.live.ui.rongyun.message.LiveShareBetMessage;
import com.zz.live.ui.rongyun.message.LiveSystemMessage;
import com.zz.live.ui.rongyun.message.LiveTextMessage;
import com.zz.live.ui.rongyun.message.RoomManageMessage;
import com.zz.live.ui.rongyun.message.SwichMoneyMessage;
import com.zz.live.utils.AESUtil;
import com.zz.live.utils.BitmapUtils;
import com.zz.live.utils.CommonStr;
import com.zz.live.utils.CustomPopupWindow;
import com.zz.live.utils.DrawerLeftEdgeSize;
import com.zz.live.utils.GetJsonDataUtil;
import com.zz.live.utils.GetPhotoFromPhotoAlbum;
import com.zz.live.utils.GlideLoadViewUtil;
import com.zz.live.utils.HeightProvider;
import com.zz.live.utils.ImageThumbnail;
import com.zz.live.utils.KeyboardHeightProvider;
import com.zz.live.utils.RequestUtils;
import com.zz.live.utils.SharePreferencesUtil;
import com.zz.live.utils.StatusBarUtil;
import com.zz.live.utils.StringMyUtil;
import com.zz.live.utils.SystemUtil;
import com.zz.live.utils.Utils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.File;
import java.lang.ref.WeakReference;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import butterknife.BindView;
import butterknife.OnClick;
import butterknife.OnTextChanged;
import butterknife.OnTouch;
import cn.nodemedia.NodeCameraView;
import cn.nodemedia.NodePublisher;
import cn.nodemedia.NodePublisherDelegate;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.ChatRoomInfo;
import io.rong.imlib.model.MessageContent;
import io.rong.message.TextMessage;
import okhttp3.Headers;
import okhttp3.ResponseBody;
import pl.droidsonroids.gif.GifImageView;
import pub.devrel.easypermissions.EasyPermissions;

import static android.graphics.BitmapFactory.decodeResource;
import static android.view.View.GONE;
import static android.view.View.VISIBLE;
import static cn.nodemedia.NodePublisher.AUDIO_PROFILE_HEAAC;
import static com.tencent.rtmp.TXLiveConstants.PAUSE_FLAG_PAUSE_VIDEO;
import static com.tencent.rtmp.TXLiveConstants.VIDEO_QUALITY_SUPER_DEFINITION;
import static com.zz.live.bean.GameTypeEnum.MARKSIX;
import static com.zz.live.bean.GameTypeEnum.valueOf;
import static com.zz.live.utils.DateUtil.timeMode;
import static io.rong.imlib.RongIMClient.ErrorCode.FORBIDDEN_IN_CHATROOM;

public class StartLiveActivity extends BaseActivity implements BasePopupWindow.OnPopClickListener,
        EasyPermissions.PermissionCallbacks,  TXLivePusher.VideoCustomProcessListener,
        BasePopupWindow.OnRecycleItemClick, FURenderer.OnTrackStatusChangedListener, SensorEventListener {
    private String TAG="StartLiveActivity";
    @BindView(R.id.pusher_tx_cloud_view)
    TXCloudVideoView mPusherView;
    @BindView(R.id.node_pusher_view)
    NodeCameraView node_pusher_view;
    @BindView(R.id.beauty_iv)
    ImageView beauty_iv;
    @BindView(R.id.live_constrainLayout_group)
    Group live_constrainLayout_group;
    @BindView(R.id.start_live_btn)
    Button start_live_btn;
    @BindView(R.id.change_camera_iv)
    ImageView change_camera_iv;
    @BindView(R.id.start_live_titile_tv)
    TextView start_live_titile_tv;
    @BindView(R.id.start_live_title_etv)
    EditText start_live_title_etv;
    @BindView(R.id.start_live_constraintLayout)
    ConstraintLayout start_live_constraintLayout;
    @BindView(R.id.add_title_frameLayout)
    FrameLayout add_title_frameLayout;
    @BindView(R.id.choose_channel_iv)
    ImageView choose_channel_iv;
    @BindView(R.id.choose_channel_tv)
    TextView choose_channel_tv;
    @BindView(R.id.cover_iv)
    ImageView cover_iv;
    @BindView(R.id.linearLayout7)
    public LinearLayout linearLayout7;
    @BindView(R.id.live_edit_panel)
    EditPanel editPanel;
    @BindView(R.id.drawwe_linear)
    ConstraintLayout drawwe_linear;
    /*  @BindView(R.id.iv_avatar)
      ImageView iv_avatar;
      @BindView(R.id.tv_name)
      TextView tv_name;
      @BindView(R.id.tv_id)
      TextView tv_id;*/
    @BindView(R.id.tv_num)
    TextView tv_num;
    @BindView(R.id.giftView)
    public GiftView giftView;
    @BindView(R.id.money_tv)
    TextView money_tv;
    @BindView(R.id.tv_countdown)
    TextView tv_countdown;
    @BindView(R.id.svga_imageview)
    public SVGAImageView svgaImageView;
    @BindView(R.id.pusher_drawerLayout)
    DrawerLayout pusher_drawerLayout;
    @BindView(R.id.bottom_pop_iv)
    ImageView iv_bottomgift;
    @BindView(R.id.meiyan_iv)
    ImageView iv_centre;
    @BindView(R.id.iv_close)
    ImageView iv_close;
    @BindView(R.id.choose_lottery_tv)
    TextView choose_lottery_tv;
    @BindView(R.id.more_iv)
    ImageView more_iv;
    @BindView(R.id.choose_lottery_iv)
    ImageView choose_lottery_iv;
    @BindView(R.id.lottrry_open_result_constraintLayout)
    ConstraintLayout lottrry_open_result_constraintLayout;
    @BindView(R.id.open_result_top_layout)
    ConstraintLayout open_result_top_layout;
    @BindView(R.id.tv_lottery_name)
    TextView tv_lottery_name;
    @BindView(R.id.tv_lottery_qishu)
    TextView tv_lottery_qishu;
    @BindView(R.id.top_close_iv)
    ImageView top_close_iv;
    @BindView(R.id.dismiss_open_result_iv)
    ImageView dismiss_open_result_iv;
    @BindView(R.id.recy_lottery)
    RecyclerView recy_lottery;
    @BindView(R.id.wrap_frameLayout)
    FrameLayout wrap_frameLayout;
    private OpenNoMulAdapter mOpenNoMulAdapter;
    private List<OpenNoMulBean> mOpenNoMulList = new ArrayList<>();//开奖结果列表
    @BindView(R.id.countdown_iv)
    GifImageView countdown_iv;
    @BindView(R.id.live_time_tv)
    TextView live_time_tv;
    @BindView(R.id.iv_lottery)
    ImageView iv_lottery;
    @BindView(R.id.set_business_tv)
    TextView set_business_tv;
    @BindView(R.id.set_business_iv)
    ImageView set_business_iv;
    @BindView(R.id.ll_gift)
    LinearLayout ll_gift;
    @BindView(R.id.room_type_tv)
    TextView room_type_tv;
    @BindView(R.id.room_type_iv)
    ImageView room_type_iv;
    @BindView(R.id.change_room_type_iv)
    ImageView change_room_type_iv;
    @BindView(R.id.nature_tv)
    TextView nature_tv;
    @BindView(R.id.nature_iv)
    ImageView nature_iv;
    @BindView(R.id.recy_renshu)
    RecyclerView recy_renshu;
    @BindView(R.id.speed_tv)
    TextView speed_tv;
    @BindView(R.id.rank_linear)
    LinearLayout rank_linear;
    @BindView(R.id.face_unity_view)
    FaceUnityView faceUnityView;
    ChatUserAdapter chatUserAdapter;
    ArrayList<ChatUserEntity>chatUserEntityArrayList= new ArrayList<>();
    @BindView(R.id.join_svga_imageview)
    SVGAImageView join_svga_imageview;
    @BindView(R.id.test_svga_imageview)
    SVGAImageView test_svga_imageview;
    @BindView(R.id.area_iv)
    ImageView area_iv;
    @BindView(R.id.area_tv)
    TextView area_tv;
    @BindView(R.id.face_wrap_linear)
    LinearLayout face_wrap_linear;
    @BindView(R.id.face_top_linear)
    LinearLayout face_top_linear;
    @BindView(R.id.count_down_fail_refresh_tv)
    TextView count_down_fail_refresh_tv;
    @BindView(R.id.count_down_fail_loading_iv)
    GifImageView count_down_fail_loading_iv;
    @BindView(R.id.toy_tv)
    TextView toy_tv;
    @BindView(R.id.toy_iv)
    ImageView toy_iv;
    @BindView(R.id.toy_disconnect_tv)
    TextView toy_disconnect_tv;
    CommonChoosePop toll2FreeTipPop;
    CommonChoosePop free2TollTipPop;
    private KeyboardHeightProvider mKeyboardHeightProvider;

    private  ArrayList<LiveGiftMessage>gearMessageList = new ArrayList<>();
    /**
     * 是否在推流 在推流时返回需要弹窗,确定退出后跳转直播结束页面
     */
    boolean isPusher =false;
    //    TXLivePusher mLivePusher;
    private CameraPush mLivePusher;
    // 当前是否正在获取 RTMP 链接
    private boolean  mIsGettingRTMPURL = false;
    //美颜界面管理
    private PusherBeautyKit manager;
    //美颜设置pop
    BeautyPop beautyPop;
    //退出直播pop
    CommonChoosePop commonChoosePop;
    //选择的频道id
    private String categoryId;
    //封面url
    private String cover;
    //选择彩票id
    private String gameClassId="";
    //输入的直播间标题
    private String title;
    private String area;
    TakeCameraPop takeCameraPop;
    private File cameraSavePath;
    //拍照返回
    private Uri uri;
    private int CAMARE_REQUEST_CODE = 1;//调用相机的请求码
    private int PHOTO_REQUEST_CODE = 2;//调用相机的请求码
    private int SKIP_CHOOSE_CHANNEL=3;//跳转频道选择的请求码
    private int SKIP_CHOOSE_LOTTERY=4;//跳转彩票选择的请求码
    private int SKIP_TOY=5;//跳转玩具
    LiveChatFragment liveChatFragment;
    private String[] PERMISSIONS={"android.permission.CAMERA",
            "android.permission.WRITE_EXTERNAL_STORAGE"
            ,"android.permission.READ_EXTERNAL_STORAGE"};
    private String streamName;
    UserInfoEntity.DataBean userDataBean;
    SharedPreferenceHelperImpl sp = new SharedPreferenceHelperImpl();
    private GiftSvgaManage giftSvgaManage;
    private JoinSvgaMage joinGiftSvgaMage;
    public AssetsSvgaManage assetsSvgaManage;
    //当前真实人数
    private int totalMemberCount;
    //最高峰人数
    private int mostHighMemberCount;
    private JSONObject pushObject;
    private String rtmpPushUrl;
    private PublishSubject onlineNumObservable=PublishSubject.create();
    private LiveMorePop liveMorePop;
    //当前是否是后置摄像头(控制liveMorePop的图标显示)
    boolean isBackCamera = false;
    //是否开启了闪光灯
    private boolean isOnLight =false;
    //是否已经静音
    private boolean isMute =false;
    //选择的彩种been
    private LotteryEntitiy.GameClasslistBean dataBean;
    private String lastqishu;//上期期数
    private String qishu;//期数
    private boolean isWaitopen = true;
    private String nowQishu;
    private long jgTime = 3000;
    private String tqtime;
    private long millionSeconds;
    private Long shijiancha;
    private long countTime;
    private long liveTime;
    private String isStart;
    private List<String> openResultList;
    private boolean showOpenResult=false;
    private Timer openResultTimer;
    boolean isPausePusher=false;
    private CustomPopupWindow customPopupWindow = new CustomPopupWindow();
    //观看人数倍数
    int watchRadio;
    private int[] avatatArray;
    private SetBusinessCardPop setBusinessCardPop;
    //设置的名片微信号
    private String busonessCard;
    //开播前设置房间类型pop
    SetRoomTypePop unPusherSetRoomTypePop;
    //开播后切换房间类型
    SetRoomTypePop pusherChangeRoomTypePop;
    // 房间类型(1普通2收费)
    int roomType=1;
    //    收费房间金额
    private String roomAmount="0";
    //切换房间类型pop
    ChangeRoomTypePop changeRoomTypePop;



    ChooseNaturePop  chooseNaturePop;
    private ArrayList<NatureEntity> natureEntityArrayList;
    private String natureStr;
    private boolean countDownStatus =true;

    private LiveRankDialogFragment liveRankDialogFragment;
    private int mFrontCameraOrientation;

    private List<ProvinceJsonBean> provinceOptionsItems = new ArrayList<>();//省份jsonBean list
    private ArrayList<ArrayList<String>> cityOptionsItems = new ArrayList<>();//城市jsonBean list
    private ArrayList<ArrayList<ArrayList<String>>> areaOptionsItems = new ArrayList<>();//地区jsonBean list
    boolean isCityLoaded=false;
    private MyHandler handler = new MyHandler();
    //收集的普通文本消息(5秒上传一次)
    private ArrayList<LiveMessageModel> commitLiveMessageModelList = new ArrayList<>();
    //上次上传的最后一个消息
    private LiveMessageModel currentTextMessage;
    private TextView mTvTrackStatus;
    private BeautyControlView beautyControlView;
    private FURenderer mFURenderer;
    private int mCameraFacing = IFURenderer.CAMERA_FACING_FRONT;
    private long currentTime;
    private boolean mIsFirstFrame = true;
    private SensorManager mSensorManager;

    public static Boolean USE_TX=true;
    private NodePublisher nodePublisher;

    private int countDownFailCount=0;
    private PublishSubject countDownFailSubject =PublishSubject.create();
    private int isUseToy=0;
    private String toyId;
    private boolean isGearing = false;

    @Override
    public int getLayoutId() {
        return R.layout.activity_start_live;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        if(!EventBus.getDefault().isRegistered(this)){
            EventBus.getDefault().register(this);
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                initJsonData();
            }
        }).start();
        mFrontCameraOrientation = CameraUtils.getCameraOrientation(Camera.CameraInfo.CAMERA_FACING_FRONT);
//        mKeyboardHeightProvider = new KeyboardHeightProvider(this);
        if(USE_TX){

            mPusherView.setVisibility(VISIBLE);
            node_pusher_view.setVisibility(GONE);
            requestTxLiveParam();
        }else {
            mPusherView.setVisibility(View.GONE);
            node_pusher_view.setVisibility(VISIBLE);
            /**
             * 初始化加密推流
             */
            nodePublisher = new NodePublisher(this,"NjAyNWM1MDAtNGU0ZDUzMjEtY29tLnp6LmxpdmU=-vfRf5M5IU1O8d3zSKZtprvw0kQIKeyHtrjn8da6hDbJ1jVBvi" +
                    "3Cnvigwatg4FhKkmnfjuaDygXTVnofz1x/L7g7/NpbfodxMXGKjblYfd5w8pgCbkEesAqZahjB1igdfQ/IVMUmknsGhyhZKS0LA38tAq6sFJ/yEchMMcFKjgZnae+dpEK4rpzvpsPRgxhb" +
                    "7+l/vIWHZ7eHnG+AsHdbgeIFAfyRZAtTJm2OuZoB2V3d8eZZHzOLFYdqU1r2RP9uVNbCpb9r1VmrVcIvGGLPffkTyWeZ6dbtbG45bubH4OJku4xYG3tvH6B/zCspk95sAOIu9Jwmp7OxYR" +
                    "Zvniy3GBQ==");
        }
        userDataBean=  Utils.getUserInfoBean();
        cameraSavePath = new File(Environment.getExternalStorageDirectory().getPath() + "/" + System.currentTimeMillis() + ".jpg");
        initDrawerLayout();
        checkPermissions();



        /**
         * 设置推流配置 (放在权限申请成功后初始化,避免第一次进入时, 没有权限就开启摄像头预览,导致一直黑屏)
         */
//        initPusher();
        /**
         * 聊天列表fragment
         */
        initLiveChatFragment();
        /**
         * 订阅人数变化
         */
        observableOnlineNum();
        /**
         * 订阅彩种倒计时开奖结果失败次数
         */
        observableCountFailCount();
        /**
         * 设置开播时的各种缓存数据
         */
        setDefaultData();

        editPanel.setStartLiveActivity(this);
        editPanel.setRoomId(sp.getRoomId());
        /**
         * 蓝牙玩具初始化
         */
        Lovense.getInstance(getApplication()).setDeveloperToken("xK23V1OSkxI9bYAZbhb1edh6IZ3wes8/etYnus4mt8ZM+Yg0OAQOXwQf/kG2oDY3");

    }

    private void setDefaultData() {
        /**
         * 设置默认名片
         */
        setDefaultBusinessCard();
        /**
         * 设置默认直播性质
         */
        setDefaultNature();
        /**
         * 设置默认彩票
         */
        setDefaultLottery();
        /**
         * 设置默认封面
         */
        setDefaultCover();
        /**
         * 设置默认标题
         */
        setDefaultTitle();
        /**
         * 设置默认频道
         */
//        setDefaultCategory();
        /**
         * 设置默认地区
         */
        setDefaultArea();
    }

    private void setDefaultArea() {
        area = SharePreferencesUtil.getString(MyApplication.getInstance(),CommonStr.LIVE_AREA,"");
        area_tv.setText(StringMyUtil.isEmptyString(area)?"选择地区":area);
    }

    private void setDefaultCategory() {
        String channelName = SharePreferencesUtil.getString(MyApplication.getInstance(), CommonStr.CHOOSE_CHANNEL_NAME, "");
        choose_channel_tv.setText(StringMyUtil.isEmptyString(channelName)?"频道":channelName);
        categoryId = SharePreferencesUtil.getString(MyApplication.getInstance(), CommonStr.CHOOSE_CHANNEL_ID, "");
    }

    private void setDefaultTitle() {
        title = SharePreferencesUtil.getString(MyApplication.getInstance(),CommonStr.LIVE_TITLE,"");
        if(StringMyUtil.isNotEmpty(title)){
            start_live_title_etv.setText(title);
        }
    }

    private void setDefaultCover() {
        cover = SharePreferencesUtil.getString(MyApplication.getInstance(), CommonStr.CHOOSE_LIVE_COVER, "");
        if(StringMyUtil.isNotEmpty(cover)){
            GlideLoadViewUtil.LoadNormalView(this,cover,cover_iv);
        }
    }

    private void setDefaultLottery() {
        String bean = SharePreferencesUtil.getString(MyApplication.getInstance(), CommonStr.CHOOSE_LOTTERY_BEAN, "");
        if(StringMyUtil.isNotEmpty(bean)){
            dataBean = JSONObject.parseObject(bean, LotteryEntitiy.GameClasslistBean.class);
            choose_lottery_tv.setText(StringMyUtil.isEmptyString(dataBean.getTypename())?"彩票":dataBean.getTypename());
            gameClassId = dataBean.getId();
        }else {
            //没有选择过彩种,默认选择一分快三
            requestLotteryList();
        }
    }

    private void setDefaultNature() {
        String natureContent = SharePreferencesUtil.getString(MyApplication.getInstance(), CommonStr.CHOOSE_NATURE, "");
        nature_tv.setText(StringMyUtil.isEmptyString(natureContent) ? "直播性质" : natureContent);
        natureStr= StringMyUtil.isEmptyString(natureContent) ? "" : natureContent;
    }

    /**
     * 设置默认名片
     */
    private void setDefaultBusinessCard() {
        String forbidCallingCard = Utils.getUserInfoBean().getForbidCallingCard();
        String callingCard = Utils.getUserInfoBean().getCallingCard();
        set_business_tv.setText(StringMyUtil.isEmptyString(callingCard)?"名片":callingCard);
        String businness = set_business_tv.getText().toString();
        if(!businness.equals("名片")){
            busonessCard = businness;
        }

        if(forbidCallingCard.equals("1")){
            busonessCard="名片";
        }
    }
    private void requestLotteryList() {
        HashMap<String, Object> data = new HashMap<>();
        data.put("isAnchorUse",1);
        HttpApiUtils.cpNormalRequest(this, null, RequestUtils.CP_LOTTERY_LIST, data, new HttpApiUtils.OnRequestLintener() {
            @Override
            public void onSuccess(String result) {
                LotteryEntitiy lotteryEntitiy = JSONObject.parseObject(result, LotteryEntitiy.class);
                List<LotteryEntitiy.GameClasslistBean> data = lotteryEntitiy.getGameClasslist();
                for (int i = 0; i < data.size(); i++) {
                    LotteryEntitiy.GameClasslistBean gameClasslistBean = data.get(i);
                    String typename = gameClasslistBean.getTypename();
                    if(typename.equals("一分快三")){
                        dataBean = gameClasslistBean;
                        String id = gameClasslistBean.getId();
                        choose_lottery_tv.setText(typename);
                        gameClassId = id;
                        SharePreferencesUtil.putString(MyApplication.getInstance(),CommonStr.CHOOSE_LOTTERY_BEAN, JSON.toJSONString(dataBean));//设置选择的彩票bean
                    }
                }
            }

            @Override
            public void onFail(String msg) {
            }
        });
    }
    /**
     * 请求腾讯直播licence  key
     */
    private void requestTxLiveParam() {
        HttpApiUtils.wwwNormalRequest(this, null,RequestUtils.TX_LIVE_PARAMS, new HashMap<String, Object>(), new HttpApiUtils.OnRequestLintener() {
            @Override
            public void onSuccess(String result) {
                LiveParamEntity liveParamEntity = JSONObject.parseObject(result, LiveParamEntity.class);
                LiveParamEntity.DataBean dataBean = liveParamEntity.getData();
                //licenceURL
                String licenceURL = null;
                try {
                    licenceURL = AESUtil.decrypt(dataBean.getSecretId());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                //licenceKey
                String licenceKey = null;
                try {
                    licenceKey = AESUtil.decrypt(dataBean.getSecretKey());
                } catch (Exception e) {
                    e.printStackTrace();
                }

                licenceKey="e1bc56d0a725d1493b1571080150c5c1";
                licenceURL="http://license.vod2.myqcloud.com/license/v1/20c37a4ebe586dac402197156034a548/TXLiveSDK.licence";
                //初始化腾讯直播sdk
                TXLiveBase.getInstance().setLicence(StartLiveActivity.this, licenceURL, licenceKey);
            }
            @Override
            public void onFail(String msg) {
            }
        });
    }

    /**
     * 请求开奖结果
     * @param game
     * @param typeId
     */
    public void requestOpenResult(int game, int typeId) {
        if(runnableRequestOpen!=null&&handler!=null){
            handler.removeCallbacks(runnableRequestOpen);
        }
        Context mContext = MyApplication.getInstance();
        String lastLottery_url = "";
        Resources res = mContext.getResources();
        String[] lastLotterys = res.getStringArray(R.array.lastLottery_cpmovie);
        if (game > 0 && game <= lastLotterys.length) {
            lastLottery_url = lastLotterys[game - 1];
        }
        new HttpApiImpl(SharePreferencesUtil.getString(MyApplication.getInstance(), CommonStr.CP_BASE_URL, BuildConfig.API_HOST2))
                .getLastLottery(lastLottery_url,typeId)
                .compose(RxTransformerUtils.io_main())
                .as(AutoDispose.autoDisposable(AndroidLifecycleScopeProvider.from((LifecycleOwner) this)))
                .subscribe(new BaseStringObserver<ResponseBody>() {
                    @Override
                    public void onSuccess(String result) {
                        countDownFailCount=0;
                        countDownFailSubject.onNext(countDownFailCount);
                        if(runnableRequestOpen!=null&&handler!=null){
                            handler.post(runnableRequestOpen);
                        }
                        String resultData = Utils.initOldCpData("", result);
                        initCountDownData(resultData, game);
                    }
                    @Override
                    public void onFail(String msg) {
                        countDownFailCount++;
                        countDownFailSubject.onNext(countDownFailCount);
                        if(runnableRequestOpen!=null&&handler!=null){
                            handler.post(runnableRequestOpen);
                        }
                        System.out.println("failCount  开奖结果失败");
                    }
                });
    }

    /**
     *
     * 筛选倒计时数据
     * @param resultData
     * @param game
     */
    private void initCountDownData(String resultData, int game) {
        JSONArray jsonArray = null;
        switch (valueOf(game)) {
            case KUAISAN:
                jsonArray = JSONObject.parseArray(JSONObject.parseObject(resultData).getString("gameLotterylist"));
                break;
            case SSC:
                jsonArray = JSONObject.parseArray(JSONObject.parseObject(resultData).getString("sscaiLotterylist"));
                break;
            case RACE:
                jsonArray = JSONObject.parseArray(JSONObject.parseObject(resultData).getString("raceLotterylist"));
                break;
            case MARKSIX:
                jsonArray = JSONObject.parseArray(JSONObject.parseObject(resultData).getString("marksixLotterylist"));
                break;
            case DANDAN:
                jsonArray = JSONObject.parseArray(JSONObject.parseObject(resultData).getString("danLotterylist"));
                break;
            case HAPPY8:
                jsonArray = JSONObject.parseArray(JSONObject.parseObject(resultData).getString("happyLotterylist"));
                break;
            case LUCKFARM:
                jsonArray = JSONObject.parseArray(JSONObject.parseObject(resultData).getString("farmLotterylist"));
                break;
            case HAPPY10:
                jsonArray = JSONObject.parseArray(JSONObject.parseObject(resultData).getString("happytenLotterylist"));
                break;
            case XUANWU:
                jsonArray = JSONObject.parseArray(JSONObject.parseObject(resultData).getString("xuanwuLotterylist"));
                break;
        }

        if(jsonArray.size()!=0){
            JSONObject jsonObject = jsonArray.getJSONObject(0);
            String lotteryqishu = jsonObject.getString("lotteryqishu");
            if(!StringMyUtil.isEmptyString(nowQishu)){
                if(Long.parseLong(lotteryqishu)==(Long.parseLong(nowQishu)-1)){
                    isWaitopen=false;
                    String lotteryNo = jsonObject.getString("lotteryNo");
                    String[] split = lotteryNo.split(",");
                    openResultList = Arrays.asList(split);
                    List<String> NoList = Arrays.asList(lotteryNo.split(","));
                    List<String> AnimalList = null;
                    List<String> ColorList = null;
                    /**
                     * 六合彩特有的 生肖AnimalList 颜色ColorList
                     */
                    if (valueOf(game) == MARKSIX) {
                        AnimalList = Arrays.asList(jsonObject.getString("animal").split(","));
                        ColorList = Arrays.asList(jsonObject.getString("color").split(","));
                    }
                    /**
                     * entity 赋值
                     */
                    LastLotteryEntity lastLotteryEntity = new LastLotteryEntity();
                    lastLotteryEntity.setGame(game);
                    lastLotteryEntity.setLotteryNo(lotteryNo);
                    lastLotteryEntity.setLotteryqishu(lotteryqishu);
                    lastLotteryEntity.setNoList(NoList);
                    lastLotteryEntity.setAnimalList(AnimalList);
                    lastLotteryEntity.setColorList(ColorList);

                    List<OpenNoMulBean> openNoMulList = new ArrayList<>();
                    int[] shaizis = Const.getShaziArray(this);
                    if (game == GameTypeEnum.KUAISAN.getValue()) {
                        for (String str : lastLotteryEntity.getNoList()) {
                            OpenNoMulBean openNoMulBean = new OpenNoMulBean();
                            openNoMulBean.setName(String.valueOf(shaizis[Integer.parseInt(str) - 1]));
                            openNoMulBean.setGame(game);
                            openNoMulBean.setItemType(1);
                            openNoMulList.add(openNoMulBean);
                        }
                    } else if (game == GameTypeEnum.RACE.getValue()) {
                        for (String str : lastLotteryEntity.getNoList()) {
                            OpenNoMulBean openNoMulBean = new OpenNoMulBean();
                            openNoMulBean.setName(str);
                            openNoMulBean.setGame(game);
                            openNoMulBean.setItemType(2);
                            openNoMulList.add(openNoMulBean);
                        }
                    } else if (game == MARKSIX.getValue()) {
                        for (int i = 0; i < lastLotteryEntity.getNoList().size(); i++) {
                            OpenNoMulBean openNoMulBean = new OpenNoMulBean();
                            openNoMulBean.setName(lastLotteryEntity.getNoList().get(i));
                            openNoMulBean.setAnimal(lastLotteryEntity.getAnimalList().get(i));
                            openNoMulBean.setColor(lastLotteryEntity.getColorList().get(i));
                            openNoMulBean.setGame(game);
                            openNoMulBean.setItemType(3);
                            openNoMulList.add(openNoMulBean);
                        }
                        //添加+号
                        OpenNoMulBean openNoMulBean = new OpenNoMulBean();
                        openNoMulBean.setName("+");
                        openNoMulBean.setGame(game);
                        openNoMulBean.setItemType(3);
                        openNoMulList.add(6,openNoMulBean);
                    } else if(game == GameTypeEnum.DANDAN.getValue()){
                        int heZhi=0;
                        for (int i = 0; i < lastLotteryEntity.getNoList().size(); i++) {
                            String name = lastLotteryEntity.getNoList().get(i);
                            OpenNoMulBean openNoMulBean = new OpenNoMulBean();
                            openNoMulBean.setName(name);
                            openNoMulBean.setGame(game);
                            openNoMulBean.setItemType(5);
                            openNoMulList.add(openNoMulBean);
                            heZhi+=Integer.parseInt(name);
                        }
                        OpenNoMulBean openNoMulBean = new OpenNoMulBean();
                        openNoMulBean.setName("+");
                        openNoMulBean.setGame(game);
                        openNoMulBean.setItemType(5);
                        openNoMulList.add(1,openNoMulBean);
                        openNoMulList.add(3,openNoMulBean);

                        OpenNoMulBean openNoMulBean2 = new OpenNoMulBean();
                        openNoMulBean2.setName("=");
                        openNoMulBean2.setGame(game);
                        openNoMulBean2.setItemType(5);
                        openNoMulList.add(5,openNoMulBean2);

                        OpenNoMulBean openNoMulBeanHeZhi = new OpenNoMulBean();
                        openNoMulBeanHeZhi.setName(heZhi+"");
                        openNoMulBeanHeZhi.setGame(game);
                        openNoMulBeanHeZhi.setItemType(5);
                        openNoMulList.add(6,openNoMulBeanHeZhi);

                    } else {
                        for (String str : lastLotteryEntity.getNoList()) {
                            OpenNoMulBean openNoMulBean = new OpenNoMulBean();
                            openNoMulBean.setName(str);
                            openNoMulBean.setGame(game);
                            openNoMulBean.setItemType(4);
                            openNoMulList.add(openNoMulBean);
                        }

                    }
                    if(mOpenNoMulAdapter!=null){
                        mOpenNoMulList.clear();
                        mOpenNoMulList.addAll(openNoMulList);
                        mOpenNoMulAdapter.notifyDataSetChanged();
                        tv_lottery_qishu.setText(lotteryqishu+"期开奖");
                        tv_lottery_name.setText(dataBean.getTypename());

                        YoYo.with(Techniques.SlideInRight)
                                .duration(700)
                                .playOn(lottrry_open_result_constraintLayout);
                        lottrry_open_result_constraintLayout.setVisibility(VISIBLE);
                        if(openResultTimer!=null){
                            openResultTimer.cancel();
                        }
                        openResultTimer   =new Timer();
                        openResultTimer.schedule(new TimerTask() {
                            @Override
                            public void run() {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        if(lottrry_open_result_constraintLayout!=null){
                                            YoYo.with(Techniques.SlideOutRight)
                                                    .duration(700)
                                                    .playOn(lottrry_open_result_constraintLayout);
                                            lottrry_open_result_constraintLayout.setVisibility(View.INVISIBLE);
                                        }
                                    }
                                });
                            }
                        },1000*10);
                    }
                }else {
                    isWaitopen=true;
                }
            }
        }else{
            Log.e(TAG, "onSuccess: 开奖结果为空" );
        }

    }

    /**
     * 倒计时请求99
     *
     */
    public void getCountDown(GifImageView gifImageView) {
        if(runnableTime!=null&&handler!=null){
            handler.removeCallbacks(runnableTime);
        }
        if(gifImageView!=null){
            gifImageView.setVisibility(VISIBLE);
        }
        countDownStatus=false;
        showLoadingLinear();
        Context mContext = MyApplication.getInstance();
        String countdown_url = "";
        Resources res = mContext.getResources();
        String[] countdowns = res.getStringArray(R.array.countdown_cpmovie);
        int game = dataBean.getGame();
        int type_id = dataBean.getType_id();
        if (game <= countdowns.length) {
            countdown_url = countdowns[game - 1];
        }
        new HttpApiImpl(SharePreferencesUtil.getString(MyApplication.getInstance(), CommonStr.CP_BASE_URL,BuildConfig.API_HOST2))
                .getCountDown(countdown_url, type_id)
                .compose(RxTransformerUtils.io_main())
                .as(AutoDispose.autoDisposable(AndroidLifecycleScopeProvider.from((LifecycleOwner) this)))
                . subscribe(new BaseStringObserver<ResponseBody>() {
                    @Override
                    public void onSuccess(String result) {
                        countDownFailCount=0;
                         countDownFailSubject.onNext(countDownFailCount);
                        if(runnableTime!=null&&handler!=null){
                            handler.post(runnableTime);
                        }
                        if(gifImageView!=null){
                            gifImageView.setVisibility(GONE);
                        }
                        LogUtils.e("onSuccess " + result);
                        String resultData = Utils.initOldCpData("", result);

                        JSONObject jsonObject = JSONObject.parseObject(resultData);
                        String stoptimestr = jsonObject.getString("stoptimestr");//倒计时结束时间
                        nowQishu = jsonObject.getString("qishu");//当前期数
                        if(StringMyUtil.isEmptyString(nowQishu)) {//期数为空,表示封盘

                        }else{
//                            stoptv.setVisibility(View.GONE);

                            if(runnableTime==null){
                                handler.postDelayed(runnableTime,300);
                            }
                            if(runnableRequestOpen==null){
                                handler.postDelayed(runnableRequestOpen, jgTime);
                            }
                            tqtime = jsonObject.getString("tqtime");//封盘时间(倒计时需要减去封盘时间)
                            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                            try {
                                millionSeconds = simpleDateFormat.parse(stoptimestr).getTime();//倒计时结束时间
                                long nowTime = System.currentTimeMillis();//当前时间
                                shijiancha = SharePreferencesUtil.getLong(StartLiveActivity.this,"shijiancha",0l);//服务器时间和当地时间差
                                countTime = millionSeconds + shijiancha - nowTime -(Long.parseLong(tqtime)*1000);
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        showTimeLInear();
                                    }
                                },600);

                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                        }
                        countDownStatus=true;
                    }

                    @Override
                    public void onFail(String msg) {
                        countDownFailCount++;
                        System.out.println("failCount  倒计时失败");
                        countDownFailSubject.onNext(countDownFailCount);
                        if(runnableTime!=null&&handler!=null){
                            handler.post(runnableTime);
                        }
                        if(gifImageView!=null){
                            gifImageView.setVisibility(GONE);
                        }
                        LogUtils.e(msg);
                        countDownStatus=true;
                    }
                });
    }

    /**
     * 请求开奖结果
     */
    Runnable runnableRequestOpen =new Runnable() {
        @Override
        public void run() {
            if(isWaitopen&&countDownFailCount<=6){
                handler.removeCallbacks(runnableRequestOpen);
                requestOpenResult(dataBean.getGame(),dataBean.getType_id());
            }
            handler.postDelayed(runnableRequestOpen,jgTime);
        }
    };
    /**
     * 文本消息收集计时器
     */
    Runnable commitMessageRunnable = new Runnable() {
        @Override
        public void run() {
            Log.e(TAG, "消息收集  进入定时器");
            if(currentTextMessage==null){
                //currentTextMessage为空说明没有上传过,commitTextMessageArrayList中的所有消息都要上传
                requestCommitMessage(0);
                Log.e(TAG, "消息收集  currentTextMessage==null");
            }else {
                //currentTextMessage不为空说明上传过,本次从currentTextMessage后面开始上传
                if(commitLiveMessageModelList.size()!=0){
                    int currentIndex = commitLiveMessageModelList.indexOf(currentTextMessage);
                    if(currentIndex!=commitLiveMessageModelList.size()-1){//上次提交后没有add新消息不用重复提交
                        requestCommitMessage(currentIndex+1);
                        Log.e(TAG, "消息收集  currentTextMessage下标为   "+currentIndex);
                    }
                }
            }
            handler.postDelayed(this,5000);
        }
    };

    Runnable watchNumRunnable  = new Runnable() {
        @Override
        public void run() {
            handler.removeCallbacks(this);
            HashMap<String, Object> data = new HashMap<>();
            data.put("streamId",streamName);
            data.put("views",totalMemberCount<=0?1:totalMemberCount);
            HttpApiUtils.normalRequest(StartLiveActivity.this, null, RequestUtils.ONLINE_WATCH_NUM, data, new HttpApiUtils.OnRequestLintener() {
                @Override
                public void onSuccess(String result) {
                    handler.postDelayed(watchNumRunnable,30*1000);
                    Log.e(TAG, "onSuccess: 人数上传成功 :"+ data.get("views") +"    streamId:"+streamName);
                }

                @Override
                public void onFail(String msg) {
                    handler.postDelayed(watchNumRunnable,30*1000);
                    Log.e(TAG, "onSuccess: 人数上传失败 :"+ data.get("views") +"     streamId:"+streamName);
                }
            });

        }
    };

    Runnable refreshAmountRunnable = new Runnable() {
        @Override
        public void run() {
            requestMoney(true);
        }
    };

    /**
     * 提交普通文本消息
     * @param startIndex 遍历消息list的起始值
     */
    private void requestCommitMessage(int startIndex) {
        if(commitLiveMessageModelList.size()!=0){
            //每次进入定时器后在提交过程中停止定时器
            handler.removeCallbacks(commitMessageRunnable);
            HashMap<String, Object> data = new HashMap<>();
            JSONArray jsonArray = new JSONArray();
            //记录本次提交的最后一个数据(下一次进入定时器时从此数据后面开始提交)
            LiveMessageModel lastLiveMessageModel=null;
            for (int i = startIndex; i < commitLiveMessageModelList.size() ; i++) {
                LiveMessageModel liveMessageModel = commitLiveMessageModelList.get(i);
                if(i==commitLiveMessageModelList.size()-1){
                    lastLiveMessageModel=liveMessageModel;
                }
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("anchorId", Utils.getUserInfoBean().getId());//主播id
                jsonObject.put("anchorName",Utils.getUserInfoBean().getNickname());//主播昵称
                jsonObject.put("content",liveMessageModel.getTextContent());//弹幕内容
                jsonObject.put("nickName",getTextMessageNickName(liveMessageModel));//消息发送者的昵称(nickName)
                jsonObject.put("rongId",liveMessageModel.getRcUserId());//消息发送者融云id
                jsonObject.put("roomId",sp.getRoomId());//房间id
                jsonObject.put("userName",getTextMessageUserName(liveMessageModel));//消息发送者的用户名
                if(liveMessageModel.getMessageDirection() == io.rong.imlib.model.Message.MessageDirection.RECEIVE){
                    jsonObject.put("platformCode",liveMessageModel.getZkCode());//平台code
                }else {
                    //如果消息是主播自己发的,platformCode固定传zhubo
                    jsonObject.put("platformCode","zhubo");
                }
                jsonArray.add(jsonObject);
            }
            data.put("req",jsonArray.toJSONString());

            String content="";
            for (int i = 0; i < jsonArray.size(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                content+=(jsonObject.getString("content")+",");

            }

            Log.e(TAG, "requestCommitMessage: 上传的消息内容======"+content );

            LiveMessageModel finalLastLiveMessageModel = lastLiveMessageModel;
            HttpApiUtils.normalRequest(StartLiveActivity.this, null, RequestUtils.COMMIT_MESSAGE, data, new HttpApiUtils.OnRequestLintener() {
                @Override
                public void onSuccess(String result) {
                    //提交成功将currentTextMessage赋值为本次提交的最后一个数据
                    currentTextMessage = finalLastLiveMessageModel;
                    handler.postDelayed(commitMessageRunnable,5000);
                    Log.e(TAG, "消息收集 成功    ");
                }
                @Override
                public void onFail(String msg) {
                    handler.postDelayed(commitMessageRunnable,5000);
                    Log.e(TAG, "消息收集 失败   ");
                }
            });
        }
    }

    /**
     * 开播时间计时器
     */
    Runnable liveTimeRunnable = new Runnable(){
        @Override
        public void run() {
            liveTime++;
            int mHour = (int) ((liveTime  ) / (60 * 60));  //  对3600 取整  就是小时
            int mMin = (int) (((liveTime  ) % (60 * 60)) / 60);//  对3600 取余除以60 就是多出的分
            int mSecond = (int) ((liveTime ) % 60); //  对60 取余  就是多出的秒
            String str_time = timeMode(mHour) + ":" + timeMode(mMin) + ":" + timeMode(mSecond);
            live_time_tv.setText(str_time);
            handler.postDelayed(this,1000);
        }
    };
    /**
     * 蓝牙连接状态
     */
    Runnable toyStatusRunnable = new Runnable() {
        @Override
        public void run() {
            if(StringMyUtil.isNotEmpty(toyId)){
                boolean connected = Lovense.getInstance(getApplication()).isConnected(toyId);
                if(connected){
                    toy_disconnect_tv.setVisibility(GONE);
                }else {
                    toy_disconnect_tv.setVisibility(GONE);
                }
            }
            handler.postDelayed(this,3000);
        }
    };
    /**
     * 彩种倒计时timer定时器
     */
    Runnable runnableTime = new Runnable() {
        @Override
        public void run() {
            if(countDownFailCount>6){
                return;
            }
            if (countTime<=0){
                if (isWaitopen){
                    if(runnableRequestOpen!=null){
                        handler.removeCallbacks(runnableRequestOpen);
                    }
                    if(countDownStatus){
                        handler.postDelayed(runnableRequestOpen,jgTime);
                    }
                }

                getCountDown(null);
            }else {
                countTime = millionSeconds + shijiancha - System.currentTimeMillis() -(Long.parseLong(tqtime)*1000);
//                countTime = countTime - 1000;
                int mHour = (int) ((countTime / 1000) / (60 * 60));  //  对3600 取整  就是小时
                int mMin = (int) (((countTime / 1000) % (60 * 60)) / 60);//  对3600 取余除以60 就是多出的分
                int mSecond = (int) ((countTime / 1000) % 60); //  对60 取余  就是多出的秒
                String str_time;
                if (mHour == 0) {
                    str_time = timeMode(mMin) + ":" + timeMode(mSecond);
                } else {
                    str_time = timeMode(mHour) + ":" + timeMode(mMin) + ":" + timeMode(mSecond);
                }
                tv_countdown.setText(str_time);
                if(countTime<=1){
                    countTime=0;
                    isWaitopen=true;
                }
                EventBus.getDefault().postSticky(new CountDownEven(dataBean.getTypename(),nowQishu,countTime));
            }

            handler.postDelayed(runnableTime,300);
        }
    };


    Runnable onlinePeopleRunnable = new Runnable() {
        @Override
        public void run() {
            getChatRoomNum();
            handler.postDelayed(this,3000);
        }
    };
    /**
     * 每秒添加一条vip1加入直播间的消息
     */
    Runnable vip1JoinRunnable = new Runnable() {
        @Override
        public void run() {
            LiveMessageModel liveMessageModel = new LiveMessageModel();
            liveMessageModel.setManagerType("0");//普通用户
            liveMessageModel.setLevelIcon("");
            liveMessageModel.setUserName(generateAccount(8));//用户名
            liveMessageModel.setStatus("0");//进入直播间
            liveChatFragment.addJoinItem(liveMessageModel);
            handler.postDelayed(this,1000);
        }
    };

    //生成随机用户名，数字和字母组成,
    public  String generateAccount(int length) {

        String val = "";
        Random random = new Random();

        //参数length，表示生成几位随机数
        for(int i = 0; i < length; i++) {

            String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num";
            //输出字母还是数字
            if( "char".equalsIgnoreCase(charOrNum) ) {
                //输出是大写字母还是小写字母
                int temp = random.nextInt(2) % 2 == 0 ? 65 : 97;
                val += (char)(random.nextInt(26) + temp);
            } else if( "num".equalsIgnoreCase(charOrNum) ) {
                val += String.valueOf(random.nextInt(10));
            }
        }
        return "萌新"+val;
    }
    /**
     * Rxjava 观察totalMemberCount,主线程处理对应逻辑
     */
    private void observableOnlineNum() {
        onlineNumObservable
                .observeOn(AndroidSchedulers.mainThread())//回调在主线程
                .subscribeOn(Schedulers.io())//执行在io线程
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }
                    @Override
                    public void onNext(Integer integer) {
                        //需要展示的头像个数
                        int showCount = integer * watchRadio;
                        chatUserEntityArrayList.clear();
                            /*
                            真实在线人数少于60人, 显示真实的头像个数, 但是最多显示60个
                             */
                        for (int i = 0; i < (showCount>=60?60:showCount); i++) {
                            int nextInt = new Random().nextInt(20);
                            if(chatUserEntityArrayList.size()< 60){
                                chatUserEntityArrayList.add(new ChatUserEntity(avatatArray[nextInt]));
                            }
                        }
                        chatUserAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
    /**
     * 开奖结果和倒计时失败次数订阅
     */
    private void observableCountFailCount() {
        countDownFailSubject
                .observeOn(AndroidSchedulers.mainThread())//回调在主线程
                .subscribeOn(Schedulers.io())//执行在io线程
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }
                    @Override
                    public void onNext(Integer integer) {
                        if(count_down_fail_refresh_tv!=null){
                            if(countDownFailCount>6){
                                if(count_down_fail_refresh_tv.getVisibility()!=VISIBLE){
                                    count_down_fail_refresh_tv.setVisibility(VISIBLE);
                                }
                            }else {
                                if(count_down_fail_refresh_tv.getVisibility()!=GONE){
                                    count_down_fail_refresh_tv.setVisibility(GONE);
                                }
                            }
                        }

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    /**
     * 在线人数recycler
     */
    private void initOnlineRecycler() {
        watchRadio = SharePreferencesUtil.getInt(this,CommonStr.WATCH_RADIO,10);
        avatatArray = Const.getAvatatArray(this);
        chatUserAdapter = new ChatUserAdapter(R.layout.item_chatuser,chatUserEntityArrayList,this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.HORIZONTAL);
        recy_renshu.setLayoutManager(layoutManager);
        recy_renshu.setAdapter(chatUserAdapter);
    }

    /**
     * 大礼物动画
     */
    private void initSvgaImageView() {
        giftSvgaManage = new GiftSvgaManage(this,svgaImageView);

    }

    /**
     * 小礼物view初始化
     */
    private void initGiftView() {
        giftView.setViewCount(3);
        giftView.init();
    }

    /**
     * 设置聊天列表fragment
     */
    private void initLiveChatFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        liveChatFragment= (LiveChatFragment) fragmentManager.findFragmentById(R.id.live_chat_fragment);
    }

    /**
     * 获取用户资金
     * @param isRefreshAmount  是否是定时器刷新调用 定时器调用时返回数据后需要重新开启定时器
     */
    private void requestMoney(boolean isRefreshAmount) {
        if(refreshAmountRunnable!=null){
            handler.removeCallbacks(refreshAmountRunnable);
        }
        HttpApiUtils.wwwNormalRequest(this, null,RequestUtils.USER_MONEY, new HashMap<String, Object>(), new HttpApiUtils.OnRequestLintener() {
            @Override
            public void onSuccess(String result) {
                UserMoneyEntity userMoneyEntity = JSONObject.parseObject(result, UserMoneyEntity.class);
                String giftAmount = userMoneyEntity.getData().getGiftAmount();
                if(!isRefreshAmount){
                    money_tv.setText(giftAmount);
                }else {
                    handler.postDelayed(refreshAmountRunnable,60*1000);
                }
            }

            @Override
            public void onFail(String msg) {
                if(isRefreshAmount){
                    handler.postDelayed(refreshAmountRunnable,60*1000);
                }
            }
        });
    }
/*
    private void setViewMarginTop() {
        //设置toolBar父view的marginTop()
        ViewGroup.LayoutParams layoutParams = pusher_drawerLayout.getLayoutParams();
        LinearLayout.LayoutParams layoutParams1 = (LinearLayout.LayoutParams) layoutParams;
        layoutParams1.setMargins(0, getStatusBarHeight(this),0,0);
        pusher_drawerLayout.setLayoutParams(layoutParams1);
    }
*/

    @Override
    public boolean  onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if(isPusher){
                //不执行父类点击事件
                initFinishLivePop();
                return true;
            }else {
                return super.onKeyDown(keyCode, event);
            }

        }
        //继续执行父类的点击事件
        return super.onKeyDown(keyCode, event);
    }

    /**
     * 结束直播弹窗
     */
    private void initFinishLivePop() {
        if(commonChoosePop ==null){
            commonChoosePop =new CommonChoosePop(this,this,"结束直播","是否结束直播?");
        }
        commonChoosePop.showAtLocation(beauty_iv, Gravity.CENTER,0,0);
        commonChoosePop.setOnClickLintener(new CommonChoosePop.OnClickLintener() {
            @Override
            public void onSureClick(View view) {
                commonChoosePop.dismiss();
                FinishLiveActivity.startAty(StartLiveActivity.this,streamName,mostHighMemberCount,watchRadio,roomAmount,"");
//                finish();
                finish();
            }
        });
    }

    private void requestStopLiveData() {
        HashMap<String, Object> data = new HashMap<>();
        data.put("onLine",mostHighMemberCount);
        data.put("streamName",streamName );
        HttpApiUtils.wwwRequest(this,null, RequestUtils.FINISH_LIVE, data, new HttpApiUtils.OnRequestLintener() {
            @Override
            public void onSuccess(String result) {
                Log.e(TAG, "onSuccess: 推流失败,请求结束直播接口成功" );
            }

            @Override
            public void onFail(String msg) {
                Log.e(TAG, "onFail: 推流失败,请求结束直播接口失败" );
            }
        });
    }
    @Override
    protected void initStatusBar() {
        super.initStatusBar();
//        initNavigateBar();
        StatusBarUtil.setColor(this, Color.WHITE);
        StatusBarUtil.setDarkMode(this);
    }
    public  void fullScreen(Window window) {
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_IMMERSIVE
                | View.SYSTEM_UI_FLAG_FULLSCREEN;
        window.getDecorView().setSystemUiVisibility(uiOptions);
    }
    /**
     * 初始化抽屉
     */
    private void initDrawerLayout() {
        DrawerLeftEdgeSize.setRightEdge(this,pusher_drawerLayout, 1f);
        //去掉抽屉的阴影
        pusher_drawerLayout.setScrimColor(Color.TRANSPARENT);
        //禁止手势滑动
        pusher_drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
    }

    @Override
    protected void onResume() {
        super.onResume();
        //考虑到一般都会设置美颜,所以提前初始化美颜pop(避免第一次创建时的轻微卡顿问题)
//        beautyPop =  new BeautyPop(StartLiveActivity.this,StartLiveActivity.this,manager);
/*        beautyViewHolder = BeautyViewHolderFactory.getBeautyViewHolder(this, wrap_frameLayout);	//创建beautyViewHolder
        beautyViewHolder.setEffectListener(this);//添加基础美颜回调
        beautyViewHolder.setMhBeautyManager(mhBeautyManager);*/
//将mhBeautyManager传入，mhBeautyManager为美颜管理类，后面有说明文档，此处mhBeautyManager不可为null
//        beautyViewHolder.show(); //显示美颜菜单
    }

    /**
     * @param context
     */
    public static void  startAty(Context context){
        context.startActivity(new Intent(context,StartLiveActivity.class));
    }

    /**
     * 检查权限(未同意全部权限结束当前activity)
     */
    private void checkPermissions() {
        String[] PERMISSIONS = {Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA,
                Manifest.permission.RECORD_AUDIO,Manifest.permission.READ_PHONE_STATE,Manifest.permission.READ_EXTERNAL_STORAGE};
        RxPermissions rxPermissions = new RxPermissions(this);
        rxPermissions.requestEachCombined(PERMISSIONS)
                .subscribe(permission -> {
                    if (permission.granted) {
                        Log.e(TAG, "init: 权限申请成功");
//                        initBeautyView();
                        initPusher();

                    } else {
                        Log.e(TAG, "init: 权限申请失败");
                        showtoast("拒绝必须权限后将无法正常使用app");
                        finish();
                    }
                });
    }


    /**
     * 推流和摄像头预览设置
     */
    private  void initPusher() {
        FURenderer.setup(this);
        mFURenderer = new FURenderer
                .Builder(this)
                .setInputTextureType(FURenderer.INPUT_TEXTURE_2D)
                .setInputImageOrientation(CameraUtils.getCameraOrientation(mCameraFacing))
//                .setOnTrackStatusChangedListener(this)
                .setRunBenchmark(true)
                .build();
        faceUnityView.setModuleManager(mFURenderer);
        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        Sensor sensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mSensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL);
        if(USE_TX){
            //初始化 TXLivePusher 组件 负责推流
            TXLivePushConfig mLivePushConfig = new TXLivePushConfig();

            mLivePusher = new CameraPushImpl(this, mPusherView);
            mLivePusher.getTXLivePusher().setVideoProcessListener(this);
            //设置垫片的最长持续时间和帧率
            mLivePushConfig.setPauseImg(24*60*60*60,5);
            // 设定画面清晰度
            mLivePusher.getTXLivePusher().setVideoQuality(VIDEO_QUALITY_SUPER_DEFINITION,false,false);
          /*
         一般情况下不需要修改 config 的默认配置
         此处设置垫片推流时暂停视频采集
         */
            mLivePushConfig.setPauseFlag(PAUSE_FLAG_PAUSE_VIDEO);
            Bitmap bitmap = decodeResource(getResources(), R.drawable.pause_publish);
            mLivePushConfig.setPauseImg(bitmap);
            mLivePusher.getTXLivePusher().setConfig(mLivePushConfig);
            //启动本地摄像头预览
            mLivePusher.getTXLivePusher().startCameraPreview(mPusherView);

            mLivePusher.getTXLivePusher().setPushListener(new ITXLivePushListener() {
                @Override
                public void onPushEvent(int event, Bundle bundle) {
                    Log.e(TAG, "onPushEvent: pusherId:"+bundle.getString("EVT_MSG")+"   CODE :"+event );
                    if (event == TXLiveConstants.PUSH_WARNING_SERVER_DISCONNECT) {
//                      //后台强制下播
                        /**
                         * 主播被拉黑
                         */
                        FinishLiveActivity.startAty(StartLiveActivity.this,streamName,mostHighMemberCount,watchRadio,roomAmount,"您已被系统强制下播");
                        finish();
                    }else if(event == TXLiveConstants.PUSH_ERR_NET_DISCONNECT){
//                        网络断连，且连续三次无法重新连接，需要自行重启推流
                        mLivePusher.getTXLivePusher().stopPusher();
                        mLivePusher.getTXLivePusher().startPusher(rtmpPushUrl);
                    }else if(event == TXLiveConstants.PUSH_WARNING_NET_BUSY){
                        //弱网环境,提示
                        showtoast("您当前的网络环境不佳，请尽快更换网络保证正常直播");
                    }else if(event ==  TXLiveConstants.PUSH_ERR_INVALID_ADDRESS ){
                        //推流地址无效, 点击确定后下播
                        CommonTipPop commonTipPop = new CommonTipPop(StartLiveActivity.this,"温馨提示","直播流已失效,请自行重新开播");
                        commonTipPop.setmOnDissmissListener(new BasePopupWindow.OnDissmissListener() {
                            @Override
                            public void onDissmiss() {
                                FinishLiveActivity.startAty(StartLiveActivity.this,streamName,mostHighMemberCount,watchRadio,roomAmount,"");
                                finish();
                            }
                        });
                        commonTipPop.showAtLocation(pusher_drawerLayout,Gravity.CENTER,0,0);
                    }
                }

                @Override
                public void onNetStatus(Bundle bundle) {
                    int net_speed = bundle.getInt("NET_SPEED");
                    if(null!=speed_tv){
                        if(net_speed<100) {
                            speed_tv.setTextColor(getResources().getColor(R.color.red));
                        }else {
                            speed_tv.setTextColor(getResources().getColor(R.color.green));
                        }
                        speed_tv.setText( Utils.getNetFileSizeDescription(net_speed*1024));
                    }


                }
            });
        }else {


/*            nodePublisher.setNodePublisherVideoTextureDelegate(new NodePublisherVideoTextureDelegate() {
                private int flags;

                @Override
                public void onCreateTextureCallback(NodePublisher streamer) {

                }

                @Override
                public void onChangeTextureCallback(NodePublisher streamer, boolean isFront, int cameraOri, int windowOri) {
                    flags = mFURenderer.getFlag(isFront);
                    mFURenderer.setRotationMode(cameraOri);
                }

                @Override
                public void onDestroyTextureCallback(NodePublisher streamer) {

                }

                @Override
                public int onDrawTextureCallback(NodePublisher streamer, int textureID, int width, int height, boolean isFront, int cameraOri) {
//                    return textureID;
                    return mFURenderer.setTextureID(flags,width,height,textureID);

                }
            });*/


            /**
             * 推流监听
             */
            nodePublisher.setNodePublisherDelegate(new NodePublisherDelegate() {
                @Override
                public void onEventCallback(NodePublisher streamer, int event, String msg) {
                    System.out.println("dsdsdsdsdsd  "+event);
                }
            });
            /**
             *摄像头预览设置
             * 推流view
             * 摄像头方向
             * 是否开启镜像
             */
            nodePublisher.setCameraPreview(node_pusher_view,NodePublisher.CAMERA_FRONT,false);


            /**
             * 视频编码
             */
            nodePublisher.setVideoParam(NodePublisher.VIDEO_PPRESET_16X9_720,30,200*1024*8,NodePublisher.VIDEO_PROFILE_MAIN,false);

            /**
             * 关键帧帧数
             */
//            nodePublisher.setKeyFrameInterval(1);

            /**
             * 音频编码
             */
            nodePublisher.setAudioParam(32000,AUDIO_PROFILE_HEAAC);

            /**
             * 是否开启视频推流
             */
            nodePublisher.setVideoEnable(true);

            /**
             * 是否开启音频推流
             */
            nodePublisher.setAudioEnable(true);

            /**
             * 是否开启硬件加速
             */
            nodePublisher.setHwEnable(true);

            /**
             * 开启摄像头预览
             */
            nodePublisher.startPreview();

        }
    }

    /**
     * 直播过程中返回桌面和重新进入页面时,进入/退出垫片模式
     * @param leaveAppEvenModel
     */
    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public void leaveBackApp(leaveAppEvenModel leaveAppEvenModel){
        if(leaveAppEvenModel.isBack2App()){
            //app回到前台,退出垫片模式
            if(mLivePusher!=null){
                mLivePusher.getTXLivePusher().resumePusher();
            }
        }else {
            //app进入后台,进入垫片模式
            if(mLivePusher!=null){
                mLivePusher.getTXLivePusher().pausePusher();
            }
        }

    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(ToyConnectEvent mToyConnectEvent) {
        int connect = mToyConnectEvent.getConnect();
        isUseToy = connect;
        toyId = mToyConnectEvent.getId();
        if(toy_tv!=null&&toy_disconnect_tv!=null){
            if(connect==1){
                toy_tv.setText("玩具已连接");
            }else {
                toy_tv.setText("玩具未连接");
            }
        }

    }
    /**
     * 结束直播接口返回成功再关闭当前页面 结束推流
     * @param stopPushEvenModel
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void stopPush(StopPushEvenModel stopPushEvenModel){
        if(stopPushEvenModel.isStopPush()){
            Log.e(TAG, "stopPush: 收到广播结束推流" );
            finish();
        }
    }
    /**
     * 请求后台开播成功接口
     */
    private void requestPushSuccess() {
        HashMap<String, Object> data = new HashMap<>();
        if(pushObject!=null){
            data.put("id",pushObject.getString("id"));
            data.put("pushUrl",rtmpPushUrl);
            data.put("streamName",streamName);
            data.put("nature",natureStr);

            HttpApiUtils.request(StartLiveActivity.this,null, RequestUtils.PUSH_SUCCESS, data, new HttpApiUtils.OnRequestLintener() {
                @Override
                public void onSuccess(String result) {
                    initPushSuccess();
                }

                @Override
                public void onFail(String msg) {
                }
            });
        }
    }

    /**
     * 成功开启直播相关
     */
    private void initPushSuccess() {
        showtoast("成功开启直播");
        isPusher =true;
        //推流成功, 隐藏美颜相关页面, 显示抽屉
        live_constrainLayout_group.setVisibility(View.GONE);
        start_live_constraintLayout.setVisibility(View.GONE);
        pusher_drawerLayout.openDrawer(GravityCompat.END);
        //打开手势滑动
        pusher_drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
        /**
         小礼物飞入特效
         */
        initGiftView();
        /**
         大礼物播放特效
         */
        initSvgaImageView();
        /**
         * 用户资金
         */
        requestMoney(false);
        /**
         * 在线人数recycler
         */
        initOnlineRecycler();
        /**
         * 初始化彩票数据
         */
        initCpData();
        /**
         * 融云相关
         */
        initRongYun();
        /**
         * 开启直播时间
         */
        handler.postDelayed(liveTimeRunnable,1000);
        /**
         * 检车蓝牙连接状态的计时器
         */
        handler.postDelayed(toyStatusRunnable,3000);

        /**
         * 每5秒上传一次文本消息的计时器
         */
        handler.postDelayed(commitMessageRunnable,5000);

        /**
         * 每30s请求一次在线人数统计的计时器
         */
        handler.postDelayed(watchNumRunnable,30*1000);

        /**
         * 每分钟请求一次用户金额,保证后台看到的数据时最新的
         */
        handler.postDelayed(refreshAmountRunnable,60*1000);




//        initNavigateBar();
    }


    public void initNavigateBar() {
        ImmersionBar immersionBar = ImmersionBar.with(this);
        immersionBar
                .titleBarMarginTop(pusher_drawerLayout)
                .statusBarColor(R.color.transparent)
                .hideBar(BarHide.FLAG_HIDE_NAVIGATION_BAR)
                .navigationBarColor(R.color.transparent)
                .keyboardEnable(true)
                .keyboardMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING)  //单独指定软键盘模式
                .init();
    }


    /**
     * 成功推流后,cp业务的相关初始化
     */
    private void initCpData() {
        //开启倒计时计时器
        if(runnableTime!=null){
            handler.removeCallbacks(runnableTime);
        }
        handler.postDelayed(runnableTime,300);
        //设置彩种图片
        GlideLoadViewUtil.cpLoadNormalView(this,dataBean.getImage(),iv_lottery);
        /**
         * 开奖结果recyclerView
         */
        mOpenNoMulAdapter = new OpenNoMulAdapter(mOpenNoMulList);
        AutoLineFeedLayoutManager layoutManager = new AutoLineFeedLayoutManager();
        layoutManager.setAutoMeasureEnabled(true);
        recy_lottery.setLayoutManager(layoutManager);
        recy_lottery.setItemAnimator(new DefaultItemAnimator());
        recy_lottery.setHasFixedSize(true);
        recy_lottery.setAdapter(mOpenNoMulAdapter);
    }

    /**
     * 获取 RTMP 推流地址
     */
    private void getRTMPPusherFromServer( ) {
        if (mIsGettingRTMPURL) return;
        mIsGettingRTMPURL = true;
        HashMap<String, Object> data = new HashMap<>();
//        data.put("categoryId",categoryId);//频道id
        data.put("cover",cover);//封面
        data.put("deviceInfo", SystemUtil.getSystemModel());//手机型号
        data.put("deviceNo", SystemUtil.getUniqueId(StartLiveActivity.this));//设备号
        data.put("title",title);//直播间标题
        data.put("gameClassId",gameClassId);
        data.put("area",area.contains("保密")?"":area);
        data.put("type",roomType);//房间类型(1普通2密码3门票4记时)
        data.put("amount",roomAmount);
        data.put("isUseToy",isUseToy);
        HttpApiUtils.request(this,null, RequestUtils.START_LIVE, data, new HttpApiUtils.OnRequestLintener() {
            @Override
            public void onSuccess(String result) {
                //设置直播间标题缓存
                SharePreferencesUtil.putString(MyApplication.getInstance(),CommonStr.LIVE_TITLE,title);
                mIsGettingRTMPURL = false;
                pushObject = JSONObject.parseObject(result).getJSONObject("data");
                String playUrl = pushObject.getString("pushUrl");
                try {
                    rtmpPushUrl = AESUtil.decrypt(playUrl);//推流地址
                } catch (Exception e) {
                    e.printStackTrace();
                }
                try {
                    streamName = AESUtil.decrypt(pushObject.getString("streamName"));//推流地址
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if(USE_TX){
                    int ret  = mLivePusher.getTXLivePusher().startPusher(rtmpPushUrl.trim());
//                int ret  = 0; //开发用,默认推流成功
                    if(ret==0){
                        Log.e(TAG, "推流成功");
                        requestPushSuccess();
                    }else {
                        if (ret == -5) {
                            Log.i(TAG, "startRTMPPush: license 校验失败");
                            showtoast("license检验失败");
                        }else if (ret == -1) {
                            Log.i(TAG, "推流失败");
                            //推流失败请求结束直播接口(后台需要移除直播列表)
                            requestStopLiveData();
                            showtoast("推流失败,请重试");
                        }
                    }
                }else {
                    nodePublisher.setOutputUrl(rtmpPushUrl);
                    String livePassword = pushObject.getString("livePassword");
                    try {
                        nodePublisher.setCryptoKey(AESUtil.decrypt(livePassword));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    int start = nodePublisher.start();
                    Log.e(TAG, "nodePublisher.start()"+ start );
                    if(start==0){
                        Log.e(TAG, "推流成功");
                        requestPushSuccess();
                    }else {
                        requestStopLiveData();
                        showtoast("推流失败,请重试");
                    }

                }

            }

            @Override
            public void onFail(String msg) {
                mIsGettingRTMPURL = false;
            }
        });
    }

    /**
     * 推流成功后的融云聊天相关融云相关
     */
    private void initRongYun() {
        joinGiftSvgaMage = new JoinSvgaMage(this,join_svga_imageview);
        assetsSvgaManage = new AssetsSvgaManage(this,test_svga_imageview);
        RongLibUtils.addEventHandler(handler);
        joinChatRoom();
        editPanel.setInputLinstener();
    }

    /**
     * 加入聊天室
     */
    private void joinChatRoom() {
        RongLibUtils.joinChatRoom(-1, sp.getRoomId(), new RongIMClient.OperationCallback() {
            @Override
            public void onSuccess() {
                /*
                加入聊天室成功
                 */
                Log.e(TAG, "加入聊天室成功" );
//            发送一条进入直播间的message
                LiveExitAndJoinMessage msgContent = new LiveExitAndJoinMessage(userDataBean.getNickname(), "0");
                msgContent.setUserInfoJson(RongLibUtils.setUserInfo(StartLiveActivity.this));
                RongLibUtils.sendMessage(sp.getRoomId(), msgContent);
//             获取聊天室人数
                handler.postDelayed(onlinePeopleRunnable,3000);

                //VIP1加入直播间（假数据）
                handler.postDelayed(vip1JoinRunnable,1000);
            }
            @Override
            public void onError(RongIMClient.ErrorCode errorCode) {
                //加入聊天室失败
                Log.e(TAG, "加入聊天室失败" );
            }
        });
    }

    /**
     融云聊天室在线人数
     */
    private void getChatRoomNum() {
        RongIMClient.getInstance().getChatRoomInfo(sp.getRoomId(), 0,
                ChatRoomInfo.ChatRoomMemberOrder.RC_CHAT_ROOM_MEMBER_ASC, new RongIMClient.ResultCallback<ChatRoomInfo>() {
                    @Override
                    public void onSuccess(ChatRoomInfo chatRoomInfo) {
                        //真实在线人数
                        totalMemberCount = chatRoomInfo.getTotalMemberCount();
                        if(totalMemberCount>mostHighMemberCount){
                            mostHighMemberCount=totalMemberCount;
                        }
                        onlineNumObservable.onNext(totalMemberCount);

                        if(tv_num!=null){
                            tv_num.setText(totalMemberCount*watchRadio+"人");
                        }
                    }
                    @Override
                    public void onError(RongIMClient.ErrorCode errorCode) {
                    }
                });
    }


    /**
     *礼物震动处理
     */
    private void handlerGiftGear() {
        if(gearMessageList.size()!=0){
            LiveGiftMessage liveGiftMessage = gearMessageList.get(0);
            Lovense.getInstance(getApplication()).sendCommand(toyId, LovenseToy.COMMAND_VIBRATE, Integer.parseInt(liveGiftMessage.getGear()));
            isGearing =true;
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Lovense.getInstance(getApplication()).sendCommand(toyId, LovenseToy.COMMAND_VIBRATE, 0);
                    if(gearMessageList.size()!=0){
                        gearMessageList.remove(0);
                    }
                    isGearing =false;
                    handlerGiftGear();
                }
            },Integer.parseInt(liveGiftMessage.getGearTime())*1000);
        }
    }

    /** 收到融云消息时,设置recyclerView itemModel的userInfoJson
     * @param messageModel
     * @param userInfoJson
     */
    private boolean initUserInfo(LiveMessageModel messageModel, String userInfoJson,String sendUserId) {
            if (StringMyUtil.isNotEmpty(userInfoJson)&&Utils.isJsonObject(userInfoJson)) {
                JSONObject jsonObject = JSONObject.parseObject(userInfoJson);
                String userId = jsonObject.getString("userId");
                String name = jsonObject.getString("name");
                String level = jsonObject.getString("level");
                if(Utils.isNotInt(level)){
                    return true;
                }
                String portrait = jsonObject.getString("portrait");
                String platUserId = jsonObject.getString("platUserId");
                String managerType = jsonObject.getString("managerType");
                String medalIcon = jsonObject.getString("medalIcon");// 勋章icon URL
                String levelIcon = jsonObject.getString("levelIcon");// 等级图标URL
                String titleIcon = jsonObject.getString("titleIcon");// 称号URL （可能有多个用逗号隔开 ）
                String levelSVGA = jsonObject.getString("levelSVGA");// 等级特效URL
                String mountSVGA = jsonObject.getString("mountSVGA");// 坐骑特效URL
                if(StringMyUtil.isNotEmpty(medalIcon)&&!medalIcon.contains(Utils.getCpFirstImgurl())){
                    medalIcon = "";
                }
                if(StringMyUtil.isNotEmpty(levelIcon)&&!levelIcon.contains(Utils.getCpFirstImgurl())){
                    levelIcon = "";
                }
                if(StringMyUtil.isNotEmpty(titleIcon)&&!titleIcon.contains(Utils.getCpFirstImgurl())){
                    titleIcon = "";
                }
                if(StringMyUtil.isNotEmpty(levelSVGA)&&!levelSVGA.contains(Utils.getCpFirstImgurl())){
                    levelSVGA = "";
                }
                if(StringMyUtil.isNotEmpty(mountSVGA)&&!mountSVGA.contains(Utils.getCpFirstImgurl())){
                    mountSVGA = "";
                }
                String mountName = jsonObject.getString("mountName");// 坐骑名
                messageModel.setUserName(StringMyUtil.initEmptyStr(name));
                messageModel.setLevel(StringMyUtil.initEmptyStr(level,"1"));
                messageModel.setPortrait(StringMyUtil.initEmptyStr(portrait));
                messageModel.setManagerType(StringMyUtil.initEmptyStr(managerType));
                messageModel.setUserInfoJson(StringMyUtil.initEmptyStr(userInfoJson));
                messageModel.setPlatUserId(StringMyUtil.initEmptyStr(platUserId));
                messageModel.setMedalIcon(StringMyUtil.initEmptyStr(medalIcon));
                messageModel.setLevelIcon(StringMyUtil.initEmptyStr(levelIcon));
                messageModel.setTitleIcon(StringMyUtil.initEmptyStr(titleIcon));
                messageModel.setLevelSVGA(StringMyUtil.initEmptyStr(levelSVGA));
                messageModel.setMountSVGA(StringMyUtil.initEmptyStr(mountSVGA));
                messageModel.setMountName(StringMyUtil.initEmptyStr(mountName));
                if(messageModel.getManagerType().equals("1") && Utils.isNotLong(sendUserId)){
                    return true;
                }
                return  false;

            }

        return true;
    }
    private String getTextMessageNickName(LiveMessageModel LiveMessageModel){
        String userInfoJson = LiveMessageModel.getUserInfoJson();
        if(StringMyUtil.isNotEmpty(userInfoJson)){
            JSONObject jsonObject = JSONObject.parseObject(userInfoJson);
            return jsonObject.getString("name");
        }
        return "";
    }
    private String getTextMessageUserName(LiveMessageModel LiveMessageModel){
        String userInfoJson = LiveMessageModel.getUserInfoJson();
        if(StringMyUtil.isNotEmpty(userInfoJson)){
            JSONObject jsonObject = JSONObject.parseObject(userInfoJson);
            return jsonObject.getString("platUserId");
        }
        return "";
    }
    /**
     * 触摸右抽屉,收回聊天输入软键盘,初始化底部布局
     * @param view
     * @param event
     */
    @OnTouch(R.id.drawwe_linear)
    public void onTouch(View view, MotionEvent event){
        editPanel.editLinear.setVisibility(GONE);
        editPanel.inputClickLinear.setVisibility(VISIBLE);
        linearLayout7.setVisibility(VISIBLE);
        Utils.hideSoftKeyBoard(this);
    }


    @OnClick({R.id.pusher_tx_cloud_view,R.id.beauty_iv,R.id.start_live_btn,R.id.change_camera_iv,
            R.id.choose_channel_tv,R.id.choose_channel_iv,R.id.add_title_frameLayout,R.id.bottom_pop_iv,R.id.meiyan_iv,
            R.id.iv_close,R.id.choose_lottery_iv,R.id.choose_lottery_tv,R.id.more_iv,R.id.dismiss_open_result_iv,R.id.iv_lottery ,
            R.id.ll_gift,R.id.set_business_iv,R.id.set_business_tv,R.id.top_close_iv,R.id.room_type_tv,R.id.room_type_iv,
            R.id.change_room_type_iv,R.id.start_live_constraintLayout,R.id.nature_tv,R.id.nature_iv,R.id.rank_linear,
            R.id.face_top_linear,R.id.area_iv,R.id.area_tv,R.id.count_down_fail_refresh_tv,R.id.toy_iv,R.id.toy_tv,R.id.toy_disconnect_tv})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.toy_iv:
            case R.id.toy_tv:
            case R.id.toy_disconnect_tv:
                startActivity(new Intent(StartLiveActivity.this, ToyActivity.class));
                break;
            case R.id.count_down_fail_refresh_tv:
                getCountDown(count_down_fail_loading_iv);
                requestOpenResult(dataBean.getGame(),dataBean.getType_id());
                break;
            case R.id.area_iv:
            case R.id.area_tv:
                showCityPickerVIew();
                break;
            case R.id.rank_linear:
                initLiveRankDialogFragment();
                break;
            case R.id.nature_tv:
            case R.id.nature_iv:
                initChooseNaturePop();
                chooseNaturePop.showAtLocation(nature_iv,Gravity.BOTTOM,0,0);
                Utils.darkenBackground(StartLiveActivity.this,0.7f);
                break;
            //切换房间类型
            case R.id.change_room_type_iv:
                initChangeRoomTypePop();
                break;
            //设置房间类型
            case R.id.room_type_tv:
            case R.id.room_type_iv:
                initUnPusherSetRoomTypePop();
                break;
            //点击美颜按钮 显示美颜 隐藏其他控件
            case R.id.beauty_iv:
                live_constrainLayout_group.setVisibility(View.GONE);
                face_wrap_linear.setVisibility(VISIBLE);
                break;
            //请求推流地址
            case R.id.start_live_btn:
                if(checkPush()){
                    getRTMPPusherFromServer();
                }
                break;
            //切换摄像头
            case R.id.change_camera_iv:
                isBackCamera=!isBackCamera;
                if(USE_TX){
                    mLivePusher.switchCamera();

                    mCameraFacing = IFURenderer.CAMERA_FACING_FRONT - mCameraFacing;
                    if (mFURenderer != null) {
                        mFURenderer.onCameraChanged(mCameraFacing, CameraUtils.getCameraOrientation(mCameraFacing));
                        if (mFURenderer.getMakeupModule() != null) {
                            mFURenderer.getMakeupModule().setIsMakeupFlipPoints(mCameraFacing == IFURenderer.CAMERA_FACING_FRONT ? 0 : 1);
                        }
                    }
                }else {
                    nodePublisher.switchCamera();
                }

                break;
            //点击屏幕,隐藏输入框
            case R.id.drawwe_linear:
                editPanel.editLinear.setVisibility(GONE);
                editPanel.inputClickLinear.setVisibility(VISIBLE);
                linearLayout7.setVisibility(VISIBLE);
                Utils.hideSoftKeyBoard(this);
                break;
            //选择频道
            case R.id.choose_channel_iv:
            case R.id.choose_channel_tv:
                startActivityForResult(new Intent(StartLiveActivity.this,ChooseChannelActivity.class),SKIP_CHOOSE_CHANNEL);
                break;
            //选择封面
            case R.id.add_title_frameLayout:
                if(takeCameraPop ==null){
                    takeCameraPop = new TakeCameraPop(StartLiveActivity.this);
//                    takeCameraPop.initPop();
                    takeCameraPop.setOnPopClickListener(this);
                }
                Utils.darkenBackground(StartLiveActivity.this,0.7f);
                takeCameraPop.showAtLocation(choose_channel_iv, Gravity.BOTTOM,0,0);
                break;
            //结束直播
            case R.id.iv_close:
                initFinishLivePop();
                break;
            //选择彩种
            case R.id.choose_lottery_iv:
            case R.id.choose_lottery_tv:
                startActivityForResult(new Intent(StartLiveActivity.this,ChooseLotteryActivity.class),SKIP_CHOOSE_LOTTERY);
                break;
            //更多弹窗(开关摄像头.麦克风.切换摄像头)
            case R.id.more_iv:
                initLiveMorePop();
                liveMorePop.showAtLocation(more_iv,Gravity.BOTTOM,0,0);
                break;
                /*
                底部美颜按钮
                 */
            case R.id.meiyan_iv:
//                beautyPop =  new BeautyPop(StartLiveActivity.this,StartLiveActivity.this,manager);
                drawwe_linear.setVisibility(GONE);//隐藏抽屉界面
                face_wrap_linear.setVisibility(VISIBLE);
         /*       if(!beautyViewHolder.isShowed()){
                    beautyViewHolder.show();
                }*/
//                beautyPop.showAtLocation(beauty_iv, Gravity.BOTTOM,0,0);
/*                beautyPop.setOnDismissListener(new PopupWindow.OnDismissListener() {
                    @Override
                    public void onDismiss() {
//                        pusher_drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
                        drawwe_linear.setVisibility(View.VISIBLE);//显示抽屉界面
                    }
                });*/
                break;
            case R.id.face_top_linear:
                if(isPusher){
                    //已经在推流,显示抽屉
                    drawwe_linear.setVisibility(VISIBLE);//显示抽屉界面

                }else {
                    //未推流,显示界面的其他控件
                    live_constrainLayout_group.setVisibility(VISIBLE);
                }
                face_wrap_linear.setVisibility(GONE);
                break;
            //飘入开奖结果的隐藏按钮
            case R.id.dismiss_open_result_iv:
                lottrry_open_result_constraintLayout.setVisibility(View.INVISIBLE);
                break;
            //开奖结果
            case R.id.iv_lottery:
                initOpenResult();
                break;
            //收礼明细
            case R.id.ll_gift:
                MineDetailsActivity.startAty(StartLiveActivity.this,3);
                break;
            //底部弹幕 礼物 倒计时pop
            case R.id.bottom_pop_iv:
                BottomDialogFragment bottomDialogFragment = new BottomDialogFragment(liveChatFragment.liveMessageModelArrayList);
                bottomDialogFragment.show(getSupportFragmentManager(),"IntegralDetailsDialog");
                break;
            //设置名片
            case R.id.set_business_iv:
            case R.id.set_business_tv:
                if(Utils.getUserInfoBean().getForbidCallingCard().equals("1")){
                    canNotSetBussiness();
                }else {
                    initSetBussinessop();
                    setBusinessCardPop.showAtLocation(set_business_iv,Gravity.CENTER,0,0);
                    Utils.darkenBackground(StartLiveActivity.this,0.7f);
                }
                break;
            //开播前右上角的退出按钮
            case R.id.top_close_iv:
                finish();
                break;
            default:
                break;
        }
    }

    private void showCityPickerVIew() {
        if(isCityLoaded){
            OptionsPickerView pvOptions = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {
                @Override
                public void onOptionsSelect(int options1, int options2, int options3, View v) {
                    //返回的分别是三个级别的选中位置
                    String opt1tx = provinceOptionsItems.size() > 0 ?
                            provinceOptionsItems.get(options1).getPickerViewText() : "";

                    String opt2tx = cityOptionsItems.size() > 0
                            && cityOptionsItems.get(options1).size() > 0 ?
                            cityOptionsItems.get(options1).get(options2) : "";

                    String opt3tx = cityOptionsItems.size() > 0
                            && areaOptionsItems.get(options1).size() > 0
                            && areaOptionsItems.get(options1).get(options2).size() > 0 ?
                            areaOptionsItems.get(options1).get(options2).get(options3) : "";

                    String tx = opt1tx + opt2tx + opt3tx;
                    Log.e(TAG, "onOptionsSelect: "+ tx);
                    area_tv.setText(opt2tx);
                    area = opt2tx;
                    SharePreferencesUtil.putString(MyApplication.getInstance(),CommonStr.LIVE_AREA,String.format("%s-%s",opt1tx,opt2tx));

                }
            })
                    .setTitleText("城市选择")
                    .setDividerColor(Color.BLACK)
                    .setTextColorCenter(Color.BLACK) //设置选中项文字颜色
                    .setContentTextSize(20)
                    .build();
            pvOptions.setPicker(provinceOptionsItems, cityOptionsItems, areaOptionsItems);//三级选择器
            pvOptions.show();
        }else {
            showtoast("正在获取城市信息,请稍后");
        }
    }

    private void canNotSetBussiness() {
        CommonTipPop commonTipPop = new CommonTipPop(StartLiveActivity.this,
                "提示", "您无权限设置名片,可忽略!带赌主播或平台禁止设置名片的主播均无法设置!");
        commonTipPop.showAtLocation(set_business_tv, Gravity.CENTER,0,0);
        Utils.darkenBackground(this,0.7f);
    }

    private void initLiveRankDialogFragment() {
//        LiveRankDialogFragment liveRankDialogFragment = new LiveRankDialogFragment(LiveFragment.this);
        liveRankDialogFragment = new LiveRankDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putString("anchorAccount","mLiveData.getAnchorAccount()");
        liveRankDialogFragment.setArguments(bundle);
        liveRankDialogFragment.show(getSupportFragmentManager(),"liveRankDialogFragment");
    }
    private void initSetBussinessop() {
        if(setBusinessCardPop==null){
            setBusinessCardPop = new SetBusinessCardPop(StartLiveActivity.this);
            setBusinessCardPop.setOnPopClickListener(this);
            setBusinessCardPop.setmOnDissmissListener(new BasePopupWindow.OnDissmissListener() {
                @Override
                public void onDissmiss() {
//                    ImmersionBar.with(StartLiveActivity.this).hideBar(BarHide.FLAG_HIDE_NAVIGATION_BAR).init();
                }
            });
        }
    }

    private void initChooseNaturePop() {
        if(chooseNaturePop==null){
            chooseNaturePop = new ChooseNaturePop(StartLiveActivity.this,false);
            chooseNaturePop.setOnPopItemClick(this);
            natureEntityArrayList = chooseNaturePop.getNatureEntityArrayList();
        }
    }

    private void initChangeRoomTypePop() {
        changeRoomTypePop = new ChangeRoomTypePop(StartLiveActivity.this,true,roomType);
        changeRoomTypePop.setOnPopClickListener(StartLiveActivity.this);
        changeRoomTypePop.showAtLocation(more_iv,Gravity.BOTTOM,0,0);
        Utils.darkenBackground(StartLiveActivity.this,0.7f);
    }

    /**
     * 设置房间类型
     */
    private void initUnPusherSetRoomTypePop() {
        if(unPusherSetRoomTypePop==null){
            unPusherSetRoomTypePop = new SetRoomTypePop(StartLiveActivity.this,roomType);
            unPusherSetRoomTypePop.setOnPopClickListener(new BasePopupWindow.OnPopClickListener() {
                @Override
                public void onPopClick(View view) {
                    switch (view.getId()){
                        case R.id.set_room_type_free_tv:
                            room_type_tv.setText("房间类型(免费)");
                            roomType=1;
                            unPusherSetRoomTypePop.dismiss();

                            break;
                        case R.id.set_room_type_sure_tv:
                            String amountToString = unPusherSetRoomTypePop.set_amount_etv.getText().toString();
                            roomAmount = (StringMyUtil.isEmptyString(amountToString)?"0": amountToString);
                            BigDecimal bigDecimal = new BigDecimal(roomAmount);
                            if(bigDecimal.compareTo(BigDecimal.ZERO)<=0||bigDecimal.compareTo(new BigDecimal(1000))==1){
                                showtoast("请输入0.1-1000的金额");
                            }else {
                                roomType=2;
                                room_type_tv.setText("房间类型(收费 "+ roomAmount+"元/10分钟"+")");
                                unPusherSetRoomTypePop.dismiss();

                            }
                            break;
                        default:
                            break;
                    }
                }
            });
            unPusherSetRoomTypePop.setmOnDissmissListener(new BasePopupWindow.OnDissmissListener() {
                @Override
                public void onDissmiss() {
                    Utils.darkenBackground(StartLiveActivity.this,1f);
                    Utils.hideSoftKeyBoard(StartLiveActivity.this);
                }
            });
        }

        unPusherSetRoomTypePop.showAtLocation(room_type_tv,Gravity.CENTER,0,-300);
        Utils.showKeyboard(unPusherSetRoomTypePop.set_amount_etv);
        Utils.darkenBackground(StartLiveActivity.this,0.7f);
    }

    private void initLiveMorePop() {
        if(liveMorePop==null){
            liveMorePop = new LiveMorePop(StartLiveActivity.this);
            liveMorePop.setOnPopClickListener(this);
        }
    }

    /**
     * 检验是否可以开始推流
     */
    private boolean checkPush() {
/*        if(StringMyUtil.isEmptyString(categoryId)){
            showtoast("请选择频道");
            return false;
        }*/
        if(StringMyUtil.isEmptyString(cover)){
            showtoast("请选择封面");
            return false;
        }
        if(StringMyUtil.isEmptyString(gameClassId)){
            showtoast("请选择彩票");
            return false;
        }
        if(StringMyUtil.isEmptyString(title)){
            showtoast("请输入房间名");
            return  false;
        }
        if(Utils.getUserInfoBean().getForbidCallingCard().equals("0")){
            if(StringMyUtil.isEmptyString(busonessCard)){
                showtoast("请设置名片");
                return false;
            }
        }
        if(StringMyUtil.isEmptyString(natureStr)){
            showtoast("请选择直播性质");
            return  false;
        }
        if(StringMyUtil.isEmptyString(area)){
            showtoast("请选择地区");
            return  false;
        }
        return true;
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        String photoPath;
        String s = null;
        if(requestCode==SKIP_CHOOSE_CHANNEL&&resultCode==RESULT_OK){
            /*
            选择频道返回
             */
            HomeClassfyEntity.DataBean dataBean = (HomeClassfyEntity.DataBean) data.getSerializableExtra("dataBean");
            categoryId= dataBean.getId();
            choose_channel_tv.setText(dataBean.getName());
            SharePreferencesUtil.putString(MyApplication.getInstance(),CommonStr.CHOOSE_CHANNEL_NAME,dataBean.getName());
            SharePreferencesUtil.putString(MyApplication.getInstance(),CommonStr.CHOOSE_CHANNEL_ID,categoryId);

        } else if(requestCode==CAMARE_REQUEST_CODE){
            /*
            从相机返回
             */
            if(USE_TX){
                mLivePusher.getTXLivePusher().startCameraPreview(mPusherView);//重新开启摄像头预览
            }else {
                nodePublisher.startPreview();
            }
            if(resultCode==RESULT_OK){
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    photoPath = String.valueOf(cameraSavePath);
                    //加载本地图片为头像
//                    showBitMap(String.valueOf(cameraSavePath));
                    s = BitmapUtils.compressReSave(photoPath, this, 400);//图片压缩
                } else {
                    photoPath = uri.getEncodedPath();
                    s = BitmapUtils.compressReSave(photoPath, this, 400);//图片压缩
                }
                // 上传图片
                if(StringMyUtil.isEmptyString(s)){
                    showtoast("图片上传失败, 请重试");
                    return;
                }
                upLoadImg(s);
            }
        }
        else if (requestCode == PHOTO_REQUEST_CODE && resultCode == RESULT_OK) {
            /*
            从相册返回
             */
            String realPathFromUri = GetPhotoFromPhotoAlbum.getRealPathFromUri(this, data.getData());
            //加载本地图片为头像
            s = BitmapUtils.compressReSave(realPathFromUri, StartLiveActivity.this, 400);//图片压缩
            if(StringMyUtil.isEmptyString(s)){
                showtoast("图片上传失败, 请重试");
                return;
            }
            if (!StringMyUtil.isEmptyString(realPathFromUri)) {
                upLoadImg(s);
            } else {
                showtoast("系统出现错误,请重试");
            }
        }else if(requestCode==SKIP_CHOOSE_LOTTERY&&resultCode==RESULT_OK){
            /*
            选择彩票返回
             */
            dataBean = (LotteryEntitiy.GameClasslistBean) data.getSerializableExtra("dataBean");
            String typeName = dataBean.getTypename();
            gameClassId = dataBean.getId();
            choose_lottery_tv.setText(typeName);
            SharePreferencesUtil.putString(MyApplication.getInstance(),CommonStr.CHOOSE_LOTTERY_BEAN, JSON.toJSONString(dataBean));//设置选择的彩票bean


        }
        super.onActivityResult(requestCode, resultCode, data);
    }
    /**
     * 开奖结果Pop
     */
    private void initOpenResult() {
//        int game = 2;//mViewModel.getCpLiveData().getValue().getGame();
//        int typeId = 1;//mViewModel.getCpLiveData().getValue().getTypeId();
        int game =dataBean.getGame();
        int typeId= dataBean.getType_id();
        switch (GameTypeEnum.valueOf(game)) {
            case KUAISAN:
                customPopupWindow.initKuaiSanTodayResult(new WeakReference<>(this), typeId, true);
                customPopupWindow.initKuaiSanTodayResultData(this, beauty_iv, typeId);
                //    customPopupWindow.showKuaiSanTodayResultPop(beauty_iv,this);
                break;
            case SSC:
            case XUANWU:
                customPopupWindow.initSscTodayResultPop(this, game, typeId, true);
                customPopupWindow.initSscTodayResultData(this, game, typeId, beauty_iv);
                break;
            case RACE:
                customPopupWindow.initRaceTodayResultPop(this, game, typeId, true);
                customPopupWindow.initRaceTodayResultData(this, game, typeId, beauty_iv);
                break;
            case MARKSIX:
                customPopupWindow.initMarksixTodayResultPop(this, game, typeId, true);
                customPopupWindow.initMarksixTodayResultData(this, game, typeId, beauty_iv);
                break;
            case DANDAN:
                customPopupWindow.initPcddTodayResult(this, typeId, true);
                customPopupWindow.initPcddTodayResultData(this, beauty_iv, typeId);
                break;
            case HAPPY8:
                customPopupWindow.initHappy8TodayResult(this, typeId, true);
                customPopupWindow.initPcddTodayResultData(this, beauty_iv, typeId);
                break;
            case LUCKFARM:
                customPopupWindow.initHappy10TodayResult(this, game, typeId, true);
                customPopupWindow.initfarmTodayResultData(this, beauty_iv, typeId);
                break;
            case HAPPY10:
                customPopupWindow.initHappy10TodayResult(this, game, typeId, true);
                customPopupWindow.initHappy10TodayResultData(this, beauty_iv, typeId);
                break;
            default:
                break;
        }

    }
    /**
     * 上传图片
     * @param fliePath  压缩后的图片地址
     */
    private void upLoadImg(String fliePath) {
        HttpApiImpl.getInstance()
                .uploadFile(fliePath)
                .compose(RxTransformerUtils.io_main())
                .as(AutoDispose.autoDisposable(AndroidLifecycleScopeProvider.from((LifecycleOwner) this)))
                .subscribe(new BaseStringObserver<ResponseBody>(){
                    @Override
                    public void onSuccess(String result) {
                        com.alibaba.fastjson.JSONObject jsonObject = com.alibaba.fastjson.JSONObject.parseObject(result);
                        com.alibaba.fastjson.JSONObject data = jsonObject.getJSONObject("data");
                        if(data!=null){
                            //封面后缀
                            cover = data.getString("url");
                            //设置封面缓存
                            SharePreferencesUtil.putString(MyApplication.getInstance(),CommonStr.CHOOSE_LIVE_COVER,cover);
                            //设置封面
                            GlideLoadViewUtil.LoadNormalView(StartLiveActivity.this,cover,cover_iv);
                        }

                    }

                    @Override
                    public void onFail(String msg) {
                    }

                    @Override
                    protected void onRequestStart() {
                        super.onRequestStart();
                        showLoading();
                    }

                    @Override
                    protected void onRequestEnd() {
                        super.onRequestEnd();
                        closeLoading();
                    }
                });
    }

    /**
     * 直播标题输入框监听
     * @param Editable
     */
    @OnTextChanged(value=R.id.start_live_title_etv,callback = OnTextChanged.Callback.AFTER_TEXT_CHANGED)
    public void  onTextChanged(Editable Editable ){
        String afterChangeStr = Editable.toString();
//        start_live_titile_tv.setText(StringMyUtil.isEmptyString(afterChangeStr)?"直播标题":afterChangeStr);
        title = afterChangeStr;

    }
    @Override
    public void onNetChange(boolean netWorkState) {
    }

    @Override
    protected void onDestroy() {
        if(EventBus.getDefault().isRegistered(this)){
            EventBus.getDefault().unregister(this);
        }
//        data.put("liveId",)
        RongLibUtils.removeEventHandler(handler);
        /**
         * 销毁播放器
         */
        if(USE_TX){
            if(mLivePusher!=null){
                Log.e(TAG, "onDestroy: 结束推流" );
                mLivePusher.getTXLivePusher().stopCameraPreview(true);//结束摄像头预览
                mLivePusher.getTXLivePusher().stopPusher();//结束推流
            }
        }else {
            if(nodePublisher!=null){
                nodePublisher.stopPreview();
                nodePublisher.stop();
                nodePublisher.release();
            }
        }
        /**
         * 退出聊天室
         */
        RongLibUtils.quitChatRoom(sp.getRoomId(), new RongIMClient.OperationCallback() {
            @Override
            public void onSuccess() {
                Log.e(TAG, "onSuccess: 退出聊天室成功" );
            }

            @Override
            public void onError(RongIMClient.ErrorCode errorCode) {
                Log.e(TAG, "onError: 退出聊天室失败" );
            }
        });
        /**
         销毁美颜
         */

        if(editPanel!=null){
            HeightProvider heightProvider = editPanel.getHeightProvider();
            if(heightProvider!=null&& heightProvider.isShowing()){
                heightProvider.dismiss();
            }
        }
        /**
         * 断开蓝牙连接
         */
        if(toyId!=null){
            if (Lovense.getInstance(getApplication()).isConnected(toyId)) {
                Lovense.getInstance(getApplication()).disconnect(toyId);
            }
        }
        super.onDestroy();
    }

    /**
     * pop点击事件回调
     * @param view
     */
    @Override
    public void onPopClick(View view) {
        switch (view.getId()){
            case R.id.forbidden_tv:
                //点击拍照
                takeCameraPop.dismiss();
                checkCameraPermission();
                break;
            case R.id.set_manager_tv:
                //点击相册
                takeCameraPop.dismiss();
                checkPhotoPermission();
                break;
            case R.id.swich_camare_iv:
            case  R.id.swich_camare_tv:
                //切换摄像头
                initSwichCamare();
                break;
            case R.id.swich_video_iv:
            case R.id.swich_video_tv:
                initVideoPusher();
                break;
            case R.id.swich_microphone_iv:
            case R.id.swich_microphone_tv:
                //开关麦克风
                initSwitchMicophone();
                break;

            case R.id.swich_light_iv:
            case R.id.swich_light_tv:
                //开关闪光灯
                initSwichLight();
                break;
            //设置名片
            case R.id.set_business_card_sure_tv:
                setBusinessCard();
                break;
            case R.id.set_business_card_cancel_tv:
                if(setBusinessCardPop!=null){
                    setBusinessCardPop.dismiss();
                }
                break;
            //切换房间类型
            case R.id.change_room_type_iv:
            case R.id.change_room_type_tv:
                if(roomType==2){
                    // 当前为收费房间,点击切换免费时,弹出提示pop
                    initToll2FreePop();
                }else {
                    if(roomAmount.equals("0")){
                        //之前没有设置过金额,弹出可以设置金额的pop
                        initPusherSetRoomTypePop();
                    }else {
                        //  之前有设置过金额,弹出提示
                        initFree2TollPop();
                    }
                }

                break;
            default:
                break;
        }
    }
    /**
     * pop recyclerView 的点击回调
     * @param view
     * @param position
     */
    @Override
    public void onPopItemClick(View view, int position) {
        switch (view.getId()){
            case R.id.nature_content_tv:
                /*
                性质pop item点击
                 */
                String content = natureEntityArrayList.get(position).getContent();
                SharePreferencesUtil.putString(MyApplication.getInstance(),CommonStr.CHOOSE_NATURE,content);
                setDefaultNature();
                chooseNaturePop.dismiss();
                break;
            default:
                break;
        }
    }

    /**
     * 免费切换收费的提示pop
     */
    private void initFree2TollPop() {
        if(free2TollTipPop==null){
            free2TollTipPop = new CommonChoosePop(StartLiveActivity.this,StartLiveActivity.this,"温馨提示","您即将切换为收费模式");
            free2TollTipPop.setOnDismissListener(new PopupWindow.OnDismissListener() {
                @Override
                public void onDismiss() {
                    if(changeRoomTypePop!=null){
                        changeRoomTypePop.dismiss();
                    }
                }
            });
            free2TollTipPop.setOnClickLintener(new CommonChoosePop.OnClickLintener() {
                @Override
                public void onSureClick(View view) {
                    requestChangeRoomType(2);
                }
            });
        }
        free2TollTipPop.showAtLocation(room_type_tv, Gravity.CENTER,0,-300);
        Utils.darkenBackground(StartLiveActivity.this,0.7f);
    }

    /**
     * 收费切换免费的提示pop
     */
    private void initToll2FreePop() {
        if(toll2FreeTipPop==null){
            toll2FreeTipPop = new CommonChoosePop(StartLiveActivity.this,StartLiveActivity.this,"温馨提示","您即将切换为免费模式");
            toll2FreeTipPop.setOnDismissListener(new PopupWindow.OnDismissListener() {
                @Override
                public void onDismiss() {
                    if(changeRoomTypePop!=null){
                        changeRoomTypePop.dismiss();
                    }
                }
            });
            toll2FreeTipPop.setOnClickLintener(new CommonChoosePop.OnClickLintener() {
                @Override
                public void onSureClick(View view) {
                    requestChangeRoomType(1);
                }
            });
        }
        toll2FreeTipPop.showAtLocation(room_type_tv, Gravity.CENTER,0,-300);
        Utils.darkenBackground(StartLiveActivity.this,0.7f);
    }

    /**
     * 开播后,且之前没有设置过金额,弹出可以设置金额的pop
     */
    private void initPusherSetRoomTypePop() {
        pusherChangeRoomTypePop = new SetRoomTypePop(StartLiveActivity.this,true);
        pusherChangeRoomTypePop.setOnPopClickListener(new BasePopupWindow.OnPopClickListener() {
            @Override
            public void onPopClick(View view) {
                switch (view.getId()){
                    case R.id.set_room_type_free_tv:
                        initChangeRoomType(1,true);
                        break;
                    case R.id.set_room_type_sure_tv:
                        initChangeRoomType(2,false);
                        break;
                    default:
                        break;
                }
            }
        });
        pusherChangeRoomTypePop.showAtLocation(room_type_tv, Gravity.CENTER,0,-300);
        Utils.darkenBackground(StartLiveActivity.this,0.7f);
    }

    private void initChangeRoomType( int requestType,boolean isFree) {
        if(isFree){
            if (roomType == 1) {
                pusherChangeRoomTypePop.dismiss();
                changeRoomTypePop.dismiss();
            } else {
                requestChangeRoomType(requestType);
            }
        }else {
            String amountToString = pusherChangeRoomTypePop.set_amount_etv.getText().toString();
            roomAmount = (StringMyUtil.isEmptyString(amountToString)?"0": amountToString);
            BigDecimal bigDecimal = new BigDecimal(roomAmount);
            if(bigDecimal.compareTo(BigDecimal.ZERO)<=0||bigDecimal.compareTo(new BigDecimal(1000))==1){
                showtoast("请输入0.1-1000的金额");
            }else {
                if (roomType == 2) {
                    pusherChangeRoomTypePop.dismiss();
                    changeRoomTypePop.dismiss();
                } else {
                    requestChangeRoomType(requestType);
                }
            }

        }

    }

    /**
     * 请求切换房间类型
     * @param type 需要切换成房间类型的type  1 免费 2 收费
     */
    private void requestChangeRoomType(int type) {
        HashMap<String, Object> data = new HashMap<>();
        data.put("amount", type==1?0:roomAmount);
        data.put("streamName", streamName);
        data.put("type", type);
        HttpApiUtils.wwwRequest(StartLiveActivity.this, null, RequestUtils.CHANGE_ROOM_TYPE, data, new HttpApiUtils.OnRequestLintener() {
            @Override
            public void onSuccess(String result) {
                ChangeRoomTypeEntity changeRoomTypeEntity = JSONObject.parseObject(result, ChangeRoomTypeEntity.class);
                //String type, String zkCode
                int nowType = changeRoomTypeEntity.getData().getType();
                int type=0;
                roomType= nowType;
                if(nowType==1){
                    showtoast("直播间已切换为免费模式");
                    type=1;
                }else {
                    showtoast("直播间已切换为收费模式");
                    type=2;
                }
                SwichMoneyMessage swichMoneyMessage = new SwichMoneyMessage(type + "", "");
                swichMoneyMessage.setUserInfoJson(RongLibUtils.setUserInfo(StartLiveActivity.this));
                RongLibUtils.sendMessage(sp.getRoomId(),swichMoneyMessage);

                if(pusherChangeRoomTypePop!=null){
                    pusherChangeRoomTypePop.dismiss();
                }
                if(changeRoomTypePop!=null){
                    changeRoomTypePop.dismiss();
                }
                if(toll2FreeTipPop!=null){
                    toll2FreeTipPop.dismiss();
                }
                if(free2TollTipPop!=null){
                    free2TollTipPop.dismiss();
                }
            }

            @Override
            public void onFail(String msg) {
            }
        });
    }

    /**
     * 设置名片
     */
    private void setBusinessCard() {
        busonessCard = setBusinessCardPop.set_business_card_etv.getText().toString();
        if(StringMyUtil.isEmptyString(busonessCard)){
            showtoast("请输入你的微信号");
        }else {
            modifyBusinessCard();
        }
    }
    /**
     * 设置名片
     * @param
     */
    private void modifyBusinessCard( ) {
        HashMap<String, Object> data = new HashMap<>();
        data.put("callingCard",busonessCard);
        HttpApiUtils.request(this,null, RequestUtils.MODIFY_USERINFO, data, new HttpApiUtils.OnRequestLintener() {
            @Override
            public void onSuccess(String result) {
                JSONObject jsonObject = JSONObject.parseObject(result);
                showtoast("设置成功");
                set_business_tv.setText(busonessCard);
                //修改缓存中的用户信息
                UserInfoEntity.DataBean userInfoBean = Utils.getUserInfoBean();
                userInfoBean.setCallingCard(busonessCard);
                Utils.setUserInfoBean(userInfoBean);
                setBusinessCardPop.dismiss();
            }

            @Override
            public void onFail(String msg) {
            }
        });
    }
    /**
     * 开关摄像头
     */
    private void initVideoPusher() {
        if(isPausePusher){
            //当前为垫片推流时,点击恢复推流
            if(USE_TX){
                mLivePusher.getTXLivePusher().resumePusher();
            }else {
                nodePublisher.setVideoEnable(true);
            }
            liveMorePop. swich_video_iv.setImageResource(R.drawable.gbsxt);
            liveMorePop.swich_video_tv.setText("关闭摄像头");
        }else{
            //当前为正常推流时,点击进入垫片推流
            if(USE_TX){
                mLivePusher.getTXLivePusher().pausePusher();
            }else {
                nodePublisher.setVideoEnable(false);
            }
            liveMorePop. swich_video_iv.setImageResource(R.drawable.kqsxt);
            liveMorePop.swich_video_tv.setText("开启摄像头");
            //关闭摄像头时,关闭闪光灯
            if(USE_TX){
                mLivePusher.getTXLivePusher().turnOnFlashLight(false);
            }else {
                nodePublisher.setFlashEnable(false);
            }
            if(liveMorePop!=null){
                //pop中开关闪光灯的显示
                liveMorePop.swich_light_iv.setImageResource(R.drawable.kqsgd);
                liveMorePop. swich_light_tv.setText("开启闪光灯");
            }

        }
        isPausePusher=!isPausePusher;
    }

    /**
     * 右上角倒计时的正常显示
     */
    private void showTimeLInear() {
        isStart=dataBean.getIsStart()+"";
        if(isStart.equals("0")){
//            is_stop_tv.setVisibility(View.VISIBLE);
            tv_countdown.setVisibility(View.GONE);
            countdown_iv.setVisibility(View.GONE);
        }else {
            tv_countdown.setVisibility(VISIBLE);
            countdown_iv.setVisibility(View.GONE);
//            is_stop_tv.setVisibility(View.GONE);
        }
    }

    /**
     * 右上角倒计时的londing显示
     */
    private void showLoadingLinear() {
        isStart=dataBean.getIsStart()+"";
        if (isStart.equals("0")) {
            countdown_iv.setVisibility(View.GONE);
            tv_countdown.setVisibility(View.GONE);
//            is_stop_tv.setVisibility(View.VISIBLE);
        } else {
            countdown_iv.setVisibility(VISIBLE);
            tv_countdown.setVisibility(View.GONE);
//            is_stop_tv.setVisibility(View.GONE);
        }
    }
    /**
     开关麦克风
     */
    private void initSwitchMicophone() {
        if(USE_TX){
            mLivePusher.getTXLivePusher().setMute(!isMute);
        }else {
            nodePublisher.setAudioEnable(!isMute);
        }
        if(isMute){
            //成功关闭静音
            liveMorePop.swich_microphone_iv.setImageResource(R.drawable.gbmkf);
            liveMorePop.swich_microphone_tv.setText("关闭麦克风");
        }else {
            //成功开启静音
            liveMorePop.swich_microphone_iv.setImageResource(R.drawable.kqmkf);
            liveMorePop.swich_microphone_tv.setText("开启麦克风");
        }
        isMute=!isMute;
    }

    /**
     * 开关闪光灯
     */
    private void initSwichLight() {
        if(isBackCamera){
            /*
            当前是后置摄像头
             */
            if(USE_TX){
                mLivePusher.getTXLivePusher().turnOnFlashLight(!isOnLight);
            }else {
                nodePublisher.setFlashEnable(!isOnLight);
            }
            //开关闪光灯之前的闪光灯状态
            if(isOnLight){
                //成功关闭闪光灯
                liveMorePop.swich_light_iv.setImageResource(R.drawable.kqsgd);
                liveMorePop.swich_light_tv.setText("开启闪光灯");
            }else {
                //成功开启闪光灯
                liveMorePop.swich_light_iv.setImageResource(R.drawable.gbsgd);
                liveMorePop.swich_light_tv.setText("关闭闪光灯");
            }
            //重置闪光灯状态
            isOnLight=!isOnLight;
        }else {
            /*
            当前是前置摄像头
             */
            showtoast("使用后置摄像头才能开启闪光灯");
        }
    }

    /**
     * 切换摄像头
     */
    private void initSwichCamare() {
        //切换摄像头
        if(USE_TX){
            mLivePusher.switchCamera();
            mCameraFacing = IFURenderer.CAMERA_FACING_FRONT - mCameraFacing;
            if (mFURenderer != null) {
                mFURenderer.onCameraChanged(mCameraFacing, CameraUtils.getCameraOrientation(mCameraFacing));
                if (mFURenderer.getMakeupModule() != null) {
                    mFURenderer.getMakeupModule().setIsMakeupFlipPoints(mCameraFacing == IFURenderer.CAMERA_FACING_FRONT ? 0 : 1);
                }
            }
        }else {
            nodePublisher.switchCamera();
        }

        //设置是否是后置摄像头的值
        isBackCamera=!isBackCamera;
        //如果切换后是前置,需要将闪光灯关闭,
        if(!isBackCamera){
            if(USE_TX){
                mLivePusher.getTXLivePusher().turnOnFlashLight(false);
            }else {
                nodePublisher.setFlashEnable(false);
            }
            //预览时切换摄像头不需要改变pop图片和文字
            if(liveMorePop!=null){
                //pop中开关闪光灯的显示
                liveMorePop.swich_light_iv.setImageResource(R.drawable.kqsgd);
                liveMorePop. swich_light_tv.setText("开启闪光灯");
            }

        }

    }
    private void initJsonData() {//解析数据

        /**
         * 注意：assets 目录下的Json文件仅供参考，实际使用可自行替换文件
         * 关键逻辑在于循环体
         *
         * */
        String JsonData = new GetJsonDataUtil().getJson(this, "province.json");//获取assets目录下的json文件数据

        ArrayList<ProvinceJsonBean> provinceJsonBean = parseData(JsonData);//用Gson 转成实体

        /**
         * 添加省份数据
         *
         * 注意：如果是添加的JavaBean实体，则实体类需要实现 IPickerViewData 接口，
         * PickerView会通过getPickerViewText方法获取字符串显示出来。
         */
        provinceOptionsItems = provinceJsonBean;
        for (int i = 0; i < provinceJsonBean.size(); i++) {//遍历省份
            ArrayList<String> cityList = new ArrayList<>();//该省的城市列表（第二级）
            ArrayList<ArrayList<String>> province_AreaList = new ArrayList<>();//该省的所有地区列表（第三极）

            for (int c = 0; c < provinceJsonBean.get(i).getCityList().size(); c++) {//遍历该省份的所有城市
                String cityName = provinceJsonBean.get(i).getCityList().get(c).getName();
                cityList.add(cityName);//添加城市
                ArrayList<String> city_AreaList = new ArrayList<>();//该城市的所有地区列表

                //如果无地区数据，建议添加空字符串，防止数据为null 导致三个选项长度不匹配造成崩溃
                /*if (jsonBean.get(i).getCityList().get(c).getArea() == null
                        || jsonBean.get(i).getCityList().get(c).getArea().size() == 0) {
                    city_AreaList.add("");
                } else {
                    city_AreaList.addAll(jsonBean.get(i).getCityList().get(c).getArea());
                }*/
                city_AreaList.addAll(provinceJsonBean.get(i).getCityList().get(c).getArea());
                province_AreaList.add(city_AreaList);//添加该省所有地区数据
            }

            /**
             * 添加城市数据
             */
            cityOptionsItems.add(cityList);

            /**
             * 添加地区数据
             */
            areaOptionsItems.add(province_AreaList);
            isCityLoaded=true;
        }


    }
    public ArrayList<ProvinceJsonBean> parseData(String result) {//Gson 解析
        ArrayList<ProvinceJsonBean> detail = new ArrayList<>();
        try {
            org.json.JSONArray data = new org.json.JSONArray(result);
            Gson gson = new Gson();
            for (int i = 0; i < data.length(); i++) {
                ProvinceJsonBean entity = gson.fromJson(data.optJSONObject(i).toString(), ProvinceJsonBean.class);
                detail.add(entity);
            }
        } catch (Exception e) {
            e.printStackTrace();

        }
        return detail;
    }
    /**
     检查相机权限(拍照时要同时请求读写权限,否则无法压缩图片)
     */
    private void checkCameraPermission() {
        //已有权限,调用相机
        if (EasyPermissions.hasPermissions(this, PERMISSIONS)) {
            goCamera();
        } else {
            //没有打开相关权限、申请权限
            EasyPermissions.requestPermissions(this, "需要获取您的相机使用权限", CAMARE_REQUEST_CODE,PERMISSIONS);
        }
    }
    /**
     检查相册权限
     */
    private void checkPhotoPermission() {
        if (EasyPermissions.hasPermissions(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            goPhotoAlbum();
        } else {
            //没有打开相关权限、申请权限
            EasyPermissions.requestPermissions(this, "需要获取您的相册使用权限", PHOTO_REQUEST_CODE, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }
    }
    //权限申请回调(默认写法)
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    /**
     权限申请成功
     */
    @Override
    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {
        //请求相机权限成功
        if (requestCode == CAMARE_REQUEST_CODE) {
            goCamera();
        }
        //请求相册权限成功
        else if (requestCode == PHOTO_REQUEST_CODE) {
            goPhotoAlbum();
        }
    }

    /**
     权限申请失败
     */
    @Override
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {
        showtoast("请同意相关权限，否则功能无法使用");
    }

    /**
     *激活相机操作
     */
    private void goCamera() {
        /**
         * 调用相机之前需要先停止摄像头预览,否则拍照完成后崩溃 在拍照完成后重新开启预览
         * 报错信息: Permission Denial:opening provider......
         */
        if(USE_TX){
            mLivePusher.getTXLivePusher().stopCameraPreview(true);
        }else {
            nodePublisher.stopPreview();
        }
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            uri = FileProvider.getUriForFile(this, getApplication().getPackageName()+".fileprovider", cameraSavePath);
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        } else {
            uri = Uri.fromFile(cameraSavePath);
        }
        intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        //直接使用startActivityForResult 如果使用getActivity().startActivityForResult, activity onActivityResult中要加上super.onActivityResult(requestCode, resultCode, data)。
        startActivityForResult(intent, CAMARE_REQUEST_CODE);
    }

    /**
     调用相册
     */
    private void goPhotoAlbum() {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, PHOTO_REQUEST_CODE);
    }
    /**
     * 从相册或者相机返回后,现将imageView设置为本地图片,然后进行上传处理
     * @param path
     */
    private void showBitMap(String path) {
        //将拍照的图片取出并缩小后显示在界面上
        Bitmap camorabitmap = BitmapFactory.decodeFile(path);
        if (null != camorabitmap) {
            int scale = ImageThumbnail.reckonThumbnail(camorabitmap.getWidth(), camorabitmap.getHeight(), cover_iv.getWidth(), cover_iv.getHeight());
            Bitmap bitMap = ImageThumbnail.PicZoom(camorabitmap, camorabitmap.getWidth() / scale, camorabitmap.getHeight() / scale);
            //由于Bitmap内存占用较大，这里需要回收内存，否则会报out of memory异常
            camorabitmap.recycle();
            Glide.with(this)
                    .load(bitMap)
                    .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                    .into(cover_iv);
        }
    }

    /*  ---------------------------------------------------------美颜自定义渲染  start  */
    @Override
    public int onTextureCustomProcess(int texture, int width, int height) {

        if (mIsFirstFrame) {
//            Log.d(TAG, "onTextureCustomProcess: texture:" + i + ", width:" + i1 + ", height:" + i2);
            mFURenderer.onSurfaceCreated();
            mIsFirstFrame = false;
            return 0;
        }
        //三星s6总是会出现花屏
        if (System.currentTimeMillis() - currentTime < 200){
            mFURenderer.onDrawFrameSingleInput(texture, width, height);
        }
        long start = System.nanoTime();
        int texId = mFURenderer.onDrawFrameSingleInput(texture, width, height);
        long renderTime = System.nanoTime() - start;
        return texId;
    }

    @Override
    public void onTextureDestoryed() {
        mFURenderer.onSurfaceDestroyed();
        mIsFirstFrame = true;
    }
    @Override
    public void onDetectFacePoints(float[] floats) {
    }
    /*  ---------------------------------------------------------美颜自定义渲染  end  */


    @Override
    public void onTrackStatusChanged(int type, int status) {
        Log.i(TAG, "onTrackStatusChanged() called with: type = [" + type + "], status = [" + status + "]");
        if (mTvTrackStatus == null) {
            return;
        }
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mTvTrackStatus.setText(type == FURenderer.TRACK_TYPE_FACE ? R.string.toast_not_detect_face : R.string.toast_not_detect_face_or_body);
                mTvTrackStatus.setVisibility(status > 0 ? View.INVISIBLE : VISIBLE);
            }
        });
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        float x = event.values[0];
        float y = event.values[1];
        if (Math.abs(x) > 3 || Math.abs(y) > 3) {
            if (Math.abs(x) > Math.abs(y)) {
                mFURenderer.onDeviceOrientationChanged(x > 0 ? 270 : 90);
            } else {
                mFURenderer.onDeviceOrientationChanged(y > 0 ? 0 : 180);
            }
        }
    }
        private void requestForbidden(String user_id) {
            HashMap<String, Object> data = new HashMap<>();
            data.put("chatRoomId",sp.getRoomId());
            data.put("gagType",6);
            JSONArray jsonArray = new JSONArray();
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("image", "");
            jsonObject.put("nickName","[自动封禁]");
            jsonObject.put("userSpeakContent","[自动封禁]");
            jsonObject.put("remark","[自动封禁]");
            jsonObject.put("rongId",user_id);
            jsonObject.put("platformCode","系统");
            jsonObject.put("userName","[自动封禁]");
            jsonArray.add(jsonObject);
            data.put("members",jsonArray);
        HttpApiUtils.normalRequest(this, null, RequestUtils.FORBIDDEN, data, new HttpApiUtils.OnRequestLintener() {
            @Override
            public void onSuccess(String result) {
                Log.e(TAG, "onSuccess: 拉黑傻逼成功" );
            }

            @Override
            public void onFail(String msg) {
            }
        });
    }
    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }


    static class DiaLogListener implements DialogInterface.OnClickListener {
        WeakReference<StartLiveActivity> activityWeakReference;
        DiaLogListener(StartLiveActivity activity) {
            this.activityWeakReference = new WeakReference<>(activity);
        }

        @Override
        public void onClick(DialogInterface dialog, int which) {
            StartLiveActivity activity = activityWeakReference.get();
            if (activity == null)return;
            switch (which){
                case -1:
                    dialog.cancel();
                    activity.finish();
                    break;
                case -2:
                    dialog.cancel();
                    break;
            }
        }
    }

    private class MyHandler extends Handler{
        @Override
        public void handleMessage(Message message) {
            super.handleMessage(message);
            if(message.obj instanceof io.rong.imlib.model.Message ){
                io.rong.imlib.model.Message obj = (io.rong.imlib.model.Message) message.obj;
                String targetId = obj.getTargetId();//消息是发往哪个直播间的
                //过滤不是本直播间的消息
                if(targetId.equals(sp.getRoomId())){
                    MessageContent content = obj.getContent();
                    switch (message.what) {
                        //发送和接受消息
                        case RongLibUtils.MESSAGE_ARRIVED:
                        case RongLibUtils.MESSAGE_SENT:
                            try {
                                /**
                                 *普通文本消息
                                 */
                                LiveMessageModel messageModel = new LiveMessageModel();
                                messageModel.setMessageDirection(obj.getMessageDirection());
                                String senderUserId = obj.getSenderUserId();
                                messageModel.setRcUserId(senderUserId);
                                if (content instanceof LiveTextMessage) {
                                    LiveTextMessage liveTextMessage = (LiveTextMessage) content;
                                    if(initUserInfo(messageModel, liveTextMessage.getUserInfoJson(),senderUserId)){
                                        requestForbidden(senderUserId);
                                        return;
                                    }

                                    messageModel.setObjectName(CommonStr.TEXT_MESSAGE);
                                    messageModel.setTextContent(liveTextMessage.getContent());
                                    messageModel.setZkCode(liveTextMessage.getZkCode());
                                    liveChatFragment.addItem(messageModel);
                                    //发送通知,底部弹幕pop中显示
                                    EventBus.getDefault().post(messageModel);
                                    //收集文本消息
                                    commitLiveMessageModelList.add(messageModel);

                                } else if (content instanceof LiveShareBetMessage) {
                                    /**
                                     *      跟投消息
                                     */
                                    LiveShareBetMessage liveShareBetMessage = (LiveShareBetMessage) content;
                                    String type_id = liveShareBetMessage.getType_id();
                                    String rule_id = liveShareBetMessage.getRule_id();
                                    String lotmoney = liveShareBetMessage.getLotmoney();
                                    String[] typeidSplit = type_id.split(",");
                                    String[] ruleidSplit = rule_id.split(",");
                                    String[] lotmoneySplit = lotmoney.split(",");
                                    boolean isPass = true;
                                    for (int i = 0; i < typeidSplit.length; i++) {
                                        if(Utils.isNotLong(typeidSplit[i])){
                                            isPass = false;
                                            break;
                                        }
                                    }
                                    for (int i = 0; i < ruleidSplit.length; i++) {
                                        if(Utils.isNotLong(ruleidSplit[i])){
                                            isPass = false;
                                            break;
                                        }
                                    }
                                    for (int i = 0; i < lotmoneySplit.length; i++) {
                                        if(Utils.isNotInt(lotmoneySplit[i])){
                                            isPass = false;
                                            break;
                                        }
                                    }
                                    if(Utils.isNotInt(liveShareBetMessage.getPointGrade())||
                                       Utils.isNotLong(liveShareBetMessage.getLotteryqishu()) ||
                                       Utils.isNotInt(liveShareBetMessage.getGame()) ||
                                       !isPass ||
                                       initUserInfo(messageModel, liveShareBetMessage.getUserInfoJson(),senderUserId)){
                                        requestForbidden(senderUserId);
                                        return;
                                    }
                                    messageModel.setObjectName(CommonStr.SHARE_MESSAGE);
                                    messageModel.setZkCode(liveShareBetMessage.getZkCode());
                                    messageModel.setLevel(liveShareBetMessage.getPointGrade());
                                    messageModel.setUserName(liveShareBetMessage.getNickname());
                                    String betMoney = liveShareBetMessage.getLotmoney();
                                    List<String> amountAmountList = Arrays.asList(betMoney.split(","));
                                    int betAMount = 0 ;
                                    for (int i = 0; i < amountAmountList.size(); i++) {
                                        betAMount += Integer.parseInt(amountAmountList.get(i));
                                    }
                                    messageModel.setBetAmount(betAMount+"");
                                    messageModel.setTypeName(liveShareBetMessage.getTypename());
                                    messageModel.setBetQiShu(liveShareBetMessage.getLotteryqishu());
                                    messageModel.setBetName(liveShareBetMessage.getName());
                                    messageModel.setBetGroupName(liveShareBetMessage.getGroupname());
                                    messageModel.setType_id(liveShareBetMessage.getType_id());
                                    messageModel.setGame(liveShareBetMessage.getGame());
                                    messageModel.setReluId(liveShareBetMessage.getRule_id());
                                    liveChatFragment.addItem(messageModel);
                                } else if (content instanceof LiveRedMessage) {
                                    /**
                                     * 抢红包消息
                                     */
                                    LiveRedMessage liveRedMessage = (LiveRedMessage) content;
                                    int redType = liveRedMessage.getRedType();
                                    if(Utils.isNotDouble(liveRedMessage.getRedPrice()) ||
                                            Utils.isNotLong(liveRedMessage.getRedId()) ||
                                            Utils.isNotInt(liveRedMessage.getRedType()+"")){
                                        requestForbidden(senderUserId);
                                        return;
                                    }
                                    //3的用户信息放在 userInfoJson中  1 2 的用户信息在qpUserName中
                                    if (redType == 3) {
                                        if(initUserInfo(messageModel, liveRedMessage.getUserInfoJson(),senderUserId)){
                                            requestForbidden(senderUserId);
                                            return;
                                        }
                                    } else {
                                        messageModel.setUserName(liveRedMessage.getQbUserName());
                                    }
                                    messageModel.setObjectName(CommonStr.RED_MESSAGE);
                                    messageModel.setRedType(redType);
                                    messageModel.setRedPrice(liveRedMessage.getRedPrice());
                                    messageModel.setRedId(Long.parseLong(liveRedMessage.getRedId()));
                                    liveChatFragment.addItem(messageModel);

                                } else if (content instanceof LiveGiftMessage) {
                                    /**
                                     *礼物消息
                                     */
//                            添加到聊天列表  开始   ---------------------
                                    LiveGiftMessage liveGiftMessage = (LiveGiftMessage) content;
                                    String userInfoJson = liveGiftMessage.getUserInfoJson();
                                    if(initUserInfo(messageModel, userInfoJson,senderUserId) ||
                                       Utils.isNotLong(liveGiftMessage.getGiftId()) ||
                                       Utils.isNotInt(liveGiftMessage.getGiftNum()) ||
                                       Utils.isNotDouble(liveGiftMessage.getGiftPrice()) ||
                                       Utils.isNotInt(liveGiftMessage.getGear()) ||
                                       Utils.isNotInt(liveGiftMessage.getGearTime())){
                                        requestForbidden(senderUserId);
                                        return;
                                    }
                                    /**
                                     * 礼物震动不为空,添加到数组,开始震动
                                     */
                                    String gear = liveGiftMessage.getGear();
                                    String gearTime = liveGiftMessage.getGearTime();
                                    if(StringMyUtil.isNotEmpty(gear)&&!gear.equals("0")&&StringMyUtil.isNotEmpty(gearTime)&&!gearTime.equals("0")&&isUseToy==1&&StringMyUtil.isNotEmpty(toyId)){
                                        gearMessageList.add(liveGiftMessage);
                                    }
                                    if(!isGearing){
                                        handlerGiftGear();
                                    }
                                    messageModel.setZkCode(liveGiftMessage.getZkCode());
                                    messageModel.setObjectName(CommonStr.GIFT_MESSAGE);
                                    messageModel.setGiftId(liveGiftMessage.getGiftId());
                                    String giftNum = liveGiftMessage.getGiftNum();
                                    messageModel.setGiftNum(giftNum);
                                    messageModel.setGiftName(liveGiftMessage.getGiftName());
                                    String giftPrice = liveGiftMessage.getGiftPrice();
                                    BigDecimal singlePrice = new BigDecimal(giftPrice);
                                    BigDecimal oldPrice = singlePrice .multiply(new BigDecimal(giftNum));
                                    liveChatFragment.addItem(messageModel);
                                    String toString = money_tv.getText().toString();
                                    BigDecimal oldNum = new BigDecimal(toString.contains("-") ? "0" : toString);
                                    money_tv.setText((oldNum.add(oldPrice))+"");
                                    //发送通知,底部礼物pop中显示
                                    EventBus.getDefault().post(messageModel);
//                            添加到聊天列表  结束   ---------------------
//     礼物特效播放   开始  -----------------------
                                    String giftUrl = liveGiftMessage.getGiftUrl();//礼物特效地址
                                    String giftIcon = liveGiftMessage.getGiftIcon();
                                    if(giftUrl.contains("http") || giftIcon.contains("http")){
                                        requestForbidden(senderUserId);
                                        return;
                                    }
                                    JSONObject jsonObject = JSONObject.parseObject(userInfoJson);//用户信息
                                    int giftCount = 0;
                                    try {
                                        giftCount = Integer.parseInt(giftNum);
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                    GiftSendModel giftSendModel = new GiftSendModel(giftCount);
                                    giftSendModel.setGiftRes(giftIcon);
                                    giftSendModel.setNickname(jsonObject.getString("name"));
                                    giftSendModel.setSig("送出" + liveGiftMessage.getGiftName());
                                    String portrait = jsonObject.getString("portrait");
                                    giftSendModel.setUserAvatarRes(portrait);
                                    giftView.addGift(giftSendModel);
                                    if(StringMyUtil.isNotEmpty(giftUrl)) {//有礼物特效表示为大礼物, 播放svga动画(拦截掉自己发的大礼物消息,因为在调用发送接口成功的时候已经播放了)
                                        if(!jsonObject.getString("userId").equals(userDataBean.getId())){
                                            LiveGiftSVGAEntity liveGiftSVGAEntity = new LiveGiftSVGAEntity(Utils.getFirstImgurl(MyApplication.getInstance())+giftUrl, null);
                                            giftSvgaManage.startAnimator(liveGiftSVGAEntity);
                                        }
                                    }
//                            礼物特效播放   结束  -----------------------
                                } else if (content instanceof LiveExitAndJoinMessage) {
                                    /**
                                     * 进入/退出直播间消息
                                     */
                                    LiveExitAndJoinMessage liveExitAndJoinMessage = (LiveExitAndJoinMessage) content;
                                    if(initUserInfo(messageModel, liveExitAndJoinMessage.getUserInfoJson(),senderUserId) ||
                                       Utils.isNotInt(liveExitAndJoinMessage.getStatus())){
                                        requestForbidden(senderUserId);
                                        return;
                                    }

                                    messageModel.setZkCode(liveExitAndJoinMessage.getZkCode());
                                    messageModel.setObjectName(CommonStr.EXIT_JOIN_MESSAGE);
                                    String userName = liveExitAndJoinMessage.getUserName();
                                    userName = StringMyUtil.isEmptyString(userName)?"***":userName;
                                    messageModel.setUserName(userName);
                                    String status = liveExitAndJoinMessage.getStatus();
                                    messageModel.setStatus(status);
                                    String userNickName = SharePreferencesUtil.getString(MyApplication.getInstance(), "userNickName", "");
                                    if(status.equals("0")){//进入直播间,在线人数增加1
                                        if(!userName.equals(userNickName)){
                    /*                totalMemberCount++;
                                    if(totalMemberCount>mostHighMemberCount){
                                        mostHighMemberCount=totalMemberCount;
                                    }

                                    tv_num.setText(totalMemberCount*watchRadio+"人");*/
                                        }
//                                liveChatFragment.addItem(messageModel);
                                        liveChatFragment.addJoinItem(messageModel);
                                        String level1 = messageModel.getLevel();
                                        int level=1;
                                        if(Utils.isInt(level1)){
                                            level = Integer.parseInt(StringMyUtil.isEmptyString(level1) ? "1" : level1);
                                        }
                                        String mountSVGA = messageModel.getMountSVGA();
                                        String levelSVGA = messageModel.getLevelSVGA();
                                        if(StringMyUtil.isNotEmpty(mountSVGA)){
                                            //坐骑特效不为空, 用礼物的播放View播放坐骑特效
                                            LiveGiftSVGAEntity liveGiftSVGAEntity = new LiveGiftSVGAEntity(Utils.checkImageUrl(mountSVGA), messageModel);
                                            giftSvgaManage.startAnimator(liveGiftSVGAEntity);
                                        }else {
                                            if(level>=2){
                                                joinGiftSvgaMage.startAnimator(messageModel,Utils.cpCheckImageUrl(levelSVGA));
                                            }
                                        }

                                    }else {//退出直播间在线人数-1
                                        if(!userName.equals(userNickName)){
                         /*           totalMemberCount--;
                                    if(totalMemberCount>mostHighMemberCount){
                                        mostHighMemberCount=totalMemberCount;
                                    }
                                    tv_num.setText(totalMemberCount*watchRadio+"人");*/
                                            //退出直播间不显示
//                                    liveChatFragment.addItem(messageModel);
                                        }
                                    }
                                    onlineNumObservable.onNext(totalMemberCount);
                                }else if(content instanceof TextMessage){
                                    /**
                                     后台发送的消息(不是自定义消息,发送的是融云默认的文本消息)
                                     */
                                    TextMessage textMessage= (TextMessage) content;

                                    String textContent = textMessage.getContent();
                                    
                                    if(!Utils.isJsonObject(textContent)||(!senderUserId.contains(CommonStr.RONG_ID_STR) && !senderUserId.equals("1"))){
                                        requestForbidden(senderUserId);
                                        return;
                                    }
                                    ZjMessageModel zjMessageModel = JSONObject.parseObject(textContent, ZjMessageModel.class);
                                    String isBack = zjMessageModel.getIsBack();
                                    messageModel.setObjectName(isBack);
                                    messageModel.setUserNickName(zjMessageModel.getUserNickName());
                                    messageModel.setTypeName(zjMessageModel.getTypename());
                                    messageModel.setUserName(zjMessageModel.getUserNickName());
                                    if(zjMessageModel.getUser_id()!=0){//user_id为0时, 是机器人中奖信息, 主播端不需要显示
                                        liveChatFragment.addItem(messageModel);
                                    }
                                }else if(content instanceof LiveSystemMessage){
                                    /**
                                     * 后台封禁/拉黑消息
                                     */
                                    if(!senderUserId.contains(CommonStr.RONG_ID_STR) && !senderUserId.equals("1")){
                                        requestForbidden(senderUserId);
                                        return;
                                    }
                                    LiveSystemMessage liveSystemMessage = (LiveSystemMessage) content;
                                    SystemMessageModel systemMessageModel = JSONObject.parseObject(liveSystemMessage.getContent(), SystemMessageModel.class);
                                    messageModel.setZkCode(systemMessageModel.getPlatformCode());
                                    messageModel.setObjectName(CommonStr.LIVE_SYSTEM_MESSAGE);
                                    messageModel.setUserNickName(systemMessageModel.getUserNickName());
                                    messageModel.setSystemStatus(systemMessageModel.getStatus());
                                    messageModel.setField(systemMessageModel.getField());
                                    liveChatFragment.addItem(messageModel);
                                }
                                else if(content instanceof LiveFollowMessage){
                                    /**
                                     *关注消息
                                     */
                                    LiveFollowMessage liveFollowMessage = (LiveFollowMessage) content;
                                    if(initUserInfo(messageModel,liveFollowMessage.getUserInfoJson(),senderUserId)){
                                        requestForbidden(senderUserId);
                                        return;
                                    }

                                    messageModel.setObjectName(CommonStr.FOLLOW_MESSAGE);
                                    liveChatFragment.addItem(messageModel);

                                }else if(content instanceof ForbiddenMessage){
                                    /**
                                     * 禁言消息
                                     */
                                    ForbiddenMessage forbiddenMessage = (ForbiddenMessage) content;
                                    if(initUserInfo(messageModel,forbiddenMessage.getUserInfoJson(),senderUserId) ||
                                       Utils.isNotInt(forbiddenMessage.getIsForbidden())){
                                        requestForbidden(senderUserId);
                                        return;
                                    }
                                    messageModel.setObjectName(CommonStr.FORBIDDEN_MESSAGE);
                                    messageModel.setZkCode(forbiddenMessage.getZkCode());
                                    messageModel.setTargetNickName(forbiddenMessage.getTargetNickName());
                                    messageModel.setIsForbidden(forbiddenMessage.getIsForbidden());
                                    if(!messageModel.getManagerType().equals("2")){
                                        liveChatFragment.addItem(messageModel);
                                    }
                                }else if (content instanceof RoomManageMessage){
                                    /**
                                     * 设置房管消息
                                     */
                                    RoomManageMessage roomManageMessage = (RoomManageMessage) content;
                                    if(initUserInfo(messageModel,roomManageMessage.getUserInfoJson(),senderUserId) ||
                                       Utils.isNotInt(roomManageMessage.getIsRoomManager())){
                                        requestForbidden(senderUserId);
                                        return;
                                    }
                                    messageModel.setObjectName(CommonStr.ROOM_MANAGER_MESSAGE);
                                    messageModel.setZkCode(roomManageMessage.getZkCode());
                                    messageModel.setTargetNickName(roomManageMessage.getTargetNickName());
                                    messageModel.setIsRoomManager(roomManageMessage.getIsRoomManager());
                                    liveChatFragment.addItem(messageModel);
                                }else if(content instanceof SwichMoneyMessage){
                                    /**
                                     * 切换房间类型 消息
                                     */
                                    SwichMoneyMessage swichMoneyMessage = (SwichMoneyMessage) content;
                                    if(initUserInfo(messageModel,swichMoneyMessage.getUserInfoJson(),senderUserId) ||
                                       Utils.isNotInt(swichMoneyMessage.gettype()) ||
                                       Utils.isNotLong(senderUserId)){
                                       requestForbidden(senderUserId);
                                        return;
                                    }

                                    messageModel.setObjectName(CommonStr.CHANGE_ROOM_TYPE_MESSAGE);
                                    messageModel.setZkCode(swichMoneyMessage.getZkCode());
                                    messageModel.setType(swichMoneyMessage.gettype());
                                    liveChatFragment.addItem(messageModel);
                                }else if(content instanceof LiveNormalRedMessage){
                                    /**
                                     * 抢普通红包消息
                                     */
                                    LiveNormalRedMessage liveNormalRedMessage = (LiveNormalRedMessage) content;
                                    if(initUserInfo(messageModel,liveNormalRedMessage.getUserInfoJson(),senderUserId) ||
                                       Utils.isNotDouble(liveNormalRedMessage.getRedPrice()) ||
                                       Utils.isNotLong(liveNormalRedMessage.getRedId())){
                                       requestForbidden(senderUserId);
                                       return;
                                    }

                                    messageModel.setObjectName(CommonStr.NORMAL_RED_MESSAGE);
                                    messageModel.setUserInfoJson(liveNormalRedMessage.getUserInfoJson());
                                    messageModel.setUserNickName(liveNormalRedMessage.getQbUserName());
                                    messageModel.setRedPrice(liveNormalRedMessage.getRedPrice());
                                    messageModel.setZkCode(liveNormalRedMessage.getZkCode());
                                    messageModel.setRedId(Long.parseLong(liveNormalRedMessage.getRedId()));
                                    liveChatFragment.addItem(messageModel);
                                }else if(content instanceof LiveRewardMessage){
                                    LiveRewardMessage liveRewardMessage = (LiveRewardMessage) content;
                                    String rewardMoney = liveRewardMessage.getRewardMoney();
                                    if(initUserInfo(messageModel,liveRewardMessage.getUserInfoJson(),senderUserId) || Utils.isNotDouble(rewardMoney)){
                                        return;
                                    }
                                    BigDecimal singlePrice = new BigDecimal(rewardMoney);
                                    String toString = money_tv.getText().toString();
                                    if(!toString.contains("-")){
                                        BigDecimal oldNum = new BigDecimal(toString);
                                        money_tv.setText((oldNum.add(singlePrice))+"");
                                    }
                                    messageModel.setObjectName(CommonStr.REWARD_ANCHOR);
                                    messageModel.setUserInfoJson(liveRewardMessage.getUserInfoJson());
                                    messageModel.setRewardPrice(rewardMoney);
                                    liveChatFragment.addItem(messageModel);

                                }
                                break;
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                            //消息发送失败
                        case RongLibUtils.MESSAGE_SEND_ERROR:
                            int arg1 = message.arg1;
                            if(arg1==FORBIDDEN_IN_CHATROOM.getValue()){
                                if(!(content instanceof LiveExitAndJoinMessage)){
                                    //被禁言(加入退出聊天室时被禁言不发消息)
                                    showtoast("您存在违规，在该聊天室中已被禁言");
                                }
                            }
                            break;
                        default:
                            break;
                    }
                }
            }
        }
    }
}
