package bwie.com.yikezhongtwo.presenter;

import okhttp3.ResponseBody;

/**
 * Created by 10419 on 2018/4/9.
 */

public interface Remen_Presenter {
    void OnSuccess(ResponseBody responseBody);
    void Error(Throwable e);
}
