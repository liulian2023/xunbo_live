package com.zz.live.ui.fragment.main_tab_fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.alibaba.fastjson.JSONObject;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.gyf.immersionbar.ImmersionBar;
import com.zz.live.R;
import com.zz.live.base.BaseFragment;
import com.zz.live.bean.MineEntity;
import com.zz.live.bean.UserInfoEntity;
import com.zz.live.net.api.HttpApiUtils;
import com.zz.live.ui.activity.main_tab_activity.LoginActivity;
import com.zz.live.ui.activity.mine_fragment_activity.InviteActivity;
import com.zz.live.ui.activity.mine_fragment_activity.income_live_activity.IncomeLiveActivity;
import com.zz.live.ui.activity.mine_fragment_activity.MessageActivity;
import com.zz.live.ui.activity.mine_fragment_activity.MineDetailsActivity;
import com.zz.live.ui.activity.user_info_activity.UserInfoActivity;
import com.zz.live.ui.adapter.MineAdapter;
import com.zz.live.utils.ClearCache;
import com.zz.live.utils.CommonStr;
import com.zz.live.utils.GlideLoadViewUtil;
import com.zz.live.utils.RequestUtils;
import com.zz.live.utils.SharePreferencesUtil;

import java.util.ArrayList;
import java.util.HashMap;
import butterknife.BindView;
import okhttp3.Headers;

public class MineLiveFragment extends BaseFragment implements View.OnClickListener {
    /*
    头部控件
     */
    TextView mine_action_title_tv;
    TextView unread_num_tv;
    Button exit_login_btn;
    LinearLayout message_linear;
    LinearLayout details_linear;
    LinearLayout income_linear;
    LinearLayout invite_linear;
    TextView username_tv;
    TextView user_id_tv;
    TextView follow_num_tv;
    TextView fans_num_tv;
    ImageView title_iv;
    ConstraintLayout userinfo_constrainLayout;
    @BindView(R.id.mine_recycler)
    RecyclerView mine_recycler;
    MineAdapter mineAdapter;
    ArrayList<MineEntity> mineEntityArrayList  = new ArrayList<>();
    UserInfoEntity.DataBean userInfoEntityData;
    @Override
    protected void init(Bundle savedInstanceState) {
        initRecycler();
    }
    @Override
    public void onStart() {
        super.onStart();
        requestUserInfo();
        requestUnReadMessage();
    }

    private void requestUnReadMessage() {
        HttpApiUtils.normalRequest(getActivity(),this, RequestUtils.UNREAD_MESSAGE, new HashMap<String, Object>(), new HttpApiUtils.OnRequestLintener() {
            @Override
            public void onSuccess(String result) {
                JSONObject jsonObject = JSONObject.parseObject(result).getJSONObject("data");
                Integer systemCount = jsonObject.getInteger("systemCount");
                Integer followCount = jsonObject.getInteger("followCount");
                Integer privateMessageCount = jsonObject.getInteger("privateMessageCount");
                int allCount = systemCount+followCount+privateMessageCount;
                if(allCount==0){
                    unread_num_tv.setVisibility(View.INVISIBLE);
                }else if(allCount<10){
                    unread_num_tv.setVisibility(View.VISIBLE);
                    unread_num_tv.setText(allCount +"");
                }else {
                    unread_num_tv.setVisibility(View.VISIBLE);
                    unread_num_tv.setText("...");
                }
            }

            @Override
            public void onFail(String msg) {
            }
        });
    }

    private void requestUserInfo() {
        HttpApiUtils.request(getActivity(),this, RequestUtils.USER_INFO, new HashMap<String,Object>(), new HttpApiUtils.OnRequestLintener() {
            @Override
            public void onSuccess(String result) {
                //用户信息请求成功才添加recyclerView item
                UserInfoEntity userInfoEntity = JSONObject.parseObject(result, UserInfoEntity.class);
                SharePreferencesUtil.putString(getContext(), CommonStr.USER_INFO,result);
                userInfoEntityData = userInfoEntity.getData();
//                addRecycleData();
                username_tv.setText(userInfoEntityData.getNickname());
                user_id_tv.setText("ID:"+userInfoEntityData.getAccountId());
//                follow_num_tv.setText(userInfoEntityData.getFollow());
                fans_num_tv.setText(userInfoEntityData.getFansNumber()+"");
                GlideLoadViewUtil.fLoadTitleView(MineLiveFragment.this,userInfoEntityData.getImage(),title_iv);
            }

            @Override
            public void onFail(String msg) {
            }
        });
    }

