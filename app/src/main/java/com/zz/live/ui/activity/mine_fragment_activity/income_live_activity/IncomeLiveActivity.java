package com.zz.live.ui.activity.mine_fragment_activity.income_live_activity;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Group;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import com.alibaba.fastjson.JSONObject;
import com.gyf.immersionbar.ImmersionBar;
import com.zz.live.R;
import com.zz.live.base.BaseActivity;
import com.zz.live.bean.BankcardEntity;
import com.zz.live.bean.UserInfoEntity;
import com.zz.live.bean.UserMoneyEntity;
import com.zz.live.net.api.HttpApiUtils;
import com.zz.live.utils.RequestUtils;
import com.zz.live.utils.StringMyUtil;
import com.zz.live.utils.Utils;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import butterknife.BindView;
import butterknife.OnClick;
import butterknife.OnTextChanged;
import okhttp3.Headers;
/**
 * 提现界面
 */
public class IncomeLiveActivity extends BaseActivity {
    @BindView(R.id.income_toolbar)
    Toolbar income_toolbar;
    @BindView(R.id.income_toolbar_back_iv)
    ImageView income_toolbar_back_iv;
    @BindView(R.id.income_toolbar_title_tv)
    TextView income_toolbar_title_tv;
    @BindView(R.id.withdraw_note_tv)
    TextView income_note_tv;
    @BindView(R.id.balance_tv)
    TextView balance_tv;
    @BindView(R.id.income_etv)
    EditText income_etv;
    @BindView(R.id.bank_card_tv)
    TextView bank_card_tv;
    @BindView(R.id.income_btn)
    Button income_btn;
    @BindView(R.id.add_bankcard_constrainLayout)
    ConstraintLayout add_bankcard_constrainLayout;
    @BindView(R.id.tip_group)
    Group tip_group;
    @BindView(R.id.tip_tv)
    TextView tip_tv;
    @BindView(R.id.tip_iv)
    ImageView tip_iv;
    public static String SKIP_BIND_BANKCORD="暂未绑定银行卡,点击绑定";
    boolean haveCard = false;
    public static int SKIP_ADD_CARD=1;
    public static int SKIP_MINE_BANKCAED =2;
    public static int SKIP_WITH_DRAW =3;
    //当前选择的银行卡(进入页面时默认银行卡列表的第一个,跳转选择银行卡后更换为用户选中的)
    BankcardEntity.DataBean currentDataBean;
    String userBanlance;
    @Override
    public int getLayoutId() {
        return R.layout.activity_income;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        requestBankList();
        requestMoney();
    }

    private void requestMoney() {
        HttpApiUtils.wwwRequest(this, null,RequestUtils.USER_MONEY, new HashMap<String, Object>(), new HttpApiUtils.OnRequestLintener() {
            @Override
            public void onSuccess(String result) {
                UserMoneyEntity userMoneyEntity = JSONObject.parseObject(result, UserMoneyEntity.class);
                String balance = userMoneyEntity.getData().getBalance();
                balance_tv.setText("¥ "+balance);
                userBanlance=balance;
            }

            @Override
            public void onFail(String msg) {
            }
        });
    }

