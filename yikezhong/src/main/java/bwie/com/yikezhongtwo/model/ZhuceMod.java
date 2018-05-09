package bwie.com.yikezhongtwo.model;

import okhttp3.ResponseBody;

/**
 * Created by 10419 on 2018/4/13.
 */

public interface ZhuceMod {
    void getZhuceSuccess(ResponseBody responseBody);
    void getZhuceError(Throwable throwable);
}
