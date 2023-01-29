package com.zz.live.ui.activity.mine_fragment_activity.certification_activity;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.FileProvider;
import androidx.lifecycle.LifecycleOwner;

import com.alibaba.fastjson.JSONObject;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.cambodia.zhanbang.rxhttp.net.common.BaseStringObserver;
import com.cambodia.zhanbang.rxhttp.net.utils.RxTransformerUtils;
import com.uber.autodispose.AutoDispose;
import com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider;
import com.zz.live.R;
import com.zz.live.base.BaseActivity;
import com.zz.live.base.BasePopupWindow;
import com.zz.live.net.api.HttpApiImpl;
import com.zz.live.net.api.HttpApiUtils;
import com.zz.live.ui.pop.TakeCameraPop;
import com.zz.live.utils.BitmapUtils;
import com.zz.live.utils.CommonToolbarUtil;
import com.zz.live.utils.GetPhotoFromPhotoAlbum;
import com.zz.live.utils.GlideLoadViewUtil;
import com.zz.live.utils.ImageThumbnail;
import com.zz.live.utils.LimitTextChange;
import com.zz.live.utils.RequestUtils;
import com.zz.live.utils.StringMyUtil;
import com.zz.live.utils.Utils;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Headers;
import okhttp3.ResponseBody;
import pub.devrel.easypermissions.EasyPermissions;

public class CertificationActivity extends BaseActivity implements EasyPermissions.PermissionCallbacks{
    @BindView(R.id.name_etv)
    EditText name_etv;
    @BindView(R.id.id_card_etv)
    EditText id_card_etv;
    @BindView(R.id.phone_num_etv)
    EditText phone_num_etv;
    @BindView(R.id.photo_one_iv)
    ImageView photo_one_iv;
    @BindView(R.id.photo_two_iv)
    ImageView photo_two_iv;
    @BindView(R.id.photo_three_iv)
    ImageView photo_three_iv;
    @BindView(R.id.upload_one_photo_tv)
    TextView upload_one_photo_tv;
    @BindView(R.id.upload_two_photo_tv)
    TextView upload_two_photo_tv;
    @BindView(R.id.upload_three_photo_tv)
    TextView upload_three_photo_tv;
    @BindView(R.id.certification_btn)
    Button certification_btn;
    private File cameraSavePath;
    //拍照返回
    private Uri uri;
    private int ONE_CAMARE_REQUEST_CODE = 1;//调用相机的请求码(第一张照片)
    private int TWO_CAMARE_REQUEST_CODE = 2;//调用相机的请求码(第二张照片)
    private int THREE_CAMARE_REQUEST_CODE = 3;//调用相机的请求码(第三张照片)
    private int ONE_PHOTO_REQUEST_CODE = 4;//调用相机的请求码
    private int TWO_PHOTO_REQUEST_CODE = 5;//调用相机的请求码
    private int THREE_PHOTO_REQUEST_CODE = 6;//调用相机的请求码

    private String[] PERMISSIONS={"android.permission.CAMERA",
            "android.permission.WRITE_EXTERNAL_STORAGE"};

    String photoOnePath;//第一张图片的网络地址(用于点击提交按钮时判断是否有上传三张图片)
    String photoTwoPath;//第二张图片的网络地址
    String photoThreePath;//第三张图片的网络地址
    @Override
    public int getLayoutId() {
        return R.layout.activity_certification;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        LimitTextChange.StringWatcher(id_card_etv);
        cameraSavePath = new File(Environment.getExternalStorageDirectory().getPath() + "/" + System.currentTimeMillis() + ".jpg");

    }
    @OnClick({R.id.upload_one_photo_tv,R.id.upload_two_photo_tv,R.id.upload_three_photo_tv,R.id.certification_btn})
    public void onClick(View v){
        switch (v.getId()){
            case R.id.certification_btn:
                if(checkEtv()){
                    String verifyPictures = photoOnePath+","+photoTwoPath+","+photoThreePath;
                    HashMap<String, Object> data = new HashMap<>();
                    data.put("identityCard",id_card_etv.getText().toString());
                    data.put("phone",phone_num_etv.getText().toString());
                    data.put("realName",name_etv.getText().toString());
                    data.put("verifyPictures",verifyPictures);
                    HttpApiUtils.request(CertificationActivity.this, null,RequestUtils.CERTIFICATION, data, new HttpApiUtils.OnRequestLintener() {
                        @Override
                        public void onSuccess(String result) {
                            JSONObject jsonObject = JSONObject.parseObject(result);
                            String msg = jsonObject.getString("msg");
                            showtoast(msg);
                        }

                        @Override
                        public void onFail(String msg) {
                            System.out.println(msg);
                        }
                    });
                }
                break;
            case R.id.upload_one_photo_tv:
                initTakeCameraPop(ONE_CAMARE_REQUEST_CODE, ONE_PHOTO_REQUEST_CODE);
                break;
            case R.id.upload_two_photo_tv:
                initTakeCameraPop(TWO_CAMARE_REQUEST_CODE, TWO_PHOTO_REQUEST_CODE);
                break;
            case R.id.upload_three_photo_tv:
                initTakeCameraPop(THREE_CAMARE_REQUEST_CODE, THREE_PHOTO_REQUEST_CODE);
                break;
            default:
                break;
        }
    }

