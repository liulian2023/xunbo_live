package com.zz.live.ui.activity.mine_fragment_activity.certification_activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.previewlibrary.GPreviewBuilder;
import com.zz.live.R;
import com.zz.live.base.BaseActivity;
import com.zz.live.bean.PictureBean;
import com.zz.live.bean.UserInfoEntity;
import com.zz.live.utils.CommonToolbarUtil;
import com.zz.live.utils.GlideLoadViewUtil;
import com.zz.live.utils.StringMyUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class CertifitionSuccessActivity extends BaseActivity {

    @BindView(R.id.name_tv)
    TextView name_tv;
    @BindView(R.id.id_card_tv)
    TextView id_card_tv;
    @BindView(R.id.phone_tv)
    TextView phone_tv;
    @BindView(R.id.one_photo_iv)
    ImageView one_photo_iv;
    @BindView(R.id.two_photo_iv)
    ImageView two_photo_iv;
    @BindView(R.id.three_photo_iv)
    ImageView three_photo_iv;
    UserInfoEntity.DataBean dataBean;
    ArrayList<PictureBean>pictureBeanArrayList = new ArrayList<>();
    @Override
    public int getLayoutId() {
        return R.layout.activity_certifition_success;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        getItentData();
        setUserData();
    }

    private void setUserData() {
        name_tv.setText(dataBean.getRealName());
        id_card_tv.setText(dataBean.getIdentityCard());
        phone_tv.setText(dataBean.getPhone());
        String verifyPictures = dataBean.getVerifyPictures();
        if(StringMyUtil.isNotEmpty(verifyPictures)){
            String[] split = verifyPictures.split(",");
            List<String> pictureList = Arrays.asList(split);
            int size = pictureList.size();
            if(size!=0){
                if(size==1){
                    pictureBeanArrayList.add(new PictureBean(pictureList.get(0)));
                    GlideLoadViewUtil.LoadCornersView(this,pictureList.get(0),8,one_photo_iv);
                }else if(size==2){
                    pictureBeanArrayList.add(new PictureBean(pictureList.get(0)));
                    pictureBeanArrayList.add(new PictureBean(pictureList.get(1)));
                    GlideLoadViewUtil.LoadCornersView(this,pictureList.get(0),8,one_photo_iv);
                    GlideLoadViewUtil.LoadCornersView(this,pictureList.get(1),8,two_photo_iv);
                }else {
                    pictureBeanArrayList.add(new PictureBean(pictureList.get(0)));
                    pictureBeanArrayList.add(new PictureBean(pictureList.get(1)));
                    pictureBeanArrayList.add(new PictureBean(pictureList.get(2)));
                    GlideLoadViewUtil.LoadCornersView(this,pictureList.get(0),8,one_photo_iv);
                    GlideLoadViewUtil.LoadCornersView(this,pictureList.get(1),8,two_photo_iv);
                    GlideLoadViewUtil.LoadCornersView(this,pictureList.get(2),8,three_photo_iv);
                }
            }
        }

    }
    @OnClick({R.id.one_photo_iv,R.id.two_photo_iv,R.id.three_photo_iv})
    public void  onClick(View v){
        switch (v.getId()){
            case R.id.one_photo_iv:
                startPictureView(0);
                break;
            case R.id.two_photo_iv:
                startPictureView(1);
                break;
            case R.id.three_photo_iv:
                startPictureView(2);
                break;
                default:
                    break;
        }
    }

    private void startPictureView(int position) {
        //打开预览界面
        GPreviewBuilder.from(CertifitionSuccessActivity.this)
                //是否使用自定义预览界面，当然8.0之后因为配置问题，必须要使用
//                        .to(ImageLookActivity.class)
                .setData(pictureBeanArrayList)
                .setCurrentIndex(position)
                .setSingleFling(true)
//                        .setType(GPreviewBuilder.IndicatorType.Number)
                // 小圆点
        .setType(GPreviewBuilder.IndicatorType.Dot)
                .start();//启动
    }

    @Override
    protected void initStatusBar() {
        super.initStatusBar();
        CommonToolbarUtil.initToolbar(this,"主播认证");
        CommonToolbarUtil.initStatusBarColor(this);
    }

    private void getItentData() {
        dataBean = (UserInfoEntity.DataBean) getIntent().getSerializableExtra("userInfoEntity");
    }

    public static void startAty(Context context, UserInfoEntity.DataBean dataBean){
        Intent intent = new Intent(context, CertifitionSuccessActivity.class);
        intent.putExtra("userInfoEntity",dataBean);
        context.startActivity(intent);
    }

    @Override
    public void onNetChange(boolean netWorkState) {

    }
}
