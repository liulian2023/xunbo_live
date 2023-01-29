package com.cambodia.zhanbang.rxhttp.net.interceptor;
import com.cambodia.zhanbang.rxhttp.net.utils.CommonModule;
import com.cambodia.zhanbang.rxhttp.net.utils.SystemUtil;
import com.cambodia.zhanbang.rxhttp.sp.SharedPreferenceHelperImpl;
import java.io.IOException;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.internal.annotations.EverythingIsNonNull;
public class HttpHeaderInterceptor implements Interceptor {

    SharedPreferenceHelperImpl mSharedPreferenceHelperImpl = new SharedPreferenceHelperImpl();
    boolean loginStatus;
    String token;
    String id;
    @Override
    @EverythingIsNonNull
    public Response intercept(Chain chain) throws IOException {
        token=  mSharedPreferenceHelperImpl.getToken();
        loginStatus = mSharedPreferenceHelperImpl.getLoginStatus();
        Request.Builder builder = chain.request().newBuilder();
        Request request=null;
        if(chain.request().url().toString().contains("auth/oauth/token/test")){
            request = builder.build();
        }else {
            builder.addHeader("deviceNumber", SystemUtil.getUniqueId(CommonModule.getAppContext()));
            if(token.equals("")||token==null){
                builder.addHeader("id", "");
                request = builder.addHeader("Authorization", "Bearer bW9iaWxlOm1vYmlsZQ==").build();
            }else {
                builder.addHeader("id", mSharedPreferenceHelperImpl.getUserId()+"");
                request = builder.addHeader("Authorization", "Bearer "+token).build();
            }
        }
        Response response = chain.proceed(request);
        return response;
    }
}