    private void initTakeCameraPop(int requestCameraCode, int requestPhotoCameraCode) {
        TakeCameraPop twoTakeCameraPop = new TakeCameraPop(CertificationActivity.this);
        twoTakeCameraPop.setOnPopClickListener(new BasePopupWindow.OnPopClickListener() {
            @Override
            public void onPopClick(View view) {
                switch (view.getId()) {
                    case R.id.forbidden_tv:
                        twoTakeCameraPop.dismiss();
                        checkCameraPermission(requestCameraCode);
                        break;
                    case R.id.set_manager_tv:
                        twoTakeCameraPop.dismiss();
                        checkPhotoPermission(requestPhotoCameraCode);
                        break;

                }
            }
        });
        Utils.darkenBackground(CertificationActivity.this, 0.7f);
        twoTakeCameraPop.showAtLocation(upload_one_photo_tv, Gravity.BOTTOM, 0, 0);
    }

    private boolean checkEtv() {
        String name = name_etv.getText().toString();
        String idCarad = id_card_etv.getText().toString();
        String phone = phone_num_etv.getText().toString();
        if(StringMyUtil.isEmptyString(name)){
            showtoast("请输入您的姓名");
            return false;
        }
        if(StringMyUtil.isEmptyString(idCarad)){
            showtoast("请输入您的身份证号");
            return false;
        }
        if(idCarad.length()<15||idCarad.length()>18){
            showtoast("身份证格式不符合规范");
            return false;
        }
        if(StringMyUtil.isEmptyString(phone)){
            showtoast("请输入您的手机号码");
            return false;
        }
        if(phone.length()!=11){
            showtoast("手机号码格式不符合规范");
            return false;
        }
        if(StringMyUtil.isEmptyString(photoOnePath)){
            showtoast("第一张生活照未上传或上传失败");
            return false;
        }
        if(StringMyUtil.isEmptyString(photoTwoPath)){
            showtoast("第二张生活照未上传或上传失败");
            return false;
        }
        if(StringMyUtil.isEmptyString(photoThreePath)){
            showtoast("第三张生活照未上传或上传失败");
            return false;
        }
        return true;
    }

    @Override
    protected void initStatusBar() {
        super.initStatusBar();
        CommonToolbarUtil.initStatusBarColor(this);
        CommonToolbarUtil.initToolbar(this,"主播认证");
    }

    @Override
    public void onNetChange(boolean netWorkState) {

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode==ONE_CAMARE_REQUEST_CODE){
            if(resultCode==RESULT_OK){
                compressCameraPhoto(photo_one_iv);
            }
        }else if(requestCode==TWO_CAMARE_REQUEST_CODE){
            if(resultCode==RESULT_OK){
            compressCameraPhoto(photo_two_iv);
            }
        } else if(requestCode==THREE_CAMARE_REQUEST_CODE){
            if(resultCode==RESULT_OK) {
                compressCameraPhoto(photo_three_iv);
            }
        } else if (requestCode == ONE_PHOTO_REQUEST_CODE ) {
            if(resultCode==RESULT_OK){
                compressAlbumPhoto(data, photo_one_iv);
            }

        }else if (requestCode == TWO_PHOTO_REQUEST_CODE ) {
            if(resultCode==RESULT_OK){
                compressAlbumPhoto(data, photo_two_iv);
            }

        }else if (requestCode == THREE_PHOTO_REQUEST_CODE ) {
            if(resultCode==RESULT_OK){
                compressAlbumPhoto(data, photo_three_iv);
            }

        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void compressAlbumPhoto(@Nullable Intent data, ImageView imageView) {
        String realPathFromUri = GetPhotoFromPhotoAlbum.getRealPathFromUri(this, data.getData());
        //加载本地图片为头像
        showBitMap(realPathFromUri, imageView);
        String s = BitmapUtils.compressReSave(realPathFromUri, CertificationActivity.this, 400);//图片压缩
        if (!StringMyUtil.isEmptyString(realPathFromUri)) {
            upLoadImg(s, imageView);
        } else {
            showtoast("系统出现错误,请重试");
        }
    }

    private void compressCameraPhoto(ImageView imageView) {
        String photoPath;
        String s;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            photoPath = String.valueOf(cameraSavePath);
            //加载本地图片为头像
            showBitMap(String.valueOf(cameraSavePath), imageView);
            s = BitmapUtils.compressReSave(photoPath, this, 400);//图片压缩
        } else {
            photoPath = uri.getEncodedPath();
            s = BitmapUtils.compressReSave(photoPath, this, 400);//图片压缩
        }
        upLoadImg(s,imageView);
    }

