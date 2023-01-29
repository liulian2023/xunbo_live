package com.zz.live.ui.activity.message_activity;


import android.app.Activity;
import android.content.Intent;

import androidx.lifecycle.LifecycleOwner;

import com.cambodia.zhanbang.rxhttp.net.common.BaseStringObserver;
import com.cambodia.zhanbang.rxhttp.net.utils.RxTransformerUtils;
import com.uber.autodispose.AutoDispose;
import com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider;
import com.zz.live.base.BaseWebActivity;
import com.zz.live.net.api.HttpApiImpl;

import okhttp3.Headers;
import okhttp3.ResponseBody;

public class MessageDetailsActivity extends BaseWebActivity {
    public static int SYSTEM_MESSAGE_REQUEST_CODE =1;
    public static int PRIVATE_MESSAGE_REQUEST_CODE =2;
    @Override
    public String getToolBarTitle() {
        return  getIntent().getStringExtra("title");
    }

    @Override
    public String getUrl() {
        return  getIntent().getStringExtra("url");
    }

    @Override
    public void doSomething() {
        super.doSomething();
        String id = getIntent().getStringExtra("id");
        if(getIntent().getBooleanExtra("isSystemMessage",true)){
            /*
            系统消息跳转而来
             */
            HttpApiImpl.getInstance()
                    .modifySystemMessageReadStatus(id)
                    .compose(RxTransformerUtils.io_main())
                    .as(AutoDispose.autoDisposable(AndroidLifecycleScopeProvider.from((LifecycleOwner) this)))
                    .subscribe(new BaseStringObserver<ResponseBody>() {
                        @Override
                        public void onSuccess(String result) {
                            Intent data = new Intent();
                            data.putExtra("id",id);
                            setResult(RESULT_OK,data);
                        }

                        @Override
                        public void onFail(String msg) {

                        }

                    });
        }else {
            /*
            私信消息跳转而来
             */
            HttpApiImpl.getInstance()
                    .modifyPrivateMessageReadStatus(id)
                    .compose(RxTransformerUtils.io_main())
                    .as(AutoDispose.autoDisposable(AndroidLifecycleScopeProvider.from((LifecycleOwner) this)))
                    .subscribe(new BaseStringObserver<ResponseBody>() {
                        @Override
                        public void onSuccess(String result) {
                            Intent data = new Intent();
                            data.putExtra("id",id);
                            setResult(RESULT_OK,data);
                        }

                        @Override
                        public void onFail(String msg) {

                        }

                    });
        }

    }

    /**
     *
     * @param activity
     * @param url 消息内容
     * @param title toolBat标题
     * @param id 消息id
     * @param isSystemMessage 是否是系统消息跳转而来
     * @param requestCode 跳转请求码
     */
    public static void startAty(Activity activity, String url, String title,String id,boolean isSystemMessage,int requestCode){
        Intent intent = new Intent(activity, MessageDetailsActivity.class);
        intent.putExtra("url",url);
        intent.putExtra("title",title);
        intent.putExtra("id",id);
        intent.putExtra("isSystemMessage",isSystemMessage);
        activity.startActivityForResult(intent, requestCode);

    }
}
