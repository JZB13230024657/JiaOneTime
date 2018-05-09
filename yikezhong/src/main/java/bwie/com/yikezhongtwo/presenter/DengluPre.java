package bwie.com.yikezhongtwo.presenter;

import okhttp3.ResponseBody;

/**
 * Created by 10419 on 2018/4/13.
 */

public interface DengluPre {
    void getDengluSuccess(ResponseBody responseBody);
    void getDengluError(Throwable throwable);
}
