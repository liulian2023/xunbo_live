package com.zz.live.ui.activity.main_tab_activity.house_keeper_activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.alibaba.fastjson.JSONObject;
import com.cambodia.zhanbang.rxhttp.net.model.SingleLoginEvent;
import com.cretin.www.cretinautoupdatelibrary.interfaces.AppDownloadListener;
import com.cretin.www.cretinautoupdatelibrary.interfaces.AppUpdateInfoListener;
import com.cretin.www.cretinautoupdatelibrary.model.DownloadInfo;
import com.cretin.www.cretinautoupdatelibrary.model.TypeConfig;
import com.cretin.www.cretinautoupdatelibrary.utils.AppUpdateUtils;
import com.google.android.material.tabs.TabLayout;
import com.gyf.immersionbar.ImmersionBar;
import com.zz.live.MainActivity;
import com.zz.live.MyApplication;
import com.zz.live.R;
import com.zz.live.base.BaseActivity;
import com.zz.live.base.BasePopupWindow;
import com.zz.live.bean.AppUpdateEntity;
import com.zz.live.bean.AppUpdateModel;
import com.zz.live.bean.HomeNoticeEntity;
import com.zz.live.bean.UserInfoEntity;
import com.zz.live.net.api.HttpApiUtils;
import com.zz.live.ui.activity.main_tab_activity.LoginActivity;
import com.zz.live.ui.activity.main_tab_activity.OnLineCustomerActivity;
import com.zz.live.ui.activity.main_tab_activity.house_keeper_activity.anchor_manage_activity.AnchorManageActivity;
import com.zz.live.ui.activity.mine_fragment_activity.income_activity.InComeActivity;
import com.zz.live.ui.activity.user_info_activity.UserInfoActivity;
import com.zz.live.ui.adapter.HomeNoticeAdapter;
import com.zz.live.ui.pop.HomeAtyPop;
import com.zz.live.utils.ActivityUtil;
import com.zz.live.utils.ClearCache;
import com.zz.live.utils.CommonStr;
import com.zz.live.utils.ProgressDialogUtil;
import com.zz.live.utils.RequestUtils;
import com.zz.live.utils.SharePreferencesUtil;
import com.zz.live.utils.Utils;
import com.zz.live.utils.VersionUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Headers;

/**
 * 家族长首页
 */
public class AnchorHouseKeeperActivity extends BaseActivity {
    @BindView(R.id.housKeeper_title_relativeLayout)
    RelativeLayout housKeeper_title_relativeLayout;
    @BindView(R.id.house_keeper_tab)
    TabLayout house_keeper_tab;
    @BindView(R.id.house_keeper_viewPager)
    ViewPager house_keeper_viewPager;
    @BindView(R.id.set_iv)
    ImageView set_iv;
    @BindView(R.id.kefu_iv)
    ImageView kefu_iv;
    @BindView(R.id.username_tv)
    TextView username_tv;
    @BindView(R.id.family_name_tv)
    TextView family_name_tv;
    @BindView(R.id.anchor_manage_linear)
    LinearLayout anchor_manage_linear;
    @BindView(R.id.anchor_report_linear)
    LinearLayout anchor_report_linear;
    @BindView(R.id.change_cashBack_linear)
    LinearLayout change_cashBack_linear;
    @BindView(R.id.finance_reconciliation_linear)
    LinearLayout finance_reconciliation_linear;
    ArrayList<String>titleList = new ArrayList<>();
    HouseKeeperTabAdapter houseKeeperTabAdapter;
    public long firstTime = 0;
    private int SKIP_SET_REQUEST_CODE=1234;

    private Integer versionCode;
    private String versionName;
    private String updateTip;
    private String TAG="AnchorHouseKeeperActivity";
    private ArrayList<HomeNoticeEntity.DataBean.RecordsBean> recordsBeanArrayList = new ArrayList<>();
    HomeAtyPop homeAtyPop;



    @Override
    public int getLayoutId() {
        return R.layout.activity_anchor_house_kerper;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        if(!EventBus.getDefault().isRegistered(this)){
            EventBus.getDefault().register(this);
        }

        initTab();
        initUserInfo();
        requestAppUpdate();
        requestNoticeData();

    }

    private void initUserInfo() {
        UserInfoEntity.DataBean userInfoBean = Utils.getUserInfoBean();
        username_tv.setText(userInfoBean.getUsername());
        family_name_tv.setText(userInfoBean.getNickname());
    }

