package bwie.com.yikezhongtwo.model;

import okhttp3.ResponseBody;

/**
 * Created by 10419 on 2018/4/11.
 */

public interface Duanzi_Mod {
    void getDuanziSuccess(ResponseBody responseBody);
    void getDuanziError(Throwable throwable);
}
