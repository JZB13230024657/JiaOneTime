package bwie.com.yikezhongtwo.model;

import okhttp3.ResponseBody;

/**
 * Created by 10419 on 2018/4/9.
 */

public interface Remen_Model {
    void ModelOnSuccess(ResponseBody responseBody);
    void Error(Throwable e);
}
