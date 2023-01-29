# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile
#代码混淆压缩比，在0-7之间，默认为5，一般不做修改
-optimizationpasses 5
#混合时不使用大小写混合，混合后的类名为小写
-dontusemixedcaseclassnames
#不忽略非公共库的类
-dontskipnonpubliclibraryclasses
-dontskipnonpubliclibraryclassmembers
#使项目混淆后产生映射文件，包含有类名->混淆后的类名的映射关系
-verbose
#不做预校验，Android不需要preverify,去掉这一步能够加快混淆速度
-dontpreverify
#保留Annotation不混淆
-keepattributes *Annotation*
#不混淆泛型
-keepattributes Signature
#抛出异常时保留代码行号
-keepattributes SourceFile,LineNumberTable
#指定混淆采用的算法，后面的参数是一个过滤器
#这个过滤器是谷歌推荐的算法，一般不做更改
#-optimizations !code/simplification/cast,!field/*,!class/merging/*

-keep class com.tencent.** { *; }
-keepattributes *JavascriptInterface*
#-keepclassmembers class * extends android.webkit.webViewClient {
#    public void *(android.webkit.WebView, java.lang.String, android.graphics.Bitmap);
#    public boolean *(android.webkit.WebView, java.lang.String);
#}
#-keepclassmembers class * extends android.webkit.webViewClient {
#    public void *(android.webkit.webView, java.lang.String);
#    public void *(android.webkit.webView, android.webkit.SslErrorHandler, android.net.http.SslError);
#}
#-keepclassmembers class * extends android.webkit.WebChromeClient{
#   public void *(android.webkit.webView, java.lang.String);
#}
#-keep class com.show.you.WebVerfiedActivity{ *; }
#-keep class android.webkit.**{ *; }
#友盟---------开始
-keep class com.umeng.** {*;}
-keepclassmembers class * {
   public <init> (org.json.JSONObject);
}
-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}

-keep public class [com.xunbo.liveAnchor].R$*{
public static final int *;
}
#友盟---------结束
