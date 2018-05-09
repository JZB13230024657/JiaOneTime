package bwie.com.yikezhongtwo.presenter;

import bwie.com.yikezhongtwo.bean.DuanziBean;

/**
 * Created by 10419 on 2018/5/2.
 */

public interface DuoTuPre {
    void getDengluSuccess(DuanziBean duanziBean);
    void getDengluError(Throwable throwable);
}
