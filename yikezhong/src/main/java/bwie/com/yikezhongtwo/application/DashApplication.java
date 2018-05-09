package bwie.com.yikezhongtwo.application;

import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.os.Process;
import android.support.v7.app.AppCompatDelegate;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.mob.MobSDK;
import com.squareup.leakcanary.LeakCanary;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;


/**
 * Created by Dash on 2018/1/23.
 */
public class DashApplication extends Application{

    private static int myTid;
    private static Handler handler;
    private static Context context;

    {

        PlatformConfig.setWeixin("wx967daebe835fbeac","5bb696d9ccd75a38c8a0bfe0675559b3");
        PlatformConfig.setQQZone("100424468", "c7394704798a158208a74ab60104f0ba");
        PlatformConfig.setSinaWeibo("3921700954", "04b48b094faeb16683c32669824ebdad","http://sns.whalecloud.com");
    }
    @Override
    public void onCreate() {
        super.onCreate();
        UMShareAPI.get(this);
        myTid = Process.myTid();
        handler = new Handler();
        context = getApplicationContext();

        MobSDK.init(this);
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        LeakCanary.install(this);
        /*

        Error:Execution failed for task ':yikezhong:processDebugManifest'.
> Manifest merger failed : Attribute application@name value=(bwie.com.yikezhongtwo.application.DashApplication) from AndroidManifest.xml:24:9-52
  	is also present at [cn.smssdk:ShortMessageSDKGUI:3.1.1] AndroidManifest.xml:3:16-53 value=(com.mob.MobApplication).
  	Suggestion: add 'tools:replace="android:name"' to <application> element at AndroidManifest.xml:23:5-70:19 to override.

         */

    }

    //获取主线程的id
    public static int getMainThreadId() {

        return myTid;
    }

    //获取handler
    public static Handler getAppHanler() {
        return handler;
    }

    public static Context getAppContext() {
        return context;
    }
}
