package bwie.com.yikezhongtwo.presenter;

import okhttp3.ResponseBody;

/**
 * Created by 10419 on 2018/4/11.
 */

public interface ShipinPre {
    void getShipinSuccess(ResponseBody responseBody);
    void getShipinError(Throwable throwable);
}
