package bwie.com.yikezhongtwo.model;

import bwie.com.yikezhongtwo.bean.DuanziBean;

/**
 * Created by 10419 on 2018/5/2.
 */

public interface DuotuMod {
    void getDengluSuccess(DuanziBean duanziBean);
    void getDengluError(Throwable throwable);
}
