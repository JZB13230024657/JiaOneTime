package bwie.com.yikezhongtwo.presenter;

import okhttp3.ResponseBody;

/**
 * Created by 10419 on 2018/4/10.
 */

public interface RV_Presenter {
    void TiaomuDataOnSuccess(ResponseBody responseBody);
    void TiaomuError(Throwable throwable);
}