    private void initNoticePop() {

        if(homeAtyPop==null){
            homeAtyPop = new HomeAtyPop(this,this,recordsBeanArrayList);
        }
    }
    private void requestNoticeData() {
        HashMap<String, Object> data = new HashMap<>();
        data.put("current",1);
        data.put("size",20);
        HttpApiUtils.normalRequest(this, null, RequestUtils.HOME_NOTICE, data, new HttpApiUtils.OnRequestLintener() {
            @Override
            public void onSuccess(String result) {
                HomeNoticeEntity homeNoticeEntity = JSONObject.parseObject(result, HomeNoticeEntity.class);
                List<HomeNoticeEntity.DataBean.RecordsBean> records = homeNoticeEntity.getData().getRecords();
                recordsBeanArrayList.addAll(records);
                initNoticePop();
                if(recordsBeanArrayList.size()!=0){
                    showAtyPop();
                }
                homeAtyPop.getHomeAtyRecyclerAdapter().notifyDataSetChanged();
            }

            @Override
            public void onFail(String msg) {
            }
        });
    }
    /*
弹出活动公告pop(延时弹出)
 */
    private void showAtyPop() {
        change_cashBack_linear.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(!AnchorHouseKeeperActivity.this.isFinishing()) {
                    homeAtyPop.showAtLocation(change_cashBack_linear, Gravity.CENTER, 0, 0); // 显示弹出窗口
                }
                ProgressDialogUtil.darkenBackground(AnchorHouseKeeperActivity.this,0.7f);
            }
        },300);
    }
    private void initTab() {
        titleList.add("今日");
        titleList.add("昨日");
        titleList.add("近七日");
        titleList.add("本月");
        titleList.add("上月");
        houseKeeperTabAdapter= new HouseKeeperTabAdapter(getSupportFragmentManager());
        house_keeper_viewPager.setAdapter(houseKeeperTabAdapter);
        house_keeper_tab.setupWithViewPager(house_keeper_viewPager);
    }
    /**
     * 版本更新
     */
    private void requestAppUpdate() {
        int appVersionCode = (int) VersionUtils.getAppVersionCode(this);
        HashMap<String, Object> data = new HashMap<>();
        HttpApiUtils.normalRequest(this, null, RequestUtils.APP_UPDATE, data, new HttpApiUtils.OnRequestLintener() {
            @Override
            public void onSuccess(String result) {
                AppUpdateEntity appUpdateEntity = JSONObject.parseObject(result, AppUpdateEntity.class);
                List<AppUpdateEntity.DataBean> records = appUpdateEntity.getData();
                AppUpdateEntity.DataBean recordsBean = null;
                for (int i = 0; i < records.size(); i++) {
                    AppUpdateEntity.DataBean bean = records.get(i);
                    if(bean.getType()==1){
                        recordsBean=bean;
                        break;
                    }
                }
                if(recordsBean!=null){
                    versionCode = recordsBean.getVersionCode();
                    versionName = recordsBean.getVersionName();
                    boolean mustUpdate = recordsBean.isMustUpdate();
                    String downloadUrl = recordsBean.getDownloadUrl();
                    updateTip = recordsBean.getTips();
                    if(versionCode==appVersionCode){
                        Log.e(TAG, "onSuccess: 当前是最新版本" );
                    }else {
                        AppUpdateModel appUploadModel = new AppUpdateModel();
                        appUploadModel.setAutoUpdateBackground(false);
                        appUploadModel.setCheckFileMD5(false);
                        appUploadModel.setSourceTypeVaule(TypeConfig.DATA_SOURCE_TYPE_MODEL);
                        appUploadModel.setUiTypeValue(TypeConfig.UI_THEME_B);
                        if (mustUpdate) {//强制更新
                            appUploadModel.setForceUpdate(true);
                        } else {//非强制更新
                            appUploadModel.setForceUpdate(false);
                        }
                        try {
                            startUpdateApp(appUploadModel, downloadUrl);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }

            }

            @Override
            public void onFail(String msg) {

            }

        });
    }
    /**
     * 弹出更新pop
     * @param appUploadModel
     * @param downloadUrl
     * @throws IOException
     */
    private void startUpdateApp(AppUpdateModel appUploadModel,String downloadUrl) throws IOException {
        DownloadInfo downloadInfo = new DownloadInfo().setApkUrl(downloadUrl)
                //EC42E7E1FE580F1E187C8E10355A13BB0
//                .setFileSize(getContentLength(downloadUrl))
                .setProdVersionCode(versionCode)
                .setProdVersionName(versionName)
                .setForceUpdateFlag(appUploadModel.isForceUpdate() ? 1 : 0)
                .setUpdateLog(updateTip);
        AppUpdateUtils appUpdateUtils = AppUpdateUtils.getInstance();
        appUpdateUtils.getUpdateConfig().setUiThemeType(appUploadModel.getUiTypeValue());
        //打开文件MD5校验
        appUpdateUtils.getUpdateConfig().setNeedFileMD5Check(appUploadModel.isCheckFileMD5());
        appUpdateUtils.getUpdateConfig().setDataSourceType(appUploadModel.getSourceTypeVaule());
        //开启或者关闭后台静默下载功能
        appUpdateUtils.getUpdateConfig().setAutoDownloadBackground(appUploadModel.isAutoUpdateBackground());
        if (appUploadModel.isAutoUpdateBackground()) {
            //开启静默下载的时候推荐关闭通知栏进度提示
            appUpdateUtils.getUpdateConfig().setShowNotification(false);
        } else {
            appUpdateUtils.getUpdateConfig().setShowNotification(true);
        }
        appUpdateUtils
                .addAppUpdateInfoListener(new AppUpdateInfoListener() {
                    @Override
                    public void isLatestVersion(boolean isLatest) {
                        Log.e("HHHHHHHHHHHHHHH", "isLatest:" + isLatest);
                    }
                })
                .addAppDownloadListener(new AppDownloadListener() {
                    @Override
                    public void downloading(int progress) {
                        Log.e("HHHHHHHHHHHHHHH", "progress:" + progress);
                    }

                    @Override
                    public void downloadFail(String msg) {
                        Log.e("HHHHHHHHHHHHHHH", "msg:" + msg);
                    }

                    @Override
                    public void downloadComplete(String path) {
                        Log.e("HHHHHHHHHHHHHHH", "path:" + path);

                    }

                    @Override
                    public void downloadStart() {
                        Log.e("HHHHHHHHHHHHHHH", "start");
//                        sendMessage2Fragment();
                    }

                    @Override
                    public void reDownload() {
                        Log.e("HHHHHHHHHHHHHHH", "reDownload");
                    }

                    @Override
                    public void pause() {
                        Log.e("HHHHHHHHHHHHHHH", "pause");
                    }
                })
                .checkUpdate(downloadInfo);
    }

    @OnClick({R.id.set_iv,R.id.kefu_iv,R.id.anchor_manage_linear,R.id.anchor_report_linear,R.id.change_cashBack_linear,R.id.finance_reconciliation_linear})
    public void onClick(View v){
        switch (v.getId()){
            case R.id.set_iv:
                Intent intent = new Intent(AnchorHouseKeeperActivity.this, UserInfoActivity.class);
                startActivityForResult(intent,SKIP_SET_REQUEST_CODE);
                break;
            case R.id.kefu_iv:
                OnLineCustomerActivity.startAty(AnchorHouseKeeperActivity.this, SharePreferencesUtil.getString(AnchorHouseKeeperActivity.this,CommonStr.CUSTOMER,""));
                break;
            case R.id.anchor_manage_linear:
                startActivity(new Intent(AnchorHouseKeeperActivity.this, AnchorManageActivity.class));
                break;
            case R.id.anchor_report_linear:
//                showtoast("敬请期待");
                startActivity(new Intent(AnchorHouseKeeperActivity.this,AnchorReportActivity.class));
                break;
            case R.id.change_cashBack_linear:
                //转化提现
                startActivity(new Intent(AnchorHouseKeeperActivity.this,ChangeCashBackActivity.class));
                break;
                //财务对账
            case R.id.finance_reconciliation_linear:
                InComeActivity.startAty(AnchorHouseKeeperActivity.this,"财务对账");
                break;
                default:
                    break;
        }
    }
    @Override
    protected void initStatusBar() {
        super.initStatusBar();
        ImmersionBar.with(this)
                .titleBarMarginTop(housKeeper_title_relativeLayout)
                .navigationBarColor(R.color.transparent)
                .init();
    }
    @Override
    public void onNetChange(boolean netWorkState) {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==SKIP_SET_REQUEST_CODE&&resultCode==RESULT_OK){
            requestUserInfo();
        }
    }
    /**
     * 设置页面返回时,重新请求用户信息
     */
    private void requestUserInfo() {
        HttpApiUtils.request(this,null, RequestUtils.USER_INFO, new HashMap<String,Object>(), new HttpApiUtils.OnRequestLintener() {
            @Override
            public void onSuccess(String result) {

                SharePreferencesUtil.putString(AnchorHouseKeeperActivity.this, CommonStr.USER_INFO,result);
                initUserInfo();
            }

            @Override
            public void onFail(String msg) {
            }
        });
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
    //单点登录
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void singleLogin(SingleLoginEvent singleLoginEvent) {
        Intent intent = new Intent();
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.setClass(AnchorHouseKeeperActivity.this, LoginActivity.class);
        intent.putExtra(CommonStr.SINGLE_LOGIN,singleLoginEvent.isSingleLogin());
        intent.putExtra("flag",singleLoginEvent.getFlag());
        ClearCache.clearCache(MyApplication.getInstance());
        startActivity(intent);
    }
    class HouseKeeperTabAdapter extends FragmentStatePagerAdapter {
        public HouseKeeperTabAdapter(@NonNull FragmentManager fm) {
            super(fm);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return AnchorHouseKeeperFragment.newInstance(position);
        }

        @Override
        public int getCount() {
            return titleList.size();
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return titleList.get(position);
        }
    }
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // 监听返回键，点击两次退出程序
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            long secondTime = System.currentTimeMillis();
            if (secondTime - firstTime > 1000) {
                showtoast("再按一次退出程序");
                firstTime = secondTime;
            } else {
                ActivityUtil.getInstance().exitSystem();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
