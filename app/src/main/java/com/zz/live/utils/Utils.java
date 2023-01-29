package com.zz.live.utils;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.os.Environment;
import android.os.Handler;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cambodia.zhanbang.rxhttp.net.utils.Base64Utils;
import com.cambodia.zhanbang.rxhttp.sp.SharedPreferenceHelperImpl;
import com.zz.live.MyApplication;
import com.zz.live.bean.LiveMessageModel;
import com.zz.live.bean.SystemParamsEntity;
import com.zz.live.bean.UserInfoEntity;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static android.content.Context.INPUT_METHOD_SERVICE;

public class Utils {
    // 两次点击按钮之间的点击间隔不能少于800毫秒
    private static final int MIN_CLICK_DELAY_TIME = 800;
    private static long lastClickTime;
    static SharedPreferenceHelperImpl mSharedPreferenceHelperImpl = new SharedPreferenceHelperImpl();


    /**
     * 设置背景亮度
     * @param activity activity实例
     * @param bgcolor 亮度值(0f-1f)值越小,背景越暗
     */
    public static void darkenBackground(Activity activity, Float bgcolor) {
        WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
        lp.alpha = bgcolor;
        if(bgcolor==1f){
            activity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        }else {

            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        }
        activity.getWindow().setAttributes(lp);
    }

    public static HashMap<String, Long> urlTime = new HashMap<>();