    private void initRecycler() {
        mineAdapter = new MineAdapter(R.layout.mine_recycler_item_layout,mineEntityArrayList);
        mine_recycler.setLayoutManager(new LinearLayoutManager(getContext()));
        mine_recycler.setAdapter(mineAdapter);
        View headView = LayoutInflater.from(getContext()).inflate(R.layout.mine_recycler_head_layout,null);
        bindHeadView(headView);
        mineAdapter.addHeaderView(headView);
        View footView = LayoutInflater.from(getContext()).inflate(R.layout.mine_recycler_foot_layout,null);
        bindFootView(footView);
        mineAdapter.addFooterView(footView);
        mineAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(@NonNull BaseQuickAdapter<?, ?> adapter, @NonNull View view, int position) {
                MineEntity mineEntity = mineEntityArrayList.get(position);
                initRecyckerItemClick(mineEntity);
            }
        });
    }

    private void initRecyckerItemClick(MineEntity mineEntity) {
        /*
      memberStatus 0 审核中  1 已认证  2认证失败 3 未认证
       */
      /*  String typeName = mineEntity.getTypeName();
        if(typeName.equals("主播认证")){
            if(userInfoEntityData==null){
                showtoast("正在努力加载用户信息, 请稍后");
                return;
            }
            int memberStatus = userInfoEntityData.getUserStatus();
            if(memberStatus==0){
                showtoast("主播认证审核中，请等待");
            }else if(memberStatus==1){
                //跳转认证信息activity
                CertifitionSuccessActivity.startAty(getContext(),userInfoEntityData);
            }else if(memberStatus==2){
                CommonChoosePop commonTipPop = new CommonChoosePop(getContext(), getActivity(), "主播认证", "您的审核未通过，是否重新提交认证资料?","重新提交");
                commonTipPop.setOnClickLintener(new CommonChoosePop.OnClickLintener() {
                    @Override
                    public void onSureClick(View view) {
                        startActivity(new Intent(getContext(), CertificationActivity.class));
                    }
                });
            }else if(memberStatus==3){
                startActivity(new Intent(getContext(), CertificationActivity.class));
            }
        }else if(typeName.equals("代理中心")){
            if(userInfoEntityData==null){
                showtoast("正在努力加载用户信息, 请稍后");
                return;
            }
            int agentStatus = userInfoEntityData.getAgentStatus();
            if(agentStatus==0){
                showtoast("代理认证审核中，请等待");
            }else if(agentStatus==1){
                startActivity(new Intent(getContext(), AgentCenterActivity.class));
            }else if(agentStatus==2){
                CommonChoosePop commonTipPop = new CommonChoosePop(getContext(), getActivity(), "代理认证", "您的审核未通过，是否重新提交认证资料?","重新提交");
                commonTipPop.setOnClickLintener(new CommonChoosePop.OnClickLintener() {
                    @Override
                    public void onSureClick(View view) {
                        requestAgent();
                    }
                });
                commonTipPop.showAtLocation(mine_recycler,Gravity.CENTER,0,0);
                Utils.darkenBackground(getActivity(),0.7f);
            }else if(agentStatus==3){
                requestAgent();
            }
        }*/
    }

/*    private void addRecycleData() {
        mineEntityArrayList.clear();
        mineEntityArrayList.add(new MineEntity(R.drawable.zbrz_icon,"主播认证",userInfoEntityData.getMemberStatus()));
        mineEntityArrayList.add(new MineEntity(R.drawable.zxkf,"在线客服",5));
        mineEntityArrayList.add(new MineEntity(R.drawable.dailizhongx_icon,"代理中心",userInfoEntityData.getAgentStatus()));
        mineAdapter.notifyDataSetChanged();
    }*/

    private void bindFootView(View footView) {
        exit_login_btn = footView.findViewById(R.id.exit_login_btn);
        exit_login_btn.setOnClickListener(this);
    }

    private void bindHeadView(View headView) {
        mine_action_title_tv = headView.findViewById(R.id.mine_action_title_tv);
        ImmersionBar.with(this)
                .titleBarMarginTop(mine_action_title_tv)
                .navigationBarColor(R.color.transparent)
                .init();
        message_linear = headView.findViewById(R.id.message_linear);
        unread_num_tv = headView.findViewById(R.id.unread_num_tv);
        details_linear = headView.findViewById(R.id.details_linear);
        income_linear = headView.findViewById(R.id.income_linear);
        invite_linear = headView.findViewById(R.id.invite_linear);
        userinfo_constrainLayout = headView.findViewById(R.id.userinfo_constrainLayout);
        username_tv = headView.findViewById(R.id.username_tv);
        user_id_tv = headView.findViewById(R.id.user_id_tv);
        follow_num_tv = headView.findViewById(R.id.follow_num_tv);
        fans_num_tv = headView.findViewById(R.id.fans_num_tv);
        title_iv = headView.findViewById(R.id.title_iv);
        message_linear.setOnClickListener(this);
        details_linear.setOnClickListener(this);
        income_linear.setOnClickListener(this);
        invite_linear.setOnClickListener(this);
        userinfo_constrainLayout.setOnClickListener(this);
    }


    @Override
    public int getLayoutId() {
        return R.layout.fragment_mine;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.message_linear:
                //我的消息
                startActivity(new Intent(getContext(), MessageActivity.class));
                break;
            //我的明细
            case R.id.details_linear:
                MineDetailsActivity.startAty(getContext(),1);
                break;
            //我的收益
            case R.id.income_linear:
                startActivity(new Intent(getContext(), IncomeLiveActivity.class));
                break;
            case R.id.invite_linear:
                startActivity(new Intent(getContext(), InviteActivity.class));
                break;
            //用户信息
            case R.id.userinfo_constrainLayout:
                startActivity(new Intent(getContext(), UserInfoActivity.class));
                break;
            //退出登录
            case R.id.exit_login_btn:
                startActivity(new Intent(getContext(),LoginActivity.class));
                ClearCache.clearCache(getContext());
                break;
            default:
                break;
        }
    }
}