    private void requestBankList() {
        HttpApiUtils.wwwRequest(this,null, RequestUtils.USER_BANK_CARD_LIST, new HashMap<String, Object>(), new HttpApiUtils.OnRequestLintener() {
            @Override
            public void onSuccess(String result) {
                BankcardEntity bankcardEntity = JSONObject.parseObject(result, BankcardEntity.class);
                List<BankcardEntity.DataBean> data = bankcardEntity.getData();
                if(data.size()==0){
                    bank_card_tv.setText(SKIP_BIND_BANKCORD);
                }else {
                    //显示data中第一个银行卡信息
                    BankcardEntity.DataBean dataBean = data.get(0);
                    currentDataBean =dataBean;
                    String bankName = dataBean.getBankName();
                    String cardNumber = dataBean.getCardNumber();
                    String substring = cardNumber.substring(cardNumber.length() - 4);
                    bank_card_tv.setText(bankName+" (尾号:"+substring+")");
                }
            }
            @Override
            public void onFail(String msg) {
            }
        });
    }
    @OnTextChanged(value = R.id.income_etv,callback = OnTextChanged.Callback.AFTER_TEXT_CHANGED)
    public void onTextChange(Editable editable){
        String incomeAmount = editable.toString();
        if(incomeAmount.length()!=0&&currentDataBean!=null){
            income_btn.setBackgroundResource(R.drawable.income_click_selector);
            income_btn.setClickable(true);
            tip_group.setVisibility(View.VISIBLE);
            UserInfoEntity.DataBean userInfoBean = Utils.getUserInfoBean();
            String memberRake = userInfoBean.getMemberRake();
            BigDecimal cashBackAmount = new BigDecimal(incomeAmount).multiply(new BigDecimal(StringMyUtil.isEmptyString(memberRake) ? "0.00" : memberRake)).divide(new BigDecimal("100"));
            tip_tv.setText("根据签约比例"+memberRake+"%,即将到账"+ cashBackAmount +"元");
        }else {
            income_btn.setBackgroundResource(R.drawable.income_unclick_btn_shape);
            income_btn.setClickable(false);
            tip_group.setVisibility(View.GONE);
        }
    }
    @OnClick({R.id.add_bankcard_constrainLayout,R.id.income_btn,R.id.income_toolbar_back_iv,R.id.withdraw_note_tv})
    public void  onCLick(View v){
        switch (v.getId()) {
            //跳转添加银行卡返回后,重新请求银行卡列表,并显示第一个银行卡信息
            case R.id.add_bankcard_constrainLayout:
                String tipStr = bank_card_tv.getText().toString();
                if(tipStr.equals(SKIP_BIND_BANKCORD)){
                    Intent intent = new Intent(IncomeLiveActivity.this, AddBankcardActivity.class);
                    startActivityForResult(intent,SKIP_ADD_CARD);
                }else {
                    Intent intent = new Intent(IncomeLiveActivity.this, MineBankcardActivity.class);
                    //跳转选择银行卡列表,currentDataBean用于列表的默认选中
                    intent.putExtra("currentDataBean",currentDataBean);
                    startActivityForResult(intent, SKIP_MINE_BANKCAED);
                }
                break;
                //提现按钮
            case R.id.income_btn:
                if(checkEtv()){
                    requestWithdraw();
                }
                break;
                //退出
            case R.id.income_toolbar_back_iv:
                finish();
                break;
                //跳转提现记录
            case R.id.withdraw_note_tv:
                startActivity(new Intent(IncomeLiveActivity.this,WithDrawNoteActivity.class));
                break;
            default:
                break;
        }

    }

    /**
     * 申请提现
     */
    private void requestWithdraw() {
        HashMap<String, Object> data = new HashMap<>();
        String putAmount = income_etv.getText().toString();
        data.put("amount", putAmount);
        data.put("bankCardId",currentDataBean.getId());
        HttpApiUtils.request(IncomeLiveActivity.this, null,RequestUtils.WITHDRAW_APPLICATION, data, new HttpApiUtils.OnRequestLintener() {
            @Override
            public void onSuccess(String result) {
                JSONObject jsonObject = JSONObject.parseObject(result);
                showtoast(jsonObject.getString("data"));
                income_etv.setText("");
                balance_tv.setText("¥ "+new BigDecimal(userBanlance).subtract(new BigDecimal(putAmount))+"");
                startActivityForResult(new Intent(IncomeLiveActivity.this,WithDrawNoteActivity.class),SKIP_WITH_DRAW);
            }

            @Override
            public void onFail(String msg) {

            }
        });
    }

    private boolean checkEtv() {
        String etvStr = income_etv.getText().toString();
        /*
        用户余额为空  当前选中的银行卡been为null 输入提现金额为空时,return
         */
        if(StringMyUtil.isEmptyString(userBanlance)||currentDataBean==null||StringMyUtil.isEmptyString(etvStr)){
            return false;
        }
        /*
        输入的提现金额大于用户余额时,return
         */
        BigDecimal incomeAmount = new BigDecimal(etvStr);
        BigDecimal bigDecimal = new BigDecimal(StringMyUtil.isEmptyString(userBanlance)?"0":userBanlance);
        if(incomeAmount.compareTo(bigDecimal)==1){
            //输入金额大于用户余额
            showtoast("最高可提现金额为:"+userBanlance+"元");
            return false;
        }
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==SKIP_ADD_CARD){
            /*
            跳转添加银行卡返回
             */
            if(resultCode==RESULT_OK){
                requestBankList();
            }
        }else if(requestCode==SKIP_MINE_BANKCAED){
            /*
            跳转选择银行卡返回
             */
            if(resultCode==RESULT_OK){
             BankcardEntity.DataBean dataBean = (BankcardEntity.DataBean) data.getSerializableExtra("dataBean");
             if(dataBean==null){
                 return;
             }
                String bankName = dataBean.getBankName();
                String cardNumber = dataBean.getCardNumber();
                String substring = cardNumber.substring(cardNumber.length() - 4);
                bank_card_tv.setText(bankName+" (尾号:"+substring+")");
                //跳转到选择银行后,将currentDataBean重新赋值
                currentDataBean =dataBean;
            }
        }
    }

    @Override
    protected void initStatusBar() {
        super.initStatusBar();
        ImmersionBar.with(this)
                .titleBar(income_toolbar)
                .statusBarDarkFont(true)   //状态栏字体是深色，不写默认为亮色
                .navigationBarColor(R.color.transparent)
                .init();

    }

    @Override
    public void onNetChange(boolean netWorkState) {

    }
}