    public static boolean fileIsExists(String strFile) {
        try {
            File sdCardDir = Environment.getExternalStorageDirectory();//获取SDCard目录
            File file = getParent();
            File f = new File(file, strFile + ".txt");
            if (!f.exists()) {
                return false;
            }

        } catch (Exception e) {
            return false;
        }

        return true;
    }
    public static boolean deleteNormalFile(File file) {
        // 如果文件路径所对应的文件存在，并且是一个文件，则直接删除
        if (file.exists() && file.isFile()) {
            if (file.delete()) {
//                System.out.println("删除单个文件" + fileName + "成功！");
                return true;
            } else {
//                System.out.println("删除单个文件" + fileName + "失败！");
                return false;
            }
        } else {
//            System.out.println("删除单个文件失败：" + fileName + "不存在！");
            return false;
        }
    }
    //保存在sd卡
    public static boolean saveFileData(String obj, String fileName) {

        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            File file = getParent();
            FileOutputStream fos = null;
            try {
                File sdFile = new File(file, fileName + ".txt");
                fos = new FileOutputStream(sdFile);
                fos.write(obj.getBytes());
                return true;
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (fos != null) fos.close();
//                    System.out.println("savaData success");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }

    @NonNull
    private static File getParent() {

        File sdCardDir = Environment.getExternalStorageDirectory();//获取SDCard目录

        String absolutePath = sdCardDir.getAbsolutePath();
        File file = new File(absolutePath + "/color");
        if (!file.exists()) {
            boolean mkdirs = file.mkdirs();
            System.out.println("mkdirs=" + absolutePath + ">" + mkdirs);
        }
        return file;
    }


    /**
     * 删除单个文件
     *
     * @param fileName 要删除的文件的文件名
     * @return 单个文件删除成功返回true，否则返回false
     */
    public static boolean deleteFile(String fileName) {
        File parent = getParent();
        File file = new File(parent.getAbsolutePath() + "/" + fileName + ".txt");
        // 如果文件路径所对应的文件存在，并且是一个文件，则直接删除
        if (file.exists() && file.isFile()) {
            if (file.delete()) {
//                System.out.println("删除单个文件" + fileName + "成功！");
                return true;
            } else {
//                System.out.println("删除单个文件" + fileName + "失败！");
                return false;
            }
        } else {
//            System.out.println("删除单个文件失败：" + fileName + "不存在！");
            return false;
        }
    }


    public static String getFileData(String path) {
        System.out.println("getFileData");
        StringBuffer buffer = new StringBuffer();
        try {
            File file = getParent();
            FileInputStream fis = new FileInputStream(file.getAbsolutePath() + "/" + path + ".txt");
            InputStreamReader isr = new InputStreamReader(fis);//文件编码Unicode,UTF-8,ASCII,GB2312,Big5
            Reader in = new BufferedReader(isr);
            int ch;
            while ((ch = in.read()) > -1) {
                buffer.append((char) ch);
            }
            in.close();
        } catch (IOException e) {
            Log.e("yichang", path + "文件不存在!");

        }
        return buffer.toString();  //buffer.toString())就是读出的内容字符
    }






    public static String getFormatSize(double size) {

        double kiloByte = size / 1024;
        if (kiloByte < 1) {
//            return size + "Byte";
            return "0.00" + "M";//清除缓存时,清理完会有0.3Byte左右的缓存,这里直接显示0.00M
        }

        double megaByte = kiloByte / 1024;
        if (megaByte < 1) {
            BigDecimal result1 = new BigDecimal(Double.toString(kiloByte));
            return result1.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "KB";
        }

        double gigaByte = megaByte / 1024;
        if (gigaByte < 1) {
            BigDecimal result2 = new BigDecimal(Double.toString(megaByte));
            return result2.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "MB";
        }

        double teraBytes = gigaByte / 1024;
        if (teraBytes < 1) {
            BigDecimal result3 = new BigDecimal(Double.toString(gigaByte));
            return result3.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "GB";
        }
        BigDecimal result4 = new BigDecimal(teraBytes);

        return result4.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "TB";
    }

    @NonNull
    private static String getCacheKey(Map<String, Object> data) {
        String game = data.get("game") + "";
        String type_id = data.get("type_id") + "";
        return "cache_" + game + type_id;
    }

    private static long getFileLastModified(String path) {
        File file = getParent();
        File fis = new File(file.getAbsolutePath() + "/" + path + ".txt");
        if (fis == null) {
            return System.currentTimeMillis();
        }
        long timeMillis = fis.lastModified();

        return timeMillis;
    }

    public static String getFirstImgurl(Context context) {
        String firstImageUrl = SharePreferencesUtil.getString(context, CommonStr.DOMIAN_URL, "");
        if(!firstImageUrl.endsWith("/")){
            firstImageUrl = firstImageUrl+"/";
        }
        return firstImageUrl;
    }
    public static String getCpFirstImgurl() {
        String firstImageUrl = SharePreferencesUtil.getString(MyApplication.getInstance(), CommonStr.CP_DOMIAN_URL, "");
        if(!firstImageUrl.endsWith("/")){
            firstImageUrl = firstImageUrl+"/";
        }
        return firstImageUrl;
    }
    public static void requestPermissions(Activity activity) {
        if (activity != null) {
            ActivityCompat.requestPermissions(activity, new String[]{android
                    .Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        }
    }

    /*
  保存文件时间戳的map(key: url  value: 时间戳 )
 */
    public static void setUrlTime(Context context, String url) {
        String key = "cache_" + url;
        long currentTimeMillis = System.currentTimeMillis();
        Utils.urlTime.put(key, currentTimeMillis);
        SharePreferencesUtil.putLong(context, key, currentTimeMillis);
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }


    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    //判断整数的位数
    public static int LengthNum(int num) {
        int count = 0; //计数
        while (num >= 1) {
            num = num / 10;
            count++;
        }
        return count;
    }

    //获取动画实例  旋转   x角度
    public static Animation getAnimation(int x) {
        Animation rotateAnimation = new RotateAnimation(0f, x, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        rotateAnimation.setFillAfter(true);
        rotateAnimation.setDuration(0);
        rotateAnimation.setRepeatCount(0);
        rotateAnimation.setInterpolator(new LinearInterpolator());
        rotateAnimation.setDetachWallpaper(true);

        return rotateAnimation;
    }

    //方法一：用JAVA自带的函数
    public static boolean isNumeric(String str) {
        for (int i = str.length(); --i >= 0; ) {
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    /***
     *
     * @param context
     * @param content
     */
    public static void initGameClassVersion(Context context, String content) {
        JSONObject jsonObject = JSONObject.parseObject(content);
        Long navigateVersion = jsonObject.getLong("navigateVersion");//后台版本号
        JSONArray gameClassVersions = jsonObject.getJSONArray("gameClassVersions");
        for (int i = 0; i < gameClassVersions.size(); i++) {
            JSONObject gameClass = gameClassVersions.getJSONObject(i);
            int game = gameClass.getIntValue("game");
            int type_id = gameClass.getIntValue("type_id");
            long version = gameClass.getLongValue("version");
            String key = "cache_" + game + type_id;
            //更新内存的彩种版本号
            Utils.urlTime.put(key, version);
            System.out.println("initGameClassVersion>" + key + ":" + version);
        }
        // 更新内存的导航栏版本号
        Utils.urlTime.put("cache_00", navigateVersion);
    }

    public static HashMap<String, Object> getNavigateListMap(int isHot) {
        HashMap<String, Object> datalottery = new HashMap<>();
        datalottery.put("isHot", "");
        datalottery.put("game", 0);//版本更新用
        datalottery.put("type_id", 0);//版本更新用
        datalottery.put("user_id", 0);//版本更新用
        return datalottery;
    }


    public static View getContentView(Activity context) {
        return ((ViewGroup) context.findViewById(android.R.id.content)).getChildAt(0);
    }
    public static String getImageUrl(String url, String domainImg){
        if(StringMyUtil.isNotEmpty(url)&&!url.startsWith("http")){
            url=domainImg+url;
        }
        return url;
    }


    public static String ImageUrlCheck(String img_url){
        if (StringMyUtil.isNotEmpty(img_url)&&!img_url.startsWith("http")&&!img_url.startsWith("https")){
            img_url = mSharedPreferenceHelperImpl.getImageDomin()+img_url;
        }
        return img_url;
    }
    public static String CPImageUrlCheck(Context context, String img_url){
        if (StringMyUtil.isNotEmpty(img_url)&&!img_url.startsWith("http")&&!img_url.startsWith("https")){
            String firstImageUrl = SharePreferencesUtil.getString(context, "FirstImageUrl", "");
            img_url = firstImageUrl+img_url;
        }
        return img_url;
    }


    public static void hideSoftKeyBoard(Activity activity) {
        if(activity!=null&&!activity.isFinishing()){
            InputMethodManager imm = (InputMethodManager) activity.getSystemService(INPUT_METHOD_SERVICE);
            if (imm.isActive() && activity.getCurrentFocus() != null) {
                if (activity.getCurrentFocus().getWindowToken() != null) {
                    imm.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                }
            }
        }

    }

    /**
     * 收回软键盘 (获取activity获得焦点的view时,可能出现焦点view在freagment中 导致activity.getCurrentFocus()为空, 所以在fragment中直接传入需要收回的editText控件)
     * @param activity
     * @param editText
     */
    public static void hideSoftKeyBoard(Activity activity , EditText editText) {
        if(activity!=null&&!activity.isFinishing()){
            InputMethodManager imm = (InputMethodManager) activity.getSystemService(INPUT_METHOD_SERVICE);
            if (imm.isActive()) {
                if (editText.getWindowToken() != null) {
                    imm.hideSoftInputFromWindow(editText.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                }
            }
        }
    }
    public static void showSoftInputFromWindow(Activity activity, EditText editText){
        if(null!=activity&&!activity.isFinishing()){
/*            editText.setFocusable(true);
            editText.setFocusableInTouchMode(true);
            editText.requestFocus();
            activity. getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);*/
            editText.requestFocus();
            InputMethodManager imm = (InputMethodManager)activity. getSystemService(INPUT_METHOD_SERVICE);
            imm.showSoftInput(editText, InputMethodManager.SHOW_IMPLICIT);

        }
    }
/*    public static int[] initScreenInfo(Activity activity) {
        int[] ints = new int[2];
        Display display = activity.getWindowManager().getDefaultDisplay();
        DisplayMetrics dm = new DisplayMetrics();
        Class c;
        try {
            c = Class.forName("android.view.Display");
            Method method = c.getMethod("getRealMetrics", DisplayMetrics.class);
            method.invoke(display, dm);
            int HEIGHT = dm.heightPixels;
            int WIDTH = dm.widthPixels;
            ints[0]=WIDTH;
            ints[1]=HEIGHT;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return ints;
    }*/

    public static int intgetWinndowWidth(Activity activity) {
        Display display = activity.getWindowManager().getDefaultDisplay();
        DisplayMetrics dm = new DisplayMetrics();
        Class c;
        int width=0;
        try {
            c = Class.forName("android.view.Display");
            Method method = c.getMethod("getRealMetrics", DisplayMetrics.class);
            method.invoke(display, dm);
            int WIDTH = dm.widthPixels;
            width=WIDTH;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return width;
    }
    public static int intgetWinndowHeight(Activity activity) {
        Display display = activity.getWindowManager().getDefaultDisplay();
        DisplayMetrics dm = new DisplayMetrics();
        Class c;
        int height=0;
        try {
            c = Class.forName("android.view.Display");
            Method method = c.getMethod("getRealMetrics", DisplayMetrics.class);
            method.invoke(display, dm);
            int HEIGHT = dm.heightPixels;
            height=HEIGHT;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return height;
    }



    public static int dp2px(float dpValue) {
        return (int)(0.5F + dpValue * Resources.getSystem().getDisplayMetrics().density);
    }

    /**
     * 改变bitmap的尺寸, 图片不失真
     * @param bitmap
     * @param newWidth 改变后的宽度(单位为dp)
     * @param newHeight
     * @return
     */
    public static Bitmap getNewBitmap(Bitmap bitmap, int newWidth , int newHeight){
        // 获得图片的宽高.
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        // 计算缩放比例. (要先转为px)
        float scaleWidth = ((float) dp2px(newWidth)) / width;
        float scaleHeight = ((float) dp2px(newHeight)) / height;
        // 取得想要缩放的matrix参数.
        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleHeight);
        // 得到新的图片.
        Bitmap newBitmap = Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, true);
        return newBitmap;
    }
    public static String checkImageUrl(String url){
        if(StringMyUtil.isEmptyString(url)||url.startsWith("http")){
            return url;
        }
        url=getFirstImgurl(MyApplication.getInstance())+url;
        return url;
    }
    public static String cpCheckImageUrl(String url){
        if(StringMyUtil.isEmptyString(url)||url.startsWith("http")){
            return url;
        }
        url=getCpFirstImgurl()+url;
        return url;
    }
    public static boolean checkAccount(String input) {
        if ((input + "").length() > 11) {
            return false;
        }
        Pattern pattern = Pattern.compile("[a-zA-Z]|([0-9]{1,}|\\_){6,11}");
//        Pattern pattern = Pattern.compile("^([a-zA-Z])+[0-9A-Za-z]{6,11}$");
        Matcher m = pattern.matcher(input);
        if (!m.find()) { //匹配不到,說明輸入的不符合條件
            return false;
        }
        return true;

    }
    public static boolean checkPsw(String input) {
        if ((input + "").length() > 20) {
            return false;
        }
        Pattern pattern = Pattern.compile("^([a-zA-Z])|[0-9]{1,}//_{8,20}");
        Matcher m = pattern.matcher(input);
        if (!m.find()) { //匹配不到,說明輸入的不符合條件
            return false;
        }
        return true;

    }
    public static String getTitle(){
        String userInfo = SharePreferencesUtil.getString(MyApplication.getInstance(), CommonStr.USER_INFO, "");
        String image = JSONObject.parseObject(userInfo, UserInfoEntity.class).getData().getImage();
        return image;
    }
    public static UserInfoEntity.DataBean getUserInfoBean(){
        String userInfo = SharePreferencesUtil.getString(MyApplication.getInstance(), CommonStr.USER_INFO, "");
        if(StringMyUtil.isEmptyString(userInfo)){
            return null;
        }
        UserInfoEntity.DataBean data = JSONObject.parseObject(userInfo, UserInfoEntity.class).getData();
        return data;
    }
    public static void setUserInfoBean(UserInfoEntity.DataBean dataBean){
        try {
            String userInfo = SharePreferencesUtil.getString(MyApplication.getInstance(), CommonStr.USER_INFO, "");
            UserInfoEntity userInfoEntity = JSONObject.parseObject(userInfo, UserInfoEntity.class);
            userInfoEntity.setData(dataBean);
            SharePreferencesUtil.putString(MyApplication.getInstance(),CommonStr.USER_INFO,JSONObject.toJSONString(userInfoEntity));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    /**
     * @param messageModel
     * @param userInfoJson
     */
    public static void initUserInfo(LiveMessageModel messageModel, String userInfoJson) {
        try {
            if (StringMyUtil.isNotEmpty()) {
                JSONObject jsonObject = JSONObject.parseObject(userInfoJson);
                String userId = jsonObject.getString("userId");
                String name = jsonObject.getString("name");
                String level = jsonObject.getString("level");
                String portrait = jsonObject.getString("portrait");
                String managerType = jsonObject.getString("managerType");
                messageModel.setUserName(name);
                messageModel.setLevel(level);
                messageModel.setPortrait(portrait);
                messageModel.setManagerType(managerType);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static boolean isFastClick() {
        boolean flag = false;
        long curClickTime = System.currentTimeMillis();
        if ((curClickTime - lastClickTime) <= MIN_CLICK_DELAY_TIME) {
            flag = true;
        }
        lastClickTime = curClickTime;
        return flag;
    }
    public static boolean isNotFastClick() {
        return !isFastClick();
    }
    public static String initOldCpData(String url,String result) {
        String resultData = "";
        if (!StringMyUtil.isEmptyString(result)) {
            if (result.contains("500 Internal Server Error")) {
                resultData = "";
            } else {
                try {
                    JSONObject json = JSONObject.parseObject(result);
                    JSONObject jsonHead = JSONObject.parseObject(json.getString("head"));
                    String dataJson = json.getString("data");
                    if ("00".equals(jsonHead.getString("code"))) {
                        if (dataJson != null) {
                            resultData = Base64Utils.decodeBase64String(dataJson);//响应体需要解码
                            String timestamp = jsonHead.getString("timestamp");//服务器时间
                            //               Utils.saveFileData(timestamp + "", "time");//保存时间差
                            Context context = MyApplication.getInstance();
                            Long oldTime = SharePreferencesUtil.getLong(context, "shijiancha", 0l);
                            long nowTime = System.currentTimeMillis() - Long.parseLong(timestamp);
                            if (oldTime == 0) {//第一次存入(后面有时间差为0的一并忽略,存新值)
                                SharePreferencesUtil.putLong(context, "shijiancha", nowTime);
                            } else if (Math.abs(oldTime) > Math.abs(nowTime)) {
                                SharePreferencesUtil.putLong(context, "shijiancha", nowTime);
                            } else {
                                SharePreferencesUtil.putLong(context, "shijiancha", oldTime);
                            }
                            System.out.println("abs =  " + SharePreferencesUtil.getLong(MyApplication.getInstance(), "shijiancha", 0l));
                        }
                    } else {
                        /* resultData = "";*/
                        resultData = Base64Utils.decodeBase64String(dataJson);//响应体需要解码
                    }//21740
                }catch (Exception e){
                    e.printStackTrace();
                    Log.e("eException",e.getMessage());
                }

            }
        } else {
            resultData = "";
        }
        return resultData;
    }
    public static SystemParamsEntity.DataBean getSystemParam(){
        String string = SharePreferencesUtil.getString(MyApplication.getInstance(), CommonStr.SYSTEM_PARAM, "");
        SystemParamsEntity systemParamsEntity = JSONObject.parseObject(string, SystemParamsEntity.class);
        if(systemParamsEntity==null){
            return null;
        }
        return systemParamsEntity.getData();
    }



    public static void showKeyboard(View view) {
        InputMethodManager imm = (InputMethodManager) view.getContext()
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    view.requestFocus();
                    imm.showSoftInput(view, 0);
                }
            },200);

        }
    }

    public static String getNetFileSizeDescription(long size) {
        StringBuffer bytes = new StringBuffer();
        DecimalFormat format = new DecimalFormat("###.0");
        if (size >= 1024 * 1024 * 1024) {
            double i = (size / (1024.0 * 1024.0 * 1024.0));
            bytes.append(format.format(i)).append("GB/s");
        }
        else if (size >= 1024 * 1024) {
            double i = (size / (1024.0 * 1024.0));
            bytes.append(format.format(i)).append("MB/s");
        }
        else if (size >= 1024) {
            double i = (size / (1024.0));
            bytes.append(format.format(i)).append("KB/s");
        }
        else if (size < 1024) {
            if (size <= 0) {
                bytes.append("0B/s");
            }
            else {
                bytes.append((int) size).append("B/s");
            }
        }
        return bytes.toString();
    }

    public static boolean isJsonObject(String content) {
        if(TextUtils.isEmpty(content)){
            return false;
        }
        boolean isJsonObject = true;
        try {
            JSONObject.parseObject(content);
        } catch (Exception e) {
            isJsonObject = false;
        }

        if(!isJsonObject){ //不是json格式
            return false;
        }
        return true;
    }
    public static boolean isInt(String content) {
        if(StringMyUtil.isEmptyString(content)){
            return false;
        }
        boolean isInt = true;
        try {
         Integer.parseInt(content);
        } catch (Exception e) {
            isInt = false;
        }
        if(!isInt){
            return false;
        }
        return true;
    }
    public static boolean isNotInt(String content) {
        return !isInt(content);
    }
    public static boolean isLong(String content) {
        if(StringMyUtil.isEmptyString(content)){
            return false;
        }
        boolean isLong = true;
        try {
            Long.parseLong(content);
        } catch (Exception e) {
            isLong = false;
        }
        if(!isLong){
            return false;
        }
        return true;
    }
    public static boolean isNotLong(String content) {
            return !isLong(content);
    }


    public static boolean isDouble(String content) {
        if(StringMyUtil.isEmptyString(content)){
            return false;
        }
        boolean isDouble = true;
        try {
            Double.parseDouble(content);
        } catch (Exception e) {
            isDouble = false;
        }
        if(!isDouble){
            return false;
        }
        return true;
    }

    public static boolean isNotDouble(String content) {
       return  !isDouble(content);
    }
}
