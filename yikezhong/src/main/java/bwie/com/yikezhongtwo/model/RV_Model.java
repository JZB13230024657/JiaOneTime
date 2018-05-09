package bwie.com.yikezhongtwo.model;

import okhttp3.ResponseBody;

/**
 * Created by 10419 on 2018/4/10.
 */

public interface RV_Model {
    void TiaomuDataOnSuccess(ResponseBody responseBody);
    void TiaomuError(Throwable throwable);
}