    /**
     * 从相册或者相机返回后,现将imageView设置为本地图片,然后进行上传处理
     * @param path
     */
    private void showBitMap(String path,ImageView imageView) {
        //将拍照的图片取出并缩小后显示在界面上
        Bitmap camorabitmap = BitmapFactory.decodeFile(path);
        if (null != camorabitmap) {
            int scale = ImageThumbnail.reckonThumbnail(camorabitmap.getWidth(), camorabitmap.getHeight(), imageView.getWidth(), imageView.getHeight());
            Bitmap bitMap = ImageThumbnail.PicZoom(camorabitmap, camorabitmap.getWidth() / scale, camorabitmap.getHeight() / scale);
            //由于Bitmap内存占用较大，这里需要回收内存，否则会报out of memory异常
            camorabitmap.recycle();
            Glide.with(this)
                    .load(bitMap)
                    .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                    .into(imageView);
        }
    }
    private void upLoadImg(String fliePath,ImageView imageView) {
        HttpApiImpl.getInstance()
                .uploadFile(fliePath)
                .compose(RxTransformerUtils.io_main())
                .as(AutoDispose.autoDisposable(AndroidLifecycleScopeProvider.from((LifecycleOwner) this)))
                .subscribe(new BaseStringObserver<ResponseBody>(){
                    @Override
                    public void onSuccess(String result) {
                        JSONObject jsonObject = JSONObject.parseObject(result);
                        JSONObject data = jsonObject.getJSONObject("data");
                        //头像后缀
                        String url = data.getString("url");
                        if(imageView==photo_one_iv){
                            photoOnePath =url;
                        }else if(imageView==photo_two_iv){
                            photoTwoPath =url;
                        }else {
                            photoThreePath =url;
                        }
                        GlideLoadViewUtil.LoadNormalView(CertificationActivity.this,url,imageView);
                    }

                    @Override
                    public void onFail(String msg) {
                        System.out.println(msg);
                    }


                });
    }

    /*
检查相机权限(拍照时要同时请求读写权限,否则无法压缩图片)
*/
    private void checkCameraPermission(int requestCameraCode) {
        //已有权限,调用相机
        if (EasyPermissions.hasPermissions(this, PERMISSIONS)) {
            goCamera(requestCameraCode);
        } else {
            //没有打开相关权限、申请权限
            EasyPermissions.requestPermissions(this, "需要获取您的相机使用权限", requestCameraCode,PERMISSIONS);
        }
    }
    /*
    检查相册权限
     */
    private void checkPhotoPermission(int requestPhotoCameraCode) {
        if (EasyPermissions.hasPermissions(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            goPhotoAlbum(requestPhotoCameraCode);
        } else {
            //没有打开相关权限、申请权限
            EasyPermissions.requestPermissions(this, "需要获取您的相册使用权限", requestPhotoCameraCode, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }
    }
    //权限申请回调(默认写法)
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    /*
    权限申请成功
     */
    @Override
    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {
        //请求相机权限成功
        if (requestCode == ONE_CAMARE_REQUEST_CODE||requestCode==TWO_CAMARE_REQUEST_CODE||requestCode==THREE_CAMARE_REQUEST_CODE) {
            goCamera(requestCode );
        }
        //请求相册权限成功
        else if (requestCode == ONE_PHOTO_REQUEST_CODE||requestCode==TWO_PHOTO_REQUEST_CODE||requestCode==THREE_CAMARE_REQUEST_CODE) {
            goPhotoAlbum(requestCode);
        }
    }

    /*
    权限申请失败
     */
    @Override
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {
        showtoast("请同意相关权限，否则功能无法使用");
    }


    //激活相机操作
    private void goCamera(int requestCameraCode) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            uri = FileProvider.getUriForFile(this, getApplication().getPackageName() + ".fileprovider", cameraSavePath);
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        } else {
            uri = Uri.fromFile(cameraSavePath);
        }
        intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        startActivityForResult(intent, requestCameraCode);
    }
    /*
    调用相册
     */
    private void goPhotoAlbum(int requestPhotoCameraCode) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, requestPhotoCameraCode);
    }
}
