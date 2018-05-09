package bwie.com.yikezhongtwo.presenter;

import okhttp3.ResponseBody;

/**
 * Created by 10419 on 2018/4/11.
 */

public interface Duanzi_Pre {
    void getDuanziSuccess(ResponseBody responseBody);
    void getDuanziError(Throwable throwable);
}
