package com.zz.live.ui.activity.mine_fragment_activity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.zz.live.MyApplication;
import com.zz.live.R;
import com.zz.live.base.BaseActivity;
import com.zz.live.utils.CommonToolbarUtil;
import com.zz.live.utils.QRCodeUtil;
import com.zz.live.utils.SavePhoto;
import com.zz.live.utils.ShareUtils;

import java.text.ParseException;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import pub.devrel.easypermissions.EasyPermissions;

public class InviteActivity extends BaseActivity implements EasyPermissions.PermissionCallbacks{
    @BindView(R.id.invite_code_tv)
    TextView invite_code_tv;
    @BindView(R.id.qr_code_iv)
    ImageView qr_code_iv;
    @BindView(R.id.rule_one_tv)
    TextView rule_one_tv;
    @BindView(R.id.rule_two_tv)
    TextView rule_two_tv;
    @BindView(R.id.rule_three_tv)
    TextView rule_three_tv;
    @BindView(R.id.rule_four_tv)
    TextView rule_four_tv;
    @BindView(R.id.rule_five_tv)
    TextView rule_five_tv;
    @BindView(R.id.invite_tip_tv)
    TextView invite_tip_tv;
    @BindView(R.id.save_qr_code_tv)
    TextView save_qr_code_tv;
    @BindView(R.id.copy_tv)
    TextView copy_tv;
    @BindView(R.id.share_tv)
    TextView share_tv;

    private String[] PERMISSIONS={"android.permission.READ_EXTERNAL_STORAGE",
            "android.permission.WRITE_EXTERNAL_STORAGE"};
    private int REQUEST_PERMISSONS_CODE=1;

    @Override
    public int getLayoutId() {
        return R.layout.activity_invite;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        initQrCode("dsdsdsdsdsdsdsjdkshdks jijdksah permissionpermissionpermissionpermissionpermissionpermissionpermissionpermissionpermissionpermissionpermissionpermissionpermissionpermissionpermissionpermissionpermissionpermissionpermissionpermissionpermissionpermissionpermissionpermissiondsahksahfaskisuksdhdfjthflpturkilyjeroyhtrftjnkyhtolt lp.yrp; jolot[pktp[;hjm");
//        qr_code_iv.setBackgroundColor(Color.RED);
   }

    @Override
    protected void initStatusBar() {
        super.initStatusBar();
        CommonToolbarUtil.initStatusBarColor(this);
        CommonToolbarUtil.initToolbar(this,"邀请");
    }

    /**
     * 二维码生成
     * @param inviteCodeShareUrl 邀请链接
     */
    private void initQrCode(String inviteCodeShareUrl) {
//        Bitmap qrImage = QRCodeUtil.createQRImage(inviteCodeShareUrl, 500, 500);
//        Bitmap qrImage = QRCodeUtil.createQRImage("https://www.baidu.com", 200, 200);
        Bitmap mBitmap = QRCodeUtil.createQRCodeBitmap("https://www.baidu.com", 166, 166);
        qr_code_iv.setImageBitmap(mBitmap);
    }
    @OnClick({R.id.save_qr_code_tv,R.id.copy_tv,R.id.share_tv})
    public void onClick(View v){
        switch (v.getId()){
            case R.id.save_qr_code_tv:
                checkReadPermission();
                break;
            case R.id.copy_tv:
                //邀请码
                //使用application进行注册, 避免内存泄漏
                ClipboardManager clipboardManagerUrl= (ClipboardManager) MyApplication.getInstance().getSystemService(CLIPBOARD_SERVICE);//实例化clipboardManager对象
                ClipData mClipDataUrl=  ClipData.newPlainText("inviteCode", invite_code_tv.getText().toString());//复制文本数据到粘贴板  newPlainText
                clipboardManagerUrl.setPrimaryClip(mClipDataUrl);
                showtoast("已复制到粘贴板");
                break;
            case R.id.share_tv:
                // 分享链接
                ShareUtils.start2Share(InviteActivity.this,"sdsdsdsdsds");
                break;
                default:
                    break;
        }
    }

    private void checkReadPermission() {
        //已经有权限,保存二维码
        if(EasyPermissions.hasPermissions(this,PERMISSIONS)){
            saveQrCode();
        }else {
            //没有权限,申请权限
            EasyPermissions.requestPermissions(this,"需要获取您的读写权限",REQUEST_PERMISSONS_CODE,PERMISSIONS);
        }
    }
    /*
   保存二维码
    */
    private void saveQrCode() {
        try {
            SavePhoto savePhoto = new SavePhoto(this);
            savePhoto.SaveBitmapFromView(findViewById(R.id.qr_code_iv));
            showtoast("二维码保存成功");
            setResult(RESULT_OK);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {
        if(requestCode==REQUEST_PERMISSONS_CODE){
            saveQrCode();
        }
    }
    @Override
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {
        showtoast("为了您的使用体验,请同意相关权限,否则功能无法实现");
        if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
            //在权限弹窗中，用户勾选了'不在提示'且拒绝权限的情况触发，可以进行相关dialog提示。
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

    }

    @Override
    public void onNetChange(boolean netWorkState) {

    }
}
